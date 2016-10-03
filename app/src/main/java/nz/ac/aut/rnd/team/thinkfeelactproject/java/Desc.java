package nz.ac.aut.rnd.team.thinkfeelactproject.java;

/**
 * Created by Aida on 28/09/16.
 */

public class Desc {
    private String desc;
    private String type;
    private float selectedValue;
    private int num;
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Desc() {

    }

    public Desc(String type) {
        this.type = type;
    }

    public Desc(int num) {
        this.num = num;
    }

    public Desc(String desc, String type, float selectedValue, int num) {
        this.desc = desc;
        this.type = type;
        this.selectedValue = selectedValue;
        this.num = num;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public float getSelectedValue() {
        return selectedValue;
    }

    public void setSelectedValue(float selectedValue) {
        this.selectedValue = selectedValue;
    }
}
