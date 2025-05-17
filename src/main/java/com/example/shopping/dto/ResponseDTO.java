package com.example.shopping.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ResponseDTO {

    public String statusCode;

    public String statusMessage;
}
