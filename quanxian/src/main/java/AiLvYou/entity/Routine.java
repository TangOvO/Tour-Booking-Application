package AiLvYou.entity;

public class Routine {
    // 线路ID
    private long id;
    // 标题
    private String title;
    // 价格
    private double price;
    // 产品特色
    private String features;
    // 行程详情
    private String detail;
    // 预定须知
    private String notice;
    // 分类ID（分类用）
    private int siteID;
    // 预计招收人数
    private int totalClients;
    // 已招收人数
    private int actualClients;
    // 出行时间
    private String date;
    // 旅游类型（出境游、国内游、周边游）
    private String kind;
    // 旅游地点（具体地点名称）
    private String location;
    //出发地点
    private String departLocation;
    //超链接地址
    private String href;
    //图片1路径
    private String img1;
    //图片2路径
    private String img2;

    public String getDepartLocation() {
        return departLocation;
    }

    public void setDepartLocation(String departLocation) {
        this.departLocation = departLocation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public int getSiteID() {
        return siteID;
    }

    public void setSiteID(int siteID) {
        this.siteID = siteID;
    }

    public int getTotalClients() {
        return totalClients;
    }

    public void setTotalClients(int totalClients) {
        this.totalClients = totalClients;
    }

    public int getActualClients() {
        return actualClients;
    }

    public void setActualClients(int actualClients) {
        this.actualClients = actualClients;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    @Override
    public String toString() {
        return "Routine{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", features='" + features + '\'' +
                ", detail='" + detail + '\'' +
                ", notice='" + notice + '\'' +
                ", siteID=" + siteID +
                ", totalClients=" + totalClients +
                ", actualClients=" + actualClients +
                ", date='" + date + '\'' +
                ", kind='" + kind + '\'' +
                ", location='" + location + '\'' +
                ", departLocation='" + departLocation + '\'' +
                ", href='" + href + '\'' +
                ", img1='" + img1 + '\'' +
                ", img2='" + img2 + '\'' +
                '}';
    }
}
