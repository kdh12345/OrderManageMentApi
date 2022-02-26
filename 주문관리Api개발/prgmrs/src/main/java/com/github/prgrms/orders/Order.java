package com.github.prgrms.orders;

import java.time.LocalDateTime;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Order {

  private final Long seq;

  private Long userId;

  private Long productId;

  private Long reviewId;

  private Review review;

  private State state;

  private String requestMessage;

  private String rejectMessage;

  private LocalDateTime completedAt;

  private LocalDateTime rejectedAt;

  private final LocalDateTime createAt;

    public Order(Long seq,
    Long userId,
    Long productId,
    Long reviewId,
    State state,
    String requestMessage,
    String rejectMessage,
    LocalDateTime completedAt,
    LocalDateTime rejectedAt,
    LocalDateTime createAt){
    
    this.seq = seq;
    this.userId = userId;
    this.productId = productId;
    this.reviewId = reviewId;
    this.state = state;
    this.requestMessage = requestMessage;
    this.rejectMessage = rejectMessage;
    this.completedAt = completedAt;
    this.rejectedAt = rejectedAt;
    this.createAt = createAt;

  }

  public Long getSeq() {
    return seq;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public Review getReview() {
    return review;
  }

  public void setReview(Review review) {
    this.review = review;
  }

  public Long getReviewId() {
    return reviewId;
  }

  public void setReviewId(Long reviewId) {
    this.reviewId = reviewId;
  }
  
  public State getState() {
    return state;
  }

  public void setState(State state) {
    this.state = state;
  }
  
  public String getRequestMessage() {
    return requestMessage;
  }

  public void setRequestMessage(String requestMessage) {
    this.requestMessage = requestMessage;
  }
  
  public String getRejectMessage() {
    return rejectMessage;
  }

  public void setRejectMessage(String rejectMessage) {
    this.rejectMessage = rejectMessage;
  }

  public LocalDateTime getCompletedAt() {
    return completedAt;
  }

  public void setCompletedAt(LocalDateTime completedAt) {
    this.completedAt = completedAt;
  }

  public LocalDateTime getRejectedAt() {
    return rejectedAt;
  }

  public void getRejectedAt(LocalDateTime rejectedAt) {
    this.rejectedAt = rejectedAt;
  }

  public LocalDateTime getCreateAt() {
    return createAt;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
      .append("seq", seq)
      .append("userId", userId)
      .append("productId", productId)
      .append("reviewId", reviewId)
      .append("review", review.toString())
      .append("state", state)
      .append("requestMessage", requestMessage)
      .append("rejectMessage", rejectMessage)
      .append("completedAt", completedAt)
      .append("rejectedAt", rejectedAt)
      .append("createAt", createAt)
      .toString();
  }

  static public class Builder {
    
    private Long seq;
    private Long userId;
    private Long productId;
    private Long reviewId;
    private State state;
    private String requestMessage;
    private String rejectMessage;
    private LocalDateTime completedAt;
    private LocalDateTime rejectedAt;
    private LocalDateTime createAt;

    public Builder() {/*empty*/}

    public Builder(Order order) {
      this.seq = order.seq;
      this.userId = order.userId;
      this.productId = order.productId;
      this.reviewId = order.reviewId;
      this.state = order.state;
      this.requestMessage = order.requestMessage;
      this.rejectMessage = order.rejectMessage;
      this.completedAt = order.completedAt;
      this.rejectedAt = order.rejectedAt;
      this.createAt = order.createAt;
    }

    public Builder seq(Long seq) {
      this.seq = seq;
      return this;
    }
    public Builder userId(Long userId) {
      this.userId = userId;
      return this;
    }
    public Builder productId(Long productId) {
      this.productId = productId;
      return this;
    }
    public Builder reviewId(Long reviewId) {
      this.reviewId = reviewId;
      return this;
    }
    public Builder state(State state) {
      this.state = state;
      return this;
    }
    public Builder requestMessage(String requestMessage) {
      this.requestMessage = requestMessage;
      return this;
    }
    public Builder rejectMessage(String rejectMessage) {
      this.rejectMessage = rejectMessage;
      return this;
    }
    public Builder completedAt(LocalDateTime completedAt) {
      this.completedAt = completedAt;
      return this;
    }
    public Builder rejectedAt(LocalDateTime rejectedAt) {
      this.rejectedAt = rejectedAt;
      return this;
    }
    public Builder createAt(LocalDateTime createAt) {
      this.createAt = createAt;
      return this;
    }


    public Order build() {
      return new Order(
        seq,
        userId,
        productId,
        reviewId,
        state,
        requestMessage,
        rejectMessage,
        completedAt,
        rejectedAt,
        createAt
      );
    }
  }
}