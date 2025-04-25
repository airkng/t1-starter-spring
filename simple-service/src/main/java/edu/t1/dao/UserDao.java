package edu.t1.dao;

import edu.t1.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public class UserDao {
    private List<User> users = new ArrayList<>();
    public Optional<User> findByName(String name) {
        return Optional.of(users.get(1));
    }
}
