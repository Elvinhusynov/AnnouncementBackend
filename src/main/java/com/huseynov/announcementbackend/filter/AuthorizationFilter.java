package com.huseynov.announcementbackend.filter;

import com.huseynov.announcementbackend.dto.BaseResponse;
import com.huseynov.announcementbackend.service.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,//request-in data-sın daşıyır
                                    @NonNull HttpServletResponse response,//response-un data-sın daşıyır.
                                    @NonNull FilterChain filterChain)//sorğunu zəncirvari davam etdirmək üçündü.
            throws ServletException, IOException {

        if (request.getServletPath().equals("/api/v1/auth/login") //->bunu yazaraq deyirikki tokensiz login ol
            || request.getServletPath().equals("/api/v1/auth/sign-up")) { //->bunu yazaraq deyirikki tokensiz sign-up ol
            filterChain.doFilter(request, response);
            return;
        }

        final String authorizationHeader = request.getHeader("Authorization");//Bearer tokensdfds
        final String prefix = "Bearer ";

        if (authorizationHeader == null || !authorizationHeader.startsWith(prefix)) {//toke-nin nulldi?vəya ilk sözü Bearer ilə başlıyır? əgər elə deyilsə xəta at
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(),
                    getBaseResponse("Authorization header is invalid"));
        } else {
            String token = authorizationHeader.substring(prefix.length());//tokensfdsf
            try {
                if (jwtService.isAccessTokenValid(token)) {
                    String username = jwtService.extractUsername(token);

                    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                        UsernamePasswordAuthenticationToken authenticationToken =
                                new UsernamePasswordAuthenticationToken(
                                        username, null, null);
                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                        filterChain.doFilter(request, response);
                        return;
                    }
                }
            } catch (ExpiredJwtException ex) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),
                        getBaseResponse(ex.getMessage()));
            }
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(),
                    getBaseResponse("JWT token is invalid"));
        }
    }

    private BaseResponse<Void> getBaseResponse(String message) {
        BaseResponse<Void> baseResponse = new BaseResponse<>();
        baseResponse.setMessage(message);
        return baseResponse;
    }

}
