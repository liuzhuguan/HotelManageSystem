package hotelSys.service;

import hotelSys.bean.Order;
import hotelSys.dao.OrderDao;
import hotelSys.dao.OrderDaoImp;

import java.util.List;

public class OrderServiceImp implements OrderService {
    private OrderDao orderDao = new OrderDaoImp();
    @Override
    public List<Order> find() {
        return orderDao.find();
    }

    @Override
    public Order findById(int id) {
        return orderDao.findById(id);
    }

    @Override
    public void update(Order order) {
        orderDao.update(order);
    }
}
