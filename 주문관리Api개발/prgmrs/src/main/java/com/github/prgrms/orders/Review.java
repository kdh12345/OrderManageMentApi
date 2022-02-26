package com.github.prgrms.orders;

import java.time.LocalDateTime;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Review {

  private final Long seq;

  private Long userId;

  private Long productId;

  private String content;

  private final LocalDateTime createAt;

    public Review(Long seq, Long userId, Long productId, String content, LocalDateTime createAt) {
      this.seq = seq;
      this.userId = userId;
      this.productId = productId;
      this.content = content;
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
  
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
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
      .append("content", content)
      .append("createAt", createAt)
      .toString();
  }

  static public class Builder {
    
    private Long seq;
    private Long userId;
    private Long productId;
    private String content;
    private LocalDateTime createAt;

    public Builder() {/*empty*/}

    public Builder(Review review) {
      this.seq = review.seq;
      this.userId = review.userId;
      this.productId = review.productId;
      this.content = review.content;
      this.createAt = review.createAt;
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
    public Builder content(String content) {
      this.content = content;
      return this;
    }
    public Builder createAt(LocalDateTime createAt) {
      this.createAt = createAt;
      return this;
    }

    public Review build() {
      return new Review(
        seq,
        userId,
        productId,
        content,
        createAt
      );
    }
  }

}