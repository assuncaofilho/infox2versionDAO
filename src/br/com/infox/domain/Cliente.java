package br.com.infox.domain;

public class Cliente {

    private int id;
    private String nome;
    private String end;
    private String fone;
    private String email;

    public Cliente(int id, String nome, String end, String fone, String email) {
        this.id = id;
        this.nome = nome;
        this.end = end;
        this.fone = fone;
        this.email = email;
    }

    public Cliente(String nome, String end, String fone, String email) {
        this.nome = nome;
        this.end = end;
        this.fone = fone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", nome=" + nome + ", end=" + end + ", fone=" + fone + ", email=" + email + '}';
    }
    
    

}
