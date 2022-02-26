package com.github.prgrms.orders;

import static com.github.prgrms.utils.DateTimeUtils.dateTimeOf;
import static java.util.Optional.ofNullable;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcReviewRepository implements ReviewRepository{

  private final JdbcTemplate jdbcTemplate;

  public JdbcReviewRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public long save(Review review) {
    jdbcTemplate.update(
      "insert into reviews (user_seq, product_seq, content) values(?, ?, ?)",
      review.getUserId(),
      review.getProductId(),
      review.getContent()
    );

    String insertSql = "insert into reviews (user_seq, product_seq, content) values(?, ?, ?)";

    KeyHolder keyHolder = new GeneratedKeyHolder();

    jdbcTemplate.update(connection -> {
        PreparedStatement ps = connection
          .prepareStatement(insertSql, new String[] { "seq" });
        
        ps.setLong(1, review.getUserId());
        ps.setLong(2, review.getProductId());
        ps.setString(3, review.getContent());
        
        return ps;
      }, keyHolder);

    System.out.println(keyHolder.getKey());
    long id = keyHolder.getKey().longValue();

    return id;

  }

  @Override
  public Optional<Review> findById(long id){
    List<Review> results = jdbcTemplate.query(
      "SELECT * FROM reviews WHERE seq=?",
      mapper,
      id
    );
    return ofNullable(results.isEmpty() ? null : results.get(0));
  }

  static RowMapper<Review> mapper = (rs, rowNum) ->
    new Review.Builder()
      .seq(rs.getLong("seq"))
      .userId(rs.getLong("user_seq"))
      .productId(rs.getLong("product_seq"))
      .content(rs.getString("content"))
      .createAt(dateTimeOf(rs.getTimestamp("create_at")))
      .build();

}