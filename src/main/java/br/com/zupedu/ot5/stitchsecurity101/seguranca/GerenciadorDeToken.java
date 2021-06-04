package br.com.zupedu.ot5.stitchsecurity101.seguranca;

import br.com.zupedu.ot5.stitchsecurity101.usuario.Perfil;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Component
public class GerenciadorDeToken {

    private Long horasDeDuracao;
    private String chaveDoToken;

    public GerenciadorDeToken(@Value("${configuracao.jwt.expiracao}") Long horasDeDuracao,
                              @Value("${configuracao.jwt.chave}") String chaveDoToken) {
        this.horasDeDuracao = horasDeDuracao;
        this.chaveDoToken = chaveDoToken;
    }

    public String geraToken(String usuario) {
        //dsl/builder
        Date dataExpiracao = Date.from(LocalDateTime.now()
                .plusHours(horasDeDuracao)
                .atZone(ZoneId.of("America/Sao_Paulo")).toInstant());

        HashMap<String, Object> payload = new HashMap<>();
        payload.put("username", usuario);

        String token = Jwts.builder()
                .setSubject("stitch-jwt-101")
                .setExpiration(dataExpiracao)
                .setIssuedAt(new Date())
                .setAudience(usuario)
                .signWith(SignatureAlgorithm.HS256, chaveDoToken)
                .compact();

        return token;
    }

    public Boolean verificaSeEhValido(String authorization) {

        try{
            Jwts.parser().setSigningKey(this.chaveDoToken).parseClaimsJws(authorization);
            return true;
        } catch (JwtException ex) {
            return false;
        }


    }

    public String getUsername(String authorization) {
        return Jwts.parser()
                .setSigningKey(this.chaveDoToken)
                .parseClaimsJws(authorization)
                .getBody()
                .getAudience();
    }

    public List<Perfil> getPerfis(String authorization) {
        return Collections.emptyList();
    }
}
