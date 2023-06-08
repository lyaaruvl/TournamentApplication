package com.example.tournament.Dao;
import com.example.tournament.models.User;
import com.example.tournament.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserDao {
    private final UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(long id){
        return userRepository.findById(id).orElseThrow(()-> new IllegalStateException("User id " + id + " not found"));
    }

    public User findByEmail(String email){
        return  userRepository.findByEmail(email);
    }

    public User save(User user){
        return userRepository.save(user);
    }
}
