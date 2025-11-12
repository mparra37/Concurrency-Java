/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.ejemplo_concurrencia;

/**
 *
 * @author Mario
 */
public class CuentaBancaria {
 private int saldo = 100;

    // Método sincronizado para evitar que dos hilos retiren al mismo tiempo
    public synchronized void retirarDinero(String nombre, int cantidad) {
        if (saldo >= cantidad) {
            System.out.println(nombre + " va a retirar " + cantidad);
            try {
                Thread.sleep(1000); // Simula el tiempo de procesamiento
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            saldo -= cantidad;
            System.out.println(nombre + " completó el retiro. Saldo restante: " + saldo);
        } else {
            System.out.println(nombre + " no pudo retirar. Saldo insuficiente.");
        }
    }
    
}
