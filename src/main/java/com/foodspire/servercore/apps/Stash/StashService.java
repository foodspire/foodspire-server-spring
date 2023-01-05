package com.foodspire.servercore.apps.Stash;

import com.foodspire.servercore.models.auth.User;
import com.foodspire.servercore.models.auth.UserRepository;
import com.foodspire.servercore.models.foodchoice.FoodChoice;
import com.foodspire.servercore.models.foodchoice.Restaurant;
import com.foodspire.servercore.models.foodchoice.RestaurantRepository;
import com.foodspire.servercore.models.utils.Address;
import com.foodspire.servercore.models.utils.Location;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional
public class StashService {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    UserRepository userRepository;

    @Value("${com.foodspire.yelp_api_key}")
    private String YELP_API_KEY;

    @Value("${com.foodspire.yelp_api_endpoint}")
    private String YELP_API_ENDPOINT;

    public List<FoodChoice> listStash(String email) {
        List<FoodChoice> ret = new ArrayList<>();
        List<Restaurant> res = listRestaurant(email);
        ret.addAll(res);
        return ret;
    }

    public List<Restaurant> listRestaurant(String email) {
        return restaurantRepository.findByUser_Email(email);
    }

    public Restaurant createRestaurant(Restaurant restaurant, String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        restaurant.setTimeAdded(new Date());
        restaurant.setUser(user);
        restaurantRepository.save(restaurant);
        return restaurant;
    }

    public Restaurant createRestaurantFromURL(String url, String email) throws Exception {
        User user = userRepository.findByEmail(email).orElseThrow();
//        HashMap<String, Matcher> regexMatchers = new HashMap<>();
        Matcher yelpMatcher = Pattern.compile("https?:\\/\\/www.yelp.com\\/biz\\/([^\\/]+)").matcher(url);
        if (yelpMatcher.find()) {
            Restaurant res = getYelpRestaurant(yelpMatcher.group(1));
            res.setUser(user);
            res.setSrcURL(url);
            restaurantRepository.save(res);
            return res;
        }
        throw new Exception("Unrecognized URL");
    }

    private Restaurant getYelpRestaurant(String id) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + YELP_API_KEY);

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<String> result =
                restTemplate.exchange(YELP_API_ENDPOINT + "/" + id, HttpMethod.GET, entity, String.class);
        JSONObject data = new JSONObject(result.getBody());
        ArrayList<String> labels = new ArrayList<>();
        JSONArray categories = data.getJSONArray("categories");
        for (int i = 0; i < categories.length(); i++) {
            labels.add(categories.getJSONObject(i).getString("title"));
        }
        JSONObject location = data.getJSONObject("coordinates");
        JSONObject address = data.getJSONObject("location");
        return new Restaurant(data.getString("name"),
                labels, new Date(), null,
                new Location(location.getDouble("latitude"), location.getDouble("longitude")),
                new Address(address.getString("address1"), address.getString("address2"),
                        address.getString("city"), address.getString("state"),
                        address.getString("zip_code")),
                data.getString("display_phone"),
                null, "Yelp");
    }
}
