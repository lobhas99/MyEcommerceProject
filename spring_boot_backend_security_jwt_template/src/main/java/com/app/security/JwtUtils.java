package com.app.security;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtUtils {

	@Value("${SECRET_KEY}")
	private String jwtSecret;

	@Value("${EXP_TIMEOUT}")
	private int jwtExpirationMs;

	private Key key;

	@PostConstruct
	public void init() {
		key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
	}

	// It will be invoked by User Controller:signin upon successful
	public String generateJwtToken(Authentication authentication) {
		log.info("generate jwt token"+ authentication);
		CustomUserDetails userPrincipal=(CustomUserDetails)authentication.getPrincipal();
		return Jwts.builder().//It is a factory class which is used to create JWT tokens
		setSubject((userPrincipal.getUsername()))//setting subject part of token
		.setIssuedAt(new Date())//setting JWT claims "iat" value of current date
		.setExpiration(new Date(new Date().getTime() + jwtExpirationMs))//sets the JWT Claims exp
		.claim("authorities", getAuthoritiesInString(userPrincipal.getAuthorities()))//setting a custom claim
		.signWith(key,SignatureAlgorithm.HS512)//Signs the constructed JWT using specified algorithm with specified key,producing a jws
		//using token signing algo:HMAC using HS512
		.compact();
	}

	// This method will be invoked by our custom filter
	public String getUserNameFromJWTToken(Claims claims) {
		return claims.getSubject();
	}

	public Claims validateJwtTokens(String jwtToken) {
		Claims claims = Jwts.parserBuilder().setSigningKey(key).build()
				// Sets the signing key used to verify JWT digital signature.
				.parseClaimsJws(jwtToken).getBody();// Parses the signed JWT returns the resulting Jws<Claims> instance
		return claims;
	}
	
	// Accepts Collection<GrantedAuthority> n rets comma separated list of it's
		// string form
	public String getAuthoritiesInString(Collection<? extends GrantedAuthority> authorities) {
		String authorityString=authorities.stream().map(authority->authority.getAuthority()).collect(Collectors.joining(","));
		System.out.println(authorityString);
		return authorityString;
	}
	
	public List<GrantedAuthority> getAuthoritiesFromClaims(Claims claims){
		String authString= (String)claims.get("authorities");
		List<GrantedAuthority> authorities=AuthorityUtils.commaSeparatedStringToAuthorityList(authString);
		authorities.forEach(System.out::println);
		return authorities;
	}
}
