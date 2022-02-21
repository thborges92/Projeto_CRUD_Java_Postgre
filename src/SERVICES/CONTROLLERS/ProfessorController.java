package SERVICES.CONTROLLERS;

import MODEL.Professor;
import SERVICES.CONNECT_DB.ConexaoPostgreSQL;
import SERVICES.Get;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ProfessorController {

    public static void cadastrar() {

        String nome, cpf, telefone;

        System.out.print("Nome do Professor: ");
        nome = Get.texto();

        System.out.print("CPF: ");
        cpf = Get.texto();

        System.out.print("Telefone: ");
        telefone = Get.texto();

        Professor t = new Professor(nome, cpf, telefone);

        Connection conexao = ConexaoPostgreSQL.getConexaoPostgreSQL();

        try {

            String sql = "SELECT novoProfessor (?, ?, ?);";

            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setString(1,t.getNome());
            statement.setString(2,t.getCpf());
            statement.setString(3,t.getTelefone());

            int rows = statement.executeUpdate();

            if(rows>0){

                System.out.println("\n\n-------------------------------------");
                System.out.println("Professor cadastrado com sucesso!");
                System.out.println("-------------------------------------");

            }

        } catch (SQLException throwables) {

            throwables.printStackTrace();

        }

        ConexaoPostgreSQL.fecharConexao();

    }

    public static ArrayList<Professor> getAll() {

        Connection conexao = ConexaoPostgreSQL.getConexaoPostgreSQL();
        ArrayList<Professor> lista = new ArrayList<>();

        try {

            String sql = "SELECT * FROM tab_professor";
            Statement statement = conexao.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {

                lista.add(new Professor(
                        resultSet.getString("nome"),
                        resultSet.getString("cpf"),
                        resultSet.getString("telefone"),
                        resultSet.getInt("id")
                ));

            }

        } catch (SQLException throwables) {

            throwables.printStackTrace();

        }

        ConexaoPostgreSQL.fecharConexao();
        return lista;

    }

    public static void imprimirLista(ArrayList<Professor> l){

        if(l.isEmpty()) {

            System.out.println("\n---------------------------------------");
            System.out.println("Não há professores cadastrados!");
            System.out.println("---------------------------------------");

        } else {

            for(Professor t : l) {

                System.out.println("--------------------------------------------------------------------------");
                System.out.println("ID: " + t.getId());
                System.out.println("Nome: " + t.getNome());
                System.out.println("CPF: " + t.getCpf());
                System.out.println("Telefone: " + t.getTelefone());
                System.out.println("--------------------------------------------------------------------------");

            }

            LocalDate data = LocalDate.now();
            System.out.println("------------Gerado em: " + data.getDayOfMonth() + "/" + (data.getMonthValue()) + "/" + data.getYear()+"------------");

        }

    }

    public static ArrayList<Professor> getCoachByName(String nome) {

        Connection conexao = ConexaoPostgreSQL.getConexaoPostgreSQL();
        ArrayList<Professor> lista = new ArrayList<>();

        try {

            String sql = "SELECT * FROM tab_professor WHERE nome like '%" + nome + "%'";
            Statement statement = conexao.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {

                lista.add(new Professor(
                        resultSet.getString("nome"),
                        resultSet.getString("cpf"),
                        resultSet.getString("telefone"),
                        resultSet.getInt("id")
                ));

            }

        } catch (SQLException throwables) {

            throwables.printStackTrace();

        }

        ConexaoPostgreSQL.fecharConexao();
        return lista;

    }

    public static void imprimirListaProfAtv(ArrayList<Professor> l){

        if(l.isEmpty()) {

            System.out.println("\n---------------------------------------");
            System.out.println("Não há professores cadastrados!");
            System.out.println("---------------------------------------");

        } else {

            for(Professor t : l) {

                System.out.println("--------------------------------------------------------------------------");
                System.out.println("ID: " + t.getId());
                System.out.println("Nome: " + t.getNome());
                System.out.println("--------------------------------------------------------------------------");

            }

            LocalDate data = LocalDate.now();
            System.out.println("------------Gerado em: " + data.getDayOfMonth() + "/" + (data.getMonthValue()) + "/" + data.getYear()+"------------");

        }

    }

    public static ArrayList<Professor> getCoachByActivity(String nome) {

        Connection conexao = ConexaoPostgreSQL.getConexaoPostgreSQL();
        ArrayList<Professor> lista = new ArrayList<>();

        try {

            String sql = "SELECT id, nome, '' AS cpf, '' AS telefone FROM getProfessorAtividade('" + nome + "');";
            Statement statement = conexao.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {

                lista.add(new Professor(
                        resultSet.getString("nome"),
                        resultSet.getString("cpf"),
                        resultSet.getString("telefone"),
                        resultSet.getInt("id")
                ));

            }

        } catch (SQLException throwables) {

            throwables.printStackTrace();

        }

        ConexaoPostgreSQL.fecharConexao();
        return lista;

    }

    public static boolean deleteByName(String nome) {

        Connection conexao = ConexaoPostgreSQL.getConexaoPostgreSQL();
        ArrayList<Professor> lista = getCoachByName(nome);

        System.out.println("========PROFESSOR COM NOME " + nome.toUpperCase() + "========");
        imprimirLista(lista);

        System.out.print("Confirme o ID à ser excluído: nº ");

        int id = Get.inteiro();

        try {

            String sql = "DELETE FROM tab_professor WHERE id = ?";

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
