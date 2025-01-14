package com.yohuang.bookrental.dto.response;

import java.util.List;

public class ErrorResponse {
    private List<String> errors;

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
