package hotelSys.dao;

import hotelSys.bean.Apartment;
import hotelSys.bean.FoodType;
import hotelSys.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodTypeDaoImp implements FoodTypeDao {
    @Override
    public List<FoodType> find(String keyword, String disabled) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //①获取连接
            connection = JDBCUtils.getConnection();

            //②准备SQL
            StringBuffer sql = new StringBuffer("SELECT * FROM food_type WHERE 1=1 ");

            if (keyword != null  &&  !keyword.equals("")) {
                sql.append(" and type_name like '%" + keyword + "%'");
            }
            if (disabled != null  &&  !disabled.equals("")) {
                sql.append(" and disabled = "+ disabled);
            }

            //③ 获取集装箱
            preparedStatement = connection.prepareStatement(sql.toString());

            resultSet = preparedStatement.executeQuery();

            List<FoodType> foodTypes = new ArrayList<>();
            while (resultSet.next()) {          //省略表头信息
                FoodType foodType = new FoodType();

                foodType.setId(resultSet.getInt("id"));
                foodType.setTypeName(resultSet.getString("type_name"));
                foodType.setCreateDate(resultSet.getTimestamp("create_date"));
                foodType.setUpdateDate(resultSet.getTimestamp("update_date"));
                foodType.setDisabled(resultSet.getInt("disabled"));

                foodTypes.add(foodType);
            }
                return foodTypes;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet,preparedStatement,connection);
        }
        return null;
    }

    @Override
    public FoodType findByFoodName(String foodTypeName) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //①获取连接
            connection = JDBCUtils.getConnection();

            //②准备SQL
            String sql = "SELECT * FROM food_type WHERE type_name = ?";

            //③ 获取集装箱
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,foodTypeName);

            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {          //省略表头信息
                FoodType foodType = new FoodType();

                foodType.setId(resultSet.getInt("id"));
                foodType.setTypeName(resultSet.getString("type_name"));
                foodType.setCreateDate(resultSet.getTimestamp("create_date"));
                foodType.setUpdateDate(resultSet.getTimestamp("update_date"));
                foodType.setDisabled(resultSet.getInt("disabled"));

                return foodType;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet,preparedStatement,connection);
        }

        return null;
    }

    @Override
    public void save(FoodType foodType) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //①获取连接
            connection = JDBCUtils.getConnection();

            //②准备SQL
            String sql = "INSERT INTO food_type(type_name,create_date) VALUES(?,NOW())";

            //③ 获取集装箱
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,foodType.getTypeName());

            int i = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(preparedStatement,connection);
        }
    }

    @Override
    public FoodType findById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //①获取连接
            connection = JDBCUtils.getConnection();

            //②准备SQL
            String sql = "SELECT * FROM food_type WHERE id = ?";

            //③ 获取集装箱
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);

            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {          //省略表头信息
                FoodType foodType = new FoodType();

                foodType.setId(resultSet.getInt("id"));
                foodType.setTypeName(resultSet.getString("type_name"));
                foodType.setCreateDate(resultSet.getTimestamp("create_date"));
                foodType.setUpdateDate(resultSet.getTimestamp("update_date"));
                foodType.setDisabled(resultSet.getInt("disabled"));

                return foodType;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet,preparedStatement,connection);
        }

        return null;
    }

    @Override
    public void update(FoodType foodType1) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //①获取连接
            connection = JDBCUtils.getConnection();

            //②准备SQL
            String sql = "UPDATE food_type SET type_name = ?, update_date = NOW(), disabled = ? WHERE id = ?";

            //③ 获取集装箱
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,foodType1.getTypeName());
            preparedStatement.setInt(2,foodType1.getDisabled());
            preparedStatement.setInt(3,foodType1.getId());


            int i = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(preparedStatement,connection);
        }
    }
}
