package MODEL;

public class Aluno extends Pessoa {

    private String endereco, nascimento;

    public Aluno(String nome) {}

    public Aluno(String nome, String telefone, String endereco, String nascimento, int id) {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.nascimento = nascimento;
        this.id = id;
    }

    public Aluno(String nome, String telefone, String endereco, String nascimento) {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.nascimento = nascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

}
