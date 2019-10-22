/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.SQLException;
import vistas.Vista;

/**
 *
 * @author juani
 */
public class Main {
    	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		new Controlador(new Vista()).iniciar();
		 
	}
}
