package com.coiffure.service;

import com.coiffure.model.User;
import java.util.List;

public interface UserService {
    public void saveUser(User user);
    public List<Object> isUserPresent(User user);
}
