package com.github.prgrms.orders;

import static com.github.prgrms.utils.DateTimeUtils.dateTimeOf;
import static java.util.Optional.ofNullable;

import java.util.List;
import java.util.Optional;

import com.github.prgrms.configures.web.Pageable;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcOrderRepository implements OrderRepository{

  private final JdbcTemplate jdbcTemplate;

  public JdbcOrderRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public void update(OrderDto order) {
    jdbcTemplate.update(
      "UPDATE orders SET state=?, reject_msg=?, rejected_at=?, completed_at=? WHERE seq=?",
      order.getState().toString(),
      order.getRejectMessage(),
      order.getRejectedAt(),
      order.getCompletedAt(),
      order.getSeq()
    );
  }

  @Override
  public void updateReviewSeq(OrderDto order) {
    jdbcTemplate.update(
      "UPDATE orders SET review_seq=? WHERE seq=?",
      order.getReviewId(),
      order.getSeq()
    );
  }

  @Override
  public List<Order> findAll(Pageable pageable) {

    String query = "SELECT * FROM orders ORDER BY seq DESC "
    + " LIMIT " + pageable.getSize() + " OFFSET " + pageable.getOffset();

    return jdbcTemplate.query(
      query,
      mapper
    );
  }

  @Override
  public Optional<Order> findById(long id){
    List<Order> results = jdbcTemplate.query(
      "SELECT * FROM orders WHERE seq=?",
      mapper,
      id
    );


    return ofNullable(results.isEmpty() ? null : results.get(0));
  }

  static RowMapper<Order> mapper = (rs, rowNum) ->
    new Order.Builder()
      .seq(rs.getLong("seq"))
      .userId(rs.getLong("user_seq"))
      .productId(rs.getLong("product_seq"))
      .reviewId(rs.getLong("review_seq"))
      .state(State.valueOf(rs.getString("state")))
      .requestMessage(rs.getString("request_msg"))
      .rejectMessage(rs.getString("reject_msg"))
      .completedAt(dateTimeOf(rs.getTimestamp("completed_at")))
      .rejectedAt(dateTimeOf(rs.getTimestamp("rejected_at")))
      .createAt(dateTimeOf(rs.getTimestamp("create_at")))
      .build();

}