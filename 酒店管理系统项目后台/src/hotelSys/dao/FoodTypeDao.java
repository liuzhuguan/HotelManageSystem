package hotelSys.dao;

import hotelSys.bean.FoodType;

import java.util.List;

public interface FoodTypeDao {
    List<FoodType> find(String keyword, String disabled);

    FoodType findByFoodName(String foodTypeName);

    void save(FoodType foodType);

    FoodType findById(int id);

    void update(FoodType foodType1);
}
