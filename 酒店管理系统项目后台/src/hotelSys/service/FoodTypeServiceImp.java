package hotelSys.service;

import hotelSys.bean.FoodType;
import hotelSys.dao.FoodTypeDao;
import hotelSys.dao.FoodTypeDaoImp;

import java.util.List;

public class FoodTypeServiceImp implements FoodTypeService {
    private FoodTypeDao foodTypeDao = new FoodTypeDaoImp();
    @Override
    public List<FoodType> find(String keyword, String disabled) {
        return foodTypeDao.find(keyword,disabled);
    }

    @Override
    public FoodType findByFoodName(String foodTypeName) {
        return foodTypeDao.findByFoodName(foodTypeName);
    }

    @Override
    public void save(FoodType foodType) {
        foodTypeDao.save(foodType);
    }

    @Override
    public FoodType findById(int id) {
        return foodTypeDao.findById(id);
    }

    @Override
    public void update(FoodType foodType1) {
        foodTypeDao.update(foodType1);
    }
}
