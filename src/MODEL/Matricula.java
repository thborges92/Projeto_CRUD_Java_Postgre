package MODEL;

public class Matricula {

    private int id;
    private String idAluno, idTurma;

    public Matricula() {}

    public Matricula(String idAluno, String idTurma, int id) {
        this.idAluno = idAluno;
        this.idTurma = idTurma;
        this.id = id;
    }

    public Matricula(String idAluno, String idTurma) {
        this.idAluno = idAluno;
        this.idTurma = idTurma;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(String idAluno) {
        this.idAluno = idAluno;
    }

    public String getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(String idTurma) {
        this.idTurma = idTurma;
    }

}
