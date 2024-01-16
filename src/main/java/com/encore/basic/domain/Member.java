package com.encore.basic.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
//모든 매개변수를 넣은 생성자

public class Member {
        @Setter
        private int id;
        private String name;
        private String email;
        private String password;
        @Setter //memoryDB때문에 어쩔수 없이
        private LocalDateTime create_time;
        public Member(String name, String email, String password){
                this.name = name;
                this.email = email;
                this.password = password;

        }
}