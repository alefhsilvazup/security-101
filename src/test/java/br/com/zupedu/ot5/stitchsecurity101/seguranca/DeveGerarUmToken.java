package br.com.zupedu.ot5.stitchsecurity101.seguranca;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeveGerarUmToken {


    @Test
    public void deveGerarUmTokenJwt() {

        // usuario e senha -> for valido -> gerar jwt
        String chave = "chaveDeTeste";
        GerenciadorDeToken gerenciadorDeToken = new GerenciadorDeToken(1L, chave);
        String token = gerenciadorDeToken.geraToken("stitch");

        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(chave).parseClaimsJws(token);

        Assertions.assertEquals(claimsJws.getBody().getSubject(), "stitch-jwt-101");
    }
}
