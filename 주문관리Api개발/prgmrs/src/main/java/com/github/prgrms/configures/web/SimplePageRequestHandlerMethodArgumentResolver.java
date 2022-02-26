package com.github.prgrms.configures.web;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class SimplePageRequestHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

  private static final String DEFAULT_OFFSET_PARAMETER = "offset";

  private static final String DEFAULT_SIZE_PARAMETER = "size";

  private static final long DEFAULT_OFFSET = 0;

  private static final int DEFAULT_SIZE = 5;

  private String offsetParameterName = DEFAULT_OFFSET_PARAMETER;

  private String sizeParameterName = DEFAULT_SIZE_PARAMETER;

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return Pageable.class.isAssignableFrom(parameter.getParameterType());
  }

  @Override
  public Object resolveArgument(
    MethodParameter methodParameter,
    ModelAndViewContainer mavContainer,
    NativeWebRequest webRequest,
    WebDataBinderFactory binderFactory
  ) {
    String offsetString = webRequest.getParameter(offsetParameterName);
    String sizeString = webRequest.getParameter(sizeParameterName);

    long offset = NumberUtils.toLong(offsetString, 0);
    int size = NumberUtils.toInt(sizeString, 5);

    // * offset: offset 기반 페이징 처리 파리미터 (최소값: 0, 최대값: Long.MAX_VALUE, 기본값: 0)
    if(offset < 0 || offset > Long.MAX_VALUE){
      offset = 0;
    }
    // * size: 출력할 아이템의 갯수 (최소값 1, 최대값: 5, 기본값: 5)
    if(size > 5 || size <= 0){
      size = 5;
    }

    SimplePageRequest spr = new SimplePageRequest(offset, size);

    return spr;
    // TODO 구현이 필요 합니다.
    // throw new UnsupportedOperationException("SimplePageRequest 인스턴스를 리턴하도록 구현 필요");
  }

  public void setOffsetParameterName(String offsetParameterName) {
    this.offsetParameterName = offsetParameterName;
  }

  public void setSizeParameterName(String sizeParameterName) {
    this.sizeParameterName = sizeParameterName;
  }

}