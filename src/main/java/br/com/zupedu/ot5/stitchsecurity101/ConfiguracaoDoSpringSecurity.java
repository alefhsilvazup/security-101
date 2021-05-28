package br.com.zupedu.ot5.stitchsecurity101;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class ConfiguracaoDoSpringSecurity extends
        WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // DSL = Domain specific languague -> ta um problema super especifico
        // Builder
        http.
                authorizeRequests() // começo o fluxo de autorização
                .antMatchers("/").permitAll() //permite acesso a rota
                .anyRequest().authenticated() //qualquer outra requisição precisa autenticar
                .and()
                .httpBasic()
                .and().formLogin(); //autenticacao eh basica (usuari/senha);
    }
}
