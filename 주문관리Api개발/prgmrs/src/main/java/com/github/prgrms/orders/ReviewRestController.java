package com.github.prgrms.orders;

import static com.github.prgrms.utils.ApiUtils.success;
import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.ObjectUtils.allNull;

import com.github.prgrms.utils.ApiUtils.ApiResult;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/orders")
public class ReviewRestController {
  private final OrderService orderService; 

  public ReviewRestController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping(path = "{id}/review")
  public ApiResult<ReviewDto> review(@PathVariable Long id, @RequestBody(required = false) ReviewRequest reviewRequest) {

    checkArgument(!allNull(reviewRequest), "content must be provided");

    return success(
      orderService.review(id, reviewRequest.getContent())
    );
  }
}