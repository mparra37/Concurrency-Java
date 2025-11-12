/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.ejemplo_concurrencia;

/**
 *
 * @author Mario
 */
public class Cajero {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        CuentaBancaria cuenta = new CuentaBancaria();

        // Dos hilos simulando dos personas retirando del mismo saldo
        Thread persona1 = new Thread(() -> cuenta.retirarDinero("Carlos", 70));
        Thread persona2 = new Thread(() -> cuenta.retirarDinero("Ana", 70));

        persona1.start();
        persona2.start();
    }
    
}
