package com.todouno.todone.clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fernando
 */
public class Conexciondb {

    private final String puerto = "3306";
    private final String nomservidor = "localhost";
    private final String db = "todone";
    private final String user = "root";
    private final String pass = "ADfrmas";

    public Conexciondb() {
    }

    public Connection conectar() {
        Connection conn = null;
        try {
            String ruta = "jdbc:mysql://";
            String servidor = nomservidor + ":" + puerto + "/";
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(ruta + servidor + db, user, pass);

            if (conn != null) {
                Logger.getLogger(Conexciondb.class.getName()).log(Level.INFO, "Conectado...");
            } else if (conn == null) {
                throw new SQLException();
            }
        } catch (SQLException | ClassNotFoundException | NullPointerException e) {
            Logger.getLogger(Conexciondb.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
        return conn;
    }

}
