/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import UtilesVerovte.Utiles;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import modelo.GestorInterfaz;
import modelo.Libros;
import modelo.Prestamo;
import modelo.Usuario;
import vistas.Vista;


/**
 *
 * @author verovte
 */
public class Controlador implements ActionListener,MouseListener{
    
    GestorInterfaz interfaz;
    
    Vista init;
    
    Usuario u = new Usuario();
    
    Libros libro = new Libros();
    
    Prestamo prestamo = new Prestamo();
    
    Utiles utiles = new Utiles();
    
    boolean numCarnetValid, nombreValid, apellidosValid, dniValid, calleValid, ciudadValid, cpValid;
    
    String numCarnet, nombreSocio, apellidosSocio, dniSocio, calleSocio, ciudadSocio;
    
    int cpSocio;
    
    boolean tituloValido, nombreAutorValido, apellidoAutorValido, editorialValido, codigoValido, idValido;
    
    String titulo, nombreAutor, apellidoAutor, editorial;
    
    int codigo;
    
    boolean idUsuarioValido = false, idLibroValido = false, finicioValido = false;
    
    int idUsuario, idLibro,idPrestamo, control = 0;;
    
    String finicio,ffin;
    
    boolean idPrestamoValido=false,ffinValido=false, idPrestamoEliminarValido = false;;
    
    private String[] datosUsuario=new String[6];
    
    private String[] datosLibro = new String[5];
    
    public enum AccionMVC {
        //-----------------------------------USUARIOS----------------------------------------
        __MOSTRAR_USUARIO, __MODIFICAR_USUARIO, __CREAR_USUARIO, __ELIMINAR_USUARIO, __CASTIGAR_USUARIO,__PERDONAR_USUARIO, 
        //-----------------------------------LIBROS----------------------------------------------\\\\\
        __CREAR_LIBRO, __MODIFICAR_LIBRO, __ELIMINAR_LIBRO, __DEVOLVER_LIBRO,__PRESTAR_LIBRO, __CONSULTAR_LIBRO,
         //-----------------------------------PRESTAMO----------------------------------------------\\\\\
	__CREAR_PRESTAMO, __DEVOLVER_PRESTAMO, __ELIMINAR_PRESTAMO, __CONSULTAR_PRESTAMO
		
    }
    
    public Controlador(Vista v){
        this.init= v;
        this.interfaz= new GestorInterfaz(v);
    }
    
    public void iniciar(){
           // Skin tipo WINDOWS
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(init);
            init.setVisible(true);
           
        } catch (UnsupportedLookAndFeelException ex) {}
          catch (ClassNotFoundException ex) {}
          catch (InstantiationException ex) {}
          catch (IllegalAccessException ex) {}
        
        //declara una acción y añade un escucha al evento producido por el componente
        //////////////////USUARIO\\\\\\\\\\\\\\\\\\\
        this.init.btn_socios_guardar.setActionCommand("__CREAR_USUARIO");
        this.init.btn_socios_guardar.addActionListener(this);
        
        this.init.btn_socios_modificar.setActionCommand("__MODIFICAR_USUARIO");
        this.init.btn_socios_modificar.addActionListener(this);
        
        this.init.btn_socios_buscar.setActionCommand("__MOSTRAR_USUARIO");
	this.init.btn_socios_buscar.addActionListener(this);
        
        this.init.btn_socios_eliminar.setActionCommand("__ELIMINAR_USUARIO");
        this.init.btn_socios_eliminar.addActionListener(this);
        
        this.init.btn_socios_penalizar.setActionCommand("__CASTIGAR_USUARIO");
        this.init.btn_socios_penalizar.addActionListener(this);
        
        this.init.btn_socios_perdonar.setActionCommand("__PERDONAR_USUARIO");
        this.init.btn_socios_perdonar.addActionListener(this);
        //////////////////LIBRO\\\\\\\\\\\\\\\\\\\
        this.init.btn_guardar_libro.setActionCommand("__CREAR_LIBRO");
        this.init.btn_guardar_libro.addActionListener(this);
        
        this.init.btn_Libros_Mod.setActionCommand("__MODIFICAR_LIBRO");
        this.init.btn_Libros_Mod.addActionListener(this);
        
        this.init.btn_libro_eliminar.setActionCommand("__ELIMINAR_LIBRO");
        this.init.btn_libro_eliminar.addActionListener(this);
        
        this.init.btn_buscar_libro.setActionCommand("__CONSULTAR_LIBRO");
        this.init.btn_buscar_libro.addActionListener(this);
        
