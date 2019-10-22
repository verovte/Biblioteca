/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author verovte
 */
public class Prestamo {
    
    Connection con;
    CallableStatement cstmt = null;
    String nombre,dni,apellido,titulo,finicio,ffin,finplazo;
    int size, id;
	
	
    public int insertarPrestamo(int idUsuario, int idLibro, String fechInicio) throws SQLException {
		//EJEMPLO PROCEDIMIENTO
        int control=0;
	con=database.getConnection();
	cstmt = con.prepareCall("{call INSERTAR_PRESTAMO(?,?,?,?)}");
	cstmt.setInt(1, idUsuario);
	cstmt.setInt(2, idLibro);
	cstmt.setString(3, fechInicio);
	cstmt.registerOutParameter(4, java.sql.Types.INTEGER);
        cstmt.execute();
        control=cstmt.getInt(4);
        
        if(control==1){
            System.out.println("Prestamo realizado");
        } else {
            System.out.println("Prestamo no realizado");
        }
        	 
        cstmt.close();
	con.close();
	return control;
        
    }
	
    public void devolverPrestamo(int idPrestamo, String fechFin) throws SQLException {
		//EJEMPLO PROCEDIMIENTO
        con=database.getConnection();
	cstmt = con.prepareCall("{call DEVOLVER_PRESTAMO(?,?)}");
	cstmt.setInt(1, idPrestamo);
	cstmt.setString(2, fechFin);
        cstmt.execute();
        cstmt.close();
	con.close();
        listarPrestamo();
    }
	
    public void borrarPrestamo(int id) throws SQLException {
		//EJEMPLO PROCEDIMIENTO
	con=database.getConnection();
	cstmt = con.prepareCall("{call BORRAR_PRESTAMO(?)}");
        cstmt.setInt(1, id);
        cstmt.execute();
        cstmt.close();
	con.close();
        listarPrestamo();
    }
	
    public DefaultTableModel listarPrestamo() {

	size=0;
	String[] headers = { "ID","Usuario","Titulo","F.Inicio","F.Fin","Fin Plazo"};
	DefaultTableModel plantilla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
		return false;
            }
	};

	try {
            con=database.getConnection();
            cstmt=con.prepareCall("{call CONSULTA_PRESTAMOS.obtener_prestamos(?)}");
	    cstmt.registerOutParameter(1, OracleTypes.CURSOR);
	    cstmt.executeQuery();
	    ResultSet cursor = (ResultSet)cstmt.getObject(1);
		    
	    while(cursor.next()) {
	    	size++;
	    }

            cursor.close();
	    cstmt.close();
	    con.close();
	
        } catch (Exception e) {
            e.printStackTrace();
	}

	String[][] filas = new String[size][6];

	try {
            con=database.getConnection();
            cstmt=con.prepareCall("{call CONSULTA_PRESTAMOS.obtener_prestamos(?)}");
	    cstmt.registerOutParameter(1, OracleTypes.CURSOR);
	    cstmt.executeQuery();
	    ResultSet cursor = (ResultSet)cstmt.getObject(1);
            int i = 0;
	
            while (cursor.next()) {
		id = cursor.getInt(1);
		nombre=cursor.getString(2);
		titulo=cursor.getString(3);
		finicio=cursor.getString(4).substring(0,10);
	
                if(cursor.getString(5) != null) 
                    ffin=cursor.getString(5).substring(0,10);
		else
                    ffin=cursor.getString(5);
                
                finplazo=cursor.getString(6).substring(0,10);
				
		filas[i][0] = Integer.toString(id);
		filas[i][1] = nombre;
		filas[i][2] = titulo;
		filas[i][3] = finicio;
		filas[i][4] = ffin;
		filas[i][5] = finplazo;
		i++;
		System.out.println(filas.toString());
            }
            con.close();
            plantilla.setDataVector(filas, headers);
	
        } catch (Exception e) {
            e.printStackTrace();
	}
            return plantilla;
    }
}
