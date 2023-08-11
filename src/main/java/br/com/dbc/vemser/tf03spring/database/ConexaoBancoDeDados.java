package br.com.dbc.vemser.tf03spring.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBancoDeDados {

    // todo: criar profile e definir as variáveis de ambiente
    private static final String SERVER = "vemser-hml.dbccompany.com.br";
    private static final String PORT = "25000";
    private static final String DATABASE = "xe";

    private static final String USER = "EQUIPE_2";
    private static final String PASS = "oracle";
    private static final String SCHEMA = "EQUIPE_2";

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:oracle:thin:@" + SERVER + ":" + PORT + ":" + DATABASE;
        // jdbc:oracle:thin:@localhost:1521:xe

        // abrir a conexão com o Banco de Dados
        Connection con = DriverManager.getConnection(url, USER, PASS);

        // sempre usar o schema vem_ser
        con.createStatement().execute("alter session set current_schema=" + SCHEMA);

        return con;
    }

}
