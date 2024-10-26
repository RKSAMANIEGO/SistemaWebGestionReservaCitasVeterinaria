package com.example.demo.Jwt;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JwtUtils {
	
	private final String keySecret="de69e8ee1367fb9aef2b7776d70152d49485cff2357464dcf9fcd0f936569431";
	private final String userGenerated="AUTH0";
	
	
	public String createToken(Authentication authentication) {
		Algorithm algorithm=Algorithm.HMAC256(this.keySecret);
		String username= authentication.getPrincipal().toString();
		String authorities=authentication.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));
		
		String jwtToken=JWT.create()
				.withIssuer(this.userGenerated)
				.withSubject(username)
				.withClaim("authorities", authorities)
				.withIssuedAt(new Date(System.currentTimeMillis()))
				.withExpiresAt(new Date(System.currentTimeMillis() + 1000*60*60*24))
				.withJWTId(UUID.randomUUID().toString())
				.withNotBefore(new Date(System.currentTimeMillis()))
				.sign(algorithm);
		return jwtToken;	
	}
	
	
	public DecodedJWT isvalidateToken(String token) {
		try {
			Algorithm algorithm=Algorithm.HMAC256(this.keySecret);
			JWTVerifier verifier= JWT.require(algorithm)
					.withIssuer(this.userGenerated)
					.build();
			DecodedJWT decodedJWT= verifier.verify(token);
			return decodedJWT;
		} catch (JWTVerificationException exception) {
			throw new JWTVerificationException("TOKEN INVALIDO, ERROR");
		}
	}
	
	public String extractUsername(DecodedJWT decodedJwt) {
		return decodedJwt.getSubject().toString();
	}
	
	public Claim getSpecificClaim(DecodedJWT decodedJWT, String claimName) {
		return decodedJWT.getClaim(claimName);
	}
	
	public Map<String,Claim> returnAllClaims(DecodedJWT decodedJWT){
		return decodedJWT.getClaims();
	}
	

}
