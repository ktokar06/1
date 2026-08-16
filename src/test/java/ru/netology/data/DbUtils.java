package ru.netology.data;

import java.sql.*;

public class DbUtils {
    private static final String DB_URL = "jdbc:postgresql://localhost:5439/app";
    private static final String DB_USER = "app";
    private static final String DB_PASSWORD = "pass";

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Драйвер PostgreSQL не найден", e);
        }
    }

    public static String getPaymentStatus(String paymentId) {
        String query = "SELECT status FROM payment_entity WHERE id = ?";
        return executeQuery(query, paymentId, "status");
    }

    public static String getCreditStatus(String creditId) {
        String query = "SELECT status FROM credit_request_entity WHERE id = ?";
        return executeQuery(query, creditId, "status");
    }

    public static String getLastTransactionId() {
        String queryPayment = "SELECT id FROM payment_entity ORDER BY created DESC LIMIT 1";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(queryPayment)) {
            if (rs.next()) {
                return rs.getString("id");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при получении payment_id: " + e.getMessage());
        }

        String queryCredit = "SELECT id FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(queryCredit)) {
            if (rs.next()) {
                return rs.getString("id");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при получении credit_id: " + e.getMessage());
        }

        return null;
    }

    public static void clearDatabase() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM payment_entity");
            stmt.executeUpdate("DELETE FROM credit_request_entity");
            stmt.executeUpdate("DELETE FROM order_entity");
        } catch (SQLException e) {
            System.out.println("Ошибка при очистке базы данных: " + e.getMessage());
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    private static String executeQuery(String query, String param, String column) {
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, param);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString(column);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при выполнении запроса: " + e.getMessage());
        }
        return null;
    }
}