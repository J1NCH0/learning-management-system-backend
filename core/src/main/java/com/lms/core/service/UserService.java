package com.lms.core.service;

import com.lms.core.dto.UserDto;
import com.lms.core.entity.User;
import com.lms.core.mapper.UserMapper;
import com.lms.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getUsers() {
        return userRepository.findAll().stream().map(UserMapper::mapToDto).toList();
    }

    public UserDto getUserById(Long userId) {
        return UserMapper.mapToDto(userRepository.findById(userId).orElseThrow());
    }

    public void createUser(UserDto userDto) {
        userRepository.save(UserMapper.mapToEntity(userDto));
    }

    public void updateUser(Long userId, UserDto userDto) {
        User user = userRepository.findById(userId).orElseThrow();

        userRepository.save(UserMapper.mapToEntity(userDto, user));
    }

    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }
}
