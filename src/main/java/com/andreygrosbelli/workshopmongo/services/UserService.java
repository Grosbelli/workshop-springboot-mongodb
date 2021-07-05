package com.andreygrosbelli.workshopmongo.services;

import com.andreygrosbelli.workshopmongo.domain.User;
import com.andreygrosbelli.workshopmongo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public List<User> findAll() {
        return repo.findAll();
    }
}
