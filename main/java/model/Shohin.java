package model;

public class Shohin {
    private int shohinId;
    private String shouhinMei;
    private String shouhinSetsumei;
    private int kakaku;
    private int kounyuuSuuryou;
    private int zaikoSuuryou;
    private String shouhinGazou;
    private String oldShouhinMei;
    private String categoryName;
    
    // ゲッターとセッター
    public int getShohinId() { return shohinId; }
    public void setShohinId(int shohinId) { this.shohinId = shohinId; }

    public String getShouhinMei() { return shouhinMei; }
    public void setShouhinMei(String shouhinMei) { this.shouhinMei = shouhinMei; }

    public String getShouhinSetsumei() { return shouhinSetsumei; }
    public void setShouhinSetsumei(String shouhinSetsumei) { this.shouhinSetsumei = shouhinSetsumei; }

    public int getKakaku() { return kakaku; }
    public void setKakaku(int kakaku) { this.kakaku = kakaku; }

    public int getKounyuuSuuryou() { return kounyuuSuuryou; }
    public void setKounyuuSuuryou(int kounyuuSuuryou) { this.kounyuuSuuryou = kounyuuSuuryou; }

    public int getZaikoSuuryou() { return zaikoSuuryou; }
    public void setZaikoSuuryou(int zaikoSuuryou) { this.zaikoSuuryou = zaikoSuuryou; }

    public String getShouhinGazou() { return shouhinGazou; }
    public void setShouhinGazou(String shouhinGazou) { this.shouhinGazou = shouhinGazou; }
    
    public String getOldShouhinMei() { return oldShouhinMei; }
    public void setOldShouhinMei(String oldShouhinMei) { this.oldShouhinMei = oldShouhinMei; }
    
    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

}
