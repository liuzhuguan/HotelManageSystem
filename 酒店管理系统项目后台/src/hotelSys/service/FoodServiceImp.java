package hotelSys.service;

import hotelSys.bean.Food;
import hotelSys.dao.FoodDao;
import hotelSys.dao.FoodDaoImp;

import java.util.List;

public class FoodServiceImp implements FoodService {
    private FoodDao foodDao = new FoodDaoImp();
    @Override
    public List<Food> find(String keyword, String foodTypeId) {
        return foodDao.find(keyword,foodTypeId);
    }

    @Override
    public Food findById(int id) {
        return foodDao.findById(id);
    }

    @Override
    public void update(Food food) {
        foodDao.update(food);
    }

    @Override
    public Food findByFoodName(String foodName) {
        return foodDao.findByFoodName(foodName);
    }

    @Override
    public void save(Food food) {
        foodDao.save(food);
    }
}
