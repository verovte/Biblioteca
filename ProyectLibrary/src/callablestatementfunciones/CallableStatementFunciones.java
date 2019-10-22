
package callablestatementfunciones;

import modelo.database;
import modelo.Usuario;
import oracle.jdbc.*;
import java.sql.*;

public class CallableStatementFunciones {

    public static void main(String[] args) {
       // int empleadoId = 60; // indentificadora recuperar salario
     
        Usuario u = new Usuario();
        try {
            Connection con = database.getConnection();
            CallableStatement cstmt = null;
         
            
           
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
