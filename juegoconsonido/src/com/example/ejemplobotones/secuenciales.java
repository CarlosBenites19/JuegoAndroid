package com.example.ejemplobotones;

public class secuenciales {
	static int[] datos;
	int posicion;
	static int posAleatoria;
	static int temp;
	public secuenciales(){
		datos=new int[26];
		for(int i=1;i<datos.length;i++){
			datos[i]=i;
		}
	}
		public void revolver(){
			for (int i = 1; i<datos.length; i++){
				posAleatoria = (int) Math.random()*25 +1;
				temp = datos[i];
				datos[i] = datos[posAleatoria];
				datos[posAleatoria] = temp;
			}
		}
}
