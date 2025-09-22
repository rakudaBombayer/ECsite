package model;

import java.util.Date;

public class CartItem {
    private int cartId;
    private int shohinId;
    private String shohinMei;
    private int kakaku;
    private int quantity;
    private int zaikoSuuryou;
    private Date orderTime; 
    private String shohinGazou;
    // ゲッター・セッター
    public int getCartId() { return cartId; }
    public void setCartId(int cartId) { this.cartId = cartId; }

    public int getShohinId() { return shohinId; }
    public void setShohinId(int shohinId) { this.shohinId = shohinId; }

    public String getShohinMei() { return shohinMei; }
    public void setShohinMei(String shohinMei) { this.shohinMei = shohinMei; }

    public int getKakaku() { return kakaku; }
    public void setKakaku(int kakaku) { this.kakaku = kakaku; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public int getZaikoSuuryou() {
        return zaikoSuuryou;
    }
    public void setZaikoSuuryou(int zaikoSuuryou) {
        this.zaikoSuuryou = zaikoSuuryou;
    }
    public Date getOrderTime() {
        return orderTime;
    }
    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }
    public String getShohinGazou() {
        return shohinGazou;
    }
    public void setShohinGazou(String shohinGazou) {
        this.shohinGazou = shohinGazou;
    }
}
