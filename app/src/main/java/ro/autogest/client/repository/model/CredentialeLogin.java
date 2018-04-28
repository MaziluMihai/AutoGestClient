package ro.autogest.client.repository.model;

public class CredentialeLogin {

    private String email;
    private String parola;

    public CredentialeLogin(String email, String password) {
        this.email = email;
        this.parola = password;
    }

    public CredentialeLogin() {
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getParola() {
        return parola;
    }
    public void setParola(String parola) {
        this.parola = parola;
    }
}
