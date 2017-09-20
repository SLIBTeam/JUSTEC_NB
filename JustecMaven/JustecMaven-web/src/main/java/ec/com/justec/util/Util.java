package ec.com.justec.util;

import java.io.File;

import ec.com.justec.recursos.Constantes;

public class Util {

	public static void crearDirectorio(String nombre) {
		if (System.getProperty("os.name").toUpperCase().contains("WINDOWS")) {
			File folder = new File(
					System.getProperty("jboss.home.dir") + "\\" + Constantes.FOLDER_SERVIDOR + "\\" + nombre);
			if (!folder.exists()) {
				folder.mkdir();
			}
		} else {
			File folder = new File(
					System.getProperty("jboss.home.dir") + "/" + Constantes.FOLDER_SERVIDOR + "/" + nombre);
			if (!folder.exists()) {
				folder.mkdir();
			}
		}
	}

	public static String reemplazarEspacios(String cadena) {
		cadena = cadena.replace(" ", "_");
		return cadena;
	}
	
	public static String quitarTildes(String cadena) {
		cadena = cadena.replaceAll("á", "a");
		cadena = cadena.replaceAll("é", "e");
		cadena = cadena.replaceAll("í", "i");
		cadena = cadena.replaceAll("ó", "o");
		cadena = cadena.replaceAll("ú", "u");
		cadena = cadena.replaceAll("Á", "A");
		cadena = cadena.replaceAll("É", "E");
		cadena = cadena.replaceAll("Í", "I");
		cadena = cadena.replaceAll("Ó", "O");
		cadena = cadena.replaceAll("Ú", "U");
		return cadena;
	}

	public static String obtenerRutaDocumentos() {
		String ruta = "";
		if (System.getProperty("os.name").toUpperCase().contains("WINDOWS")) {
			ruta = System.getProperty("jboss.home.dir") + "\\" + Constantes.FOLDER_SERVIDOR + "\\"
					+ Constantes.FOLDER_LEYES+"\\";
		} else {
			ruta = System.getProperty("jboss.home.dir") + "/" + Constantes.FOLDER_SERVIDOR + "/"
					+ Constantes.FOLDER_LEYES+"/";
		}
		return ruta;
	}

}
