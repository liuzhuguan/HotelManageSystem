package hotelSys.service;

import hotelSys.bean.Apartment;

import java.util.List;

public interface ApartmentService {
    List<Apartment> find(String keyword, String apartmentStatus, String disabled);

    Apartment findById(int id);

    void update(Apartment apartment);

    Apartment findByApartmentName(String apartmentName);

    void save(Apartment apartment);
}
