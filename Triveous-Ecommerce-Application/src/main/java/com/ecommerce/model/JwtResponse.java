package com.ecommerce.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtResponse {
	
	private String jwtToken;
	
	private String email;

}
