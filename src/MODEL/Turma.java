package MODEL;

public class Turma {

    private String nome, professor, atividade;
    private int id;

    public Turma(String nome) {}

    public Turma(String nome, String professor, String atividade, int id) {
        this.nome = nome;
        this.professor = professor;
        this.atividade = atividade;
        this.id = id;
    }

    public Turma(String nome, String professor, String atividade) {
        this.nome = nome;
        this.professor = professor;
        this.atividade = atividade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getAtividade() {
        return atividade;
    }

    public void setAtividade(String atividade) {
        this.atividade = atividade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
