package hotelSys.dao;

import hotelSys.bean.User;

public interface UserDao {
    User findByLoginNameAndPass(String loginname, String password);

    void save(User user);
}
