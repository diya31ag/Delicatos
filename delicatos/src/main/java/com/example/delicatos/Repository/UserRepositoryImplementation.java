package com.example.delicatos.Repository;

import com.example.delicatos.Models.User;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.example.delicatos.Models.Address;
import com.example.delicatos.Models.Location;
@Repository
public class UserRepositoryImplementation implements UserRepository{
    JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryImplementation(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<User> userRowMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(rs.getInt("id"), rs.getString("email"), rs.getString("role"), rs.getString("password"));
        }
    };
    @Override
    public User findByEmail(String email) {
//        System.out.println("select * from user where email = ?");
        String sqlQuery = "select * from user where email = ?";
        User user = jdbcTemplate.queryForObject(sqlQuery,new Object[]{email},userRowMapper);
        return user;
    }
    @Override
    public void save(User user) {
        System.out.println(user);
        String sqlQuery = "insert into user(email, role, password) values(?,?,?)";
        jdbcTemplate.update(sqlQuery, user.getEmail(), user.getRole(), user.getPassword());
    }
}
