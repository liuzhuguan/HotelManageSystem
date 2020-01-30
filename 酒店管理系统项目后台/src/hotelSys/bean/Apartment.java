package hotelSys.bean;

import java.util.Date;

public class Apartment {
    private Integer id;
    private String apartment_name;
    private Integer apartment_id;   //房间类型
    private Integer apartment_status;
    private double price;
    private double discount;
    private String remark;
    private Date create_date;
    private Date update_date;
    private Integer disabled;
    private String img;

    public Apartment() {
    }

    public Apartment(Integer id, String apartment_name, Integer apartment_id, Integer apartment_status, double price, double discount, String remark, Date create_date, Date update_date, Integer disabled, String img) {
        this.id = id;
        this.apartment_name = apartment_name;
        this.apartment_id = apartment_id;
        this.apartment_status = apartment_status;
        this.price = price;
        this.discount = discount;
        this.remark = remark;
        this.create_date = create_date;
        this.update_date = update_date;
        this.disabled = disabled;
        this.img = img;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApartment_name() {
        return apartment_name;
    }

    public void setApartment_name(String apartment_name) {
        this.apartment_name = apartment_name;
    }

    public Integer getApartment_id() {
        return apartment_id;
    }

    public void setApartment_id(Integer apartment_id) {
        this.apartment_id = apartment_id;
    }

    public Integer getApartment_status() {
        return apartment_status;
    }

    public void setApartment_status(Integer apartment_status) {
        this.apartment_status = apartment_status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }

    public Integer getDisabled() {
        return disabled;
    }

    public void setDisabled(Integer disabled) {
        this.disabled = disabled;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "id=" + id +
                ", apartment_name='" + apartment_name + '\'' +
                ", apartment_id=" + apartment_id +
                ", apartment_status=" + apartment_status +
                ", price=" + price +
                ", discount=" + discount +
                ", remark='" + remark + '\'' +
                ", create_date=" + create_date +
                ", update_date=" + update_date +
                ", disabled=" + disabled +
                ", img='" + img + '\'' +
                '}';
    }
}
