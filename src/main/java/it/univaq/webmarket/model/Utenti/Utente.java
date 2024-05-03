package it.univaq.webmarket.model.Utenti;

public abstract class Utente {
    private String email;
    private String password;
    private Integer id;

    public Utente(Integer id,String email, String password) {
        this.email = email;
        this.password = password;
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Integer getId() {
        return id;
    }
}
