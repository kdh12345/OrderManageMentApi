package com.github.prgrms.orders;

import java.util.Optional;

public interface ReviewRepository {

  Optional<Review> findById(long id);
  long save(Review review);
}