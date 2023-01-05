package com.foodspire.servercore.models.foodchoice;

import com.foodspire.servercore.models.utils.Address;
import com.foodspire.servercore.models.utils.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class MerchantFoodChoice extends FoodChoice {
    private Location location;
    private Address address;
    private String phone;
}
