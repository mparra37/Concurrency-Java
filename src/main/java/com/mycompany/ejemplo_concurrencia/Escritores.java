/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.ejemplo_concurrencia;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author Mario
 */
public class Escritores {

  private static boolean ocupado = false; // indica si el archivo está en uso
    private static final Object lock = new Object(); // objeto para sincronizar
    private static final String archivo = "mensajes.txt";

    public static void main(String[] args) {

        Thread escritor1 = new Thread(() -> escribirArchivo("Escritor 1"));
        Thread escritor2 = new Thread(() -> escribirArchivo("Escritor 2"));

        escritor1.start();
        escritor2.start();
    }

    public static void escribirArchivo(String nombreEscritor) {
        Scanner sc = new Scanner(System.in);

        synchronized (lock) {
            if (ocupado) {
                System.out.println(nombreEscritor + ": el archivo está ocupado, esperaré...");
            }

            while (ocupado) { // espera hasta que quede libre
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    return;
                }
            }

            ocupado = true;
        }

        // sección crítica: solo un hilo puede escribir
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo, true))) {
            System.out.println(nombreEscritor + ": puede escribir. Escribe tus líneas ('fin' para salir):");
            while (true) {
                String linea = sc.nextLine();
                if (linea.equalsIgnoreCase("fin")) break;
                pw.println(nombreEscritor + ": " + linea);
            }
            System.out.println(nombreEscritor + ": terminó de escribir.");
        } catch (IOException e) {
            System.out.println(nombreEscritor + ": error al escribir -> " + e.getMessage());
        }

        // libera el archivo para el siguiente escritor
        synchronized (lock) {
            ocupado = false;
            lock.notifyAll();
        }
    }
}
