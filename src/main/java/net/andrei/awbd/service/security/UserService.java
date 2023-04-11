package net.andrei.awbd.service.security;


import net.andrei.awbd.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
