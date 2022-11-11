package com.usersystem.service;

import java.time.LocalDateTime;
import java.util.List;

public interface UserService {
  List<String> findAllUsersNamesAndEmailsByEmailProvider(String provider);

  Integer setForDeletionInactiveFrom(LocalDateTime dateTime);

  void deleteMarkedForDeletion();
}
