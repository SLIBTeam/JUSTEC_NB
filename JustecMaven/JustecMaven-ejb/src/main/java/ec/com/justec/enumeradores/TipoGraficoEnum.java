/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.justec.enumeradores;


public enum TipoGraficoEnum {
	LINEAL(" Gráfico lineal", 1)
    ,BARRAS(" Gráfico de barras", 2)
    //,PASTEL(" Gráfico de pastel", 3)
    ;

    private String nombre;
    private int opcion;

    TipoGraficoEnum(String nombre, int opcion) {
        this.nombre = nombre;
        this.opcion = opcion;
    }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getOpcion() {
		return opcion;
	}

	public void setOpcion(int opcion) {
		this.opcion = opcion;
	}

 

}
