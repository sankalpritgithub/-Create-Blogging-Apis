package com.Blog.Project.Related.SpringBoot.Security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  @Autowired
  private UserDetailsService userDetailsService;
  @Autowired
  private JwtTokenHelper jwtTokenHelper;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    // first step get Token

    String requestToken = request.getHeader("Authorization");
    // Start token With Bearer 2345678jhgfdsajhgfdshgfdsa
    System.out.println(requestToken);
    String username = null;
    String token = null;

    if (requestToken != null && requestToken.startsWith("Bearer")) {

      token = requestToken.substring(7);
      try {
        username = jwtTokenHelper.getUsernameFromToken(token);
      } catch (IllegalArgumentException e) {
        System.out.println("unable to get JWT Token");
      } catch (ExpiredJwtException e) {
        System.out.println("Jwt token has Expired");
      } catch (MalformedJwtException e) {
        System.out.println("Invaild Jwt Exception");
      }
    } else {

      System.out.println("JWt Token does not begin with Bearer");
    }

    // once we get the Token now validate
    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

      UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

      if (this.jwtTokenHelper.validateToken(token, userDetails)) {
        
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
            userDetails, null, userDetails.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

       
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

      } else {
        System.out.println("Invalid Jwt Token");
      }
    } else {
      System.out.println("username is null or context is not null");
    }
    filterChain.doFilter(request, response);
  }

}
