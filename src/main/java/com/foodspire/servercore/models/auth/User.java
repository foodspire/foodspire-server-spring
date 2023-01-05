package com.foodspire.servercore.models.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Email
    @NotBlank
    @NotNull
    @Column(name = "username", unique = true)
    private String email;

    @NotBlank
    @NotNull
    @JsonIgnore
    @Column(name = "password")
    private String passwordHash;

    @NotBlank
    @NotNull
    private String firstName;

    @NotBlank
    private String lastName;
}
