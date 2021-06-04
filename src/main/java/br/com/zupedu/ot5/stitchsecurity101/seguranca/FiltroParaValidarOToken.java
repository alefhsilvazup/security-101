package br.com.zupedu.ot5.stitchsecurity101.seguranca;

import br.com.zupedu.ot5.stitchsecurity101.usuario.Usuario;
import br.com.zupedu.ot5.stitchsecurity101.usuario.UsuarioRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Security;
import java.util.Objects;
import java.util.Optional;

public class FiltroParaValidarOToken extends OncePerRequestFilter {

    private GerenciadorDeToken gerenciadorDeToken;
    private UsuarioRepository usuarioRepository;

    public FiltroParaValidarOToken(GerenciadorDeToken gerenciadorDeToken,
                                   UsuarioRepository usuarioRepository) {
        this.gerenciadorDeToken = gerenciadorDeToken;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        if(Objects.nonNull(authorization)) {
            Boolean ehValido = gerenciadorDeToken.verificaSeEhValido(authorization);

            if (ehValido) {
                //busca os dados do meu usuario
                Usuario usuario = usuarioRepository
                        .findByUsername(gerenciadorDeToken.getUsername(authorization))
                        .orElseThrow(() -> new UsernameNotFoundException("usuario não existe"));

                //** para autenticacao funcionar precisamos alterar nossa geração de token e incluir o perfil dentro do token
//                Usuario usuarioSemIrNoBanco = new Usuario(gerenciadorDeToken.getUsername(authorization), gerenciadorDeToken.getPerfis(authorization));
//                UsernamePasswordAuthenticationToken authenticationToken =
//                        new UsernamePasswordAuthenticationToken(usuarioSemIrNoBanco, null, usuarioSemIrNoBanco.getAuthorities());

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }


        }

        filterChain.doFilter(request, response);

        //SecurityContextHolder.getContext().setAuthentication();
    }
}
