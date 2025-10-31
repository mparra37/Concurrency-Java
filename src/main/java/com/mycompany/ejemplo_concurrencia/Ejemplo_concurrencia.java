/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ejemplo_concurrencia;

import java.util.concurrent.ForkJoinPool;

/**
 *
 * @author Mario
 */
public class Ejemplo_concurrencia {

    public static void main(String[] args) {
        
        
        int datos[] = new int[100000000];
        for (int i = 0; i < datos.length; i++) {
            datos[i] = i;
        }
        
        ForkJoinPool pool = new ForkJoinPool();
        Sumador tarea = new Sumador(0, datos.length, datos);
        
        
        long resultado = pool.invoke(tarea);
        System.out.println("Suma =" + resultado);
        
    }
}
