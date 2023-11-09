package com.ecommerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.model.OrderHistory;

public interface OrderHistoryRepo extends JpaRepository<OrderHistory, Integer>{

}
