package SERVICES.CONTROLLERS;

import MODEL.Aluno;
import SERVICES.CONNECT_DB.ConexaoPostgreSQL;
import SERVICES.Get;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class AlunoController {

    public static void cadastrar() throws ParseException {

        String nome, telefone, endereco, nascimento;

        System.out.print("Nome do aluno: ");
        nome = Get.texto();

        System.out.print("Telefone: ");
        telefone = Get.texto();

        System.out.print("Endereço: ");
        endereco = Get.texto();

        System.out.print("Data de Nascimento: ");
        nascimento = Get.texto();

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date dataFormatada = formato.parse(nascimento);
        java.sql.Date sqlDate = new java.sql.Date(dataFormatada.getTime());

        Aluno s = new Aluno(nome, telefone, endereco, nascimento);

        Connection conexao = ConexaoPostgreSQL.getConexaoPostgreSQL();

        try {

            String sql = "SELECT novoAluno (?, ?, ?, ?);";

            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setString(1,s.getNome());
            statement.setString(2,s.getTelefone());
            statement.setString(3,s.getEndereco());
            statement.setDate(4,sqlDate);

            int rows = statement.executeUpdate();

            if(rows>0){

                System.out.println("\n\n-------------------------------------");
                System.out.println("Aluno cadastrado com sucesso!");
                System.out.println("-------------------------------------");

            }

        } catch (SQLException throwables) {

            throwables.printStackTrace();

        }

        ConexaoPostgreSQL.fecharConexao();

    }

    public static ArrayList<Aluno> getAll() {

        Connection conexao = ConexaoPostgreSQL.getConexaoPostgreSQL();
        ArrayList<Aluno> lista = new ArrayList<>();

        try {

            String sql = "SELECT * FROM tab_aluno";
            Statement statement = conexao.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {

                lista.add(new Aluno(
                        resultSet.getString("nome"),
                        resultSet.getString("telefone"),
                        resultSet.getString("endereco"),
                        resultSet.getString("nascimento"),
                        resultSet.getInt("id")
                ));

            }

        } catch (SQLException throwables) {

            throwables.printStackTrace();

        }

        ConexaoPostgreSQL.fecharConexao();
        return lista;

    }

    public static void imprimirLista(ArrayList<Aluno> l){

        if(l.isEmpty()) {

            System.out.println("\n---------------------------------------");
            System.out.println("Não há alunos cadastrados!");
            System.out.println("---------------------------------------");

        } else {

            for(Aluno s : l) {

                System.out.println("--------------------------------------------------------------------------");
                System.out.println("ID: " + s.getId());
                System.out.println("Nome: " + s.getNome());
                System.out.println("Telefone: " + s.getTelefone());
                System.out.println("Endereço: " + s.getEndereco());
                System.out.println("Data de Nascimento: " + s.getNascimento());
                System.out.println("--------------------------------------------------------------------------");

            }

            LocalDate data = LocalDate.now();
            System.out.println("------------Gerado em: " + data.getDayOfMonth() + "/" + (data.getMonthValue()) + "/" + data.getYear()+"------------");

        }

    }

    public static ArrayList<Aluno> getStudentByName(String nome) {

        Connection conexao = ConexaoPostgreSQL.getConexaoPostgreSQL();
        ArrayList<Aluno> lista = new ArrayList<>();

        try {

            String sql = "SELECT * FROM tab_aluno WHERE nome like '%" + nome + "%'";
            Statement statement = conexao.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {

                lista.add(new Aluno(
                        resultSet.getString("nome"),
                        resultSet.getString("telefone"),
                        resultSet.getString("endereco"),
                        resultSet.getString("nascimento"),
                        resultSet.getInt("id")
                ));

            }

        } catch (SQLException throwables) {

            throwables.printStackTrace();

        }

        ConexaoPostgreSQL.fecharConexao();
        return lista;

    }

    public static void imprimirListaAlunoReg(ArrayList<Aluno> l){

        if(l.isEmpty()) {

            System.out.println("\n---------------------------------------");
            System.out.println("Não há alunos cadastrados!");
            System.out.println("---------------------------------------");

        } else {

            for(Aluno s : l) {

                System.out.println("--------------------------------------------------------------------------");
                System.out.println("ID: " + s.getId());
                System.out.println("Nome: " + s.getNome());
                System.out.println("--------------------------------------------------------------------------");

            }

            LocalDate data = LocalDate.now();
            System.out.println("------------Gerado em: " + data.getDayOfMonth() + "/" + (data.getMonthValue()) + "/" + data.getYear()+"------------");

        }

    }

    public static ArrayList<Aluno> getStudentByRegistration() {

        Connection conexao = ConexaoPostgreSQL.getConexaoPostgreSQL();
        ArrayList<Aluno> lista = new ArrayList<>();

        try {

            String sql = "SELECT id, nome, '' AS telefone, '' AS endereco, '' AS nascimento FROM getAlunoMatricula();";
            Statement statement = conexao.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {

                lista.add(new Aluno(
                        resultSet.getString("nome"),
                        resultSet.getString("telefone"),
                        resultSet.getString("endereco"),
                        resultSet.getString("nascimento"),
                        resultSet.getInt("id")
                ));

            }

        } catch (SQLException throwables) {

            throwables.printStackTrace();

        }

        ConexaoPostgreSQL.fecharConexao();
        return lista;

    }

    public static ArrayList<Aluno> getStudentByAge() {

        Connection conexao = ConexaoPostgreSQL.getConexaoPostgreSQL();
        ArrayList<Aluno> lista = new ArrayList<>();

        try {

            String sql = "SELECT nome, idade, '' AS nascimento, '' AS telefone, '' AS endereco, '' AS id FROM getAlunoIdade();";
            Statement statement = conexao.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {

                lista.add(new Aluno(
                        resultSet.getString("nome"),
                        resultSet.getString("telefone"),
                        resultSet.getString("endereco"),
                        resultSet.getString("nascimento"),
                        resultSet.getInt("id")
                ));

            }

        } catch (SQLException throwables) {

            throwables.printStackTrace();

        }

        ConexaoPostgreSQL.fecharConexao();
        return lista;

    }

    public static ArrayList<Aluno> getStudentByBirthday(int mes) {

        Connection conexao = ConexaoPostgreSQL.getConexaoPostgreSQL();
        ArrayList<Aluno> lista = new ArrayList<>();

        try {

            String sql = "SELECT nome, nascimento, '' AS telefone, '' AS endereco, '' AS id FROM getAniversarianteMes(" + mes + ");";
            Statement statement = conexao.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {

                lista.add(new Aluno(
                        resultSet.getString("nome"),
                        resultSet.getString("telefone"),
                        resultSet.getString("endereco"),
                        resultSet.getString("nascimento"),
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
        ArrayList<Aluno> lista = getStudentByName(nome);

        System.out.println("========ALUNOS COM NOME " + nome.toUpperCase() + "========");
        imprimirLista(lista);

        System.out.print("Confirme o ID à ser excluído: nº ");

        int id = Get.inteiro();

        try {

            String sql = "DELETE FROM tab_aluno WHERE id = ?";

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
