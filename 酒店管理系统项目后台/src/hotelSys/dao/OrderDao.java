package hotelSys.dao;

import hotelSys.bean.Order;

import java.util.List;

public interface OrderDao {
    List<Order> find();

    Order findById(int id);

    void update(Order order);
}
