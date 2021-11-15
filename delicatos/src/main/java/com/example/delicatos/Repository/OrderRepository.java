package com.example.delicatos.Repository;

import com.example.delicatos.Models.Order;
import com.example.delicatos.Services.CustomerServiceImplementation;
import com.example.delicatos.Services.RestaurantServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.example.delicatos.Models.OrderItem;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.example.delicatos.Services.FoodMenuService;
@Repository
public class OrderRepository {
    JdbcTemplate jdbcTemplate;
    FoodMenuService foodMenuService;
    RestaurantServiceImplementation restaurantServiceImplementation;
    CustomerServiceImplementation customerServiceImplementation;
    @Autowired
    public OrderRepository(JdbcTemplate jdbcTemplate, FoodMenuService foodMenuService, RestaurantServiceImplementation restaurantServiceImplementation, CustomerServiceImplementation customerServiceImplementation) {
        this.jdbcTemplate = jdbcTemplate;
        this.foodMenuService=foodMenuService;
        this.restaurantServiceImplementation=restaurantServiceImplementation;
        this.customerServiceImplementation=customerServiceImplementation;
    }
    private RowMapper<Order> orderRowMapper=new RowMapper<Order>() {
        @Override
        public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Order(rs.getInt("id"), rs.getString("customer"), rs.getString("restaurant"), new ArrayList<OrderItem>(), rs.getString("dateTime"), rs.getString("status"), rs.getDouble("total"), restaurantServiceImplementation.findByUsername(rs.getString("restaurant")).getName(), customerServiceImplementation.findByUsername(rs.getString("customer")));
        }
    };
    public void addOrder(Order order){
        String sqlQuery="insert into orders(customer, restaurant,dateTime, status,total) values(?,?,?,?,?)";
        jdbcTemplate.update(sqlQuery,order.getCustomer(), order.getRestaurant(), order.getDateTime(), order.getStatus(), order.getTotal());
        String sqlQuery2="select * from orders where customer = '"+ order.getCustomer()+"' and restaurant = '"+ order.getRestaurant()+"' and dateTime = '"+order.getDateTime()+"'";
        Order orders=jdbcTemplate.queryForObject(sqlQuery2,orderRowMapper);
        for(int i=0;i<order.getItemList().size();i++){
            String sqlQuery1="insert into orderItems(itemId, quantity, orderId) values(?,?,?)";
            jdbcTemplate.update(sqlQuery1,order.getItemList().get(i).getMenuItem().getId(), order.getItemList().get(i).getQuantity(), orders.getId());
        }
    }
    private RowMapper<OrderItem> orderItemRowMapper=new RowMapper<OrderItem>() {
        @Override
        public OrderItem mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new OrderItem(rs.getInt("id"),foodMenuService.getMenuItemById(rs.getInt("itemId")), rs.getInt("quantity"));
        }
    };
    public List<Order> getOrderByCustomer(String customer){
        String sqlQuery="select * from orders where customer = '"+customer+"'";
        List<Order> orderList=jdbcTemplate.query(sqlQuery,orderRowMapper);
        for(int i=0;i<orderList.size();i++){
            String sqlQuery1 = "select * from orderItems where orderId = "+orderList.get(i).getId();
            List<OrderItem> orderItemList= jdbcTemplate.query(sqlQuery1, orderItemRowMapper);
            orderList.get(i).setItemList(orderItemList);
        }
        return orderList;
    }
    public List<Order> getOrderByRestaurant(String restaurant){
        String sqlQuery="select * from orders where restaurant = '"+restaurant+"'";
        List<Order> orderList=jdbcTemplate.query(sqlQuery,orderRowMapper);
        for(int i=0;i<orderList.size();i++){
            String sqlQuery1 = "select * from orderItems where orderId = "+orderList.get(i).getId();
            List<OrderItem> orderItemList= jdbcTemplate.query(sqlQuery1, orderItemRowMapper);
            orderList.get(i).setItemList(orderItemList);
        }
        return orderList;
    }
}
