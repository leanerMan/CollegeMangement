package com.app.college.payload.response;

import java.util.List;

import lombok.Data;

@Data
public class JwtResponse {
  private String token;
  private String type = "Bearer";
  private UserInfoResponse user;


  public JwtResponse(String accessToken, Long id, String username, String email, List<String> roles) {
    this.token = accessToken;
   this.user=new UserInfoResponse(id, username, email, roles);
  }


  



  



  
  
}
