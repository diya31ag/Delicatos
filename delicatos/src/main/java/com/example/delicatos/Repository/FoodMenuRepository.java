package com.example.delicatos.Repository;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.example.delicatos.Models.MenuItem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Repository
public class FoodMenuRepository {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public FoodMenuRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<MenuItem> menuItemRowMapper = new RowMapper<MenuItem>() {
        @Override
        public MenuItem mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new MenuItem(rs.getInt("id"), rs.getString("restaurant"), rs.getString("itemName"), rs.getDouble("price"), Collections.emptyList(), rs.getString("image"));
        }
    };
    private RowMapper<String> categoryRowMapper = new RowMapper<String>() {
        @Override
        public String mapRow(ResultSet rs, int rowNum) throws SQLException {
            return rs.getString("name");
        }
    };
    public MenuItem addMenuItem(MenuItem menuItem){
        String sqlQuery = "insert into menuItem(restaurant, itemName, price, image) values (?,?,?,?)";
        jdbcTemplate.update(sqlQuery, menuItem.getRestaurant(), menuItem.getItemName(), menuItem.getPrice(), menuItem.getImage());
        String sqlQuery1 = "select * from menuItem where restaurant = '" + menuItem.getRestaurant() + "' and itemName = '" + menuItem.getItemName()+ "'";
        MenuItem menuItem1 = jdbcTemplate.queryForObject(sqlQuery1, menuItemRowMapper);
        for(int i=0;i<menuItem.getCategories().size();i++){
            String sqlQuery2 = "insert into categories(name, itemId) values (?,?)";
            jdbcTemplate.update(sqlQuery2, menuItem.getCategories().get(i), menuItem1.getId());
        }
        return menuItem1;
    }

    public List<MenuItem> getMenuItemByRestaurantId(String restaurant){
        String sqlQuery = "select * from menuItem where restaurant = '"+restaurant+"'";
        List<MenuItem> menuItemList = jdbcTemplate.query(sqlQuery, menuItemRowMapper);
        for(int i=0;i<menuItemList.size();i++){
            String sqlQuery1 = "select * from categories where itemId = '" + menuItemList.get(i).getId() + "'";
            List<String> category = jdbcTemplate.query(sqlQuery1,categoryRowMapper);
            menuItemList.get(i).setCategories(category);
        }
        return menuItemList;
    }

//    public List<MenuItem> getMenuItemByRestaurantIdAndCategory(String category, int restaurantId){
//        String sqlQuery = "select * from menuItem where restaurant = '" + restaurantId + "' and cuisineType = '" + cuisineType + "'";
//        List<MenuItem> menuItemList = jdbcTemplate.query(sqlQuery, menuItemRowMapper);
//        return menuItemList;
//    }
//
//    public MenuItem getMenuItemById(int itemId){
//        String sqlQuery = "select * from menuItem where id = '"+itemId+"'";
//        MenuItem menuItem = jdbcTemplate.queryForObject(sqlQuery, menuItemRowMapper);
//        return menuItem;
//    }

}
