package model;

import java.sql.Timestamp;

public class OrderHistory {
    private int historyId;
    private int kaiinId;
    private String shohinMei;
    private int kakaku;
    private int quantity;
    private Timestamp orderTime;
    private String shohinGazou;
    private int shohinId;

    // getter/setter
    public int getHistoryId() { return historyId; }
    public void setHistoryId(int historyId) { this.historyId = historyId; }

    public int getKaiinId() { return kaiinId; }
    public void setKaiinId(int kaiinId) { this.kaiinId = kaiinId; }

    public String getShohinMei() { return shohinMei; }
    public void setShohinMei(String shohinMei) { this.shohinMei = shohinMei; }

    public int getKakaku() { return kakaku; }
    public void setKakaku(int kakaku) { this.kakaku = kakaku; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public Timestamp getOrderTime() { return orderTime; }
    public void setOrderTime(Timestamp orderTime) { this.orderTime = orderTime; }
    
    public String getShohinGazou() {return shohinGazou;}
    public void setShohinGazou(String shohinGazou) {this.shohinGazou = shohinGazou;}
    
    public int getShohinId() { return shohinId; }
    public void setShohinId(int shohinId) { this.shohinId = shohinId; }
}
