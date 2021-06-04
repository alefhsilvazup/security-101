package br.com.zupedu.ot5.stitchsecurity101.usuario;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
public class Usuario implements UserDetails {

    @Id
    private UUID id;

    private String username, senha;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Perfil> perfils = new ArrayList<>();

    @Deprecated
    /**
     *
     * somente o hibernate deve usar
     */

    public Usuario() {
    }

    public Usuario(String usuario, String senha) {
        this.username = usuario;
        this.senha = senha;
        this.id = UUID.randomUUID();
    }

    public Usuario(String usuario, List<Perfil> perfis) {
        this.username = usuario;
        this.perfils = perfis;
    }

    public UUID getId() {
        return id;
    }


    public String getSenha() {
        return senha;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

