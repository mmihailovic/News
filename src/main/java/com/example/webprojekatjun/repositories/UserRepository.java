package com.example.webprojekatjun.repositories;


import com.example.webprojekatjun.entities.User;

import java.util.List;

public interface UserRepository {
    User addUser(User user);
    User findUser(String username);

    List<User> allUsers();
    boolean updateUser(String username, String newUsername, String ime,String prezime,String tip);
    boolean setStatus(String username,boolean active);
}
