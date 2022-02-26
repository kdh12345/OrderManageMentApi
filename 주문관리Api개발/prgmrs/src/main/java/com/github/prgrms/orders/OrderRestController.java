package com.github.prgrms.orders;

import static com.github.prgrms.utils.ApiUtils.success;
import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.ObjectUtils.allNull;

import java.util.List;

import com.github.prgrms.configures.web.Pageable;
import com.github.prgrms.utils.ApiUtils.ApiResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/orders")
public class OrderRestController {

  private final OrderService orderService; 

  public OrderRestController(OrderService orderService) {
    this.orderService = orderService;
  }

  // TODO findAll, findById, accept, reject, shipping, complete 메소드 구현이 필요합니다.

  @GetMapping
  public ApiResult<List<OrderDto>> findAll(Pageable pageable) {
    
    List<OrderDto> orders = orderService.findOrders(pageable);

    return success(orders);
  }

  @GetMapping(value = "{id}")
  public ApiResult<OrderDto> findById(@PathVariable Long id) {
    
    return success(
      orderService.findOrderById(id));
  }

  @PatchMapping(path = "{id}/accept")
  public ApiResult<Boolean> accept(@PathVariable Long id) {
    return success(
      orderService.changeState(id, State.ACCEPTED, null)
    );
  }

  @PatchMapping(path = "{id}/reject")
  public ApiResult<Boolean> reject(@PathVariable Long id, @RequestBody(required = false) OrderRequest orderRequest) {

    checkArgument(!allNull(orderRequest), "message must be provided");

    return success(
      orderService.changeState(id, State.REJECTED, orderRequest.getMessage())
    );
  }

  @PatchMapping(path = "{id}/shipping")
  public ApiResult<Boolean> shipping(@PathVariable Long id) {
    return success(
      orderService.changeState(id, State.SHIPPING, null)
    );
  }

  @PatchMapping(path = "{id}/complete")
  public ApiResult<Boolean> complete(@PathVariable Long id) {
    return success(
      orderService.changeState(id, State.COMPLETED, null)
    );
  }
}