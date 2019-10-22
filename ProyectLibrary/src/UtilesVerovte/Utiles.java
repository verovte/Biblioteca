/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UtilesVerovte;

import java.io.*;
import java.util.*;

/**
 *
 * @author verovte
 */
public class Utiles {
    
    BufferedReader Leer = new BufferedReader(new InputStreamReader(System.in));
	Random randon = new Random();
	boolean aux;
	boolean error = false;

	///////////////////////////////////////////////////////////////////////////////////////
	/*
	 * METODOS DE CARACTERES
	 * 
	 */
	///////////////////////////////////////////////////////////////////////////////////////

	/*
	 * METODO PARA CONTAR VOCALES
	 */
	public String ContarVocales(String frase) throws IOException {
		int contador = 0;

		for (int i = 0; i < frase.length(); i++) {
			if (frase.charAt(i) == 'a' || frase.charAt(i) == 'á' || frase.charAt(i) == 'A' || frase.charAt(i) == 'Á'
					|| frase.charAt(i) == 'e' || frase.charAt(i) == 'é' || frase.charAt(i) == 'E'
					|| frase.charAt(i) == 'É' || frase.charAt(i) == 'i' || frase.charAt(i) == 'í'
					|| frase.charAt(i) == 'I' || frase.charAt(i) == 'Í' || frase.charAt(i) == 'o'
					|| frase.charAt(i) == 'ó' || frase.charAt(i) == 'O' || frase.charAt(i) == 'Ó'
					|| frase.charAt(i) == 'u' || frase.charAt(i) == 'ú' || frase.charAt(i) == 'U'
					|| frase.charAt(i) == 'Ú') {
				contador++;
			}

		}

		return "La palabra o frase <" + frase + "> contiene " + contador + " vocales.";

	}

