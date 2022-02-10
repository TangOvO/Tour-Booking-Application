package AiLvYou.entity;

public class RoutineFeatureImg {
    private int routineID;

    private int no;

    private String img;

    public int getRoutineID() {
        return routineID;
    }

    public void setRoutineID(int routineID) {
        this.routineID = routineID;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "RoutineFeatureImg{" +
                "routineID=" + routineID +
                ", no=" + no +
                ", img='" + img + '\'' +
                '}';
    }
}
