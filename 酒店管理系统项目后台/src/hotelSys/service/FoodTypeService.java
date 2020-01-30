package hotelSys.service;

import hotelSys.bean.FoodType;

import java.util.List;

public interface FoodTypeService {
    List<FoodType> find(String keyword, String disabled);

    FoodType findByFoodName(String foodTypeName);

    void save(FoodType foodType);

    FoodType findById(int parseInt);

    void update(FoodType foodType1);
}
