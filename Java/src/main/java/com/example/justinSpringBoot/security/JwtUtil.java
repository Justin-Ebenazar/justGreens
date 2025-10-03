package com.example.justinSpringBoot.security;

import javax.crypto.SecretKey;
import java.util.Date;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	private static final String SECRET_KEY_STRING = "f0fab9211c6f3da262da02ca2257587ae790349b32f97750cd8d95348c5cf1f8";
	

	private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET_KEY_STRING.getBytes());
	
	public String generateToken(UserDetails userDetails) {	
		
		return Jwts.builder()
				.subject(userDetails.getUsername())
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + 1000*60*60))
				.signWith(SECRET_KEY,Jwts.SIG.HS256)
				.compact();
	}
	
	public boolean validateToken(String token, UserDetails userDetails) {
		return extractUsername(token).equals(userDetails.getUsername());
	}
	
	public String extractUsername(String token) {
		return Jwts.parser()
		.verifyWith(SECRET_KEY)
		.build()
		.parseSignedClaims(token)
		.getPayload()
		.getSubject();
	}

}
