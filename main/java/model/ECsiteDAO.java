package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ECsiteDAO {

    private final String JDBC_URL = "jdbc:postgresql://localhost:5432/shop_site";
    private final String DB_USER = "postgres";
    private final String DB_PASS = "sql";

    // JDBCドライバを一度だけロード
    static {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("JDBCドライバのロード成功");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBCドライバのロード失敗");
            throw new IllegalStateException("JDBCドライバを読み込めませんでした", e);
        }
    }

    public boolean isConnected() {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            System.out.println("データベース接続成功");
            return true;
        } catch (SQLException e) {
            System.out.println("データベース接続失敗");
            e.printStackTrace();
            return false;
        }
    }

    // ユーザー認証メソッド
    public boolean isValidUser(String userId, String password) {
        String sql = "SELECT * FROM account WHERE shimei = ? AND password = ?";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, userId);
            stmt.setString(2, password);
            
            System.out.println("SQL実行: " + stmt.toString());
            
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // 該当するユーザーがいれば true
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
