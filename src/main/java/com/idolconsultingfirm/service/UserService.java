package com.idolconsultingfirm.service;

import com.idolconsultingfirm.model.User;

public interface UserService {
    User addUser(User user);
    User updateUser(String userId, User user);
    User getUser(String userId);
    void deleteUser(String userId);
}
