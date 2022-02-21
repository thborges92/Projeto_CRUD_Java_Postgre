package SERVICES.CONNECT_DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoPostgreSQL {

    public static String status = "Não iniciado.";

    public ConexaoPostgreSQL(){}

    public static Connection getConexaoPostgreSQL(){
        Connection connection = null;

        try{
            //Carregar o Driver de conexão
            String driverName = "org.postgresql.Driver";
            Class.forName(driverName);

            //DADOS DA CONEXÃO
            String serverName = "localhost";
            String myDataBase = "ProjetoFinal_java_pg";
            String url = "jdbc:postgresql://"+serverName+"/"+myDataBase;

            String username = "postgres";
            String password = "fl4meng0";

            connection = DriverManager.getConnection(url, username, password);

        } catch (ClassNotFoundException e) {

            System.err.println("\n\nFalha ao carregar!\n\n");

        } catch (SQLException e) {

            System.err.println("\n\nNão foi possível conectar ao banco!");
            System.out.println(e + "\n\n");

        }

        return connection;

    }

    public static String StatusConnection(){
        return status;
    }

    public static boolean fecharConexao(){

        try {

            ConexaoPostgreSQL.getConexaoPostgreSQL().close();
            return true;

        } catch (SQLException e) {

            System.err.println("Falha ao fechar a conexão!");
            System.out.println(e);

        }

        return false;

    }

    public static Connection ReiniciarConexao(){

        fecharConexao();

        return getConexaoPostgreSQL();

    }

}
