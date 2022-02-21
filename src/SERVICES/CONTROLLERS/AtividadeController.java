package SERVICES.CONTROLLERS;

import MODEL.Atividade;
import SERVICES.CONNECT_DB.ConexaoPostgreSQL;
import SERVICES.Get;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class AtividadeController {

    public static void cadastrar() {

        String nome, turno, professor;

        System.out.print("Nome da Atividade: ");
        nome = Get.texto();

        System.out.print("Turno: ");
        turno = Get.texto();

        System.out.print("Professor: ");
        professor = Get.texto();

        Atividade a = new Atividade(nome, turno, professor);

        Connection conexao = ConexaoPostgreSQL.getConexaoPostgreSQL();

        try {

            String sql = "SELECT novaAtividade (?, ?, ?);";

            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setString(1,a.getNome());
            statement.setString(2,a.getTurno());
            statement.setString(3,a.getProfessor());

            int rows = statement.executeUpdate();

            if(rows>0){

                System.out.println("\n\n-------------------------------------");
                System.out.println("Atividade cadastrada com sucesso!");
                System.out.println("-------------------------------------");

            }

        } catch (SQLException throwables) {

            throwables.printStackTrace();

        }

        ConexaoPostgreSQL.fecharConexao();

    }

    public static ArrayList<Atividade> getAll() {

        Connection conexao = ConexaoPostgreSQL.getConexaoPostgreSQL();
        ArrayList<Atividade> lista = new ArrayList<>();

        try {

            String sql = "SELECT * FROM tab_atividade";
            Statement statement = conexao.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {

                lista.add(new Atividade(
                        resultSet.getString("nome"),
                        resultSet.getString("turno"),
                        resultSet.getString("idProfessor"),
                        resultSet.getInt("id")
                ));

            }

        } catch (SQLException throwables) {

            throwables.printStackTrace();

        }

        ConexaoPostgreSQL.fecharConexao();
        return lista;

    }

    public static void imprimirLista(ArrayList<Atividade> l){

        if(l.isEmpty()) {

            System.out.println("\n---------------------------------------");
            System.out.println("Não há atividades cadastradas!");
            System.out.println("---------------------------------------");

        } else {

            for(Atividade a : l) {

                System.out.println("--------------------------------------------------------------------------");
                System.out.println("ID: " + a.getId());
                System.out.println("Nome: " + a.getNome());
                System.out.println("Turno: " + a.getTurno());
                System.out.println("Professor: " + a.getProfessor());
                System.out.println("--------------------------------------------------------------------------");

            }

            LocalDate data = LocalDate.now();
            System.out.println("------------Gerado em: " + data.getDayOfMonth() + "/" + (data.getMonthValue()) + "/" + data.getYear()+"------------");

        }

    }

    public static ArrayList<Atividade> getActivityByShift(String nome) {

        Connection conexao = ConexaoPostgreSQL.getConexaoPostgreSQL();
        ArrayList<Atividade> lista = new ArrayList<>();

        try {

            String sql = "SELECT * FROM tab_atividade WHERE turno like '%" + nome + "%'";
            Statement statement = conexao.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {

                lista.add(new Atividade(
                        resultSet.getString("nome"),
                        resultSet.getString("turno"),
                        resultSet.getString("idProfessor"),
                        resultSet.getInt("id")
                ));

            }

        } catch (SQLException throwables) {

            throwables.printStackTrace();

        }

        ConexaoPostgreSQL.fecharConexao();
        return lista;

    }

    public static boolean deleteByShift(String nome) {

        Connection conexao = ConexaoPostgreSQL.getConexaoPostgreSQL();
        ArrayList<Atividade> lista = getActivityByShift(nome);

        System.out.println("========ATIVIDADES DO TURNO - " + nome.toUpperCase() + "========");
        imprimirLista(lista);

        System.out.print("Confirme o ID à ser excluído: nº ");

        int id = Get.inteiro();

        try {

            String sql = "DELETE FROM tab_atividade WHERE id = ?";

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
