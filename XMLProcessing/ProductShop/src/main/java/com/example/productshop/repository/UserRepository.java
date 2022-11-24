package com.example.productshop.repository;

import java.util.List;
import java.util.Optional;


import com.example.productshop.model.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


Optional<List<User>> findAllByProductsSoldBuyerIsNotNullOrderByLastNameAscFirstNameAsc();

List<User> findAllByProductsSoldBuyerIsNotNullOrderByProductsSoldDescLastName();
}
