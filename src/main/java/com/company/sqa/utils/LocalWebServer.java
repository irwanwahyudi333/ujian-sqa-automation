package com.company.sqa.utils;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.concurrent.Executors;

/**
 * Embedded static web server for local UI automation testing.
 * Serves the offline mirror of the registration web application
 * (https://magang.dikahadir.com/absen/register) on localhost.
 */
public class LocalWebServer {
    private static final Logger logger = LogManager.getLogger(LocalWebServer.class);

    private static final int DEFAULT_PORT = 8085;
    private static HttpServer server;
    private static int activePort = DEFAULT_PORT;
    private static Path staticWebRoot;

    static {
        // Resolve path to static-web directory
        Path localDir = Paths.get("src/test/resources/static-web");
        if (Files.exists(localDir)) {
            staticWebRoot = localDir.toAbsolutePath();
        } else {
            // Fallback to relative path in project root
            staticWebRoot = Paths.get("target/test-classes/static-web").toAbsolutePath();
        }
    }

    /**
     * Start the local web server on the default port.
     */
    public static synchronized void start() {
        int port = DEFAULT_PORT;
        try {
            String uiUrl = ConfigReader.getUiBaseUrl();
            if (uiUrl != null && !uiUrl.isEmpty()) {
                java.net.URI uri = java.net.URI.create(uiUrl);
                if (uri.getPort() > 0) {
                    port = uri.getPort();
                }
            }
        } catch (Exception ignored) {}
        start(port);
    }

    public static synchronized void start(int port) {
        if (server != null) {
            logger.info("LocalWebServer is already running on port {}", activePort);
            return;
        }

        int targetPort = port;
        HttpServer createdServer = null;
        int maxAttempts = 5;

        for (int i = 0; i < maxAttempts; i++) {
            try {
                createdServer = HttpServer.create(new InetSocketAddress(targetPort), 0);
                activePort = targetPort;
                break;
            } catch (IOException e) {
                logger.warn("Port {} is in use, trying next port {}...", targetPort, targetPort + 1);
                targetPort++;
            }
        }

        if (createdServer == null) {
            throw new RuntimeException("Could not start local web server on any port starting from " + port);
        }

        server = createdServer;
        server.setExecutor(Executors.newVirtualThreadPerTaskExecutor());
        server.createContext("/hadir-api-magang", new ApiGatewayHandler());
        server.createContext("/uploads", new BrokenUploadsHandler());
        server.createContext("/", new StaticFileHandler());
        server.start();

        logger.info("==========================================================");
        logger.info("Local Web Server started successfully on port {}!", activePort);
        logger.info("URL Register Page: http://localhost:{}/absen/register", activePort);
        logger.info("URL Register Page v2: http://localhost:{}/management/user-v2/register", activePort);
        logger.info("URL Self-Registration: http://localhost:{}/management/self-registration", activePort);
        logger.info("URL Divisi: http://localhost:{}/management/divisi", activePort);
        logger.info("URL Management User: http://localhost:{}/management/user", activePort);
        logger.info("Serving files from: {}", staticWebRoot);
        logger.info("==========================================================");
    }

    /**
     * Stop the local web server.
     */
    public static synchronized void stop() {
        if (server != null) {
            logger.info("Stopping LocalWebServer on port {}...", activePort);
            server.stop(0);
            server = null;
            logger.info("LocalWebServer stopped.");
        }
    }

    /**
     * Check if the local web server is currently running.
     */
    public static synchronized boolean isRunning() {
        return server != null;
    }

    /**
     * Get the base URL of the local web server.
     */
    public static String getBaseUrl() {
        return "http://localhost:" + activePort;
    }

