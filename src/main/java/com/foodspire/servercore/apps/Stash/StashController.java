package com.foodspire.servercore.apps.Stash;

import com.foodspire.servercore.models.foodchoice.FoodChoice;
import com.foodspire.servercore.models.foodchoice.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class StashController {

    @Autowired
    StashService stashService;

    @GetMapping("/stash")
    public List<FoodChoice> getStash(Principal principal) {
        return stashService.listStash(principal.getName());
    }

    @GetMapping("/stash/restaurant")
    public List<Restaurant> getRestaurant(Principal principal) {
        return stashService.listRestaurant(principal.getName());
    }

    @PutMapping("/stash/restaurant")
    public Restaurant putRestaurant(@RequestBody Restaurant restaurant, Principal principal) {
        return stashService.createRestaurant(restaurant, principal.getName());
    }

    @PostMapping("/stash/restaurant")
    public Restaurant postRestaurant(@RequestParam String url, Principal principal) throws Exception {
        return stashService.createRestaurantFromURL(url, principal.getName());
    }

    @DeleteMapping("/stash/restaurant")
    public boolean deleteRestaurant(Principal principal) {
//        return stashService.createRestaurantFromURL(url, principal.getName());
        return true;
    }
}
