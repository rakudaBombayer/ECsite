package model;

public class OrderCustomer {
    private int orderId;
    private int kaiinId;
    private String shimei;
    private String yuubinBangou;
    private String address;
    private String denwaBangou;
    private String mailAddress;
    private java.sql.Timestamp kounyubi;
    private String shiharaiHouhou;

    // ゲッターとセッター
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public int getKaiinId() { return kaiinId; }
    public void setKaiinId(int kaiinId) { this.kaiinId = kaiinId; }

    public String getShimei() { return shimei; }
    public void setShimei(String shimei) { this.shimei = shimei; }

    public String getYuubinBangou() { return yuubinBangou; }
    public void setYuubinBangou(String yuubinBangou) { this.yuubinBangou = yuubinBangou; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getDenwaBangou() { return denwaBangou; }
    public void setDenwaBangou(String denwaBangou) { this.denwaBangou = denwaBangou; }

    public String getMailAddress() { return mailAddress; }
    public void setMailAddress(String mailAddress) { this.mailAddress = mailAddress; }

    public java.sql.Timestamp getKounyubi() { return kounyubi; }
    public void setKounyubi(java.sql.Timestamp kounyubi) { this.kounyubi = kounyubi; }

    public String getShiharaiHouhou() { return shiharaiHouhou; }
    public void setShiharaiHouhou(String shiharaiHouhou) { this.shiharaiHouhou = shiharaiHouhou; }
}
