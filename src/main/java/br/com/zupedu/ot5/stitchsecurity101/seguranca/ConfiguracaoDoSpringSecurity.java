package br.com.zupedu.ot5.stitchsecurity101.seguranca;

import br.com.zupedu.ot5.stitchsecurity101.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Configuration
@EnableWebSecurity
public class ConfiguracaoDoSpringSecurity extends
        WebSecurityConfigurerAdapter {

    @Autowired private GerenciadorDeToken gerenciadorDeToken;
    @Autowired private UsuarioRepository usuarioRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // DSL = Domain specific languague -> ta um problema super especifico
        // Builder
        http.
                authorizeRequests() // começo o fluxo de autorização
                .antMatchers("/").permitAll() //permite acesso a rota
                .antMatchers("/autenticacao").permitAll() //permite acesso a rota
                .anyRequest().authenticated() //qualquer outra requisição precisa autenticar
                .and()
                .httpBasic()
                .and().formLogin() //autenticacao eh basica (usuari/senha);
                .and().csrf().disable() //desabilitando os filtors do csrf do spring para trabalhar com api
                .addFilterBefore(new FiltroParaValidarOToken(gerenciadorDeToken, usuarioRepository), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username -> {
            return usuarioRepository.findByUsername(username)
                    .orElseThrow(() ->
                            new UsernameNotFoundException("deu xablau o usuário não existe"));
        }).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}