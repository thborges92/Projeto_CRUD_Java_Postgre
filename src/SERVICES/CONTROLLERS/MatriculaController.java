package SERVICES.CONTROLLERS;

import MODEL.Matricula;
import SERVICES.CONNECT_DB.ConexaoPostgreSQL;
import SERVICES.Get;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class MatriculaController {

    public static void cadastrar() {

        String aluno, turma;

        System.out.print("NOME DO ALUNO a cadastrar a matrícula: ");
        aluno = Get.texto();

        System.out.print("NOME DA TURMA que o aluno está cadastrado: ");
        turma = Get.texto();

        Matricula r = new Matricula(aluno, turma);

        Connection conexao = ConexaoPostgreSQL.getConexaoPostgreSQL();

        try {

            String sql = "SELECT novaMatricula (?, ?);";

            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setString(1,r.getIdAluno());
            statement.setString(2,r.getIdTurma());

            int rows = statement.executeUpdate();

            if(rows>0){

                System.out.println("\n\n-------------------------------------");
                System.out.println("Matrícula cadastrada com sucesso!");
                System.out.println("-------------------------------------");

            }

        } catch (SQLException throwables) {

            throwables.printStackTrace();

        }

        ConexaoPostgreSQL.fecharConexao();

    }

    public static ArrayList<Matricula> getAll() {

        Connection conexao = ConexaoPostgreSQL.getConexaoPostgreSQL();
        ArrayList<Matricula> lista = new ArrayList<>();

        try {

            String sql = "SELECT * FROM tab_matricula";
            Statement statement = conexao.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {

                lista.add(new Matricula(
                        resultSet.getString("idAluno"),
                        resultSet.getString("idTurma"),
                        resultSet.getInt("id")
                ));

            }

        } catch (SQLException throwables) {

            throwables.printStackTrace();

        }

        ConexaoPostgreSQL.fecharConexao();
        return lista;

    }

    public static void imprimirLista(ArrayList<Matricula> l){

        if(l.isEmpty()) {

            System.out.println("\n---------------------------------------");
            System.out.println("Não há matrículas cadastradas!");
            System.out.println("---------------------------------------");

        } else {

            for(Matricula r : l) {

                System.out.println("--------------------------------------------------------------------------");
                System.out.println("ID: " + r.getId());
                System.out.println("Aluno: " + r.getIdAluno());
                System.out.println("Turma: " + r.getIdTurma());
                System.out.println("--------------------------------------------------------------------------");

            }

            LocalDate data = LocalDate.now();
            System.out.println("------------Gerado em: " + data.getDayOfMonth() + "/" + (data.getMonthValue()) + "/" + data.getYear()+"------------");

        }

    }

    public static void imprimirListaMatAluno(ArrayList<Matricula> l){

        if(l.isEmpty()) {

            System.out.println("\n---------------------------------------");
            System.out.println("Não há matrículas cadastradas!");
            System.out.println("---------------------------------------");

        } else {

            for(Matricula r : l) {

                System.out.println("--------------------------------------------------------------------------");
                System.out.println("ID: " + r.getId());
                System.out.println("Aluno: " + r.getIdAluno());
                System.out.println("--------------------------------------------------------------------------");

            }

            LocalDate data = LocalDate.now();
            System.out.println("------------Gerado em: " + data.getDayOfMonth() + "/" + (data.getMonthValue()) + "/" + data.getYear()+"------------");

        }

    }

    public static ArrayList<Matricula> getRegistrationByName(String nome) {

        Connection conexao = ConexaoPostgreSQL.getConexaoPostgreSQL();
        ArrayList<Matricula> lista = new ArrayList<>();

        try {

            String sql = "SELECT idAluno, id, '' AS idTurma FROM getMatriculaAluno('" + nome + "');";
            Statement statement = conexao.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {

                lista.add(new Matricula(
                        resultSet.getString("idAluno"),
                        resultSet.getString("idTurma"),
                        resultSet.getInt("id")
                ));

            }

        } catch (SQLException throwables) {

            throwables.printStackTrace();

        }

        ConexaoPostgreSQL.fecharConexao();
        return lista;

    }

    public static void imprimirListaMatTurma(ArrayList<Matricula> l){

        if(l.isEmpty()) {

            System.out.println("\n---------------------------------------");
            System.out.println("Não há matrículas cadastradas!");
            System.out.println("---------------------------------------");

        } else {

            for(Matricula r : l) {

                System.out.println("--------------------------------------------------------------------------");
                System.out.println("ID: " + r.getId());
                System.out.println("Turma: " + r.getIdTurma());
                System.out.println("--------------------------------------------------------------------------");

            }

            LocalDate data = LocalDate.now();
            System.out.println("------------Gerado em: " + data.getDayOfMonth() + "/" + (data.getMonthValue()) + "/" + data.getYear()+"------------");

        }

    }

    public static ArrayList<Matricula> getRegistrationByClass(String nome) {

        Connection conexao = ConexaoPostgreSQL.getConexaoPostgreSQL();
        ArrayList<Matricula> lista = new ArrayList<>();

        try {

            String sql = "SELECT idTurma, id, '' AS idAluno FROM getMatriculaTurma('" + nome + "');";
            Statement statement = conexao.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {

                lista.add(new Matricula(
                        resultSet.getString("idAluno"),
                        resultSet.getString("idTurma"),
                        resultSet.getInt("id")
                ));

            }

        } catch (SQLException throwables) {

            throwables.printStackTrace();

        }

        ConexaoPostgreSQL.fecharConexao();
        return lista;

    }

    public static boolean deleteRegistration(String nome) {

        Connection conexao = ConexaoPostgreSQL.getConexaoPostgreSQL();
        ArrayList<Matricula> lista = getRegistrationByName(nome);

        System.out.println("========MATRÍCULAS COM NOME " + nome.toUpperCase() + "========");
        imprimirLista(lista);

        System.out.print("Confirme o ID à ser excluído: nº ");

        int id = Get.inteiro();

        try {

            String sql = "DELETE FROM tab_matricula WHERE id = ?";

            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setInt(1,id);

            int rows = statement.executeUpdate();

            if(rows>0){

                System.out.println("\n\n-------------------------------------");

            }

            return true;

        } catch (SQLException throwables) {

            throwables.printStackTrace();

        }

        ConexaoPostgreSQL.fecharConexao();

        return false;

    }

}
