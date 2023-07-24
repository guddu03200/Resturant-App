package com.geekster.Restaurant.management.service.API.Model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignInInput {
   private String email;
   private  String Password;
}
