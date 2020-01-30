package hotelSys.service;

import hotelSys.bean.User;

public interface UserService {
    User findByLoginNameAndPass(String loginname, String password);

    void save(User user);
}
