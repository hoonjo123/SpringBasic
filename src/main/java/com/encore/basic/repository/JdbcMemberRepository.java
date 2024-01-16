package com.encore.basic.repository;

import com.encore.basic.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*레거시 Jdbc
* 조회부분 자동화는 어려운걸까?
* */

//유지보수가 굉장히 어렵다(코드가 너무 길어서)
@Repository
public class JdbcMemberRepository implements MemberRepository {

    //Datasource는 DB와 JDBC에서 사용하는 DB연결 드라이버 객체
    @Autowired
    private DataSource dataSource;

    @Override
    public List<Member> findAll() {
        List<Member> members = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            String sql = "select * from member";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                LocalDateTime now = resultSet.getTimestamp("create_time").toLocalDateTime();
                System.out.println(now.toString());
                Member member = new Member(name, email, password);
                member.setId(id);
                member.setCreate_time(now);
                members.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }

    @Override
    public Member save(Member member) {
        try {
            Connection connection = dataSource.getConnection();
            String sql = "insert into member(name,email,password) values(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, member.getName());
            preparedStatement.setString(2, member.getEmail());
            preparedStatement.setString(3, member.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Member> findById(int inputid) {
        String sql = "select * from member where id =?";

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, inputid);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                Member member = new Member(name, email, password);
                member.setId(resultSet.getInt("id"));
                member.setCreate_time(resultSet.getTimestamp("create_time").toLocalDateTime());
                return Optional.of(member);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
            return Optional.empty();
        }
    }

