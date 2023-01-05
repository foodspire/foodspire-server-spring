package com.foodspire.servercore.models.foodchoice;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Delivery extends MerchantFoodChoice {
    private String srcURL;
    private String platform;
}