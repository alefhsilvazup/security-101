package br.com.zupedu.ot5.stitchsecurity101.usuario;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Perfil implements GrantedAuthority {

    @Id
    private String perfil;

    @Deprecated
    /**
     * somente o hibernate deve usar isso aqui
     */
    public Perfil() {

    }

    public Perfil(String perfil) {
        this.perfil = perfil;
    }

    @Override
    public String getAuthority() {
        return this.perfil;
    }
}
