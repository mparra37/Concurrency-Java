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
public class Sumador1 extends RecursiveTask<Long>{
    private int limite = 1000;
    private int[] arreglo;
    private int inicio;
    private int fin;
    
    public Sumador1(int inicio, int fin, int[] arreglo){
        this.inicio = inicio;
        this.fin = fin;
        this.arreglo = arreglo;
    }
    
   
    public Sumador1(int inicio, int fin, int[] arreglo, int limite){
        this(inicio, fin, arreglo);
        this.limite = limite;
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
            
            int mitad = (fin+inicio) / 2;
            
            Sumador1 izquierda = new Sumador1(inicio, mitad, arreglo);
            Sumador1 derecha = new Sumador1(mitad, fin, arreglo);
            
            //fork lanza la tarea en paralelo
            izquierda.fork();
            
            //compute ejecuta directamente la tarea
            long res_derecho = derecha.compute();
            
            long res_izquierda = izquierda.join();
            
            return res_izquierda + res_derecho;
            
        }
        
        
        
    }
    
}
