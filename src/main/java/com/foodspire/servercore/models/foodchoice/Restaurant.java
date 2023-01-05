package com.foodspire.servercore.models.foodchoice;

import com.foodspire.servercore.models.auth.User;
import com.foodspire.servercore.models.utils.Address;
import com.foodspire.servercore.models.utils.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Restaurant extends MerchantFoodChoice {
    private String srcURL;
    private String platform;

    public Restaurant(String name, ArrayList<String> labels, Date timeAdded, User user,
                      Location location, Address address, String phone,
                      String srcURL, String platform) {
        super();
        this.setName(name);
        this.setLabels(labels);
        this.setTimeAdded(timeAdded);
        this.setUser(user);
        this.setLocation(location);
        this.setAddress(address);
        this.setPhone(phone);
        this.srcURL = srcURL;
        this.platform = platform;
    }
}
