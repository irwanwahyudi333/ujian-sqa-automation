package com.company.sqa.utils;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
        server.createContext("/", new StaticFileHandler());
        server.start();

        logger.info("==========================================================");
        logger.info("Local Web Server started successfully on port {}!", activePort);
        logger.info("URL Register Page: http://localhost:{}/absen/register", activePort);
        logger.info("URL Register Page v2: http://localhost:{}/management/user-v2/register", activePort);
        logger.info("URL Self-Registration: http://localhost:{}/management/self-registration", activePort);
        logger.info("URL Divisi: http://localhost:{}/management/divisi", activePort);
        logger.info("URL Monthly Schedule: http://localhost:{}/management/monthly-schedule", activePort);
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
            if (uriPath.equals("/management/departemens") || uriPath.equals("/management/departemens/")) {
                serveFile(exchange, "/management/departemens.html");
                return;
            }
            if (uriPath.equals("/management/departements-settings") || uriPath.equals("/management/departements-settings/")) {
                serveFile(exchange, "/management/departements-settings.html");
                return;
            }
            if (uriPath.equals("/management/position") || uriPath.equals("/management/position/")) {
                serveFile(exchange, "/management/position.html");
                return;
            }
            if (uriPath.equals("/management/client-upliner") || uriPath.equals("/management/client-upliner/")) {
                serveFile(exchange, "/management/client-upliner.html");
                return;
            }
            if (uriPath.equals("/management/location-point") || uriPath.equals("/management/location-point/")) {
                serveFile(exchange, "/management/location-point.html");
                return;
            }
            if (uriPath.equals("/management/job-level") || uriPath.equals("/management/job-level/")) {
                serveFile(exchange, "/management/job-level.html");
                return;
            }
            if (uriPath.equals("/management/shifting") || uriPath.equals("/management/shifting/")) {
                serveFile(exchange, "/management/shifting.html");
                return;
            }
            if (uriPath.equals("/management/schedule") || uriPath.equals("/management/schedule/")) {
                serveFile(exchange, "/management/schedule.html");
                return;
            }
            if (uriPath.equals("/management/calendar") || uriPath.equals("/management/calendar/")) {
                serveFile(exchange, "/management/calendar.html");
                return;
            }
            if (uriPath.equals("/management/unit-leave") || uriPath.equals("/management/unit-leave/")) {
                serveFile(exchange, "/management/unit-leave.html");
                return;
            }
            if (uriPath.equals("/management/day-off") || uriPath.equals("/management/day-off/")) {
                serveFile(exchange, "/management/day-off.html");
                return;
            }
            if (uriPath.equals("/management/monthly-schedule") || uriPath.equals("/management/monthly-schedule/")) {
                serveFile(exchange, "/management/monthly-schedule.html");
                return;
            }
            if (uriPath.equals("/management/user-monitoring") || uriPath.equals("/management/user-monitoring/")) {
                serveFile(exchange, "/management/user-monitoring.html");
                return;
            }
            if (uriPath.equals("/management/ot-reason") || uriPath.equals("/management/ot-reason/")) {
                serveFile(exchange, "/management/ot-reason.html");
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
            if (cleanPath.endsWith("/")) {
                cleanPath = cleanPath.substring(0, cleanPath.length() - 1);
            }
            Path filePath = staticWebRoot.resolve(cleanPath).normalize();

            // Security check against directory traversal
            if (!filePath.startsWith(staticWebRoot) || !Files.exists(filePath) || Files.isDirectory(filePath)) {
                // Check if adding .html resolves to an existing file in staticWebRoot (e.g. /laporan/activity -> /laporan/activity.html)
                Path candidateWithHtml = staticWebRoot.resolve(cleanPath + ".html").normalize();
                if (candidateWithHtml.startsWith(staticWebRoot) && Files.exists(candidateWithHtml) && !Files.isDirectory(candidateWithHtml)) {
                    filePath = candidateWithHtml;
                } else if (cleanPath.equals("absen/register")) {
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
                } else if (cleanPath.equals("management/monthly-schedule") || cleanPath.equals("management/monthly-schedule/")) {
                    filePath = staticWebRoot.resolve("management/monthly-schedule.html");
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
