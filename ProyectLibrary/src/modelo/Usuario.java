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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author verovte
 */
public class Usuario {
    
    Connection con;
    CallableStatement cstmt = null;
    int Ncarnet;
    String direccion;
    String Nombre;
    String Apellido;
    int penalizacion;
    String dni;
    
    public Usuario(int Ncarnet, String Nombre, String Apellido, String dni, String direccion,int penalizacion) {
	super();
	this.Ncarnet=Ncarnet;
        this.dni=dni;
        this.direccion=direccion;
        this.Nombre=Nombre;
        this.penalizacion=penalizacion;
    }
    
    public Usuario(){
        super();
    }
    
    public void consultarUsuarios() throws SQLException {
		//EJEMPLO CURSOR
        con=database.getConnection();
	cstmt=con.prepareCall("{call CONSULTA_USUARIOS.obtener_usuarios(?)}");
	cstmt.registerOutParameter(1, OracleTypes.CURSOR);
	cstmt.executeQuery();
	ResultSet cursor = (ResultSet)cstmt.getObject(1);
	int tamanho=0;
	while(cursor.next()) {
            tamanho++;
	    System.out.println("TAMANHO:"+tamanho+" Carnet = " + cursor.getInt(1)+" Nombre:"+cursor.getString(2)+" Apellido:"+cursor.getString(3)+ " DNI:"+cursor.getString(4)+" Calle:"+cursor.getString(5)+" Ciudad:"+cursor.getString(6)+" CP:"+cursor.getString(7));
	    
	}
	cursor.close();
	cstmt.close();
	con.close();
        listarlectores();
    }
	
    public void insertarUsuario(String dni, String nombre, String calle, String ciudad, int cod_post, int penalizacion) {
        try {
            con = database.getConnection();
            cstmt = con.prepareCall("{call CREAR_USER(?,?,?,?,?,?)}");
            cstmt.setString(1, dni);
            cstmt.setString(2, nombre);
            cstmt.setString(3, calle);
            cstmt.setString(4, ciudad);
            cstmt.setInt(5, cod_post);
            cstmt.setInt(6, penalizacion);
            cstmt.execute();
            cstmt.close();
            con.close();
	} catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
	}

    }
	
    public void modificarUsuario( String nombre, String apellido, String dni,String calle, String ciudad,int cp) throws SQLException {
		//EJEMPLO PROCEDIMIENTO
	con=database.getConnection();
	cstmt = con.prepareCall("{call MODIFICAR_USUARIO(?,?,?,?,?,?)}");
	cstmt.setString(1, nombre);
	cstmt.setString(2, apellido);
	cstmt.setString(3, dni);
	cstmt.setString(4, calle);
	cstmt.setString(5, ciudad);
	cstmt.setInt(6, cp);
        cstmt.execute();
        cstmt.close();
	con.close();
    }
	
    public void borrarUsuario(int carnet) throws SQLException {
		//EJEMPLO PROCEDIMIENTO
        con=database.getConnection();
	cstmt = con.prepareCall("{call ELIMINAR_USER(?)}");
	cstmt.setInt(1, carnet);
        cstmt.execute();
        cstmt.close();
	con.close();
        listarlectores();
    }
	
    public void castigarUsuario(int carnet) throws SQLException {
		//EJEMPLO PROCEDIMIENTO
        con=database.getConnection();
	cstmt = con.prepareCall("{call CASTIGAR_USUARIO(?)}");
	cstmt.setInt(1, carnet);
        cstmt.execute();
        cstmt.close();
	con.close();
    }
	
    public void perdonarUsuario(int carnet) throws SQLException {
		//EJEMPLO PROCEDIMIENTO
	con=database.getConnection();
	cstmt = con.prepareCall("{call PERDONAR_USUARIO(?)}");
	cstmt.setInt(1, carnet);
        cstmt.execute();
        cstmt.close();
	con.close();
    }
    
    public String[] listar_por_id(int id) {

	String[] info = new String[6];
	try {
            con = database.getConnection();
            this.cstmt = con.prepareCall("{call SELECT_USER_ID.obtener_usuarios_ID(?,?)}");
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.setInt(2, id);
            cstmt.execute();
            cstmt.executeQuery();

            ResultSet cursor = (ResultSet) cstmt.getObject(1);
            while (cursor.next()) {
		info[0] = cursor.getString(2);
		info[1] = cursor.getString(3);
		System.out.print(cursor.getString(3) + "ento");
		info[2] = cursor.getString(4);
		info[3] = cursor.getString(5);
		System.out.print(cursor.getInt(6));
		info[4] = Integer.toString(cursor.getInt(6));
		info[5] = Integer.toString(cursor.getInt(7));
            }

            cursor.close();
            cstmt.close();
            con.close();
	} catch (SQLException ex) {

	}
	return info;

    }
	
    public DefaultTableModel listarlectores() {
	int t = 0;
	String[] headers = { "Nº Carnet", "Nombre", "DNI", "Calle", "Ciudad", "Cod_Post", "Penalizacion" };
	DefaultTableModel tabla = new DefaultTableModel();

	try {
            con = database.getConnection();
            this.cstmt = con.prepareCall("{call CONSULTA_USER.obtener_usuarios(?)}");
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.executeQuery();

            ResultSet cursor = (ResultSet) cstmt.getObject(1);
            while (cursor.next()) {
		t++;
            }
            cursor.close();
            cstmt.close();
            con.close();
	
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
	}
	String[][] filas = new String[t][7];

	int Ncarnet, cod_post, penalizacion;
	String nombre, calle, ciudad, dni;

	try {
            con = database.getConnection();
            this.cstmt = con.prepareCall("{call CONSULTA_USER.obtener_usuarios(?)}");
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.executeQuery();
            ResultSet cursor = (ResultSet) cstmt.getObject(1);
            int i = 0;
            while (cursor.next()) {
                Ncarnet = cursor.getInt(1);
		dni = cursor.getString(3);
		nombre = cursor.getString(2);
		calle = cursor.getString(4);
		ciudad = cursor.getString(5);
		cod_post = cursor.getInt(6);
		penalizacion = cursor.getInt(7);

		filas[i][0] = Integer.toString(Ncarnet);
		filas[i][1] = dni;
		filas[i][2] = nombre;
		filas[i][3] = calle;
		filas[i][4] = ciudad;
		filas[i][5] = Integer.toString(cod_post);
		filas[i][6] = Integer.toString(penalizacion);
		i++;
            }
            cursor.close();
            cstmt.close();
            con.close();
            tabla.setDataVector(filas, headers);
	} catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
	}
	return tabla;
    }
    
}
