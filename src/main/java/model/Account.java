package model;

public class Account {
    private int kaiinId;
    private String password;
    private String shimei;
    private String yuubinBangou;
    private String address;
    private String denwaBangou;
    private java.sql.Date seinengappi;
    private String mailAddress;
    private String shiharaiHouhou;
    private boolean isAdmin;

    // ゲッターとセッター
    public int getKaiinId() { return kaiinId; }
    public void setKaiinId(int kaiinId) { this.kaiinId = kaiinId; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getShimei() { return shimei; }
    public void setShimei(String shimei) { this.shimei = shimei; }

    public String getYuubinBangou() { return yuubinBangou; }
    public void setYuubinBangou(String yuubinBangou) { this.yuubinBangou = yuubinBangou; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getDenwaBangou() { return denwaBangou; }
    public void setDenwaBangou(String denwaBangou) { this.denwaBangou = denwaBangou; }

    public java.sql.Date getSeinengappi() { return seinengappi; }
    public void setSeinengappi(java.sql.Date seinengappi) { this.seinengappi = seinengappi; }

    public String getMailAddress() { return mailAddress; }
    public void setMailAddress(String mailAddress) { this.mailAddress = mailAddress; }

    public String getShiharaiHouhou() { return shiharaiHouhou; }
    public void setShiharaiHouhou(String shiharaiHouhou) { this.shiharaiHouhou = shiharaiHouhou; }

    public boolean isAdmin() { return isAdmin; }
    public void setAdmin(boolean isAdmin) { this.isAdmin = isAdmin; }
}
