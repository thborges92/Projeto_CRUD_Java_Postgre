package MODEL;

public class Atividade {

    private String nome, turno, professor;
    private int id;

    public Atividade(String nome) {}

    public Atividade(String nome, String turno, String professor, int id) {
        this.nome = nome;
        this.turno = turno;
        this.professor = professor;
        this.id = id;
    }

    public Atividade(String nome, String turno, String professor) {
        this.nome = nome;
        this.turno = turno;
        this.professor = professor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }
}
