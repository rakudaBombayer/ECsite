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
    public boolean insertShohin(String name, String category, String desc, int price, int stock,  String image) {
    	String sql = "INSERT INTO shohin (shouhin_mei, category_name, shouhin_setsumei, kakaku, zaiko_suuryou,  shouhin_gazou) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
        	System.out.println("DB接続成功");
            System.out.println("name: " + name);
            System.out.println("name: " + category);
            System.out.println("desc: " + desc);
            System.out.println("price: " + price);
            System.out.println("stock: " + stock);
            System.out.println("image: " + image);
        	
        	
        	
            stmt.setString(1, name);
            stmt.setString(2, category);
            stmt.setString(3, desc);
            stmt.setInt(4, price);
            stmt.setInt(5, stock);
            stmt.setString(6, image);
            
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
        String sql = "UPDATE shohin SET shouhin_mei = ?, category_name = ?,  shouhin_setsumei = ?, kakaku = ?, zaiko_suuryou = ?, shouhin_gazou = ? WHERE shouhin_mei = ?";
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, shohin.getShouhinMei());
            stmt.setString(2, shohin.getCategoryName());
            stmt.setString(3, shohin.getShouhinSetsumei());
            stmt.setInt(4, shohin.getKakaku());
            stmt.setInt(5, shohin.getZaikoSuuryou());
            stmt.setString(6, shohin.getShouhinGazou());
            stmt.setString(7, oldShouhinMei);
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
                account.setAdmin(rs.getString("is_admin").equals("t"));
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
                    //ここ-----------------------からです    
    public List<CartItem> getCartList(int kaiinId) {
        List<CartItem> cartList = new ArrayList<>();

        String sql = "SELECT sc.cart_id, sc.shohin_id, sc.quantity, s.shouhin_mei, s.kakaku, s.shouhin_gazou " +
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
                item.setShohinGazou(rs.getString("shouhin_gazou"));
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
    public boolean insertOrderCustomer(int kaiinId, String shimei, String yuubin, String address,
            String denwa, String mail, String shiharai, java.sql.Timestamp kounyubi) {
boolean success = false;

String sql = "INSERT INTO order_customer (kaiin_id, shimei, yuubin_bangou, address, denwa_bangou, mail_address, shiharai_houhou, kounyubi) " +
"VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
PreparedStatement pstmt = conn.prepareStatement(sql)) {

pstmt.setInt(1, kaiinId);
pstmt.setString(2, shimei);
pstmt.setString(3, yuubin);
pstmt.setString(4, address);
pstmt.setString(5, denwa);
pstmt.setString(6, mail);
pstmt.setString(7, shiharai);
pstmt.setTimestamp(8, kounyubi);

int rows = pstmt.executeUpdate();
if (rows > 0) {
success = true;
}
} catch (Exception e) {
e.printStackTrace();
}

return success;
}
    public boolean clearCart(int kaiinId) {
        String sql = "DELETE FROM shopping_cart WHERE kaiin_id = ?";
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, kaiinId);
            int rowsDeleted = ps.executeUpdate();
            return rowsDeleted >= 0;  // 0件でも成功とみなす
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean insertOrderHistory(int kaiinId, int shohinId, int quantity, java.sql.Timestamp now) {
        String sql = "INSERT INTO order_history (kaiin_id, shohin_id, quantity, order_time) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, kaiinId);
            stmt.setInt(2, shohinId);
            stmt.setInt(3, quantity);
            stmt.setTimestamp(4, now);

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
 // 注文履歴一覧を取得する
    public List<OrderHistory> getOrderHistoryByKaiinId(int kaiinId) {
        List<OrderHistory> historyList = new ArrayList<>();
        String sql = "SELECT oh.order_time, oh.quantity, oh.shohin_id, s.shouhin_mei, s.kakaku, s.shouhin_gazou " + 
                "FROM order_history oh JOIN shohin s ON oh.shohin_id = s.shohin_id " +
                "WHERE oh.kaiin_id = ? ORDER BY oh.order_time DESC";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, kaiinId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                OrderHistory history = new OrderHistory();
                history.setOrderTime(rs.getTimestamp("order_time"));
                history.setQuantity(rs.getInt("quantity"));
                history.setShohinId(rs.getInt("shohin_id"));
                history.setShohinMei(rs.getString("shouhin_mei"));
                history.setKakaku(rs.getInt("kakaku"));
                history.setShohinGazou(rs.getString("shouhin_gazou"));
                historyList.add(history);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return historyList;
    }
    public List<CartItem> getOrderHistory(int kaiinId) {
        List<CartItem> list = new ArrayList<>();
        String sql = "SELECT s.shouhin_mei, s.kakaku, oh.quantity, oh.order_time " +
                "FROM order_history oh " +
                "JOIN shohin s ON oh.shohin_id = s.shohin_id " +
                "WHERE oh.kaiin_id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, kaiinId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CartItem item = new CartItem();
                item.setShohinMei(rs.getString("shohin_mei"));
                item.setKakaku(rs.getInt("kakaku"));
                item.setQuantity(rs.getInt("quantity"));
                item.setOrderTime(rs.getTimestamp("order_time"));  // Timestampをセット
                list.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
    }
    
 // 商品名であいまい検索
    public List<Shohin> searchShohin(String keyword, String category) {
        List<Shohin> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM shohin WHERE 1=1");

        if (keyword != null && !keyword.isEmpty()) {
            sql.append(" AND shouhin_mei LIKE ?");
        }
        if (category != null && !category.isEmpty()) {
            sql.append(" AND category_name = ?");
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
            
            int idx = 1;
            if (keyword != null && !keyword.isEmpty()) {
                pstmt.setString(idx++, "%" + keyword + "%");
            }
            if (category != null && !category.isEmpty()) {
                pstmt.setString(idx++, category);
            }

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Shohin s = new Shohin();
                s.setShohinId(rs.getInt("shohin_id"));
                s.setShouhinMei(rs.getString("shouhin_mei"));
                s.setShouhinSetsumei(rs.getString("shouhin_setsumei"));
                s.setKakaku(rs.getInt("kakaku"));
                s.setCategoryName(rs.getString("category_name"));
                s.setShouhinGazou(rs.getString("shouhin_gazou"));
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
        
    // カテゴリーで検索
    public List<Product> searchProducts(String name, String category) {
        List<Product> list = new ArrayList<>();

        StringBuilder sql = new StringBuilder("SELECT * FROM shohin WHERE 1=1");

        // 商品名条件
        if (name != null && !name.isEmpty()) {
            sql.append(" AND name LIKE ?");
        }

        // カテゴリー条件
        if (category != null && !category.isEmpty()) {
            sql.append(" AND category_name = ?");
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {

            int idx = 1;
            if (name != null && !name.isEmpty()) {
                pstmt.setString(idx++, "%" + name + "%");
            }
            if (category != null && !category.isEmpty()) {
                pstmt.setString(idx++, category);
            }

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getInt("price"));
                p.setCategoryName(rs.getString("category_name")); // ← 追加
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    //ログインユーザーで検索してカートの中の個数を検索する処理。
    
    public int getCartTotalQuantity(int kaiinId) {
        String sql = "SELECT SUM(quantity) AS total_items FROM shopping_cart WHERE kaiin_id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, kaiinId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("total_items");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}


