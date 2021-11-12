package com.example.delicatos.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.example.delicatos.Models.Customer;

@Repository
public class CustomerProfileRepository {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerProfileRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private RowMapper<Customer> customerRowMapper = new RowMapper<Customer>() {
        @Override
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Customer(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"), rs.getString("mobileNo"), rs.getString("address"), rs.getString("city"));
        }
    };

    public Customer findByEmail(String email) {
        String sqlQuery = "select * from customer where email = ?";
        Customer customer = jdbcTemplate.queryForObject(sqlQuery,new Object[]{email},customerRowMapper);
        return customer;
    }

    public void save(Customer customer){
        System.out.println(customer);
        String sqlQuery = "insert into customer(firstName, lastName, email, mobileNo, address, city) values(?,?,?,?,?,?)";
        jdbcTemplate.update(sqlQuery, customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getMobileNo(), customer.getAddress(), customer.getCity());
    }
}
