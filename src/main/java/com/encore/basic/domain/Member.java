package com.encore.basic.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

//모든 매개변수를 넣은 생성자
@Getter

//entity annotation을 통해 mariadb의 테이블 및 컬럼을 자동생성
//class명은 테이블명, 변수명은 컬럼명
//jdbc에서는 쿼리생성 후 실행 -> 조회결과를 member객체로 변환하였지만 jpa에서는 쿼리 생성을 자동화해준다.
//어떤 테이블 대상으로 쿼리를 실행할지 알려줘야함.
//findByName,findById,findByEmail 등..

//1)테이블 및 컬럼 생성 자동화(ddl auto)
//2)dml쿼리 생성 자동화 : 메서드 명으로 약속. findAll
//3)return까지 자동화!
@Entity
@NoArgsConstructor
public class Member {
        @Setter
        @Id//pk설정
        //identity = auto_increment setting. auto = jpa구현체가 자동으로 적절한 키생성 전략선택.
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        //String -> Varchar(255)
        private String name;
        @Column(nullable = false, length = 50)
        private String email;
        private String password;
        @Setter //memoryDB때문에 어쩔수 없이
        @Column(name = "created_time")  //name option을 통해 db의 컬럼명 별도 지정가능
        private LocalDateTime create_time;
        public Member(String name, String email, String password){
                this.name = name;
                this.email = email;
                this.password = password;
                create_time = LocalDateTime.now();
        }
}