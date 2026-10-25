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
            if (uiUrl != null && uiUrl.contains(":")) {
                String[] parts = uiUrl.split(":");
                String portPart = parts[parts.length - 1].replaceAll("[^0-9]", "");
                if (!portPart.isEmpty()) {
                    port = Integer.parseInt(portPart);
                }
            }
        } catch (Exception ignored) {}
        start(port);
    }

    /**
     * Start the local web server on a specific port.
     *
     * @param port the TCP port to bind to
     */
    public static synchronized void start(int port) {
        if (server != null) {
            logger.info("LocalWebServer is already running on port {}", activePort);
            return;
        }

        try {
            activePort = port;
            server = HttpServer.create(new InetSocketAddress(activePort), 0);
            server.setExecutor(Executors.newVirtualThreadPerTaskExecutor());

            server.createContext("/", new StaticFileHandler());
            server.start();

            logger.info("==========================================================");
            logger.info("Local Web Server started successfully!");
            logger.info("URL Register Page: http://localhost:{}/absen/register", activePort);
            logger.info("URL Self-Registration: http://localhost:{}/management/self-registration", activePort);
            logger.info("URL Divisi: http://localhost:{}/management/divisi", activePort);
            logger.info("URL Management User: http://localhost:{}/management/user", activePort);
            logger.info("Serving files from: {}", staticWebRoot);
            logger.info("==========================================================");
        } catch (IOException e) {
            logger.error("Failed to start LocalWebServer on port {}: {}", port, e.getMessage());
            throw new RuntimeException("Could not start local web server", e);
        }
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
