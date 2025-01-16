package com.yohuang.bookrental.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class ErrorResponse {
    private List<String> errors;
}
