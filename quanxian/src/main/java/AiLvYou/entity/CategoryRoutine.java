package AiLvYou.entity;

public class CategoryRoutine {
    private int categoryID;
    private int routineID;

    @Override
    public String toString() {
        return "CategoryRoutine{" +
                "categoryID=" + categoryID +
                ", routineID=" + routineID +
                '}';
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getRoutineID() {
        return routineID;
    }

    public void setRoutineID(int routineID) {
        this.routineID = routineID;
    }
}
