package com.usersystem.controller;

import java.time.LocalDateTime;
import java.util.List;

import com.usersystem.service.SeedService;
import com.usersystem.service.UserService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ConsoleRunner implements CommandLineRunner {

    private final UserService userService;
    private final SeedService seedService;

    @Override
    public void run(String... args) throws Exception {
//        seedService.seedAll();
//        findAllUsersByEmailProvider("google");
        removeInactiveUsers(LocalDateTime.of(2022, 5 , 5 , 10 , 15, 00));
    }

    private void removeInactiveUsers(LocalDateTime dateTime) {
        System.out.println("Marked for deletion: " + userService.setForDeletionInactiveFrom(dateTime));
        userService.deleteMarkedForDeletion();
    }

    private void findAllUsersByEmailProvider(String provider) {
        List<String> resultList = userService.findAllUsersNamesAndEmailsByEmailProvider(provider);
        String notFound = "No users found with email domain " + provider;
        System.out.println(resultList.isEmpty() ? notFound : String.join(System.lineSeparator(), resultList));
    }
}
