package com.example.cardealer.repository;

import java.util.List;


import com.example.cardealer.model.dto.exportDto.CustomerWithSalesDto;
import com.example.cardealer.model.entity.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

  List<Customer> findAllByOrderByBirthDateAscIsYoungDriverDesc();

  @Query("select new com.example.cardealer.model.dto.exportDto.CustomerWithSalesDto( " +
    "c.name, count(s), avg(p.price * s.discount)) "+
    "from Customer c " +
    "join c.sales  s " +
    "join s.car ca " +
    "join ca.parts p " +
    "group by c " +
    "order by count(s) desc , avg(p.price * s.discount) desc")
  List<CustomerWithSalesDto> findCustomerWithSales();

}
