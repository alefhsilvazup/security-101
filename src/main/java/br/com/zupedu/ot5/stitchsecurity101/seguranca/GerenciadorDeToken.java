package br.com.zupedu.ot5.stitchsecurity101.seguranca;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

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
}