	/*
	 * METODO DE EXCEPCION
	 */
	public static boolean IsNumeric(String cadena) throws IOException {
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	/*
	 * METODO PARA INVERTIR UN STRING
	 */
	public String InvertirString(String frase) throws IOException {
		String fraseinvertida = "";
		for (int i = frase.length() - 1; i >= 0; i--) {
			fraseinvertida += frase.charAt(i);
		}
		return "La frase o palabra invertida es: " + fraseinvertida;
	}

	/*
	 * METODO PARA SUSTITUIR UNA PALABRA POR OTRA
	 */
	public void SustiturPalabra(String frase) throws IOException {
		// DECLARAMOS VARIABLES NECESARIAS
		String reemplazo, nuevo;

		// ALMACENA LA FRASE
		System.out.println("Introduce una frase");
		frase = try_String();

		// ALMACENA LA PALABRA ESCOGIDA DE LA FRASE
		System.out.println("Escriba la palabra que desea cambiar");
		reemplazo = try_String();

		// ALMACENA LA NUEVA PALABRA
		System.out.println("Introduzca la nueva palabra");
		nuevo = try_String();

		// MUESTRA POR PANTALLA LA FRASE CON LA PALABRA CAMBIADA
		System.out.println(frase.replace(reemplazo, nuevo));
	}

	/*
	 * METODO PARA ELIMINAR ESPACIOS EN BLANCO
	 */
	public void EliminarEspacios() throws IOException {
		String frase = null;

		System.out.println("Procesando espacios en blanco");
		System.out.println("...");
		System.out.println("...");
		System.out.println(frase.replaceAll(" ", ""));

	}

	/*
	 * METODO PARA BUSCAR UNA PALABRA DENTRO DE UNA FRASE
	 */
	@SuppressWarnings("null")
	public String BuscarPalabra() throws IOException {
		String frase = null, palabra;
		String[] palabras = frase.split(" ");

		System.out.println("Introduce palabra a buscar");
		palabra = try_String();

		if (palabras.equals(palabra)) {
			return "Palabra encontrada: " + palabra;
		} else {
			return "Palabra no encontrada";
		}

	}

	/*
	 * METODO AVERIGUAR SI UNA PALABRA ES O NO PALINDROMO
	 */
	public void Palindromo() throws IOException {
		System.out.println("Introduce una palabra para saber si es un palíndromo");
		String palabra = try_String();

		// DECLARACIONES DE VARIABLES
		int inc = 0;
		int desc = palabra.length() - 1;
		boolean bError = false;

		// SE RECORRE LA PALABRA INTRODUCIDA MIENTRAS EL PRINCIPIO Y EL FINAL SEAN
		// IGUALES SI NO SON IGUALES SALTA AL ERROR
		while ((inc < desc) && (!bError)) {
			if (palabra.charAt(inc) == palabra.charAt(desc)) {
				inc++;
				desc--;
			} else {
				bError = true;
			}

		}
		// SI LA PALABRA ES DISTINTO A ERROR ENTONCES ES UN PALINDROMO SINO NO LO ES
		if (!bError) {
			System.out.println(palabra + " es un palíndromo");
		} else {
			System.out.println(palabra + " no es un palíndromo");
		}
	}

	/*
	 * METODO QUE CONVIERTE UNA CADENA DE CARACTERES A STRING
	 */

	public String deCadenaATexto(char[] array) {

		String string = String.copyValueOf(array);

		return string;

	}

	/*
	 * METODO PARA VALIDAR DNI
	 */
        
        public Boolean compruebaNIF(String s) throws IOException {
		String respuesta=s;
		boolean error = false;
		char c;
		
			if(respuesta.length()!=9) {
				System.out.println("Longitud del DNI incorrecta");
				return false;
				
			}
			
			for (int i = 0; i < 8; i++) {
				c = respuesta.charAt(i);
				if (!Character.isDigit(c)) {
					return false;
				}
			}
			if(!Character.isAlphabetic(respuesta.charAt(8)))
				return false;
			
			
		return true;
	}

	public String ValidarDNI(String s) throws IOException {
		String respuesta = s;
		boolean error = false;
		char c;

		do {
			error = false;
			while (respuesta.length() != 9) {
				System.out.println("Longitud del DNI incorrecta, vuelva a introducirlo");
				respuesta = try_String();
			}

			for (int i = 0; i < 8; i++) {
				c = respuesta.charAt(i);
				if (!Character.isDigit(c)) {
					error = true;
					break;
				}
			}

			if (!Character.isAlphabetic(respuesta.charAt(8))) {
				error = true;
				if (error) {
					System.out.println("DNI no válido. Vuélvalo a introducir");
					respuesta = try_String();
				}
			}
		} while (error);

		return respuesta;
	}

	////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * METODOS DE EXCEPCIONES
	 * 
	 */
	////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * METODO PARA CONTROLAR DATOS TIPO INT
	 * 
	 * @return
	 * 
	 * @throws IOException
	 */
	public int try_int() throws IOException {

		int num = 0;
		do {
			try {
				num = Integer.parseInt(Leer.readLine());
				aux = true;

			} catch (NumberFormatException e) {
				aux = false;
				System.out.println("Valor incorrecto");
			}

		} while (aux = false);
		return num;

	}

	/*
	 * METODO PARA CONTROLAR DATOS TIPO FLOAT
	 * 
	 * @throws IOException
	 */
	public float try_float() throws IOException {
		float n = 0;
		do {
			try {
				n = Integer.parseInt(Leer.readLine());
				aux = true;

			} catch (NumberFormatException e) {
				aux = false;
				System.out.println("Valor incorrecto");
			}

		} while (aux = false);
		return n;

	}

	/*
	 * METODO PARA CONTROLAR DATOS TIPO DOUBLE
	 * 
	 * @return
	 * 
	 * @throws IOException
	 */
	public double try_double() throws IOException {
		double n = 0;
		do {
			try {
				n = Double.parseDouble(Leer.readLine());
				aux = true;

			} catch (NumberFormatException e) {
				aux = false;
				System.out.println("Valor incorrecto");
			}

		} while (!aux);
		return n;

	}

	/*
	 * METODO PARA CONTROLAR DATOS TIPO BOOLEAN
	 * 
	 * @return
	 * 
	 * @throws IOException
	 */
	public boolean try_boolean() throws IOException {
		boolean n = false;
		do {
			try {
				n = Boolean.parseBoolean(Leer.readLine());
				aux = true;

			} catch (NumberFormatException e) {
				aux = false;
				System.out.println("Valor incorrecto");
			}

		} while (!aux);
		return n;

	}

	/*
	 * METODO PARA CONTROLAR DATOS TIPO LONG
	 * 
	 * @return
	 * 
	 * @throws IOException
	 */
	public long try_Long() throws IOException {
		long n = 0;
		do {
			try {
				n = Long.parseLong(Leer.readLine());
				aux = true;

			} catch (NumberFormatException e) {
				aux = false;
				System.out.println("Valor incorrecto");
			}

		} while (!aux);
		return n;

	}

	/*
	 * METODO PARA CONTROLAR DATOS TIPO STRING
	 * 
	 * @return
	 * 
	 * @throws IOException
	 */
	public String try_String() throws IOException {
		String n = null;
		do {
			try {
				n = Leer.readLine();
				aux = true;

			} catch (NumberFormatException e) {
				aux = false;
				System.out.println("Valor incorrecto");
			}

		} while (!aux);
		return n;

	}

	///////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * METODOS MIS ARRAYS
	 * 
	 */
	///////////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * METODO PARA RELLENAR UN ARRAY CON NUMEROS ALEATORIOS
	 */
	public void RellenarAreglo(int array[]) {
		for (int i = 0; i < array.length; i++) {
			array[i] = randon.nextInt(100) + 1;
		}
	}

	/*
	 * METODO PARA IMPRIMIR UN ARRAY
	 */
	public void ImprimirArrayInt(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
	}

	public void ImprimirArrayFloat(float[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
	}

	public void ImprimirArrayDouble(double[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
	}

	/*
	 * METODO QUE INVIERTE LOS VALORES DE UN ARRAY
	 */
	public int InvertirArray(int[] array) {
		int aux = 0;

		for (int i = 0; i < array.length / 2; i++) {
			aux = array[i];
			array[i] = array[array.length - 1 - i];
			array[array.length - 1 - i] = aux;
		}
		return aux;
	}

	/*
	 * METODO QUE CALCULA EL NUMERO MENOR DE UN ARRAY
	 */
	public int MenorArray(int[] array) {
		int menor = array[0];

		for (int i = 0; i < array.length; i++) {
			if (array[i] < menor) {
				menor = array[i];
			} else {
				menor = menor;
			}
		}
		return menor;
	}

	/*
	 * METODO QUE CALCULA EL NUMERO MAYOR DE UN ARRAY
	 */
	public int MayorArray(int[] array) {
		int mayor = array[0];

		for (int i = 0; i < array.length; i++) {
			if (array[i] > mayor) {
				mayor = array[i];
			} else {
				mayor = mayor;
			}
		}
		return mayor;
	}

	/*
	 * METODO BURBUJA CON ARRAY
	 */
	public void Burbuja_Ascendente(int[] array) {
		int aux;

		for (int i = 0; i < array.length; i++) {
			for (int j = 1; j <= array.length - 1; j++) {
				if (array[j - 1] > array[j]) {
					aux = array[j - 1];
					array[j - 1] = array[j];
					array[j] = aux;
				}
			}
		}
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}

	}

	public void Burbuja_Descendente(int array[]) {
		int aux;

		for (int i = 0; i < array.length - 1; i++) {
			for (int j = 0; j < array.length - 1 - i; j++) {
				if (array[j] < array[j + 1]) {
					aux = array[j];
					array[j] = array[j + 1];
					array[j + 1] = aux;
				}
			}
		}
	}

	/*
	 * METODO INSERSION DIRECTA CON ARRAY
	 */
	public void InsercionDirectaAscendente(int array[]) {
		int pos;
		int aux;

		for (int i = 0; i < array.length; i++) {
			pos = i;
			aux = array[i];
			while (pos > 0 && (array[pos - 1] > aux)) {
				array[pos] = array[pos - 1];
				pos--;
			}
			array[pos] = aux;
		}
	}

	public void InsercionDirectaDescendente(int array[]) {
		int pos;
		int aux;

		for (int i = 0; i < array.length; i++) {
			pos = i;
			aux = array[i];
			while (pos > 0 && (array[pos - 1] < aux)) {
				array[pos] = array[pos - 1];
				pos--;
			}
			array[pos] = aux;
		}
	}

	/*
	 * METODO QUE ORDENA DE MENOR A MAYOR Y VICEVERSA UN ARRAY CON .SORT
	 */
	public void OrdenarArrayMenorMayor(int array[]) {
		Arrays.sort(array);
		for (int arrays : array) {
			System.out.print(arrays + " ");
		}
	}

	public void OrdenarArrayMayorMenor(int array[]) {
		Arrays.sort(array);
		for (int i = array.length - 1; i >= 0; i--) {
			System.out.print(array[i] + " ");
		}
	}

	// METODO QUE AVERIGUA LOS PARES DE UN ARRAY
	public void Pares(int[] array) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] % 2 == 0) {
				System.out.print(array[i] + " ");
			}
		}
	}

	// METODO QUE CUENTA LOS PARES DE UN ARRAY
	public int ContarPares(int array[]) {
		int cont = 0;

		for (int i = 0; i < array.length; i++) {
			if (array[i] % 2 == 0) {
				cont++;
			}
		}
		System.out.println("en este vector hay " + cont + " numeros pares");
		return cont;

	}

	// METODO QUE EXTRAE LOS PARES DE UN ARRAY
	public int[] ExtraerPares(int array[]) {
		int[] par = new int[ContarPares(array)];
		int cont = 0;

		for (int i = 0; i < array.length; i++) {
			if (array[i] % 2 == 0) {
				par[cont] = array[i];
				cont++;
			}
		}
		for (int i = 0; i < par.length; i++) {
			System.out.println(par[i]);
		}
		return par;
	}

	// METODO QUE AVERIGUA LOS IMPARES DE UN ARRAY
	public void Impares(int[] array) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] % 2 != 0) {
				System.out.print(array[i] + " ");
			}
		}
	}

	// METODO QUE CUENTA LOS IMPARES DE UN ARRAY
	public int ContarImpares(int array[]) {
		int cont = 0;

		for (int i = 0; i < array.length; i++) {
			if (array[i] % 2 != 0) {
				cont++;
			}
		}
		System.out.println("en este vector hay " + cont + " numeros impares");
		return cont;
	}

	// METODO QUE EXTRAE LOS IMPARES DE UN ARRAY
	public int[] ExtraerImpares(int array[]) {
		int[] impar = new int[ContarImpares(array)];
		int cont = 0;

		for (int i = 0; i < array.length; i++) {
			if (array[i] % 2 != 0) {
				impar[cont] = array[i];
				cont++;
			}
		}
		for (int i = 0; i < impar.length; i++) {
			System.out.println(impar[i]);
		}
		return impar;

	}

	// METODO QUE SUMA LOS VALORES DE UN ARRAY
	public void SumaArray(int array[]) {
		int suma = 0;

		for (int i = 0; i < array.length; i++) {
			suma += array[i];
		}
		System.out.println(suma);
	}

	// METODO QUE SUMA LOS VALORES DE DOS ARRAYS
	public void SumarArrays(int array[], int array2[]) {
		int total = array.length;
		int[] suma = new int[total];

		for (int i = 0; i < suma.length; i++) {
			suma[i] = array[i] + array2[i];
		}
		for (int i = 0; i < suma.length; i++) {
			System.out.println(suma[i]);
		}
	}

	// METODO QUE RESTA LOS VALORES DE UN ARRAY
	public void RestaArray(int array[]) {
		int resta = 0;

		for (int i = 0; i < array.length; i++) {
			resta -= array[i];
		}
		System.out.println(resta);

	}

	// METODO QUE RESTA LOS VALORES DE DOS ARRAYS
	public void RestaArrays(int array[], int array2[]) {
		int total = array.length;
		int[] resta = new int[total];

		for (int i = 0; i < resta.length; i++) {
			resta[i] = array[i] - array2[i];
		}
		for (int i = 0; i < resta.length; i++) {
			System.out.println(resta[i]);
		}

	}

	// METODO QUE MULTIPLICA LOS VALORES DE UN ARRAY
	public void MultiArray(int[] array) {
		int multi = 0;

		for (int i = 0; i < array.length; i++) {
			multi *= array[i];
		}
		System.out.println(multi);
	}

	// METODO QUE MULTIPLICA LOS VALORES DE DOS ARRAYS
	public void MultiArrays(int[] array, int array2[]) {
		int total = array.length;
		int[] multi = new int[total];

		for (int i = 0; i < multi.length; i++) {
			multi[i] = array[i] * array2[i];
		}
		for (int i = 0; i < multi.length; i++) {
			System.out.println(multi[i]);
		}
	}

	// METODO QUE DIVIDE LOS VALORES DE UN ARRAY
	public void DivideArray(int[] array) {
		int divi = 0;

		for (int i = 0; i < array.length; i++) {
			divi -= array[i];
		}
		System.out.println(divi);
	}

	// METODO QUE DIVIDE LOS VALORES DE DOS ARRAYS
	public void DivideArrays(int array[], int array2[]) {
		int total = array.length;
		int[] divi = new int[total];

		for (int i = 0; i < divi.length; i++) {
			divi[i] = array[i] / array2[i];
		}
		for (int i = 0; i < divi.length; i++) {
			System.out.println(divi[i]);
		}
	}

