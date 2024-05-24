package com.idolconsultingfirm.repository;

import com.idolconsultingfirm.model.User;

public interface UserRepository {
    User save(User user);
    User findById(String userId);
    void deleteById(String userId);
}
