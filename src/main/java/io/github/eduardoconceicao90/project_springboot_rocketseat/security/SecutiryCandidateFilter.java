package io.github.eduardoconceicao90.project_springboot_rocketseat.security;

import io.github.eduardoconceicao90.project_springboot_rocketseat.security.provider.JWTCandidateProvider;
import io.github.eduardoconceicao90.project_springboot_rocketseat.security.provider.JWTProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class SecutiryCandidateFilter extends OncePerRequestFilter {

    @Autowired
    private JWTCandidateProvider jwtCandidateProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        SecurityContextHolder.getContext().setAuthentication(null);
        String header = request.getHeader("Authorization");

        if(header != null && header.startsWith("Bearer ")){
            var subjectToken = jwtCandidateProvider.validateToken(header);
            request.setAttribute("candidate_id", subjectToken.getSubject());
            var authentication = new UsernamePasswordAuthenticationToken(subjectToken, null, Collections.emptyList());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
