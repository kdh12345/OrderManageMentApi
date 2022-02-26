package com.github.prgrms.orders;

import javax.validation.constraints.NotBlank;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ReviewRequest {
    @NotBlank(message = "content must be provided")
    private String content;

    protected ReviewRequest() {/*empty*/}

    public ReviewRequest(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ReviewRequest content(String content) {
        this.content = content;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
      .append("content", content)
      .toString();
    }
}