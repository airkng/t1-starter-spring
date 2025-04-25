package edu.t1.service;

import edu.t1.aspect.annotation.Loggable;
import edu.t1.dao.UserDao;
import edu.t1.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;

    public User createUser(String name, String pass) {
        userDao.findByName(name);
        return new User(name, pass);
    }
    @Loggable
    public User getUserByName(String name) {
        return new User(name, "123");
    }
}
