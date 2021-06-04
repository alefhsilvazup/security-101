package br.com.zupedu.ot5.stitchsecurity101.seguranca;


import br.com.zupedu.ot5.stitchsecurity101.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticacao")
public class AutenticacaoController {

    private GerenciadorDeToken gerenciadorDeToken;
    private AuthenticationManager authenticationManager;

    @Autowired
    public AutenticacaoController(GerenciadorDeToken gerenciadorDeToken,
                                  AuthenticationManager authenticationManager) {
        this.gerenciadorDeToken = gerenciadorDeToken;
        this.authenticationManager = authenticationManager;
    }


    @PostMapping
    public ResponseEntity<?> autenticar(@RequestBody AutenticacaoRequest request) {
        System.out.println(request);
        Authentication authentication = request.paraAutenticacaoDoSpring();
        Authentication authenticate = authenticationManager.authenticate(authentication);

        authenticate.getCredentials();
        Usuario principal = (Usuario) authenticate.getPrincipal();

        String token = gerenciadorDeToken.geraToken(principal.getUsername());
        return ResponseEntity.ok(new TokenResponse(token));

    }
}
