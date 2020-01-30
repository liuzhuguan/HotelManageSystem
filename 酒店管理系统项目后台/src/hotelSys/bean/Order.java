package hotelSys.bean;

import java.util.Date;
import java.util.List;

public class Order {
    private Integer id;
    private String orderCode;//订单编号
    private Integer roomId;//订单是那一餐桌的
    private Double totalPrice;//订单总金额
    private Integer orderStatus;//订单的状态 0表示未付款  1表示已付款
    private Date orderDate;//下单，提交购物车的世界
    private Date payDate;//付款的时间
    private Date updateDate;//最后更新的时间，菜品信息更改的时间
    private Integer disabled;


    private Apartment apartment;
    private List<OrderDetail> orderDetails;

    public Order() {
    }

    public Order(Integer id, String orderCode, Integer roomId, Double totalPrice, Integer orderStatus, Date orderDate, Date payDate, Date updateDate, Integer disabled, Apartment apartment, List<OrderDetail> orderDetails) {
        this.id = id;
        this.orderCode = orderCode;
        this.roomId = roomId;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
        this.payDate = payDate;
        this.updateDate = updateDate;
        this.disabled = disabled;
        this.apartment = apartment;
        this.orderDetails = orderDetails;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getDisabled() {
        return disabled;
    }

    public void setDisabled(Integer disabled) {
        this.disabled = disabled;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderCode='" + orderCode + '\'' +
                ", roomId=" + roomId +
                ", totalPrice=" + totalPrice +
                ", orderStatus=" + orderStatus +
                ", orderDate=" + orderDate +
                ", payDate=" + payDate +
                ", updateDate=" + updateDate +
                ", disabled=" + disabled +
                ", apartment=" + apartment +
                ", orderDetails=" + orderDetails +
                '}';
    }
}
