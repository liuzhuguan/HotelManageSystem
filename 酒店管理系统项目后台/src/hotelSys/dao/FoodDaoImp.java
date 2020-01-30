package hotelSys.dao;

import hotelSys.bean.Apartment;
import hotelSys.bean.Food;
import hotelSys.bean.FoodType;
import hotelSys.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodDaoImp implements FoodDao {
    @Override
    public List<Food> find(String keyword, String foodTypeId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //①获取连接
            connection = JDBCUtils.getConnection();

            //②准备SQL
            StringBuffer sql = new StringBuffer("SELECT food.*,foodType.`type_name` FROM hotel_food food LEFT JOIN food_type foodType ON food.`foodType_id` = foodType.`id` WHERE 1=1");

            if (keyword != null  &&  !keyword.equals("")) {
                sql.append(" and food_name LIKE '%" + keyword + "%'");
            }
            if (foodTypeId != null  &&  !foodTypeId.equals("")) {
                sql.append(" AND foodType_id = " + foodTypeId);
            }
            //③ 获取集装箱
            preparedStatement = connection.prepareStatement(sql.toString());

            resultSet = preparedStatement.executeQuery();

            List<Food> foods = new ArrayList<>();
            while (resultSet.next()) {          //省略表头信息
                Food food = new Food();

                food.setId(resultSet.getInt("id"));
                food.setCreateDate(resultSet.getTimestamp("create_date"));
                food.setUpdateDate(resultSet.getTimestamp("update_date"));
                food.setDisabled(resultSet.getInt("disabled"));
                food.setDiscount(resultSet.getDouble("discount"));
                food.setFoodName(resultSet.getString("food_name"));
                food.setFoodTypeId(resultSet.getInt("foodType_id"));
                food.setPrice(resultSet.getDouble("price"));
                food.setImg(resultSet.getString("img"));
                food.setRemark(resultSet.getString("remark"));

                FoodType foodType = new FoodType();
                foodType.setTypeName(resultSet.getString("type_name"));
                food.setFoodType(foodType);

                foods.add(food);
            }
            return foods;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet,preparedStatement,connection);
        }

        return null;
    }

    @Override
    public Food findById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //①获取连接
            connection = JDBCUtils.getConnection();

            //②准备SQL
            String sql = "SELECT * FROM hotel_food WHERE id = ?";
            //③ 获取集装箱
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {          //省略表头信息
                Food food = new Food();

                food.setId(resultSet.getInt("id"));
                food.setCreateDate(resultSet.getTimestamp("create_date"));
                food.setUpdateDate(resultSet.getTimestamp("update_date"));
                food.setDisabled(resultSet.getInt("disabled"));
                food.setDiscount(resultSet.getDouble("discount"));
                food.setFoodName(resultSet.getString("food_name"));
                food.setFoodTypeId(resultSet.getInt("foodType_id"));
                food.setPrice(resultSet.getDouble("price"));
                food.setImg(resultSet.getString("img"));
                food.setRemark(resultSet.getString("remark"));

                return food;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet,preparedStatement,connection);
        }

        return null;
    }

    @Override
    public void update(Food food) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //①获取连接
            connection = JDBCUtils.getConnection();

            //②准备SQL
            String sql = "UPDATE hotel_food SET food_name = ?, foodType_id = ?, price = ?, discount = ?, remark = ?, img = ?, update_date = NOW(), disabled = ? WHERE id = ?;";
            //③ 获取集装箱
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,food.getFoodName());
            preparedStatement.setInt(2,food.getFoodTypeId());
            preparedStatement.setDouble(3,food.getPrice());
            preparedStatement.setDouble(4,food.getDiscount());
            preparedStatement.setString(5,food.getRemark());
            preparedStatement.setString(6,food.getImg());
            preparedStatement.setInt(7,food.getDisabled());
            preparedStatement.setInt(8,food.getId());

            int i = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet,preparedStatement,connection);
        }
    }

    @Override
    public Food findByFoodName(String foodName) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //①获取连接
            connection = JDBCUtils.getConnection();

            //②准备SQL
            String sql = "SELECT * FROM hotel_food WHERE food_name = ?";
            //③ 获取集装箱
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,foodName);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {          //省略表头信息
                Food food = new Food();

                food.setId(resultSet.getInt("id"));
                food.setCreateDate(resultSet.getTimestamp("create_date"));
                food.setUpdateDate(resultSet.getTimestamp("update_date"));
                food.setDisabled(resultSet.getInt("disabled"));
                food.setDiscount(resultSet.getDouble("discount"));
                food.setFoodName(resultSet.getString("food_name"));
                food.setFoodTypeId(resultSet.getInt("foodType_id"));
                food.setPrice(resultSet.getDouble("price"));
                food.setImg(resultSet.getString("img"));
                food.setRemark(resultSet.getString("remark"));

                return food;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet,preparedStatement,connection);
        }

        return null;
    }

    @Override
    public void save(Food food) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //①获取连接
            connection = JDBCUtils.getConnection();

            //②准备SQL
            String sql = "INSERT INTO hotel_food(food_name,foodType_id,price,discount,remark,img,create_date) VALUES(?,?,?,?,?,?,NOW())";
            //③ 获取集装箱
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,food.getFoodName());
            preparedStatement.setInt(2,food.getFoodTypeId());
            preparedStatement.setDouble(3,food.getPrice());
            preparedStatement.setDouble(4,food.getDiscount());
            preparedStatement.setString(5,food.getRemark());
            preparedStatement.setString(6,food.getImg());

            int i = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(preparedStatement,connection);
        }


    }
}
