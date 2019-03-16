package jblog.service;

import jblog.repository.UserDao;
import jblog.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDaoImpl;

    @Override
    public boolean join(UserVo vo) {
        return userDaoImpl.insert(vo) == 1;
    }

    @Override
    public UserVo getUser(String id) {
        System.out.println("서비스 아이디 체크");
        System.out.println(id);
        return userDaoImpl.selectUser(id);
    }

    @Override
    public UserVo getUser(String id, String password) {
        System.out.println("서비스");
        return userDaoImpl.selectUser(id, password);
    }
}
