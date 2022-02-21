package MODEL;

public class Professor extends Pessoa {

    private String cpf;

    public Professor(String nome) {}

    public Professor(String nome, String cpf, String telefone, int id) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.id = id;
    }

    public Professor(String nome, String cpf, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
