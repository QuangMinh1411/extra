package com.training.vti.cardemo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    HttpStatus status;
    String message;
}
