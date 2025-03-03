package com.example.uiuxtools.repository;

import com.example.uiuxtools.model.Role;
import com.example.uiuxtools.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    //Users -> type of Entity class
    //integer is the type of primary key

    // Custom query method to search for users by last name
    List<Users> findByLastnameContaining(String lastName);

    // Custom query method to search for user by id
    Users findByUserId(Integer userId);

    // Custom query method to search for users by email
    List<Users> findByEmailContaining(String email);

    // Custom query method to search for users by typeOfUser
    List<Users> findByTypeOfUser(Role typeOfUser);

    Optional<Users> findByEmail(String email);
}
