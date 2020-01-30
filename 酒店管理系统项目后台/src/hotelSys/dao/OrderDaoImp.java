package hotelSys.dao;

import hotelSys.bean.Apartment;
import hotelSys.bean.Food;
import hotelSys.bean.FoodType;
import hotelSys.bean.Order;
import hotelSys.util.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImp implements OrderDao {
    @Override
    public List<Order> find() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //①获取连接
            connection = JDBCUtils.getConnection();

            //②准备SQL
            String sql = "SELECT * FROM hotel_order LEFT JOIN bighotel_apartment ON hotel_order.`room_id` = bighotel_apartment.`id`";

            //③ 获取集装箱
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            List<Order> orders = new ArrayList<>();
            while(resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setOrderCode(resultSet.getString("order_code"));
                order.setRoomId(resultSet.getInt("room_id"));
                order.setTotalPrice(resultSet.getDouble("total_Price"));
                order.setDisabled(resultSet.getInt("disabled"));
                order.setOrderDate(resultSet.getTimestamp("order_Date"));
                order.setOrderStatus(resultSet.getInt("order_Status"));
                order.setPayDate(resultSet.getTimestamp("pay_date"));

                Apartment apartment = new Apartment();
                apartment.setApartment_name(resultSet.getString("apartment_name"));
                order.setApartment(apartment);

                orders.add(order);
            }
            return  orders;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet,preparedStatement,connection);
        }

        return null;
    }

    @Override
    public Order findById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //①获取连接
            connection = JDBCUtils.getConnection();

            //②准备SQL
            String sql = "SELECT * FROM hotel_order LEFT JOIN bighotel_apartment ON hotel_order.`room_id` = bighotel_apartment.`id` WHERE hotel_order.`id` =?";

            //③ 获取集装箱
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);

            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setOrderCode(resultSet.getString("order_code"));
                order.setRoomId(resultSet.getInt("room_id"));
                order.setTotalPrice(resultSet.getDouble("total_Price"));
                order.setDisabled(resultSet.getInt("disabled"));
                order.setOrderDate(resultSet.getTimestamp("order_Date"));
                order.setOrderStatus(resultSet.getInt("order_Status"));
                order.setPayDate(resultSet.getTimestamp("pay_date"));

                Apartment apartment = new Apartment();
                apartment.setApartment_name(resultSet.getString("apartment_name"));
                order.setApartment(apartment);

                return order;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet,preparedStatement,connection);
        }

        return null;
    }

    @Override
    public void update(Order order) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //①获取连接
            connection = JDBCUtils.getConnection();

            //②准备SQL
            String sql = "UPDATE hotel_order SET order_code = ?, room_id = ?, total_Price = ?, order_Status = ?, order_Date = ?, pay_date = ?, disabled = ? WHERE id = ?";


            Date create_date = order.getOrderDate() != null ? new Date(order.getOrderDate().getTime()) : null;
            Date pay_date = order.getPayDate() != null ? new Date(order.getPayDate().getTime()) : null;
            //③ 获取集装箱
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,order.getOrderCode());
            preparedStatement.setInt(2,order.getRoomId());
            preparedStatement.setDouble(3,order.getTotalPrice());
            preparedStatement.setInt(4,order.getOrderStatus());
            preparedStatement.setDate(5,create_date);
            preparedStatement.setDate(6,pay_date);
            preparedStatement.setInt(7,order.getDisabled());
            preparedStatement.setInt(8,order.getId());

            int i = preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(preparedStatement,connection);
        }
    }
}
