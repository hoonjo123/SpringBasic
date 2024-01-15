package com.encore.basic.domain;

import lombok.Data;

//entity와 분리시킴

@Data
public class MemberRequestDto {
    private String name;
    private String email;
    private String password;
}