/////////////////////////////////////////////////////////////////

	// METODO RELLENAR UNA MATRIZ CON NUMEROS ALEATORIOS
	public void RellenarMatriz(int matriz[][]) {
		Random randon = new Random();
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				matriz[i][j] = randon.nextInt(100);
			}
		}
	}

	// METODO PARA MOSTRAR MATRIZ
	public void ImprimirMatrizInt(int matriz[][]) {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				System.out.print(matriz[i][j] + " ");

			}
			System.out.println(" ");
		}
	}

	public void ImprimirMatrizFloat(float matriz[][]) {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				System.out.print(matriz[i][j] + " ");

			}
			System.out.println(" ");
		}
	}

	public void ImprimirMatrizDouble(double matriz[][]) {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				System.out.print(matriz[i][j] + " ");

			}
			System.out.println(" ");
		}
	}

	public void ImprimirMatrizString(String matriz[][]) {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				System.out.print(matriz[i][j] + " ");
			}
			System.out.println(" ");
		}
	}

	/*
	 * METODO QUE INVIERTE LOS VALORES DE UNA MATRIZ
	 */
	public void InvertirMatriz(int[][] matriz) {
		for (int i = matriz.length - 1; i > -1; i--) {
			for (int j = matriz.length - 1; j > -1; j--) {
				System.out.print(matriz[i][j] + "\t");
			}
			System.out.println();
		}
	}

	/*
	 * METODO QUE SUMA DOS MATRICES
	 */
	public int[][] SumaMatriz(int matriz[][], int matriz1[][]) {
		int fila = matriz.length;
		int columna = matriz[0].length;
		int suma[][] = new int[fila][columna];

		for (int i = 0; i < suma.length; i++) {
			for (int j = 0; j < suma[i].length; j++) {
				suma[i][j] = matriz[i][j] + matriz1[i][j];
			}
		}
		return suma;
	}

	/*
	 * METODO QUE CALCULA EL NUMERO MENOR DE UN MATRIZ
	 */
	public int MenorDeMatriz(int matriz[][]) {
		int menor = matriz[0][0];

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				if (matriz[i][j] < menor)
					menor = matriz[i][j];
			}
		}
		return menor;
	}

	/*
	 * METODO QUE CALCULA LA POSICION DEL NUMERO MENOR DE UNA MATRIZ
	 */
	public void PosicionMenorMatriz(int matriz[][]) {
		int menor = MenorDeMatriz(matriz);

		System.out.println("El menor de la matriz se encuentra en la posición: ");

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				if (menor == matriz[i][j])
					System.out.print("[" + i + "][" + j + "]  ");
			}
		}
	}

	/*
	 * METODO QUE CALCULA EL NUMERO MAYOR DE UN MATRIZ
	 */
	public int MayoDeMatriz(int matriz[][]) {
		int aux = matriz[0][0];

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				if (matriz[i][j] > aux) {
					aux = matriz[i][j];
				}
			}
		}

		return aux;
	}

	/*
	 * METODO QUE CALCULA LA POSICION DEL NUMERO MAYOR DE UNA MATRIZ
	 */
	public void PosicionMayorMatriz(int matriz[][]) {
		int mayor = MayoDeMatriz(matriz);

		System.out.print("El mayor de la matriz se encuentra en la posición: ");

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				if (mayor == matriz[i][j])
					System.out.print("[" + i + "][" + j + "]  ");
			}
		}
	}

	/*
	 * METODO QUE CALCULA EL NUMERO MENOR FILA DE UN MATRIZ
	 */
	public int MenorFilaMatriz(int matriz[][]) throws IOException {
		int fila;
		System.out.println("Introduce la fila que desees obtener el menor");
		fila = try_int();

		if (fila >= matriz.length) {
			System.out.println("se pasa de la matriz");
			fila = try_int();
		}

		int aux;
		aux = matriz[fila][0];

		for (int i = 0; i < matriz.length; i++) {
			if (aux < matriz[fila][i]) {
				aux = matriz[fila][i];
			} else {
				aux = aux;
			}
		}
		return aux;
	}

	/*
	 * METODO QUE CALCULA LA POSICION DEL MENOR DE LA FILA DE UNA MATRIZ
	 */
	public int PosicionMenorFilaMatriz(int matriz[][]) throws IOException {
		int fila;
		int pos = 0;

		System.out.println("Introduce la fila que desees obtener la posición del menor");
		fila = try_int();

		if (fila >= matriz.length) {
			System.out.println("se pasa de la matriz");
			fila = try_int();
		}

		int aux;
		aux = matriz[fila][0];

		for (int i = 0; i < matriz.length; i++) {
			if (aux > matriz[fila][i]) {
				aux = matriz[fila][i];
				pos = i;
			} else {
				aux = aux;
				pos = pos;
			}
		}
		return pos;
	}

	/*
	 * METODO QUE CALCULA EL NUMERO MAYOR FILA DE UN MATRIZ
	 */
	public int MayorFilaMatriz(int matriz[][]) throws IOException {
		int fila;
		System.out.println("Introduce la fila que desees obtener el mayor");
		fila = try_int();

		if (fila >= matriz.length) {
			System.out.println("Se pasa de la matriz");
			fila = try_int();
		}

		int aux;
		aux = matriz[fila][0];

		for (int i = 0; i > matriz.length; i++) {
			if (aux > matriz[fila][i]) {
				aux = matriz[fila][i];
			} else {
				aux = aux;
			}
		}
		return aux;
	}

	/*
	 * METODO QUE CALCULA LA POSICION DEL MAYOR DE UNA FILA DE LA MATRIZ
	 */

	public int PosicionMayorFilaMatriz(int matriz[][]) throws IOException {
		int fila;
		int pos = 0;

		System.out.println("Introduce la fila que desees obtener la posición del mayor");
		fila = try_int();

		if (fila >= matriz.length) {
			System.out.println("Se pasa de la matriz");
			fila = try_int();
		}

		int aux;
		aux = matriz[fila][0];

		for (int i = 0; i < matriz.length; i++) {
			if (aux < matriz[fila][i]) {
				aux = matriz[fila][i];
				pos = i;
			} else {
				aux = aux;
				pos = pos;
			}
		}
		return pos;
	}

	/*
	 * METODO QUE CALCULA EL NUMERO MENOR COLUMNA DE UN MATRIZ
	 */
	public int MenorColumnaMatriz(int matriz[][]) throws IOException {
		int columna;

		System.out.println("Introduce la columna que desees obtener el menor");
		columna = try_int();

		if (columna >= matriz.length) {
			System.out.println("se pasa de la matriz");
			columna = try_int();
		}

		int aux;
		aux = matriz[0][columna];

		for (int i = 0; i < matriz.length; i++) {
			if (aux > matriz[i][columna]) {
				aux = matriz[i][columna];
			} else {
				aux = aux;
			}
		}
		return aux;
	}

	/*
	 * METODO QUE CALCULA LA POSICION DEL MENOR DE LA COLUMNA DE UNA MATRIZ
	 */
	public int PosicionMenorColumnaMatriz(int matriz[][]) throws IOException {
		int columna;

		System.out.println("Introduce la columna que desees obtener la posición del menor");
		columna = try_int();

		if (columna >= matriz.length) {
			System.out.println("se pasa de la matriz");
			columna = try_int();
		}

		int aux;
		int pos = 0;
		aux = matriz[0][columna];

		for (int i = 0; i < matriz.length; i++) {
			if (aux > matriz[i][columna]) {
				aux = matriz[i][columna];
				pos = i;
			} else {
				aux = aux;
				pos = pos;
			}
		}
		return pos;
	}

	/*
	 * METODO QUE CALCULA EL NUMERO MAYOR COLUMNA DE UN MATRIZ
	 */
	public int mayordelacolumna(int matriz[][]) throws IOException {
		int columna;
		System.out.println("Introduce la columna que desees obtener el mayor");
		columna = try_int();

		if (columna >= matriz.length) {
			System.out.println("se pasa de la matriz");
			columna = try_int();
		}

		int aux;
		aux = matriz[0][columna];

		for (int i = 0; i < matriz.length; i++) {
			if (aux < matriz[i][columna]) {
				aux = matriz[i][columna];
			} else {
				aux = aux;
			}
		}
		return aux;
	}

	/*
	 * METODO QUE CALCULA LA POSICION DEL MAYOR DE LA COLUMNA DE LA MATRIZ
	 */
	public int PosicionMayorColumnaMatriz(int matriz[][]) throws IOException {
		int columna;

		System.out.println("Introduce la columna que desees obtener la posicion del mayor");
		columna = try_int();

		if (columna >= matriz.length) {
			System.out.println("se pasa de la matriz");
			columna = try_int();
		}

		int aux;
		int pos = 0;
		aux = matriz[0][columna];

		for (int i = 0; i < matriz.length; i++) {
			if (aux < matriz[i][columna]) {
				aux = matriz[i][columna];
				pos = i;
			} else {
				aux = aux;
				pos = pos;
			}
		}
		return pos;
	}

	/*
	 * METODO QUE CALCULA EL PUNTO SILLA DE UN MATRIZ
	 */
	public void PuntoSillaMatriz(int[][] matriz) {
		int[] menor = { -999, -999, -999, -999, -999 };
		int[] mayor = { 999, 999, 999, 999, 999, 999 };
		int aux = 0;
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				if (matriz[i][j] > menor[i]) {
					menor[i] = matriz[i][j];
				}
				if (matriz[i][j] < mayor[j]) {
					mayor[j] = matriz[i][j];
				}

			}

		}

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				if (menor[i] == mayor[j]) {
					aux = 1;
					System.out.println("El punto de silla en fila " + i + " columna " + j);
					System.out.println("Y corresponde al numero: " + matriz[i][j]);
				}
			}
		}
		if (aux == 0) {
			System.out.println("No hay punto de silla");
		}
	}

	/*
	 * METODO QUE HACE Y MUESTRA LA MATRIZ EN CARACOL
	 */
	public void MatrizCaracol() throws NumberFormatException, IOException {
		int n;

		System.out.println("Intrduzca el tamaño de la matriz, N*N");
		n = Integer.parseInt(Leer.readLine());
		String[][] caracol = new String[n][n];
		int a = 0;
		int b = n - 1;
		int valor = 1;

		for (int j = 0; j < caracol.length; j++) {
			// LLENAMOS LA FILA SUPERIOR
			for (int i = a; i <= b; i++) {
				caracol[a][i] = valor++ + "\t";
			}
			// LLENAMOS LA COLUMNA DEL EXTREMO DERECHO
			for (int i = a + 1; i <= b; i++) {
				caracol[i][b] = valor++ + "\t";
			}
			// LLENAMOS LA FILA INFERIOR DE DERECHA A IZQUIERDA
			for (int i = b - 1; i >= a; i--) {
				caracol[b][i] = valor++ + "\t";
			}
			// LLENAMOS LA COLUMNA DEL EXTREMO IZQUIERDO DE ABAJO HACIA ARRIBA
			for (int i = b - 1; i >= a + 1; i--) {
				caracol[i][a] = valor++ + "\t";
			}
			a++;
			b--;
		}

		// MOSTRAR MATRIZ
		for (int f = 0; f < caracol.length; f++) {
			for (int i = 0; i < caracol[f].length; i++) {
				System.out.print(caracol[f][i]);
			}
			System.out.println();
		}
	}

	/*
	 * METODO BURBUJA MATRIZ
	 */
	public void BurbujaMatrizFilaAsc(int[][] matriz) throws IOException {
		int aux;

		int fila;

		int total;

		total = matriz[0].length;

		int[] numeros = new int[total];

		System.out.println("Dime la fila que quieres ordenar");

		fila = try_int();

		for (int j = 0; j < numeros.length; j++) {

			numeros[j] = matriz[fila][j];

		}

		Burbuja_Ascendente(numeros);

		for (int i = 0; i < numeros.length; i++) {

			matriz[fila][i] = numeros[i];

		}

		System.out.println("la fila " + fila + " ya está ordenada correctamente");

		ImprimirMatrizInt(matriz);

	}

	public void BurbujaMatrizColumnaAscendente(int matriz[][]) throws IOException {

		int columna;

		int total;

		total = matriz.length;

		int[] numeros = new int[total];

		System.out.println("Dime la columna que quieres ordenar");

		columna = try_int();

		for (int j = 0; j < numeros.length; j++) {

			numeros[j] = matriz[j][columna];

		}

		Burbuja_Ascendente(numeros);

		for (int i = 0; i < numeros.length; i++) {

			matriz[i][columna] = numeros[i];

		}

		System.out.println("la columna " + columna + " ya está ordenada correctamente");

		ImprimirMatrizInt(matriz);

	}

	public void BurbujaMatrizFilaDesc(int matriz[][]) throws IOException {

		int fila;

		int total;

		total = matriz[0].length;

		int[] numeros = new int[total];

		System.out.println("Dime la fila que quieres ordenar");

		fila = try_int();

		for (int j = 0; j < numeros.length; j++) {

			numeros[j] = matriz[fila][j];

		}

		Burbuja_Descendente(numeros);

		for (int i = 0; i < numeros.length; i++) {

			matriz[fila][i] = numeros[i];

		}

		System.out.println("la fila " + fila + " ya está ordenada correctamente");

		ImprimirMatrizInt(matriz);

	}

	public void BurbujaMatrizColumnaDescendete(int matriz[][]) throws IOException {

		int columna;

		int total;

		total = matriz.length;

		int[] numeros = new int[total];

		System.out.println("Dime la columna que quieres ordenar");

		columna = try_int();

		for (int j = 0; j < numeros.length; j++) {

			numeros[j] = matriz[j][columna];

		}

		Burbuja_Descendente(numeros);

		for (int i = 0; i < numeros.length; i++) {

			matriz[i][columna] = numeros[i];

		}

		System.out.println("la columna " + columna + " ya está ordenada correctamente");

		ImprimirMatrizInt(matriz);

	}

	/*
	 * METODO INSERCION DIRECTA MATRIZ
	 */
	public void InsercionDirectaMatrizFilaAscendente(int matriz[][]) throws IOException {

		int fila;

		int total;

		total = matriz[0].length;

		int[] numeros = new int[total];

		System.out.println("Dime la fila que quieres ordenar");

		fila = try_int();

		for (int j = 0; j < numeros.length; j++) {

			numeros[j] = matriz[fila][j];

		}

		InsercionDirectaAscendente(numeros);

		for (int i = 0; i < numeros.length; i++) {

			matriz[fila][i] = numeros[i];

		}

		System.out.println("la fila " + fila + " ya está ordenada correctamente");

		ImprimirMatrizInt(matriz);

	}

	public void InsercionDirectaMatrizFilaDescendente(int matriz[][]) throws IOException {

		int fila;

		int total;

		total = matriz[0].length;

		int[] numeros = new int[total];

		System.out.println("Dime la fila que quieres ordenar");

		fila = try_int();

		for (int j = 0; j < numeros.length; j++) {

			numeros[j] = matriz[fila][j];

		}

		InsercionDirectaAscendente(numeros);

		for (int i = 0; i < numeros.length; i++) {

			matriz[fila][i] = numeros[i];

		}

		System.out.println("la fila " + fila + " ya está ordenada correctamente");

		ImprimirMatrizInt(matriz);

	}

	public void InsercionMatrizColunmaAscendente(int matriz[][]) throws IOException {

		int columna;

		int total;

		total = matriz.length;

		int[] numeros = new int[total];

		System.out.println("Dime la columna que quieres ordenar");

		columna = try_int();

		for (int j = 0; j < numeros.length; j++) {

			numeros[j] = matriz[j][columna];

		}

		InsercionDirectaAscendente(numeros);

		for (int i = 0; i < numeros.length; i++) {

			matriz[i][columna] = numeros[i];

		}

		System.out.println("la columna " + columna + " ya está ordenada correctamente");

		ImprimirMatrizInt(matriz);

	}

	public void InsercionMatrizColumnaDescente(int matriz[][]) throws IOException {

		int columna;

		int total;

		total = matriz.length;

		int[] numeros = new int[total];

		System.out.println("Dime la columna que quieres ordenar");

		columna = try_int();

		for (int j = 0; j < numeros.length; j++) {

			numeros[j] = matriz[j][columna];

		}

		InsercionDirectaDescendente(numeros);

		for (int i = 0; i < numeros.length; i++) {

			matriz[i][columna] = numeros[i];

		}

		System.out.println("la columna " + columna + " ya está ordenada correctamente");

		ImprimirMatrizInt(matriz);

	}

	/*
	 * DETERMINAR SI UNA COLUMNA DE LA MATRIZ ES IGUAL AL VECTOR
	 */
	public void ColumnaVector() {

		int matriz[][] = { { 5, 3, 56, 98 }, { 0, 76, 88, 34 }, { 17, 1, 23, 23 }, { 61, 5, 45, 20 } };

		int vector[] = { 0, 76, 88, 34 };
		for (int i = 0; i < matriz.length; i++) {
			int aux = 0;
			for (int j = 1; j < matriz.length; j++) {
				if (matriz[i][j] == vector[j]) {
					aux = 1;
				} else {
					aux = 0;
				}
			}
			if (aux == 1) {
				System.out.println("El columna " + i + " coincide con el vector");
			} else {
				System.out.println("La columna " + i + " no coincide con el vector");
			}
		}
	}

	/*
	 * METODO QUE TRANSPUESTA LA MATRIZ (LAS FILAS SE CONVIERTEN EN COLUMNAS)
	 */
	public void Transpuesta(int[][] matriz) {
		int aux;
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < i; j++) {
				aux = matriz[i][j];
				matriz[i][j] = matriz[j][i];
				matriz[j][i] = aux;
			}
		}
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				System.out.print(matriz[i][j] + " ");
			}
			System.out.println(" ");
		}
	}

	/*
	 * METODO QUE ORDENA LA DIAGONAL DE UNA MATRIZ
	 */
	public void OrdenaDiagonal(int matriz[][]) {
		int cont = 0;
		int[] numeros = new int[5];

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				if (i == j) {
					numeros[cont] = matriz[i][j];
					cont++;
				}
			}
		}

		cont = 0;
		InsercionDirectaAscendente(numeros);

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				if (i == j) {
					matriz[i][j] = numeros[cont];
					cont++;
				}
			}
		}
		System.out.println("Diagonal ordenada:");
		ImprimirMatrizInt(matriz);
	}

	/*
	 * METODO QUE COMPRUEBA SI UN ARRAY ESTÁ EN UNA COLUMNA DE UNA MATRIZ
	 * 
	 * @param matriz MATRIZ EN LA CUELA QUEREMOS COMPROBAR SI EL ARRAY SE ENCUENTRA
	 * 
	 * @param array ARAY QUE QUEREMOS COMPROBAR SI ESTÁ
	 * 
	 */
	public int comprobarColumnaContenida(int[][] matriz, int[] array) {

		int index = 0;
		int coincidencia = 0;
		int aux = 0;
		int columna = 0;
		boolean parar = false;

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				index = 0;
				aux = i;

				// Comprueba las coincidencias entre columna y array a partir de la primera.
				// Está calculado, con la última expresión, para que no se desborde el array.
				// Si se completan todas las coincidencias, está contenido.

				while ((index < array.length) && (array[index] == matriz[aux][j])
						&& ((array.length - i) > (array.length - 1))) {
					coincidencia++;
					index++;
					aux++;
				}

				if (coincidencia == array.length) {
					System.out.println("\nEl array se encuentra en la columna " + j);
					parar = true;
					columna = j;
					break;
				} else
					coincidencia = 0;
			}
			if (parar) {
				break;
			}
		}

		if (parar)
			return columna;
		else
			return -1;
	}

	/*
	 * METODO QUE COMPRUEBA SI UN ARRAY ESTÁ EN UNA FILA DE LA MATRIZ
	 * 
	 * @param matriz MATRIZ EN LA CUAL QUEREMOS COMPROBAR SI EL ARRAY SE ENCUENTRA
	 * 
	 * @param array ARRAY QUE QUEREMOS COMPROBAR SI ESTÁ
	 * 
	 */
	public int comprobarFilaContenida(int[][] matriz, int[] array) {

		int index = 0;
		int coincidencia = 0;
		int aux = 0;
		int fila = 0;
		boolean parar = false;

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				index = 0;
				aux = j;

				while ((index < array.length) && (array[index] == matriz[i][aux])
						&& ((array.length - j) > (array.length - 1))) {
					coincidencia++;
					index++;
					aux++;
				}

				if (coincidencia == array.length) {
					System.out.println("\nEl array se encuentra en la fila " + i);
					parar = true;
					fila = i;
					break;
				} else
					coincidencia = 0;
			}

			if (parar)
				break;
		}

		if (parar)
			return fila;
		else
			return -1;
	}

	/*
	 * METODO QUE EXTRE UNA FILA CONCRETA DE UNA MATRIZ
	 * 
	 * @param matriz MATRIZ DE LA CUAL QUEREMOS EXTRAER LA FILA
	 * 
	 * @param fila FILA QUE QUEREMOS EXTRAER
	 * 
	 * @return FILA YA EXTRAIDA EN UN ARRAY
	 * 
	 */
	public int[] extraeFila(int[][] matriz, int fila) {

		int[] arrayAuxiliar = new int[matriz[0].length];
		for (int i = 0; i < matriz[0].length; i++) {
			arrayAuxiliar[i] = matriz[fila][i];
		}
		return arrayAuxiliar;
	}

	/*
	 * METODO QUE EXTRE UNA COLUMNA CONCRETA DE UNA MATRIZ
	 * 
	 * @param matriz MATRIZ DE LA CUAL QUEREMOS EXTRAER LA COLUMNA
	 * 
	 * @param columna COLUMNA QUE QUEREMOS EXTRAER
	 * 
	 * @return COLUMNA YA EXTRAIDA EN UN ARRAY
	 * 
	 */
	public int[] extraeColumna(int[][] matriz, int columna) {

		int[] arrayAuxiliar = new int[matriz.length];

		for (int i = 0; i < matriz.length; i++) {
			arrayAuxiliar[i] = matriz[i][columna];
		}
		return arrayAuxiliar;
	}

	/*
	 * METODO QUE INSERTA UNA FILA EN UNA MATRIZ INDICANDO LA POSICION INCIAR
	 * 
	 * @param posX Posición Y del hueco de la matriz
	 * 
	 * @param posY Posición Y del hueco de la matriz
	 * 
	 * @param array Fila que deseamos insertar
	 * 
	 * @param matriz Matriz en la cual deseamos insertar la fila
	 * 
	 */
	public int[][] insertaFila(int posX, int posY, int[] array, int[][] matriz) {

		if (matriz[0].length - posY >= array.length) {
			for (int i = 0; i < array.length; i++) {
				matriz[posX][posY + i] = array[i];
			}
		} else {
			System.out.println("Esa Fila no cabe en esa posición");
		}
		return matriz;
	}

	/*
	 * METODO QUE INSERTA UNA COLUMNA EN UNA MATRIZ INDICANDO LA POSICION INICIAL
	 * 
	 * @param posX Posición Y del hueco de la matriz
	 * 
	 * @param posY Posición Y del hueco de la matriz
	 * 
	 * @param array Columna que deseamos insertar
	 * 
	 * @param matriz Matriz en la cual deseamos insertar la columna
	 * 
	 */
	public int[][] insertaColumna(int posX, int posY, int[] array, int[][] matriz) {

		if (matriz.length - posX >= array.length) {
			for (int i = 0; i < array.length; i++) {
				matriz[posX + i][posY] = array[i];
			}
		} else {
			System.out.println("Esa Columna no cabe en esa posición");
		}
		return matriz;
	}

	/*
	 * METODO QUE INSERTA UNA DIAGONAL EN UNA MATRIZ INDICANDO LA POSICION INICIAL
	 * 
	 * @param posX Posición Y del hueco de la matriz
	 * 
	 * @param posY Posición Y del hueco de la matriz
	 * 
	 * @param array Diagonal que deseamos insertar
	 * 
	 * @param matriz Matriz en la cual deseamos insertar la diagonal
	 * 
	 */
	public int[][] insertaDiagonal(int posX, int posY, int[] array, int[][] matriz) {

		if ((matriz.length - posX >= array.length) && (matriz[0].length - posY >= array.length)) {
			for (int i = 0; i < array.length; i++) {
				matriz[posX + i][posY + i] = array[i];
			}
		} else {
			System.out.println("Esa Diagonal no cabe en esa posición");
		}
		return matriz;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * METODO UTILES
	 */
	/////////////////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * METODO QUE CALCULA EL FACTORIAL
	 */
	public static int CalculaFactorial(int num) {

		if (num == 0)
			return 1;
		else
			return num * CalculaFactorial(num - 1);
	}

	public int factorialRecursivo(int n) {
		if (n < 0) {
			return 0;
		} else if (n == 0) {
			return 1;
		} else {
			return n * factorialRecursivo(n - 1);
		}

	}

	/*
	 * METODO QUE CALCULA LA SERIE FIBONACCI
	 */
	public int fibonacciRecursivo(int n) {
		if (n == 1 || n == 0) {
			return n;
		} else {
			return fibonacciRecursivo(n - 1) + fibonacciRecursivo(n - 2);
		}
	}

	/*
	 * TRIANGULO PASCAL
	 */
	public void TrianguloPascal() {
		int pisos = 5;
		int[] arreglo = new int[1];

		// CICLO FOR QUE VA RECORRER LA VARIABLE PISOS
		for (int i = 1; i <= pisos; i++) {
			// DECLARAMOS OTRO MATRIZ Y LE ASIGNAMOS EL VALOR DE I QUE SERÁ EL TAMAÑO QUE
			// TENDRÁ LA MATRIZ CADA VEZ QUE EL CICLO FOR SE EJECUTE
			int[] arreglo2 = new int[i];
			// FOR QUE SE DECREMENTA
			for (int j = pisos; j > i; j--) {
				// GENERA ESPACIOS
				System.out.print(" ");
			}
			for (int k = 0; k < i; k++) {
				// CONDICIÓN QUE EVALÚA LA VARIABLE DEL CICLO FOR
				if (k == 0 || k == (i - 1)) {
					arreglo2[k] = 1;
				} else {
					arreglo2[k] = arreglo[k] + arreglo[k - 1];
				}
				System.out.print(arreglo2[k]);
				System.out.print(" ");
			}
			arreglo = arreglo2;
			System.out.println();
		}
	}

	/*
	 * METODO CAPICUA
	 */
	public void esCapicua() throws IOException {

		System.out.println("Introduce un número");

		int numero = try_int();

		int faltante = numero;
		int numeroInvertido = 0;
		int restante = 0;

		while (faltante != 0) {
			restante = faltante % 10;
			numeroInvertido = numeroInvertido * 10 + restante;
			faltante = faltante / 10;
		}

		if (numeroInvertido == numero) {
			System.out.println("El número es capicua");
		} else {
			System.out.println("El número no es capicua");
		}

	}

	/*
	 * METODO PARA INVERTIR EL ORDEN DE UN NUMERO
	 */
	public int InvertirNumero(int num) {
		int numero_invertido, division, resto_division;

		numero_invertido = 0;
		division = num;
		resto_division = 0;
		while (division != 0) {
			resto_division = division % 10;
			division = division / 10;
			numero_invertido = numero_invertido * 10 + resto_division;
		}

		System.out.println("El numero " + num + " invertido es " + numero_invertido);
		return numero_invertido;
	}

	/*
	 * METODO QUE SOLICITA UN DATO DE TIPO INTEGER Y APLICA LOS TRY_CATCH
	 * 
	 * @return NUMERO ENTERO CONTROLADO
	 * 
	 */

	public int controlaInt() throws IOException {

		int entero = 0;

		do {

			try {

				entero = Integer.parseInt(Leer.readLine());

				error = false;

			} catch (NumberFormatException e) {

				// TODO: handle exception

				System.out.println("Valor no válido");

				error = true;

			}

		} while (error);

		return entero;

	}

	/*
	 * METODO QUE SOLICITA INTRODUCIR DATOS TIPO INT Y APLICA LOS TRY_CATCH
	 * NECESARIOS
	 * 
	 * @return NUMERO ENTERO CONTROLADO POSITIVO INCLUYENDO EL 0
	 */
	public int controlaIntPositivo() throws IOException {

		int n = 0;

		do {
			try {
				n = Integer.parseInt(Leer.readLine());

				aux = true;

				if (n < 0)

					System.out.println("Introduzca un valor positivo");

			} catch (NumberFormatException e) {

				System.out.println("Valor no válido");

				aux = false;

			}

		} while (aux = false || n < 0);

		return n;

	}
        
        public boolean controlaIntPositivoValido(int entero) throws IOException {
		boolean valido=true;
			try {
				if (entero < 0)
					valido=false;
				} catch (NumberFormatException e) {
						}
		return valido;
	}

	/*
	 * METODO QUE SOLICITA UN DATO DE TIPO INT Y APLICA LOS TRY_CATCH NECESARIOS
	 * 
	 * @return NUMERO ENTERO CONTROLADO ENTRE 0 Y 100
	 * 
	 */
	public int controlaIntPorcentaje() throws IOException {

		int n = 0;

		do {

			try {

				n = Integer.parseInt(Leer.readLine());

				aux = true;

				if (n < 0)

					System.out.println("Introduzca un valor mayor a 0");

				if (n > 100)

					System.out.println("Introduzca un valor menor a 100");

			} catch (NumberFormatException e) {

				// TODO: handle exception

				System.out.println("Valor no válido");

				aux = false;

			}

		} while (aux = false || n < 0 || n > 100);

		return n;

	}

	/*
	 * METODO QUE SOLICITA UN DATO TIPO DOUBLE Y APLICA LOS TRY_CATCH NECESARIOS
	 * 
	 * @return NUMERO DE TIPO DOUBLE CONTROLADO
	 * 
	 */

	public double controlaDouble() throws IOException {

		double entero = 0;

		do {

			try {

				entero = Double.parseDouble(Leer.readLine());

				error = false;

			} catch (NumberFormatException e) {

				// TODO: handle exception

				System.out.println("Valor no válido, vuelva a introducirlo");

				error = true;

			}

		} while (error);

		return entero;

	}

	/*
	 * METODO QUE SOLICITA UN DATO DE TIPO DOUBLE Y APLICA LOS TRY_CATCH NECESARIOS
	 * 
	 * @return NUMERO DOUBLE CONTROLADO POSITIVO INCLUYENDO EL 0
	 * 
	 */

	public double controlaDoubePositivo() throws IOException {

		double n = 0;

		do {
			try {
				n = Double.parseDouble(Leer.readLine());

				aux = true;

				if (n < 0)

					System.out.println("Introduzca un valor positivo");

			} catch (NumberFormatException e) {

				System.out.println("Valor no válido");

				aux = false;

			}

		} while (aux = false || n < 0);

		return n;

	}

	/*
	 * METODO QUE SOLICITA UN DATO DE TIPO DOUBLE Y APLICA LOS TRY_CATCH NECESARIOS
	 * 
	 * @return NUMERO TIPO DOUBLE CONTROLADO ENTRE 0 Y 100
	 * 
	 */
	public double controlaDoublePorcentaje() throws IOException {

		double n = 0;

		do {

			try {

				n = Integer.parseInt(Leer.readLine());

				aux = true;

				if (n < 0)

					System.out.println("Introduzca un valor mayor a 0");

				if (n > 100)

					System.out.println("Introduzca un valor menor a 100");

			} catch (NumberFormatException e) {

				// TODO: handle exception

				System.out.println("Valor no válido");

				aux = false;

			}

		} while (aux = false || n < 0 || n > 100);

		return n;
	}

	/*
	 * METODO QUE SOLICITA UN DATO TIPO INTEGER Y APLICA LOS TRY_CATCH NECESARIOS
	 * ENTRE UN MINIMO Y UN MAXIMO
	 * 
	 * @param min RANGO MINIMO DEL ENTERO
	 * 
	 * @param max RANGO MAXIMO DEL ENTERO
	 * 
	 * @return NUMERO ENTERO CONTROLADO
	 * 
	 */

	public int controlaIntMinMax(int min, int max) throws IOException {

		int aux = 0;

		if (min > max) {

			aux = max;

			max = min;

			min = aux;

		}

		int entero = 0;

		do {

			try {

				entero = Integer.parseInt(Leer.readLine());

				error = false;

				if (entero < min || entero > max)

					System.out.println("Introduzca un valor entre " + min + " y " + max);

			} catch (NumberFormatException e) {

				// TODO: handle exception

				System.out.println("Valor no válido");

				error = true;

			}

		} while (error || entero < min || entero > max);

		return entero;

	}

	/*
	 * METODO QUE SOLICITA UN DATO TIPO FLOAT Y APLICA LOS TRY_CATCH NECESARIOS
	 * 
	 * @return NUMERO TIPO FLOAT CONTROLADO
	 * 
	 */

	public float controlaFloat() throws IOException {

		float entero = 0;

		do {

			try {

				entero = Float.parseFloat(Leer.readLine());

				error = false;

			} catch (NumberFormatException e) {

				// TODO: handle exception

				System.out.println("Valor no válido, vuelva a introducirlo");

				error = true;

			}

		} while (error);

		return entero;

	}

	/*
	 * METODO QUE SOLICITA UN DATO TIPO FLOAT Y APLICA LOS TRY_CATCH NECESARIOS
	 * 
	 * @return NUMERO TIPO FLOAT POSITIVO INCLUYENDO EL 0
	 * 
	 */

	public float controlaFloatPositivo() throws IOException {

		float entero = 0;

		do {

			try {

				entero = Float.parseFloat(Leer.readLine());

				error = false;

				if (entero < 0)

					System.out.println("Introduzca un valor positivo");

			} catch (NumberFormatException e) {

				// TODO: handle exception

				System.out.println("Valor no válido, vuelva a introducirlo");

				error = true;

			}

		} while (error || entero < 0);

		return entero;

	}

	/*
	 * METODO QUE SOLICITA UNA CADENA DE CARACTERES POR TECLADO
	 * 
	 * COMPRUEBA SI SE HA ESCRITO 's', 'si', 'sí', 'yes', 'y', 'no', 'n'
	 * 
	 * TRUE SI SE HA ESCRITO 's', 'sí', 'si', 'yes','y' FALSE SI SE HA ESCRITO 'n',
	 * 'no
	 * 
	 */

	public boolean afirmaODesmiente() throws IOException {

		String respuesta;

		do {

			respuesta = Leer.readLine();

			aux = true;

			if (respuesta.equalsIgnoreCase("si") || respuesta.equalsIgnoreCase("y") || respuesta.equalsIgnoreCase("sí")

					|| respuesta.equalsIgnoreCase("s") || respuesta.equalsIgnoreCase("yes"))

				return true;

			else if (respuesta.equalsIgnoreCase("no") || respuesta.equalsIgnoreCase("n"))

				return false;

			else {

				aux = false;

				System.out.println("Valor no válido, introduzca si o no");

			}

		} while (aux == false);

		return true;

	}

	/*
	 * METODO PARA COMPROBAR QUE EL TEXTO NO ESTE VACIO
	 */
	public String compruebaTexto(String s) throws IOException {
		String respuesta = s;
		boolean vacio = true;
		char c;

		do {
			for (int i = 0; i < respuesta.length(); i++) {
				c = respuesta.charAt(i);
				if (!Character.isWhitespace(c)) {
					vacio = false;
					break;
				}
			}
			if (vacio) {
				System.out.println("El texto no debe estar vacío, introduzca datos");
				respuesta = try_String();
			}
		} while (vacio);
		return respuesta;
	}
        
        public boolean compruebaTextoVacio(String s) throws IOException {
            String respuesta=s;
            boolean valido = false;
            char c;
            for (int i = 0; i < respuesta.length(); i++) {
		c = respuesta.charAt(i);
		if (!Character.isWhitespace(c)) {
                    valido = true;
                    break;
		}
            }
            return valido;
	}

	/*
	 * METODO QUE COMPRUEBA EL FORMATO ADECUADO DE UN TLF
	 * 
	 */

	public String compruebaTlf(String s) throws IOException {

		String respuesta = s;

		respuesta = respuesta.trim().replaceAll("\\s", "");

		boolean error = false;

		char c;

		do {

			error = false;

			while (respuesta.length() != 9) {

				System.out.println("Longitud del teléfono incorrecta, vuelva a introducirlo");

				respuesta = compruebaTexto(respuesta);

				respuesta = respuesta.trim().replaceAll("\\s", "");

			}

			for (int i = 0; i < 9; i++) {

				c = respuesta.charAt(i);

				if (!Character.isDigit(c)) {

					error = true;

					break;

				}

			}

			if (error) {

				System.out.println("Teléfono no válido. Vuelva a introducir");

				respuesta = Leer.readLine();

				respuesta = respuesta.trim().replaceAll("\\s", "");

			}

		} while (error);

		return respuesta;

	}

	/*
	 * METODOS DE MANEJO DE FICHEROS
	 * 
	 */

	public File crearArchivo(String nombreArchivo) {

		File archivo = new File(nombreArchivo);

		try {

			PrintWriter salida = new PrintWriter(new FileWriter(archivo));
			salida.close();
			System.out.println("El fichero se ha creado correctamente\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return archivo;

	}

	public File abrirArchivo(String nombreArchivo) {

		File archivo = new File(nombreArchivo);

		try {
			PrintWriter salida = new PrintWriter(new FileWriter(archivo, true));
			salida.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return archivo;

	}

	public File abrirArchivo(File archivo) {

		try {
			PrintWriter salida = new PrintWriter(new FileWriter(archivo, true));
			salida.close();
			System.out.println("El fichero se ha abierto correctamente\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return archivo;

	}

	public void escribirArchivo(File archivo, String contenido) {

		try {
			PrintWriter salida = new PrintWriter(new FileWriter(archivo, true));
			salida.println("\n" + contenido);
			salida.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void leerArchivo(File archivo) {

		try {
			BufferedReader entrada = new BufferedReader(new FileReader(archivo));

			String lectura;
			lectura = entrada.readLine();

			while (lectura != null) {
				System.out.println(lectura);
				lectura = entrada.readLine();
			}
			entrada.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void anexarArchivo(File archivo, String contenido) {

		try {
			PrintWriter salida = new PrintWriter(new FileWriter(archivo, true));
			salida.println(contenido);
			salida.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void cuentaPalabras(File file) throws IOException {

		ArrayList<String> lineas = new ArrayList<String>();

		ArrayList<String> palabras = new ArrayList<String>();

		BufferedReader lector = new BufferedReader(new FileReader(file));

		String linea = lector.readLine();

		while (linea != null) {
			lineas.add(linea);
			linea = lector.readLine();
		}
		lector.close();
		for (String s : lineas) {
			palabras.addAll(Arrays.asList(s.split("\\s")));
		}

		System.out.println(palabras.size());

	}

	public void cuentaPalabrasPorLinea(File file) throws IOException {

		ArrayList<String> lineas = new ArrayList<String>();

		ArrayList<String> palabras = new ArrayList<String>();

		int cont = 1;

		BufferedReader lector = new BufferedReader(new FileReader(file));

		String linea = lector.readLine();

		while (linea != null) {
			lineas.add(linea);
			linea = lector.readLine();
		}

		lector.close();

		for (String s : lineas) {
			System.out.println("Línea " + cont + " : " + s.split("\\s").length + " palabras");
			cont++;
		}
	}

	public void contieneCaracter(char c, File f) throws IOException {

		BufferedReader lector = new BufferedReader(new FileReader(f));

		String linea = lector.readLine();

		while (linea != null) {
			char[] aux = linea.toCharArray();
			boolean contiene = false;
			for (int i = 0; i < aux.length; i++) {
				if (linea.charAt(i) == c) {
					contiene = true;
					break;
				}
			}

			if (contiene) {
				System.out.println(linea);
				linea = lector.readLine();
			} else {
				linea = lector.readLine();
			}
		}
		lector.close();
	}

	public void leerCaracterPorCaracter(File f) throws IOException {

		BufferedReader lector = new BufferedReader(new FileReader(f));

		char c = (char) lector.read();

		while (c != (char) (-1)) {
			System.out.println(c);
			c = (char) lector.read();

		}

		lector.close();

	}

	public static void leerCPorCMayus(File f) throws IOException {

		BufferedReader lector = new BufferedReader(new FileReader(f));

		char c = (char) lector.read();

		while (c != (char) (-1)) {
			if (Character.isDigit(c))
				c = (char) lector.read();
			else {
				if (Character.isLowerCase(c))
					c = Character.toUpperCase(c);
				System.out.println(c);
				c = (char) lector.read();

			}
		}

		lector.close();
	}
}
