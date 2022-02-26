package com.github.prgrms.orders;

import java.util.List;
import java.util.Optional;

import com.github.prgrms.configures.web.Pageable;

public interface OrderRepository {

  void update(OrderDto order);

  void updateReviewSeq(OrderDto order);
  
  Optional<Order> findById(long id);

  List<Order> findAll(Pageable pageable);
}