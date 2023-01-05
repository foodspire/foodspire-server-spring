package com.foodspire.servercore.models.foodchoice;


import com.foodspire.servercore.models.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findByUser_Email(String email);

}
