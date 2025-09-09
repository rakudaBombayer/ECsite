package model;

import java.sql.Connection;
import java.sql.Date;
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
    
    //アカウントを登録する処理
    public boolean insertAccount(String shimei, String password, String yuubin, String address,
            String denwa, Date birth, String mail, String shiharai) {

    	String sql = "INSERT INTO account (shimei, password, yuubin_bangou, address, denwa_bangou, seinengappi, mail_address, shiharai_houhou) " +
    	"VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    	try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
    	PreparedStatement stmt = conn.prepareStatement(sql)) {

    	stmt.setString(1, shimei);
    	stmt.setString(2, password);
    	stmt.setString(3, yuubin);
    	stmt.setString(4, address);
    	stmt.setString(5, denwa);
    	stmt.setDate(6, birth);
    	stmt.setString(7, mail);
    	stmt.setString(8, shiharai);

    	int rowsInserted = stmt.executeUpdate();
    	if (rowsInserted == 0) {
    	    System.out.println("データの登録に失敗しました");
    	}

    	
    	return rowsInserted > 0;

    	} catch (SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }

}
