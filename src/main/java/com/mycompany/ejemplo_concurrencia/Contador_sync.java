/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejemplo_concurrencia;

/**
 *
 * @author Mario
 */
public class Contador_sync {
    private int contador = 0;

    // Método sincronizado: solo un hilo puede ejecutarlo a la vez
    public synchronized void incrementar() {
        contador++;
        System.out.println(Thread.currentThread().getName() + " incrementó el contador a " + contador);
    }

    public static void main(String[] args) {
        Contador_sync ejemplo = new Contador_sync();

        // Creamos dos hilos que intentan modificar el mismo contador
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                ejemplo.incrementar();
            }
        }, "Hilo 1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                ejemplo.incrementar();
            }
        }, "Hilo 2");

        t1.start();
        t2.start();
    }
}
