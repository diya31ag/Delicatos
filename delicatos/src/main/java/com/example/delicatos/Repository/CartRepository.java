package com.example.delicatos.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.example.delicatos.Models.CartItem;
import com.example.delicatos.Models.MenuItem;
import com.example.delicatos.Services.FoodMenuService;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CartRepository {
    JdbcTemplate jdbcTemplate;
    FoodMenuService foodMenuService;
    @Autowired
    public CartRepository(JdbcTemplate jdbcTemplate, FoodMenuService foodMenuService) {
        this.jdbcTemplate = jdbcTemplate;
        this.foodMenuService=foodMenuService;
    }
    public void addItemToCart(CartItem item){
        String sqlQuery="insert into cartItem(customer, restaurant, itemId, quantity) values(?,?,?,?)";
        jdbcTemplate.update(sqlQuery,item.getCustomer(), item.getRestaurant(),item.getItem().getId() , item.getQuantity());
    }
    private RowMapper<CartItem> cartItemRowMapper=new RowMapper<CartItem>() {
        @Override
        public CartItem mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new CartItem(rs.getInt("id"), rs.getString("customer"), rs.getString("restaurant"), foodMenuService.getMenuItemById(rs.getInt("itemId")), rs.getInt("quantity"));
        }
    };
    public List<CartItem> findByCustomer(String customer){
        String sqlQuery="select * from cartItem where customer = '"+customer+"'";
        List<CartItem> cartItemList= jdbcTemplate.query(sqlQuery,cartItemRowMapper);
        return cartItemList;
    }
    public CartItem findById(int id){
        String sqlQuery="select * from cartItem where id = '"+id+"'";
        CartItem cartItem=jdbcTemplate.queryForObject(sqlQuery,cartItemRowMapper);
        return cartItem;
    }
    public void updateQuantity(int quantity, int id){
        String sqlQuery="update cartItem set quantity = "+quantity+" where id = '"+id+"'";
        jdbcTemplate.update(sqlQuery);
    }
    public void deleteItem(int id){
        String sqlQuery="delete from cartItem where id = "+id;
        jdbcTemplate.update(sqlQuery);
    }
}
