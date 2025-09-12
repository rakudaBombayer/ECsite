package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
 // 商品IDから商品を1件取得するメソッド
    public Shohin getProductById(int id) {
        String sql = "SELECT * FROM shohin WHERE shohin_id = ?";
        
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Shohin shohin = new Shohin();
                shohin.setShohinId(rs.getInt("shohin_id"));
                shohin.setShouhinMei(rs.getString("shouhin_mei"));
                shohin.setShouhinSetsumei(rs.getString("shouhin_setsumei"));
                shohin.setKakaku(rs.getInt("kakaku"));
                shohin.setZaikoSuuryou(rs.getInt("zaiko_suuryou"));
                shohin.setShouhinGazou(rs.getString("shouhin_gazou"));
                return shohin;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // 取得できなかった場合
    }
 // 商品追加
    public boolean insertShohin(String name, String desc, int price, int stock,  String image) {
    	String sql = "INSERT INTO shohin (shouhin_mei, shouhin_setsumei, kakaku, zaiko_suuryou,  shouhin_gazou) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
        	System.out.println("DB接続成功");
            System.out.println("name: " + name);
            System.out.println("desc: " + desc);
            System.out.println("price: " + price);
            System.out.println("stock: " + stock);
            System.out.println("image: " + image);
        	
        	
        	
            stmt.setString(1, name);
            stmt.setString(2, desc);
            stmt.setInt(3, price);
            stmt.setInt(4, stock);
            stmt.setString(5, image);
            
            //お試し
            int rows = stmt.executeUpdate();
            System.out.println("追加された行数: " + rows);

            return rows > 0;
        } catch (SQLException e) {
        	System.out.println("SQL例外発生");
            e.printStackTrace();
            return false;
        }
    }

    // 商品更新
    public boolean updateShohin(Shohin shohin, String oldShouhinMei) {
        String sql = "UPDATE shohin SET shouhin_mei = ?, shouhin_setsumei = ?, kakaku = ?, zaiko_suuryou = ?, shouhin_gazou = ? WHERE shouhin_mei = ?";
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, shohin.getShouhinMei());
            stmt.setString(2, shohin.getShouhinSetsumei());
            stmt.setInt(3, shohin.getKakaku());
            stmt.setInt(4, shohin.getZaikoSuuryou());
            stmt.setString(5, shohin.getShouhinGazou());
            stmt.setString(6, oldShouhinMei);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 商品削除
    public boolean deleteShohin(String oldShouhinMei) {
        String sql = "DELETE FROM shohin WHERE shouhin_mei = ?";
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
        	stmt.setString(1, oldShouhinMei);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    //商品の全件取得(メニュー画面用)
    public List<Shohin> getAllShohin() {
        List<Shohin> list = new ArrayList<>();
        String sql = "SELECT * FROM shohin";
        

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Shohin shohin = new Shohin();
                shohin.setShohinId(rs.getInt("shohin_id"));
                shohin.setShouhinMei(rs.getString("shouhin_mei"));
                shohin.setShouhinSetsumei(rs.getString("shouhin_setsumei"));
                shohin.setKakaku(rs.getInt("kakaku"));
                shohin.setZaikoSuuryou(rs.getInt("zaiko_suuryou"));
                shohin.setShouhinGazou(rs.getString("shouhin_gazou"));
                list.add(shohin);
                

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
 // ECsiteDAO.java に追加
    public Account getAccountById(int kaiinId) {
        String sql = "SELECT * FROM account WHERE kaiin_id = ?";
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, kaiinId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Account account = new Account();
                account.setKaiinId(rs.getInt("kaiin_id"));
                account.setShimei(rs.getString("shimei"));
                account.setPassword(rs.getString("password"));
                account.setYuubinBangou(rs.getString("yuubin_bangou"));
                account.setAddress(rs.getString("address"));
                account.setDenwaBangou(rs.getString("denwa_bangou"));
                account.setSeinengappi(rs.getDate("seinengappi"));
                account.setMailAddress(rs.getString("mail_address"));
                account.setShiharaiHouhou(rs.getString("shiharai_houhou"));
                return account;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Account getAccountByNameAndPassword(String shimei, String password) {
        String sql = "SELECT * FROM account WHERE shimei = ? AND password = ?";
        
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, shimei);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Account account = new Account();
                account.setKaiinId(rs.getInt("kaiin_id"));
                account.setShimei(rs.getString("shimei"));
                account.setPassword(rs.getString("password"));
                account.setYuubinBangou(rs.getString("yuubin_bangou"));
                account.setAddress(rs.getString("address"));
                account.setDenwaBangou(rs.getString("denwa_bangou"));
                account.setSeinengappi(rs.getDate("seinengappi"));
                account.setMailAddress(rs.getString("mail_address"));
                account.setShiharaiHouhou(rs.getString("shiharai_houhou"));
                return account;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean updateAccount(int kaiinId, String shimei, String password, String yuubin, String address,
            String denwa, Date birth, String mail, String shiharai) {

        String sql = "UPDATE account SET shimei=?, password=?, yuubin_bangou=?, address=?, denwa_bangou=?, seinengappi=?, mail_address=?, shiharai_houhou=? WHERE kaiin_id=?";

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
            stmt.setInt(9, kaiinId); 

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace(); 
            return false;
        }
    }
    
    public List<CartItem> getCartList(int kaiinId) {
        List<CartItem> cartList = new ArrayList<>();

        String sql = "SELECT sc.cart_id, sc.shohin_id, sc.quantity, s.shouhin_mei, s.kakaku " +
                     "FROM shopping_cart sc JOIN shohin s ON sc.shohin_id = s.shohin_id " +
                     "WHERE sc.kaiin_id = ?";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, kaiinId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                CartItem item = new CartItem();
                item.setCartId(rs.getInt("cart_id"));
                item.setShohinId(rs.getInt("shohin_id"));
                item.setShohinMei(rs.getString("shouhin_mei"));
                item.setKakaku(rs.getInt("kakaku"));
                item.setQuantity(rs.getInt("quantity"));
                cartList.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cartList;
    }
    public boolean updateCartQuantity(int kaiinId, int shohinId, int quantity) {
        String sql = "UPDATE shopping_cart SET quantity = ? WHERE kaiin_id = ? AND shohin_id = ?";
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, quantity);
            stmt.setInt(2, kaiinId);
            stmt.setInt(3, shohinId);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteCartItem(int kaiinId, int shohinId) {
        String sql = "DELETE FROM shopping_cart WHERE kaiin_id = ? AND shohin_id = ?";
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, kaiinId);
            stmt.setInt(2, shohinId);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean insertOrUpdateCart(int kaiinId, int shohinId, int quantity) {
        String selectSql = "SELECT quantity FROM shopping_cart WHERE kaiin_id = ? AND shohin_id = ?";
        String updateSql = "UPDATE shopping_cart SET quantity = quantity + ? WHERE kaiin_id = ? AND shohin_id = ?";
        String insertSql = "INSERT INTO shopping_cart (kaiin_id, shohin_id, quantity) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

            // まずは既に存在するか確認
            try (PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {
                selectStmt.setInt(1, kaiinId);
                selectStmt.setInt(2, shohinId);

                ResultSet rs = selectStmt.executeQuery();
                if (rs.next()) {
                    // 存在するので UPDATE
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                        updateStmt.setInt(1, quantity);
                        updateStmt.setInt(2, kaiinId);
                        updateStmt.setInt(3, shohinId);
                        int rows = updateStmt.executeUpdate();
                        return rows > 0;
                    }
                } else {
                    // 存在しないので INSERT
                    try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                        insertStmt.setInt(1, kaiinId);
                        insertStmt.setInt(2, shohinId);
                        insertStmt.setInt(3, quantity);
                        int rows = insertStmt.executeUpdate();
                        return rows > 0;
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
