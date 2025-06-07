import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors; // For a better default executor

public class SimpleHttpLogin {

    // --- Database Configuration ---
    private static final String DB_URL = "jdbc:mysql://localhost:3306/restdb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Deep@mysql1"; // !! IMPORTANT: Replace with your actual MySQL password !!

    // JDBC Driver Name
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    public static void main(String[] args) throws IOException {
        // 1. Explicitly load the JDBC driver (good practice)
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("MySQL JDBC Driver registered successfully.");
        } catch (ClassNotFoundException e) {
            System.err.println("Could not find JDBC Driver. Make sure MySQL Connector/J JAR is in the classpath.");
            e.printStackTrace();
            return; // Exit if driver not found
        }

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/login", new LoginHandler());
        server.setExecutor(Executors.newCachedThreadPool()); // Use a cached thread pool for better concurrency
        server.start();
        System.out.println("Server started on port 8000. Listening for POST requests at /login");
        System.out.println("DB URL: " + DB_URL + ", User: " + DB_USER);
        System.out.println("Ensure your DB_PASSWORD is correctly set in the code.");
    }

    static class LoginHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String responseMessage = "An unexpected error occurred.";
            int statusCode = 500; // Default to Internal Server Error

            try {
                if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                    InputStream requestBodyStream = exchange.getRequestBody();
                    String requestBodyString = new String(requestBodyStream.readAllBytes(), StandardCharsets.UTF_8);
                    requestBodyStream.close(); // Close the input stream

                    Map<String, String> params = parseFormData(requestBodyString);

                    String username = params.get("username");
                    String password = params.get("password");

                    // 4. Handle missing parameters
                    if (username == null || username.trim().isEmpty() || password == null) { // Password can be empty string, but username shouldn't
                        statusCode = 401; // Bad Request
                        responseMessage = "Invalid credentials";
                    } else {
                        try {
                            if (authenticate(username, password)) {
                                statusCode = 200;
                                responseMessage = "Login successful";
                            } else {
                                statusCode = 401;
                                responseMessage = "Invalid credentials";
                            }
                        } catch (SQLException e) {
                            // Log SQL exceptions more specifically if needed
                            System.err.println("SQLException during authentication: " + e.getMessage());
                            // e.printStackTrace(); // Uncomment for full stack trace during debugging
                            statusCode = 500;
                            responseMessage = "Database error during authentication.";
                        }
                    }
                } else {
                    statusCode = 405; // Method Not Allowed
                    responseMessage = "Only POST method is supported";
                    // 5. Add Allow header for 405
                    exchange.getResponseHeaders().set("Allow", "POST");
                }
            } catch (IllegalArgumentException e) { // Catch bad form data from parseFormData
                statusCode = 400;
                responseMessage = "Bad Request: " + e.getMessage();
            }
            catch (Exception e) { // Catch-all for other unexpected errors
                System.err.println("Unhandled error processing request: " + e.getMessage());
                e.printStackTrace(); // Log the full stack trace for unexpected errors
                statusCode = 500;
                responseMessage = "Internal Server Error: " + e.getClass().getSimpleName();
            } finally {
                // Send response
                exchange.getResponseHeaders().set("Content-Type", "text/plain; charset=utf-8");
                byte[] responseBytes = responseMessage.getBytes(StandardCharsets.UTF_8);
                exchange.sendResponseHeaders(statusCode, responseBytes.length);
                OutputStream os = exchange.getResponseBody();
                os.write(responseBytes);
                os.close(); // Closes response body stream

                // 2. Ensure HttpExchange is closed
                exchange.close();
            }
        }

        // 3. Improved parseFormData with URL decoding
        private Map<String, String> parseFormData(String formData) throws UnsupportedEncodingException {
            Map<String, String> params = new HashMap<>();
            if (formData == null || formData.trim().isEmpty()) {
                return params;
            }
            String[] pairs = formData.split("&");
            for (String pair : pairs) {
                int idx = pair.indexOf("=");
                if (idx > 0 && idx < pair.length() - 1) { // Key and value present
                    String key = URLDecoder.decode(pair.substring(0, idx), StandardCharsets.UTF_8.name());
                    String value = URLDecoder.decode(pair.substring(idx + 1), StandardCharsets.UTF_8.name());
                    params.put(key, value);
                } else if (idx == pair.length() - 1) { // Key with empty value, e.g., "param="
                     String key = URLDecoder.decode(pair.substring(0, idx), StandardCharsets.UTF_8.name());
                     params.put(key, "");
                } else if (idx == -1 && !pair.isEmpty()) { // Key without value, e.g. "param" (less common in forms)
                     params.put(URLDecoder.decode(pair, StandardCharsets.UTF_8.name()), "");
                } else if (!pair.isEmpty()){
                    // Malformed pair, could throw an IllegalArgumentException or log
                    System.err.println("Malformed form data pair: " + pair);
                    // For simplicity, we can ignore it or throw:
                    // throw new IllegalArgumentException("Malformed form data: " + pair);
                }
            }
            return params;
        }

        private boolean authenticate(String username, String password) throws SQLException {
            // Using try-with-resources for automatic resource management (Connection, PreparedStatement, ResultSet)
            String sql = "SELECT password FROM users WHERE username = ?"; // More secure to fetch password then compare
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, username);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String storedPassword = resultSet.getString("password");
                        // IMPORTANT: This is plain text comparison as per requirement.
                        // For real apps, use hashed passwords and secure comparison.
                        return password.equals(storedPassword);
                    }
                }
            }
            // No need to catch SQLException here if we re-throw it or let it propagate
            // The 'throws SQLException' in the method signature handles that.
            return false; // User not found
        }
    }
}