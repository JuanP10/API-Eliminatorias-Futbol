package com.example.eliminatoriasapi.security.jwt;

import com.example.eliminatoriasapi.security.services.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;


public class JwtTokenFilter extends OncePerRequestFilter {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(JwtTokenFilter.class);

    @Autowired
    private  JwtProvider jwtProvider;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    private String getToken(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        if (StringUtils.hasText(header) && header.startsWith("Bearer"))//
            return header.substring(7); //
        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws  ServletException, IOException {
        try {
            String token = getToken(req);
            if (token != null && jwtProvider.validateToken(token)) {
                String userName = jwtProvider.getUserNameFromToken(token);

                UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(userName);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));//

                SecurityContextHolder.getContext().setAuthentication(auth);

            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        filterChain.doFilter(req,res);
    }

}
