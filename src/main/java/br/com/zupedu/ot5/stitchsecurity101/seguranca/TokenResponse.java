package br.com.zupedu.ot5.stitchsecurity101.seguranca;

public class TokenResponse {
    private String token;
    private String tipo;

    public TokenResponse(String token) {
        this.token = token;
        this.tipo = "JWT";
    }

    public String getToken() {
        return token;
    }

    public String getTipo() {
        return tipo;
    }
}
