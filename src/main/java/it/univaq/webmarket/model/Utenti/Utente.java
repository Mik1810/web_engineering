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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
