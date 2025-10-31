/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejemplo_concurrencia;

import java.util.concurrent.RecursiveTask;

/**
 *
 * @author Mario
 */
public class Sumador extends RecursiveTask<Long>{
    private int limite = 1000;
    private int[] arreglo;
    private int inicio;
    private int fin;
    
    public Sumador(int inicio, int fin, int[] arreglo){
        this.arreglo = arreglo;
        this.inicio = inicio;
        this.fin = fin;
        
    }

    @Override
    protected Long compute() {
        int largo = fin-inicio;
        
        if(largo <= limite){
            long suma = 0;
            for(int i = inicio; i < fin; i++){
                suma += arreglo[i];
            }
            return suma;
        }else{
            
            int mitad = (inicio + fin) / 2;
            
            Sumador izquierda = new Sumador(inicio, mitad, arreglo);
            Sumador derecha = new Sumador(mitad, fin, arreglo);
            
            
            // fork() lanza la tarea en paralelo
            izquierda.fork();
            
            //compute() ejecuta la otra tarea directamente
            long res_derecha = derecha.compute();
            
            long res_izq = izquierda.join();
            
            return res_izq + res_derecha;
            
            
        }
        
        
    }
    
}