        this.init.btn_libro_disponible.setActionCommand("__DEVOLVER_LIBRO");
        this.init.btn_libro_disponible.addActionListener(this);
        
        this.init.btn_libro_prestar.setActionCommand("__PRESTAR_LIBRO");
        this.init.btn_libro_prestar.addActionListener(this);
        //////////////////PRESTAMO\\\\\\\\\\\\\\\\\\\
        this.init.btn_prestamo_crear.setActionCommand("__CREAR_PRESTAMO");
        this.init.btn_prestamo_crear.addActionListener(this);
        
        this.init.btn_Prestamo_Devolver.setActionCommand("__DEVOLVER_PRESTAMO");
        this.init.btn_Prestamo_Devolver.addActionListener(this);
        
        this.init.btn_prestamo_eliminar.setActionCommand("__ELIMINAR_PRESTAMO");
        this.init.btn_prestamo_eliminar.addActionListener(this);
        
        this.init.btn_prestamo_mostrar.setActionCommand("__CONSULTAR_PRESTAMO");
        this.init.btn_prestamo_mostrar.addActionListener(this);
                
    }
         
 
    @Override
    public void actionPerformed(ActionEvent e) {
	switch (AccionMVC.valueOf(e.getActionCommand())) {
            case __CREAR_USUARIO:
                boolean validez=true;
                
                try{
                    String nombre=this.init.Text_Socio_Nombre.getText();
                    String dni=this.init.Text_Socio_dni.getText();
                    String calle= this.init.Text_Socios_Direc_Calle.getText();
                    String ciudad=this.init.Text_Socios_Direc_Ciudad.getText();
                    int cod_post=Integer.parseInt( this.init.Text_Socios_Direc_CP.getText().trim());
                    int penalizacion= Integer.parseInt( this.init.penalizacointxt.getText().trim());
             
                    u.insertarUsuario(dni, nombre,calle, ciudad, cod_post, penalizacion);
                    this.init.tablaUsuario.setModel(u.listarlectores());
                    
                }catch(NumberFormatException a){
                      JOptionPane.showMessageDialog(null, "Datos incorrectos, por favor rellene todos los campos");
                      a.printStackTrace();
                 
	        }
                
		break;
                
            case __MODIFICAR_USUARIO:
                    validez=true;
                    try {
                        
                        nombreValid = utiles.compruebaTextoVacio(init.Text_Socio_Nombre.getText().toString().trim());
                        dniValid = utiles.compruebaTextoVacio(init.Text_Socio_dni.getText().toString().trim());
                        calleValid = utiles.compruebaTextoVacio(init.Text_Socios_Direc_Calle.getText().toString().trim());
                        ciudadValid = utiles.compruebaTextoVacio(init.Text_Socios_Direc_Ciudad.getText().toString().trim());
                        cpValid = utiles.compruebaTextoVacio(init.Text_Socios_Direc_CP.getText().toString().trim());
                        
                    } catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			if(!nombreValid || !apellidosValid || !dniValid || !calleValid || !ciudadValid || !cpValid) {
				validez=false;
				JOptionPane.showMessageDialog(null, "Hay campos en blanco");
			}
					try {
				
                    dniValid = utiles.compruebaNIF(init.Text_Socio_dni.getText().toString().trim());
				
		} catch (IOException e2) {
				// TODO Auto-generated catch block
                    e2.printStackTrace();
		}
		if(!dniValid) {
                    validez=false;
                    JOptionPane.showMessageDialog(null, "DNI invalido");
		}
		try {
                    cpValid = utiles.controlaIntPositivoValido(Integer.parseInt(this.init.Text_Socios_Direc_CP.getText().toString().trim()));
		} catch (NumberFormatException | IOException e1) {
				// TODO Auto-generated catch block
                    JOptionPane.showMessageDialog(null, "Caracteres Invalidos en Cod Postal");
                    validez=false;
		}
		if(!cpValid ) {
                    JOptionPane.showMessageDialog(null, "Introduzca un valor positivo de Cod Postal");
                    validez=false;
		}
		cpSocio = Integer.parseInt(init.Text_Socios_Direc_CP.getText().toString().trim());
		if( cpSocio!=5) {
                    JOptionPane.showMessageDialog(null, "El código postal debe tener 5 digitos");
                    validez=false;
		}
		if(validez) {            
                   
                    nombreSocio = init.Text_Socio_Nombre.getText().toString().trim();
                    dniSocio = init.Text_Socio_dni.getText().toString().trim();
                    calleSocio = init.Text_Socios_Direc_Calle.getText().toString().trim();
                    ciudadSocio = init.Text_Socios_Direc_Ciudad.getText().toString().trim();
                    cpSocio = Integer.parseInt(init.Text_Socios_Direc_CP.getText().toString().trim());
                    
                    try {
                        u.modificarUsuario( nombreSocio, apellidosSocio, dniSocio, calleSocio, ciudadSocio, cpSocio);
                    } catch (SQLException e1) {
			// TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    this.init.tablaUsuario.setModel(u.listarlectores());
		}
		break;
		
		case __ELIMINAR_USUARIO:
                    try {
                        numCarnetValid = utiles.controlaIntPositivoValido(Integer.parseInt(this.init.Text_Socios_Eliminar.getText().toString().trim()));
                    } catch (NumberFormatException | IOException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Caracteres Invalidos en NumCarnet");
			validez=false;
                    }
		
                    if(!numCarnetValid ) {
			JOptionPane.showMessageDialog(null, "Introduzca un valor positivo de NumCarnet");
			validez=false;
                    }
                    if(numCarnetValid) {
			int carnet = Integer.parseInt(init.Text_Socios_Eliminar.getText().trim());
			try {
                            u.borrarUsuario(carnet);
                        } catch (SQLException e1) {
				// TODO Auto-generated catch block
                            e1.printStackTrace();
			}
			this.init.tablaUsuario.setModel(u.listarlectores());
                    }
                    break;
                    
		case __CASTIGAR_USUARIO:
                    int carnet = Integer.parseInt(init.Text_Socios_Penalizar.getText().trim());
                    try {
			u.castigarUsuario(carnet);
                    } catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
                    }
                    this.init.tablaUsuario.setModel(u.listarlectores());
                    break;
                    
		case __PERDONAR_USUARIO:
                    carnet = Integer.parseInt(init.Text_Socios_Penalizar.getText().trim());
                    try {
			u.perdonarUsuario(carnet);
                    } catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
                    }
                    this.init.tablaUsuario.setModel(u.listarlectores());
                    break;
                    
		case __MOSTRAR_USUARIO:
                    String valor[]= new String [6];

                    init.tablaUsuario.setModel(u.listarlectores());
                    
                    init.Text_Socios_Dni_Mod.setText(valor[0]);
                    init.Text_Socios_Nombre_Mod.setText(valor[1]);
                    init.Text_Socios_Calle_Mod.setText(valor[2]);
                    init.Text_Socios_Ciudad_Mod.setText(valor[3]);
                    init.Text_Socios_CP_Mod.setText(valor[4]);
                    init.Text_Socios_Penalizar.setText(valor[5]);
		break;
                    
/////////////////////////////   *** LIBROS ***   ///////////////////////////////////////////////////////////////	

		case __CREAR_LIBRO:
                    validez=true;
                    try {
			tituloValido= utiles.compruebaTextoVacio(init.Text_Titulo_Libro.getText().toString().trim());
			nombreAutorValido= utiles.compruebaTextoVacio(init.Text_Libro_Autor_Nombre.getText().toString().trim());
			apellidoAutorValido= utiles.compruebaTextoVacio(init.Text_Libro_Autor_Apellidos.getText().toString().trim());
			editorialValido= utiles.compruebaTextoVacio(init.Text_Libro_Editorial.getText().toString().trim());
                        codigoValido = utiles.compruebaTextoVacio(init.Text_Libro_Clase.getText().toString().trim());
                    } catch (IOException e2) {
				// TODO Auto-generated catch block
			e2.printStackTrace();
                    }
                    if(!tituloValido || !nombreAutorValido || !apellidoAutorValido || !editorialValido || !codigoValido ) {
			validez=false;
			JOptionPane.showMessageDialog(null, "Hay campos en blanco");
                    }

                    if(validez) {
                        titulo = init.Text_Libro_Autor_Nombre.getText().toString().trim();
                        nombreAutor = init.Text_Libro_Autor_Nombre.getText().toString().trim();
                        apellidoAutor = init.Text_Libro_Autor_Apellidos.getText().toString().trim();
                        editorial = init.Text_Libro_Editorial.getText().toString().trim();
                        int clase = Integer.parseInt( this.init.Text_Libro_Clase.getText().trim());
                        int disponible = Integer.parseInt( this.init.Text_Libro_InsertDisponible.getText().trim());
                        try {
                            libro.insertarLibro(titulo, nombreAutor, apellidoAutor, editorial, clase, disponible);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
                            e1.printStackTrace();
			}
			this.init.tablaLibros.setModel(libro.listarLibros());
                    }
                    break;
                    
		case __MODIFICAR_LIBRO:
			validez=true;
			try {
                            tituloValido= utiles.compruebaTextoVacio(init.Text_Libro_Titulo_Mod.getText().toString().trim());
                            nombreAutorValido= utiles.compruebaTextoVacio(init.Text_Libro_AutorNombre_Mod.getText().toString().trim());
                            apellidoAutorValido= utiles.compruebaTextoVacio(init.Text_Libro_AutorApellido_Mod.getText().toString().trim());
                            editorialValido= utiles.compruebaTextoVacio(init.Text_Libro_Editorial_Mod.getText().toString().trim());
                            codigoValido = utiles.compruebaTextoVacio(init.Text_Libro_Clase_Mod.getText().toString().trim());
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			if(!tituloValido || !nombreAutorValido || !apellidoAutorValido || !editorialValido || !codigoValido) {
				validez=false;
				JOptionPane.showMessageDialog(null, "Hay campos en blanco");
			}
			codigo=Integer.parseInt(init.Text_Libro_Clase_Mod.getText().trim());
			if(codigo>3||codigo<1) {
				JOptionPane.showMessageDialog(null, "El código de libro debe ser 1,2 o 3\n1:7 dias\n2:15 dias\n3:21 dias");
				validez=false;
			}

			if(!idValido ) {
				JOptionPane.showMessageDialog(null, "Introduzca un valor positivo de ID");
				validez=false;
			}
			if(validez) {
                            titulo = init.Text_Libro_Autor_Nombre.getText().toString().trim();
                            nombreAutor = init.Text_Libro_Autor_Nombre.getText().toString().trim();
                            apellidoAutor = init.Text_Libro_Autor_Apellidos.getText().toString().trim();
                            editorial = init.Text_Libro_Editorial.getText().toString().trim();
                            codigo = Integer.parseInt(init.Text_Libro_Clase_Mod.getText().trim());
				//int id=Integer.parseInt(init.Text_Buscar_Libro.getText().toString().trim());
				//try {
				//	libro.modificarLibro(titulo, nombreAutor, apellidoAutor, editorial, codigo);
				//} catch (SQLException e1) {
					// TODO Auto-generated catch block
				//	e1.printStackTrace();
				//}
				this.init.tablaLibros.setModel(libro.listarLibros());
			}
			break;
                    
		case __ELIMINAR_LIBRO:
			validez=true;
			try {
				idValido=utiles.controlaIntPositivoValido(Integer.parseInt(this.init.Text_Libro_eliminar.getText().toString().trim()));
			} catch (NumberFormatException | IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Caracteres Invalidos en ID");
				validez=false;
			}
			if(!idValido ) {
				JOptionPane.showMessageDialog(null, "Introduzca un valor positivo de ID");
				validez=false;
			}
			if(validez) {
				int id=Integer.parseInt(init.Text_Libro_eliminar.getText().toString().trim());
				try {
					libro.borrarLibro(id);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				this.init.tablaLibros.setModel(libro.listarLibros());
			}
			break;
                    
		case __CONSULTAR_LIBRO:
                        
                                String datosLibro[]= new String [6];

                                this.init.tablaLibros.setModel(libro.listarLibros());
                    
				this.init.Text_Libro_Titulo_Mod.setText(datosLibro[0]);
				this.init.Text_Libro_AutorNombre_Mod.setText(datosLibro[1]);
				this.init.Text_Libro_AutorApellido_Mod.setText(datosLibro[2]);
				this.init.Text_Libro_Editorial_Mod.setText(datosLibro[3]);
				this.init.Text_Libro_Clase_Mod.setText(datosLibro[4]);
			break;
                    
		case __DEVOLVER_LIBRO:
			int id=Integer.parseInt(init.Text_Libro_ID_Disponible.getText().toString().trim());
			try {
				libro.devolverLibro(id);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
                            e1.printStackTrace();
			}
			this.init.tablaLibros.setModel(libro.listarLibros());
			break;
                    
		case __PRESTAR_LIBRO:
			id=Integer.parseInt(init.Text_Libros_ID_Prestar.getText().toString().trim());
			try {
				libro.prestarLibro(id);;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.init.tablaLibros.setModel(libro.listarLibros());
			break;
			
//////////////////////////////   *** PRESTAMOS ***   //////////////////////////////////////		

		case __CREAR_PRESTAMO:
			validez=true;
			try {
                            idUsuarioValido = utiles.compruebaTextoVacio(init.Text_Prestamo_NCarnet.getText().toString().trim());
                            idLibroValido= utiles.compruebaTextoVacio(init.Text_Prestamos_ID_Libro.getText().toString().trim());
                            finicioValido= utiles.compruebaTextoVacio(init.Text_Prestamo_FechaI.getText().toString().trim());
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
                            idUsuarioValido = utiles.compruebaTextoVacio(init.Text_Prestamo_NCarnet.getText().toString().trim());
			} catch (NumberFormatException | IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Caracteres Invalidos en ID Usuario");
				validez=false;
			}
			if(!idUsuarioValido ) {
				JOptionPane.showMessageDialog(null, "Introduzca un valor positivo de ID");
				validez=false;
			}
			try {
				idLibroValido = utiles.compruebaTextoVacio(init.Text_Prestamos_ID_Libro.getText().toString().trim());
			} catch (NumberFormatException | IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Caracteres Invalidos en ID Libro");
				validez=false;
			}
			if(!idLibroValido ) {
				JOptionPane.showMessageDialog(null, "Introduzca un valor positivo de ID");
				validez=false;
			}
			if( !idUsuarioValido || !idLibroValido || !finicioValido ) {
				validez=false;
				JOptionPane.showMessageDialog(null, "Hay campos en blanco");
			}
			if(validez) {
				idUsuario = Integer.parseInt(init.Text_Prestamo_NCarnet.getText().toString().trim());
				idLibro = Integer.parseInt(init.Text_Prestamos_ID_Libro.getText().toString().trim());
				finicio = init.Text_Prestamo_FechaI.getText().toString().trim();
                            try {
				control=prestamo.insertarPrestamo(idUsuario, idLibro, finicio);
                            } catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Fecha o id inexistente");
				validez=false;
                            }
                            this.init.tablaPrestamo.setModel(prestamo.listarPrestamo());
                            if(control!=1)
				JOptionPane.showMessageDialog(null, "Prestamo no posible");
			}
			break;
                    
		case __DEVOLVER_PRESTAMO:
			validez=true;
			try {
				idPrestamoValido = utiles.compruebaTextoVacio(init.Text_Prestamo_Devolver.getText().toString().trim());
				ffinValido = utiles.compruebaTextoVacio(init.Text_Prestamo_FechaD.getText().toString().trim());
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				idPrestamoValido = utiles.controlaIntPositivoValido(Integer.parseInt(this.init.Text_Prestamo_Devolver.getText().toString().trim()));
			} catch (NumberFormatException | IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Caracteres Invalidos en ID Prestamo");
				validez = false;
			}
			if(!idPrestamoValido ) {
				JOptionPane.showMessageDialog(null, "Introduzca un valor positivo de ID");
				validez = false;
			}
			
			if( !idPrestamoValido || !ffinValido ) {
				validez = false;
				JOptionPane.showMessageDialog(null, "Hay campos en blanco");
			}
			if(validez) {
				idPrestamo = Integer.parseInt(init.Text_Prestamo_Devolver.getText().toString().trim());
				ffin= init.Text_Prestamo_FechaD.getText().toString().trim();
				try {
					prestamo.devolverPrestamo(idPrestamo, ffin);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Fecha o id inexistente");
				}
				this.init.tablaPrestamo.setModel(prestamo.listarPrestamo());
			}
			break;
                    
		case __ELIMINAR_PRESTAMO:
			validez=true;
			
			try {
				idPrestamoEliminarValido=utiles.compruebaTextoVacio(init.Text_Prestamos_Eliminar.getText().toString().trim());
				} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				idPrestamoEliminarValido=utiles.compruebaTextoVacio(init.Text_Prestamos_Eliminar.getText().toString().trim());
			} catch (NumberFormatException | IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Caracteres Invalidos en ID Eliminar Prestamo");
				validez=false;
			}
			if(!idPrestamoEliminarValido ) {
				JOptionPane.showMessageDialog(null, "Introduzca un valor positivo de ID");
				validez=false;
			}
			
			if( !idPrestamoEliminarValido ) {
				validez=false;
				JOptionPane.showMessageDialog(null, "Hay campos en blanco");
			}
			if(validez) {
				id=Integer.parseInt(init.Text_Prestamos_Eliminar.getText().toString().trim());
				try {
					prestamo.borrarPrestamo(id);;
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Id inexistente");
				}
				this.init.tablaPrestamo.setModel(prestamo.listarPrestamo());
			}
				break;
                    	case __CONSULTAR_PRESTAMO:
                        
                                String datosPrestamo[]= new String [6];

                                this.init.tablaPrestamo.setModel(prestamo.listarPrestamo());
     
			break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

    @Override
    public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

    @Override
    public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

    @Override
    public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

    @Override
    public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
    
}
