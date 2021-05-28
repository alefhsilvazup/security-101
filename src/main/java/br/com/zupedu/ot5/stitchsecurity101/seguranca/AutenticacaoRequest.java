package br.com.zupedu.ot5.stitchsecurity101.seguranca;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public class AutenticacaoRequest {

    private String usuario, senha;

    public AutenticacaoRequest(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }

    public Authentication paraAutenticacaoDoSpring() {
        return new UsernamePasswordAuthenticationToken(this.usuario, this.senha);
    }
}
