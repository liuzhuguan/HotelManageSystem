package hotelSys.dao;

import hotelSys.bean.Apartment;
import hotelSys.util.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApartmentDaoImp implements ApartmentDao {
    @Override
    public List<Apartment> find(String keyword, String apartmentStatus, String disabled) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //①获取连接
            connection = JDBCUtils.getConnection();

            //②准备SQL
            StringBuffer sql = new StringBuffer("SELECT * FROM bighotel_apartment where 1=1 " );

            if(keyword != null && !keyword.equals("")) {
                sql.append(" and apartment_name like '%"+ keyword +"%' ");
            }
            if(apartmentStatus != null && !apartmentStatus.equals("")) {
                sql.append(" and apartment_status = "+ apartmentStatus);
            }
            if(disabled != null && !disabled.equals("")) {
                sql.append(" and disabled = "+ disabled);
            }

            //③ 获取集装箱
            preparedStatement = connection.prepareStatement(sql.toString());

            resultSet = preparedStatement.executeQuery();

            List<Apartment> apartments = new ArrayList<>();
            while (resultSet.next()) {          //省略表头信息
                Apartment apartment = new Apartment();

                apartment.setId(resultSet.getInt("id"));
                apartment.setApartment_name(resultSet.getString("apartment_name"));
                apartment.setApartment_status(resultSet.getInt("apartment_status"));
                apartment.setApartment_id(resultSet.getInt("apartment_id"));
                apartment.setPrice(resultSet.getDouble("price"));
                apartment.setDiscount(resultSet.getDouble("discount"));
                apartment.setRemark(resultSet.getString("remark"));
                apartment.setDisabled(resultSet.getInt("disabled"));
                apartment.setCreate_date(resultSet.getTimestamp("create_date"));
                apartment.setUpdate_date(resultSet.getTimestamp("update_date"));

                apartments.add(apartment);
            }
            return apartments;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet,preparedStatement,connection);
        }

        return null;
    }

    @Override
    public Apartment findById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //①获取连接
            connection = JDBCUtils.getConnection();

            //②准备SQL
            String sql = "SELECT * FROM bighotel_apartment WHERE id = ?";

            //③ 获取集装箱
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {          //省略表头信息
                Apartment apartment = new Apartment();

                apartment.setId(resultSet.getInt("id"));
                apartment.setApartment_name(resultSet.getString("apartment_name"));
                apartment.setApartment_status(resultSet.getInt("apartment_status"));
                apartment.setApartment_id(resultSet.getInt("apartment_id"));
                apartment.setPrice(resultSet.getDouble("price"));
                apartment.setDiscount(resultSet.getDouble("discount"));
                apartment.setRemark(resultSet.getString("remark"));
                apartment.setDisabled(resultSet.getInt("disabled"));
                apartment.setCreate_date(resultSet.getTimestamp("create_date"));
                apartment.setUpdate_date(resultSet.getTimestamp("update_date"));
                apartment.setImg(resultSet.getString("img"));

                return apartment;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet,preparedStatement,connection);
        }

        return null;
    }

    @Override
    public void update(Apartment apartment) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //①获取连接
            connection = JDBCUtils.getConnection();

            //②准备SQL
            String sql = "UPDATE bighotel_apartment SET apartment_status = ?, apartment_name =?,apartment_id=? ,price =? ,discount= ?, remark = ?, img =? ,create_date =? ,update_date =? ,disabled=? WHERE id = ?";

            Date create_date = apartment.getCreate_date() != null ? new Date(apartment.getCreate_date().getTime()) : null;
            Date update_date = apartment.getUpdate_date() != null ? new Date(apartment.getUpdate_date().getTime()) : null;

            //③ 获取集装箱
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,apartment.getApartment_status());
            preparedStatement.setString(2,apartment.getApartment_name());
            preparedStatement.setInt(3,apartment.getApartment_id());
            preparedStatement.setDouble(4,apartment.getPrice());
            preparedStatement.setDouble(5,apartment.getDiscount());
            preparedStatement.setString(6,apartment.getRemark());
            preparedStatement.setString(7,apartment.getImg());
            preparedStatement.setDate(8,create_date);
            preparedStatement.setDate(9,update_date);
            preparedStatement.setInt(10,apartment.getDisabled());
            preparedStatement.setInt(11,apartment.getId());

            int i = preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet,preparedStatement,connection);
        }
    }

    @Override
    public Apartment findByApartmentName(String apartmentName) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //①获取连接
            connection = JDBCUtils.getConnection();

            //②准备SQL
            String sql = "SELECT * FROM bighotel_apartment WHERE apartment_name = ?";

            //③ 获取集装箱
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,apartmentName);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {          //省略表头信息
                Apartment apartment = new Apartment();

                apartment.setId(resultSet.getInt("id"));
                apartment.setApartment_name(resultSet.getString("apartment_name"));
                apartment.setApartment_status(resultSet.getInt("apartment_status"));
                apartment.setApartment_id(resultSet.getInt("apartment_id"));
                apartment.setPrice(resultSet.getDouble("price"));
                apartment.setDiscount(resultSet.getDouble("discount"));
                apartment.setRemark(resultSet.getString("remark"));
                apartment.setDisabled(resultSet.getInt("disabled"));
                apartment.setCreate_date(resultSet.getTimestamp("create_date"));
                apartment.setUpdate_date(resultSet.getTimestamp("update_date"));
                apartment.setImg(resultSet.getString("img"));

                return apartment;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet,preparedStatement,connection);
        }

        return null;
    }

    @Override
    public void save(Apartment apartment) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //①获取连接
            connection = JDBCUtils.getConnection();

            //②准备SQL
            String sql = "INSERT INTO bighotel_apartment(apartment_name,apartment_status,create_date) VALUES(?,0,NOW())";

            //③ 获取集装箱
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,apartment.getApartment_name());

            int i = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet,preparedStatement,connection);
        }

    }
}
