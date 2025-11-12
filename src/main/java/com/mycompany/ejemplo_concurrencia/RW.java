/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.ejemplo_concurrencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author Mario
 */
public class RW {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        
        String archivo = "mensajes.txt";
        Scanner sc = new Scanner(System.in);

        // Hilo escritor: guarda cada línea del usuario en el archivo
        Thread escritor = new Thread(() -> {
            try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
                System.out.println("Escribe tus mensajes (escribe 'fin' para terminar):");
                while (true) {
                    String linea = sc.nextLine();
                    if (linea.equalsIgnoreCase("fin")) break;
                    pw.println(linea);
                }
                System.out.println("Escritura terminada. Archivo guardado.");
            } catch (IOException e) {
                System.out.println("Error al escribir: " + e.getMessage());
            }
        });

        // Hilo lector: leerá el archivo completo después de que termine el escritor
        Thread lector = new Thread(() -> {
            System.out.println("\nContenido del archivo:");
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    System.out.println(linea);
                }
            } catch (IOException e) {
                System.out.println("Error al leer: " + e.getMessage());
            }
        });

        // Ejecutar
        escritor.start();
        try {
            escritor.join(); // Esperar a que termine de escribir
        } catch (InterruptedException ex) {
            System.getLogger(RW.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        lector.start();
    }
    
    
}
