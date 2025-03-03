package com.example.uiuxtools.service;

import com.example.uiuxtools.model.Role;
import com.example.uiuxtools.model.Users;
import com.example.uiuxtools.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {
    private final UsersRepository usersRepository;

    // Constructor-based Dependency Injection
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    // Fetch all users
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    // Save or update a user
    public Users saveUser(Users users) {
        return usersRepository.save(users);
    }

    // Search tools by last name
    public List<Users> searchUserByLastName(String lastName) {
        return usersRepository.findByLastnameContaining(lastName);
    }

    // Search user by user id
    //    public List<Users> searchUserByUserId(Integer userId) {
    //        return usersRepository.findByUserId(userId);
    //    }

    // Search user by email
    public List<Users> searchUserByEmail(String email) {
        return usersRepository.findByEmailContaining(email);
    }

    // Search user by type of user
    public List<Users> searchUserByTypeOfUser(Role typeOfUser) {
        return usersRepository.findByTypeOfUser(typeOfUser);
    }

    // Get user by user id
    public Users getUserByUserId(Integer userId) {
        return usersRepository.findByUserId(userId);
    }

    // Get user by email
//    public Users getUserByEmail(String email) {
//        return usersRepository.findByEmail(email);
//    }
}
