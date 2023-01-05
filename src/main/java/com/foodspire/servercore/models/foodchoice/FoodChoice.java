package com.foodspire.servercore.models.foodchoice;

import com.foodspire.servercore.models.auth.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodChoice {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String name;
    private ArrayList<String> labels;

    @NotBlank
    private Date timeAdded;

    @NotNull
    @ManyToOne
    private User user;
}


