package com.encore.basic.domain;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;


@Getter

public class Member {
        private int id;
        private String name;
        private String email;
        private String password;
        private LocalDateTime create_time;
}
