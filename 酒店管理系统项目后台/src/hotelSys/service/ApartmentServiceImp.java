package hotelSys.service;

import hotelSys.bean.Apartment;
import hotelSys.dao.ApartmentDao;
import hotelSys.dao.ApartmentDaoImp;

import java.util.List;

public class ApartmentServiceImp implements ApartmentService {
    private ApartmentDao apartmentDao = new ApartmentDaoImp();
    @Override
    public List<Apartment> find(String keyword, String apartmentStatus, String disabled) {
        return apartmentDao.find(keyword,apartmentStatus,disabled);
    }

    @Override
    public Apartment findById(int id) {
        return apartmentDao.findById(id);
    }

    @Override
    public void update(Apartment apartment) {
        apartmentDao.update(apartment);
    }

    @Override
    public Apartment findByApartmentName(String apartmentName) {
        return apartmentDao.findByApartmentName(apartmentName);
    }

    @Override
    public void save(Apartment apartment) {
        apartmentDao.save(apartment);
    }
}
