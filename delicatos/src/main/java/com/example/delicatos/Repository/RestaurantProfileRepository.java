package com.example.delicatos.Repository;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.example.delicatos.Models.Restaurant;
import java.util.ArrayList;
import java.util.List;
@Repository
public class RestaurantProfileRepository {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public RestaurantProfileRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private RowMapper<Restaurant> restaurantRowMapper = new RowMapper<Restaurant>() {
        @Override
        public Restaurant mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Restaurant(rs.getInt("id"), rs.getString("email"), rs.getString("name"), rs.getString("address"), rs.getString("contact"), rs.getString("city"), rs.getString("description"), rs.getString("image"));
        }
    };

    public Restaurant findByEmail(String email) {
        String sqlQuery = "select * from restaurant where email = ?";
        System.out.println(email);
        Restaurant restaurant = jdbcTemplate.queryForObject(sqlQuery,new Object[]{email}, restaurantRowMapper);
        return restaurant;
    }

    public void save(Restaurant restaurant){
        System.out.println(restaurant);
        String sqlQuery = "insert into restaurant(email, name, address, contact, city,description,image) values(?,?,?,?,?,?,?)";
        jdbcTemplate.update(sqlQuery, restaurant.getEmail(), restaurant.getName(), restaurant.getAddress(), restaurant.getContact(), restaurant.getCity(), restaurant.getDescription(),restaurant.getImage());
    }
    public List<Restaurant> getAllRestaurants(){
        String sqlQuery = "select * from restaurant";
        List<Restaurant> restaurants = jdbcTemplate.query(sqlQuery, restaurantRowMapper);
        return restaurants;
    }
    public Restaurant findById(int id){
        String sqlQuery = "select * from restaurant where id = ?";
        System.out.println(id);
        Restaurant restaurant = jdbcTemplate.queryForObject(sqlQuery,new Object[]{id}, restaurantRowMapper);
        return restaurant;
    }
//    public List<Restaurant> getRestaurantByName(final String restaurantName){
//
//    }
//    public List<Restaurant> getRestaurantsByCity(final String city){
//
//    }
}
