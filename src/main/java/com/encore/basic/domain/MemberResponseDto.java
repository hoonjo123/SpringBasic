package com.encore.basic.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

//entity와 분리시킴

@Data

public class MemberResponseDto {
    private String name;
    private String email;
    private String password;
    private int id;
}