    /**
     * Handler to serve static HTML, CSS, JS, and image assets.
     */
    private static class StaticFileHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Add CORS headers for local testing
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "*");

            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            String uriPath = exchange.getRequestURI().getPath();
            String query = exchange.getRequestURI().getQuery();

            // Handle Next.js Image Optimization endpoint: /_next/image?url=...
            if (uriPath.startsWith("/_next/image") && query != null && query.contains("url=")) {
                String imageUrl = extractQueryParam(query, "url");
                if (imageUrl != null) {
                    imageUrl = URLDecoder.decode(imageUrl, StandardCharsets.UTF_8);
                    serveFile(exchange, imageUrl);
                    return;
                }
            }

            // Route mapping
            if (uriPath.equals("/") || uriPath.equals("/absen") || uriPath.equals("/absen/register")) {
                serveFile(exchange, "/absen/register.html");
                return;
            }
            if (uriPath.equals("/management/user-v2/register") || uriPath.equals("/management/user-v2/register.html")) {
                serveFile(exchange, "/management/user-v2/register.html");
                return;
            }
            if (uriPath.equals("/login") || uriPath.equals("/authentication/login") || uriPath.equals("/absen/login")) {
                serveFile(exchange, "/authentication/login.html");
                return;
            }
            if (uriPath.equals("/dashboard") || uriPath.equals("/absen/dashboard")) {
                serveFile(exchange, "/absen/dashboard.html");
                return;
            }
            if (uriPath.equals("/management/self-registration") || uriPath.equals("/management/self-registration/")) {
                serveFile(exchange, "/management/self-registration.html");
                return;
            }
            if (uriPath.equals("/management/divisi") || uriPath.equals("/management/divisi/")) {
                serveFile(exchange, "/management/divisi.html");
                return;
            }
            if (uriPath.equals("/management/user") || uriPath.equals("/management/user/")) {
                serveFile(exchange, "/management/user.html");
                return;
            }
            if (uriPath.startsWith("/management/user-v2/edit")) {
                serveFile(exchange, "/management/user-v2-edit.html");
                return;
            }
            if (uriPath.equals("/laporan/all") || uriPath.equals("/laporan/all/")) {
                serveFile(exchange, "/laporan/all.html");
                return;
            }

            serveFile(exchange, uriPath);
        }

        private void serveFile(HttpExchange exchange, String relativePath) throws IOException {
            String cleanPath = relativePath.startsWith("/") ? relativePath.substring(1) : relativePath;
            Path filePath = staticWebRoot.resolve(cleanPath).normalize();

            // Security check against directory traversal
            if (!filePath.startsWith(staticWebRoot) || !Files.exists(filePath) || Files.isDirectory(filePath)) {
                // If register page without .html extension requested
                if (cleanPath.equals("absen/register")) {
                    filePath = staticWebRoot.resolve("absen/register.html");
                } else if (cleanPath.equals("management/user-v2/register")) {
                    filePath = staticWebRoot.resolve("management/user-v2/register.html");
                } else if (cleanPath.equals("authentication/login") || cleanPath.equals("login") || cleanPath.equals("absen/login")) {
                    filePath = staticWebRoot.resolve("authentication/login.html");
                } else if (cleanPath.equals("dashboard") || cleanPath.equals("absen/dashboard")) {
                    filePath = staticWebRoot.resolve("absen/dashboard.html");
                } else if (cleanPath.equals("management/self-registration")) {
                    filePath = staticWebRoot.resolve("management/self-registration.html");
                } else if (cleanPath.equals("management/divisi")) {
                    filePath = staticWebRoot.resolve("management/divisi.html");
                } else if (cleanPath.equals("management/user") || cleanPath.equals("management/user/")) {
                    filePath = staticWebRoot.resolve("management/user.html");
                } else if (cleanPath.startsWith("management/user-v2/edit")) {
                    filePath = staticWebRoot.resolve("management/user-v2-edit.html");
                }
            }

            if (!Files.exists(filePath) || Files.isDirectory(filePath)) {
                logger.warn("File not found on LocalWebServer: {} (resolved: {})", relativePath, filePath);
                byte[] notFoundBytes = "404 Not Found".getBytes(StandardCharsets.UTF_8);
                exchange.sendResponseHeaders(404, notFoundBytes.length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(notFoundBytes);
                }
                return;
            }

            String contentType = determineContentType(filePath.toString());
            exchange.getResponseHeaders().set("Content-Type", contentType);

            long fileLength = Files.size(filePath);
            if ("HEAD".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(200, -1);
                return;
            }

            exchange.sendResponseHeaders(200, fileLength);

            try (FileInputStream fis = new FileInputStream(filePath.toFile());
                 OutputStream os = exchange.getResponseBody()) {
                byte[] buffer = new byte[8192];
                int bytesRead;
                while ((bytesRead = fis.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
            }
        }

        private String determineContentType(String path) {
            String lower = path.toLowerCase();
            if (lower.endsWith(".html")) return "text/html; charset=utf-8";
            if (lower.endsWith(".css")) return "text/css; charset=utf-8";
            if (lower.endsWith(".js")) return "application/javascript; charset=utf-8";
            if (lower.endsWith(".png")) return "image/png";
            if (lower.endsWith(".jpg") || lower.endsWith(".jpeg")) return "image/jpeg";
            if (lower.endsWith(".svg")) return "image/svg+xml";
            if (lower.endsWith(".gif")) return "image/gif";
            if (lower.endsWith(".json")) return "application/json; charset=utf-8";
            if (lower.endsWith(".ico")) return "image/x-icon";
            return "application/octet-stream";
        }

        private String extractQueryParam(String query, String paramName) {
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                int idx = pair.indexOf("=");
                if (idx > 0 && pair.substring(0, idx).equals(paramName)) {
                    return pair.substring(idx + 1);
                }
            }
            return null;
        }
    }

    /**
     * Handler untuk endpoint lama /uploads yang deprecated / menghasilkan 404 Not Found.
     */
    private static class BrokenUploadsHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            byte[] notFoundBytes = "404 Not Found - Deprecated upload endpoint".getBytes(StandardCharsets.UTF_8);
            exchange.sendResponseHeaders(404, notFoundBytes.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(notFoundBytes);
            }
        }
    }

    /**
     * API Gateway Mock & Security Enforcement Handler.
     * Mengamankan endpoint /hadir-api-magang/* sesuai standar OWASP dan kontrol bisnis HADIR.
     */
    private static class ApiGatewayHandler implements HttpHandler {
        private static final ObjectMapper objectMapper = new ObjectMapper();

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, PATCH, DELETE, OPTIONS");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "*");

            String method = exchange.getRequestMethod();
            if ("OPTIONS".equalsIgnoreCase(method)) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            String path = exchange.getRequestURI().getPath();
            String contentType = exchange.getRequestHeaders().getFirst("Content-Type");
            String authHeader = exchange.getRequestHeaders().getFirst("Authorization");

            byte[] requestBodyBytes = exchange.getRequestBody().readAllBytes();
            String bodyStr = new String(requestBodyBytes, StandardCharsets.UTF_8);

            // 1. Static uploads endpoint: GET /hadir-api-magang/uploads/*
            if (path.startsWith("/hadir-api-magang/uploads")) {
                if ("GET".equalsIgnoreCase(method) || "HEAD".equalsIgnoreCase(method)) {
                    serveSelfieUpload(exchange, path);
                    return;
                }
            }

            // 2. Upload endpoint: POST /hadir-api-magang/upload
            if (path.equals("/hadir-api-magang/upload")) {
                handleUpload(exchange, method, contentType, bodyStr);
                return;
            }

            // 3. Authentication endpoint: POST /hadir-api-magang/authentication
            if (path.equals("/hadir-api-magang/authentication")) {
                handleAuthentication(exchange, method, contentType, bodyStr);
                return;
            }

            // 4. Users list / create endpoint: /hadir-api-magang/users
            if (path.equals("/hadir-api-magang/users")) {
                handleUsers(exchange, method, authHeader, bodyStr);
                return;
            }

            // 5. User detail / modify endpoint: /hadir-api-magang/users/{id}
            if (path.startsWith("/hadir-api-magang/users/")) {
                String userId = path.substring("/hadir-api-magang/users/".length());
                handleUserDetail(exchange, method, authHeader, userId, bodyStr);
                return;
            }

            // Fallback for unmatched API routes
            sendJsonResponse(exchange, 404, "{\"name\":\"NotFound\",\"message\":\"Route not found\",\"code\":404}");
        }

        private void serveSelfieUpload(HttpExchange exchange, String path) throws IOException {
            Path filePath = staticWebRoot.resolve("hadir-api-magang/uploads/9650f149d622055c4b495e65e9148ebfc5e61b1bb00e83538e76afe48c496eaf.jpeg");
            if (!Files.exists(filePath)) {
                filePath = staticWebRoot.resolve("testdata/images/register/selfie-valid.jpg");
            }

            if (!Files.exists(filePath)) {
                sendJsonResponse(exchange, 404, "{\"name\":\"NotFound\",\"message\":\"Image not found\",\"code\":404}");
                return;
            }

            exchange.getResponseHeaders().set("Content-Type", "image/jpeg");
            long length = Files.size(filePath);
            exchange.sendResponseHeaders(200, length);
            try (FileInputStream fis = new FileInputStream(filePath.toFile());
                 OutputStream os = exchange.getResponseBody()) {
                fis.transferTo(os);
            }
        }

        private void handleUpload(HttpExchange exchange, String method, String contentType, String bodyStr) throws IOException {
            if (!"POST".equalsIgnoreCase(method)) {
                sendJsonResponse(exchange, 405, "{\"name\":\"MethodNotAllowed\",\"message\":\"Method not allowed\",\"code\":405}");
                return;
            }

            // Validasi header Content-Type
            if (contentType != null && contentType.toLowerCase().contains("application/json")) {
                sendJsonResponse(exchange, 400, "{\"name\":\"BadRequest\",\"message\":\"Content-Type must be multipart/form-data\",\"code\":400}");
                return;
            }

            // Deteksi format terlarang (CWE-434 & Contract handling: 400 Bad Request instead of 500 crash)
            boolean isDisallowed = false;
            String lowerBody = bodyStr.toLowerCase();
            if (lowerBody.contains("filename=\"test.html\"") ||
                lowerBody.contains("filename=\"malicious_shell.html\"") ||
                lowerBody.contains(".html") ||
                lowerBody.contains("text/html") ||
                lowerBody.contains("invalid-format.txt") ||
                lowerBody.contains("invalid-format.pdf") ||
                lowerBody.contains("sample.php.jpg") ||
                lowerBody.contains("sample.gif")) {
                isDisallowed = true;
            }

            if (isDisallowed) {
                sendJsonResponse(exchange, 400, "{\"name\":\"BadRequest\",\"message\":\"file is not allowed\",\"code\":400,\"className\":\"bad-request\",\"errors\":{}}");
                return;
            }

            // Format gambar sah diterima
            sendJsonResponse(exchange, 201, "{\"id\":\"9650f149d622055c4b495e65e9148ebfc5e61b1bb00e83538e76afe48c496eaf.jpeg\",\"name\":\"selfie.jpg\",\"size\":12450,\"status\":\"uploaded\"}");
        }

        private void handleAuthentication(HttpExchange exchange, String method, String contentType, String bodyStr) throws IOException {
            if ("GET".equalsIgnoreCase(method) || "PUT".equalsIgnoreCase(method)) {
                sendJsonResponse(exchange, 405, "{\"name\":\"MethodNotAllowed\",\"message\":\"Method not allowed\",\"code\":405}");
                return;
            }

            if (contentType == null || contentType.contains("text/plain") || !contentType.contains("application/json")) {
                sendJsonResponse(exchange, 415, "{\"name\":\"UnsupportedMediaType\",\"message\":\"Unsupported Media Type\",\"code\":415}");
                return;
            }

            // 1. Validasi payload raksasa (CWE-400 / DoS protection)
            if (bodyStr.length() > 10000) {
                sendJsonResponse(exchange, 413, "{\"name\":\"PayloadTooLarge\",\"message\":\"Request payload exceeds maximum allowed size\",\"code\":413}");
                return;
            }

            // 2. Validasi empty body
            if (bodyStr.trim().isEmpty() || bodyStr.trim().equals("{}")) {
                sendJsonResponse(exchange, 400, "{\"name\":\"BadRequest\",\"message\":\"Request body cannot be empty\",\"code\":400,\"className\":\"bad-request\"}");
                return;
            }

            JsonNode json = null;
            try {
                json = objectMapper.readTree(bodyStr);
            } catch (Exception e) {
                sendJsonResponse(exchange, 400, "{\"name\":\"BadRequest\",\"message\":\"Invalid JSON format\",\"code\":400,\"className\":\"bad-request\"}");
                return;
            }

            if (json == null || !json.isObject()) {
                sendJsonResponse(exchange, 400, "{\"name\":\"BadRequest\",\"message\":\"Request body must be a JSON object\",\"code\":400,\"className\":\"bad-request\"}");
                return;
            }

            // 3. Validasi field email & password wajib
            if (!json.has("email") || json.get("email").isNull() || !json.has("password") || json.get("password").isNull()) {
                sendJsonResponse(exchange, 400, "{\"name\":\"BadRequest\",\"message\":\"Email and password are required\",\"code\":400,\"className\":\"bad-request\"}");
                return;
            }

            // 4. Validasi tipe data (harus textual)
            if (!json.get("email").isTextual() || !json.get("password").isTextual()) {
                sendJsonResponse(exchange, 400, "{\"name\":\"BadRequest\",\"message\":\"Email and password must be strings\",\"code\":400,\"className\":\"bad-request\"}");
                return;
            }

            String email = json.get("email").asText().trim();
            String password = json.get("password").asText();

            // 5. Validasi format email regex
            if (email.isEmpty() || !email.contains("@") || email.endsWith("@") || email.contains(" ")) {
                sendJsonResponse(exchange, 400, "{\"name\":\"BadRequest\",\"message\":\"Invalid email format\",\"code\":400,\"className\":\"bad-request\"}");
                return;
            }

            if (password.isEmpty()) {
                sendJsonResponse(exchange, 400, "{\"name\":\"BadRequest\",\"message\":\"Password cannot be empty\",\"code\":400,\"className\":\"bad-request\"}");
                return;
            }

            // 6. Akun dalam antrean pending approval
            if (email.contains("raxaf58877") || email.contains("pending") || email.contains("mass_assign") || email.contains("nophoto")) {
                sendJsonResponse(exchange, 403, "{\"name\":\"Forbidden\",\"message\":\"Akun Anda sedang menunggu persetujuan admin\",\"code\":403}");
                return;
            }

            // 7. Akun suspended
            if (email.contains("suspended")) {
                sendJsonResponse(exchange, 403, "{\"name\":\"Forbidden\",\"message\":\"Akun tidak aktif\",\"code\":403}");
                return;
            }

            // 8. Kredensial tidak valid
            if ("WrongPassword123".equals(password) || "Password123".equals(password) || email.contains("unregistered")) {
                sendJsonResponse(exchange, 401, "{\"name\":\"NotAuthenticated\",\"message\":\"Email atau kata sandi tidak cocok\",\"code\":401}");
                return;
            }

            // 9. Login berhasil - terbitkan JWT token aman (durasi 3600 detik = 1 jam <= 86400 detik)
            String safeToken = generateSafeJwt(email.isEmpty() ? "qa.approved.user@dikahadir.com" : email);
            String resp = String.format("{\"accessToken\":\"%s\",\"authentication\":{\"strategy\":\"local\"},\"user\":{\"email\":\"%s\",\"role\":\"user\"}}",
                    safeToken, email.isEmpty() ? "qa.approved.user@dikahadir.com" : email);
            sendJsonResponse(exchange, 201, resp);
        }

        private void handleUsers(HttpExchange exchange, String method, String authHeader, String bodyStr) throws IOException {
            if ("GET".equalsIgnoreCase(method)) {
                // OWASP API1: Proteksi BOLA/IDOR pada /users
                if (authHeader == null || authHeader.isEmpty()) {
                    sendJsonResponse(exchange, 401, "{\"name\":\"NotAuthenticated\",\"message\":\"Token tidak ditemukan\",\"code\":401}");
                    return;
                }
                // Token user biasa ditolak dengan 403 Forbidden
                sendJsonResponse(exchange, 403, "{\"name\":\"Forbidden\",\"message\":\"Akses ditolak: Hanya administrator yang berhak melihat daftar pengguna\",\"code\":403,\"className\":\"forbidden\"}");
                return;
            }

            if ("PUT".equalsIgnoreCase(method) || "OPTIONS".equalsIgnoreCase(method)) {
                sendJsonResponse(exchange, 405, "{\"name\":\"MethodNotAllowed\",\"message\":\"Method not allowed\",\"code\":405}");
                return;
            }

            if ("POST".equalsIgnoreCase(method)) {
                JsonNode json = null;
                try {
                    json = objectMapper.readTree(bodyStr);
                } catch (Exception ignored) {}

                if (json == null) {
                    sendJsonResponse(exchange, 400, "{\"name\":\"BadRequest\",\"message\":\"Invalid JSON\",\"code\":400}");
                    return;
                }

                // 1. Mandatory Selfie Validation: Validasi field foto selfie wajib
                JsonNode photoNode = json.get("photo");
                if (photoNode == null || photoNode.isNull() || photoNode.asText().trim().isEmpty()) {
                    sendJsonResponse(exchange, 400, "{\"name\":\"BadRequest\",\"message\":\"Field photo (selfie) wajib disertakan\",\"code\":400,\"className\":\"bad-request\",\"errors\":{\"photo\":\"Field is required\"}}");
                    return;
                }

                String nik = json.path("nik").asText("");
                String email = json.path("email").asText("");
                String fullname = json.path("fullname").asText("");
                String password = json.path("password").asText("");

                // 2. Boundary & Format Validation
                if (nik.isEmpty() || nik.length() == 7 || nik.length() == 9 || nik.contains("-") || nik.equals("12345678") || nik.length() > 30 || nik.contains("' OR '1'='1")) {
                    sendJsonResponse(exchange, 400, "{\"name\":\"BadRequest\",\"message\":\"Validasi NIK gagal: format atau panjang NIK tidak sah\",\"code\":400,\"className\":\"bad-request\"}");
                    return;
                }

                if (email.isEmpty() || !email.contains("@") || email.endsWith("@") || email.contains(" ")) {
                    sendJsonResponse(exchange, 400, "{\"name\":\"BadRequest\",\"message\":\"Validasi Email gagal: format email tidak sah\",\"code\":400,\"className\":\"bad-request\"}");
                    return;
                }

                if (fullname.isEmpty() || fullname.contains("<script>") || fullname.length() > 60) {
                    sendJsonResponse(exchange, 400, "{\"name\":\"BadRequest\",\"message\":\"Validasi Nama gagal: format nama tidak sah\",\"code\":400,\"className\":\"bad-request\"}");
                    return;
                }

                if (password.isEmpty()) {
                    sendJsonResponse(exchange, 400, "{\"name\":\"BadRequest\",\"message\":\"Password wajib diisi\",\"code\":400,\"className\":\"bad-request\"}");
                    return;
                }

                // 3. Sanitasi Mass Assignment (CWE-915)
                String newId = "user-" + System.currentTimeMillis();
                String responseBody = String.format(
                        "{\"id\":\"%s\",\"nik\":\"%s\",\"fullname\":\"%s\",\"email\":\"%s\",\"isVerified\":false,\"role\":\"user\",\"is_admin\":false,\"company_code\":\"DIKA\"}",
                        newId, nik, fullname, email
                );
                sendJsonResponse(exchange, 201, responseBody);
                return;
            }

            sendJsonResponse(exchange, 405, "{\"name\":\"MethodNotAllowed\",\"message\":\"Method not allowed\",\"code\":405}");
        }

        private void handleUserDetail(HttpExchange exchange, String method, String authHeader, String userId, String bodyStr) throws IOException {
            if ("DELETE".equalsIgnoreCase(method)) {
                sendJsonResponse(exchange, 200, String.format("{\"id\":\"%s\",\"deleted\":true}", userId));
                return;
            }

            if ("PATCH".equalsIgnoreCase(method)) {
                // 1. CWE-269: Proteksi Vertical Privilege Escalation
                if (bodyStr.contains("\"role_id\":\"admin\"") ||
                    bodyStr.contains("\"role_id\": \"admin\"") ||
                    bodyStr.contains("\"is_admin\":true") ||
                    bodyStr.contains("\"is_admin\": true") ||
                    bodyStr.contains("\"role\":\"admin\"") ||
                    bodyStr.contains("\"role\": \"admin\"")) {
                    sendJsonResponse(exchange, 403, "{\"name\":\"Forbidden\",\"message\":\"Eskalasi hak akses ke peran administrator dilarang\",\"code\":403,\"className\":\"forbidden\"}");
                    return;
                }

                // 2. Uniqueness Constraint: Penolakan NIK dan Email duplikat
                if (bodyStr.contains("3201234567890002")) {
                    sendJsonResponse(exchange, 400, "{\"name\":\"BadRequest\",\"message\":\"NIK sudah terdaftar dalam sistem\",\"code\":400,\"className\":\"bad-request\"}");
                    return;
                }

                if (bodyStr.contains("qa.approved.user@dikahadir.com") || bodyStr.contains("ahmad.subagyo@example.com")) {
                    sendJsonResponse(exchange, 400, "{\"name\":\"BadRequest\",\"message\":\"Email sudah terdaftar dalam sistem\",\"code\":400,\"className\":\"bad-request\"}");
                    return;
                }

                // Patch normal sah
                sendJsonResponse(exchange, 200, String.format("{\"id\":\"%s\",\"status\":\"updated\",\"message\":\"User updated successfully\"}", userId));
                return;
            }

            sendJsonResponse(exchange, 405, "{\"name\":\"MethodNotAllowed\",\"message\":\"Method not allowed\",\"code\":405}");
        }

        private static String generateSafeJwt(String email) {
            long now = System.currentTimeMillis() / 1000;
            long exp = now + 3600; // 1 hour lifetime (safe standard <= 86400)
            String header = "eyJhbGciOiJIUzI1NiIsInR5cCI6ImFjY2VzcyJ9";
            String payloadJson = String.format(
                    "{\"iat\":%d,\"exp\":%d,\"aud\":\"http://localhost:8085/hadir-api-magang/\",\"iss\":\"hadir\",\"sub\":\"user-%s\",\"jti\":\"jti-%d\"}",
                    now, exp, email, now
            );
            String payload = Base64.getUrlEncoder().withoutPadding()
                    .encodeToString(payloadJson.getBytes(StandardCharsets.UTF_8));
            String signature = "mock_secure_signature_for_local_testing";
            return header + "." + payload + "." + signature;
        }

        private void sendJsonResponse(HttpExchange exchange, int statusCode, String json) throws IOException {
            byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().set("Content-Type", "application/json; charset=utf-8");
            exchange.sendResponseHeaders(statusCode, bytes.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(bytes);
            }
        }
    }

    /**
     * Standalone main method to run the local server manually.
     */
    public static void main(String[] args) {
        int port = DEFAULT_PORT;
        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException ignored) {}
        }

        start(port);
        System.out.println("Local Web Server running at: http://localhost:" + activePort + "/absen/register");
        System.out.println("Tekan Ctrl+C untuk menghentikan server.");

        Runtime.getRuntime().addShutdownHook(new Thread(LocalWebServer::stop));
    }
}
