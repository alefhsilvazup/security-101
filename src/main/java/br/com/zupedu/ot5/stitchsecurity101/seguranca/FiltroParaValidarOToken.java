package br.com.zupedu.ot5.stitchsecurity101.seguranca;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Security;

public class FiltroParaValidarOToken extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        System.out.println("passando pelo filtro");
        String authorization = request.getHeader("Authorization");
        filterChain.doFilter(request, response);

        SecurityContextHolder.getContext().setAuthentication();
    }
}
