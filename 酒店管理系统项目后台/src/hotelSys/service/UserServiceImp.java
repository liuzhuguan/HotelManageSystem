package hotelSys.service;

import hotelSys.bean.User;
import hotelSys.dao.UserDao;
import hotelSys.dao.UserDaoImp;

public class UserServiceImp implements UserService {
    private UserDao userDao = new UserDaoImp();
    @Override
    public User findByLoginNameAndPass(String loginname, String password) {
        return userDao.findByLoginNameAndPass(loginname,password);

    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }
}
