package com.idolconsultingfirm.service;

import com.idolconsultingfirm.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private final Map<String, User> userDatabase = new HashMap<>();

    @Override
    public User addUser(User user) {
        userDatabase.put(user.getUserId(), user);
        return user;
    }

    @Override
    public User updateUser(String userId, User user) {
        return userDatabase.replace(userId, user);
    }

    @Override
    public User getUser(String userId) {
        return userDatabase.get(userId);
    }

    @Override
    public void deleteUser(String userId) {
        userDatabase.remove(userId);
    }
}
