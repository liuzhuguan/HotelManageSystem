package hotelSys.service;

import hotelSys.bean.Order;

import java.util.List;

public interface OrderService {
    List<Order> find();

    Order findById(int parseInt);

    void update(Order order);
}
