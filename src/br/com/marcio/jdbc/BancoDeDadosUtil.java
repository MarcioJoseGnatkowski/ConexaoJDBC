
package br.com.marcio.jdbc;

//import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
//import com.mysql.jdbc.jdbc2.optional.MysqlPooledConnection;

//import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BancoDeDadosUtil {

    static Connection PegarConexaoComBanco() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/lojavirtual", "root","root");
    }
    /*
    //conexão com pool de conexões MYSQL
    private DataSource fonteDeDados;

    BancoDeDadosUtil() {
        MysqlConnectionPoolDataSource pool = new MysqlConnectionPoolDataSource();
        pool.setUrl("jdbc:mysql://localhost:3306/lojavirtual");
        pool.setPassword("root");
        pool.setUser("root");
        this.fonteDeDados = pool;
        //https://stackoverflow.com/questions/2353569/am-i-using-java-pooledconnections-correctly
    }

    Connection getConnection() throws SQLException {

        Connection connection = fonteDeDados.getConnection();
        return connection;

    }
    */
}