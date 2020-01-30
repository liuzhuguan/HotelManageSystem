package hotelSys.service;

import hotelSys.bean.Food;

import java.util.List;

public interface FoodService {
    List<Food> find(String keyword, String foodTypeId);

    Food findById(int parseInt);

    void update(Food food);

    Food findByFoodName(String foodName);

    void save(Food food);
}
