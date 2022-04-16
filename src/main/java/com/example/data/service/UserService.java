package com.example.data.service;

import com.example.data.dto.UserDTO;
import com.example.data.model.User;
import com.example.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository repository) {
        this.userRepository = repository;
    }

    public Optional<User> get(int id) {
        return userRepository.findById(id);
    }

    public User update(UserDTO entity) {
        return userRepository.save(convertToUser(entity));
    }

    public void delete(int id) {
        userRepository.deleteById(id);
    }

    public Page<User> list(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public int count() {
        return (int) userRepository.count();
    }

    public boolean checkNotExistUsername(String username) {
        return userRepository.findByUsername(username) == null;
    }

    public boolean checkNotExistEmail(String email) {
        return userRepository.findUserByEmail(email) == null;
    }

    private User convertToUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setFirstname(userDTO.getFirstName());
        user.setLastname(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRoles(userDTO.getRoles());
        return user;
    }

    protected static UserDTO convertToWorkoutDTO(User user) {
        return new UserDTO(user.getId(),
                user.getUsername(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getPassword());
    }

    public UserDTO getDTO(int id) {
        return convertToWorkoutDTO(get(id).orElse(new User()));
    }


}
