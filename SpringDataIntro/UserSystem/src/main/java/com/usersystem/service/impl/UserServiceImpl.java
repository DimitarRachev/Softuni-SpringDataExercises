package com.usersystem.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.usersystem.model.entity.User;
import com.usersystem.repository.UserRepository;
import com.usersystem.service.UserService;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<String> findAllUsersNamesAndEmailsByEmailProvider(String provider) {
        return userRepository
                .findAllByEmailContains(provider)
                .stream()
                .map(u -> u.getUsername() + " " + u.getEmail())
                .collect(Collectors.toList());
    }

    @Override
    public Integer setForDeletionInactiveFrom(LocalDateTime dateTime) {
        List<User> users = userRepository.findAllByLastTimeLoggedInBefore(dateTime);
        users.stream()
                .peek(u -> u.setDeleted(false))
                .forEach(userRepository::save);
        return users.size();
    }

    @Override
    public void deleteMarkedForDeletion() {
        userRepository.deleteAllByIsDeletedIsTrue();
    }
}
