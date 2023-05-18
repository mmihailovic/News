package com.example.webprojekatjun.repositories;


import com.example.webprojekatjun.entities.User;

public interface UserRepository {
    User addUser(User user);
    User findUser(String username);
    boolean updateUser(String username, String newUsername, String ime,String prezime,String tip);
    boolean setStatus(String username,boolean active);
}
