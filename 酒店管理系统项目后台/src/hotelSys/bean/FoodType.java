package hotelSys.bean;

import java.util.Date;

public class FoodType {
    private int id;
    private String typeName;//菜系名称
    private Date createDate;
    private Date updateDate;
    private Integer disabled;
    public int getId() {
        return id;
    }

    public FoodType(int id, String typeName, Date createDate, Date updateDate, Integer disabled) {
        this.id = id;
        this.typeName = typeName;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.disabled = disabled;
    }

    public FoodType() {
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getTypeName() {
        return typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getDisabled() {
        return disabled;
    }
    public void setDisabled(Integer disabled) {
        this.disabled = disabled;
    }

    public Date getUpdateDate() {
        return updateDate;
    }
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    @Override
    public String toString() {
        return "FoodType [id=" + id + ", typeName=" + typeName + ", createDate=" + createDate + ", updateDate="
                + updateDate + ", disabled=" + disabled + "]";
    }
}
