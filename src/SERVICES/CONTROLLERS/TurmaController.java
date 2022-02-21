package SERVICES.CONTROLLERS;

import MODEL.Turma;
import SERVICES.CONNECT_DB.ConexaoPostgreSQL;
import SERVICES.Get;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class TurmaController {

    public static void cadastrar() {

        String nome, professor, atividade;

        System.out.print("Nome da Turma: ");
        nome = Get.texto();

        System.out.print("Professor: ");
        professor = Get.texto();

        System.out.print("Atividade: ");
        atividade = Get.texto();

        Turma c = new Turma(nome, professor, atividade);

        Connection conexao = ConexaoPostgreSQL.getConexaoPostgreSQL();

        try {

            String sql = "SELECT novaTurma (?, ?, ?);";

            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setString(1,c.getNome());
            statement.setString(2,c.getProfessor());
            statement.setString(3,c.getAtividade());

            int rows = statement.executeUpdate();

            if(rows>0){

                System.out.println("\n\n-------------------------------------");
                System.out.println("Turma cadastrada com sucesso!");
                System.out.println("-------------------------------------");

            }

        } catch (SQLException throwables) {

            throwables.printStackTrace();

        }

        ConexaoPostgreSQL.fecharConexao();

    }

    public static ArrayList<Turma> getAll() {

        Connection conexao = ConexaoPostgreSQL.getConexaoPostgreSQL();
        ArrayList<Turma> lista = new ArrayList<>();

        try {

            String sql = "SELECT * FROM tab_turma";
            Statement statement = conexao.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {

                lista.add(new Turma(
                        resultSet.getString("nome"),
                        resultSet.getString("idProfessor"),
                        resultSet.getString("idAtividade"),
                        resultSet.getInt("id")
                ));

            }

        } catch (SQLException throwables) {

            throwables.printStackTrace();

        }

        ConexaoPostgreSQL.fecharConexao();
        return lista;

    }

    public static void imprimirLista(ArrayList<Turma> l){

        if(l.isEmpty()) {

            System.out.println("\n---------------------------------------");
            System.out.println("Não há turmas cadastradas!");
            System.out.println("---------------------------------------");

        } else {

            for(Turma c : l) {

                System.out.println("--------------------------------------------------------------------------");
                System.out.println("ID: " + c.getId());
                System.out.println("Nome: " + c.getNome());
                System.out.println("Professor: " + c.getProfessor());
                System.out.println("Atividade: " + c.getAtividade());
                System.out.println("--------------------------------------------------------------------------");

            }

            LocalDate data = LocalDate.now();
            System.out.println("------------Gerado em: " + data.getDayOfMonth() + "/" + (data.getMonthValue()) + "/" + data.getYear()+"------------");

        }

    }

    public static ArrayList<Turma> getClassByName(String nome) {

        Connection conexao = ConexaoPostgreSQL.getConexaoPostgreSQL();
        ArrayList<Turma> lista = new ArrayList<>();

        try {

            String sql = "SELECT * FROM tab_turma WHERE nome like '%" + nome + "%'";
            Statement statement = conexao.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {

                lista.add(new Turma(
                        resultSet.getString("nome"),
                        resultSet.getString("idProfessor"),
                        resultSet.getString("idAtividade"),
                        resultSet.getInt("id")
                ));

            }

        } catch (SQLException throwables) {

            throwables.printStackTrace();

        }

        ConexaoPostgreSQL.fecharConexao();
        return lista;

    }

    public static void imprimirListaTurmaAtv(ArrayList<Turma> l){

        if(l.isEmpty()) {

            System.out.println("\n---------------------------------------");
            System.out.println("Não há turmas cadastradas!");
            System.out.println("---------------------------------------");

        } else {

            for(Turma c : l) {

                System.out.println("--------------------------------------------------------------------------");
                System.out.println("ID: " + c.getId());
                System.out.println("Turma: " + c.getNome());
                System.out.println("--------------------------------------------------------------------------");

            }

            LocalDate data = LocalDate.now();
            System.out.println("------------Gerado em: " + data.getDayOfMonth() + "/" + (data.getMonthValue()) + "/" + data.getYear()+"------------");

        }

    }

    public static ArrayList<Turma> getClassByActivity(String nome) {

        Connection conexao = ConexaoPostgreSQL.getConexaoPostgreSQL();
        ArrayList<Turma> lista = new ArrayList<>();

        try {

            String sql = "SELECT id, nome, '' AS idAtividade, '' AS idProfessor FROM getTurmaAtividade('" + nome + "');";
            Statement statement = conexao.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {

                lista.add(new Turma(
                        resultSet.getString("nome"),
                        resultSet.getString("idProfessor"),
                        resultSet.getString("idAtividade"),
                        resultSet.getInt("id")
                ));

            }

        } catch (SQLException throwables) {

            throwables.printStackTrace();

        }

        ConexaoPostgreSQL.fecharConexao();
        return lista;

    }

    public static void imprimirListaTurmaProf(ArrayList<Turma> l){

        if(l.isEmpty()) {

            System.out.println("\n---------------------------------------");
            System.out.println("Não há turmas cadastradas!");
            System.out.println("---------------------------------------");

        } else {

            for(Turma c : l) {

                System.out.println("--------------------------------------------------------------------------");
                System.out.println("ID: " + c.getId());
                System.out.println("Turma: " + c.getNome());
                System.out.println("--------------------------------------------------------------------------");

            }

            LocalDate data = LocalDate.now();
            System.out.println("------------Gerado em: " + data.getDayOfMonth() + "/" + (data.getMonthValue()) + "/" + data.getYear()+"------------");

        }

    }

    public static ArrayList<Turma> getClassByCoach(String nome) {

        Connection conexao = ConexaoPostgreSQL.getConexaoPostgreSQL();
        ArrayList<Turma> lista = new ArrayList<>();

        try {

            String sql = "SELECT id, nome, '' AS idProfessor, '' AS idAtividade FROM getTurmaProfessor('" + nome + "');";
            Statement statement = conexao.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {

                lista.add(new Turma(
                        resultSet.getString("nome"),
                        resultSet.getString("idProfessor"),
                        resultSet.getString("idAtividade"),
                        resultSet.getInt("id")
                ));

            }

        } catch (SQLException throwables) {

            throwables.printStackTrace();

        }

        ConexaoPostgreSQL.fecharConexao();
        return lista;

    }

    public static boolean deleteClassByName(String nome) {

        Connection conexao = ConexaoPostgreSQL.getConexaoPostgreSQL();
        ArrayList<Turma> lista = getClassByName(nome);

        System.out.println("========TURMA COM NOME " + nome.toUpperCase() + "========");
        imprimirLista(lista);

        System.out.print("Confirme o ID à ser excluído: nº ");

        int id = Get.inteiro();

        try {

            String sql = "DELETE FROM tab_turma WHERE id = ?";

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
