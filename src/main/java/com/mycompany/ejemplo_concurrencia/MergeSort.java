/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.ejemplo_concurrencia;

/**
 *
 * @author Mario
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] nums = {5, 2, 8, 3, 1, 7};
        mergeSort(nums);
        for (int n : nums) {
            System.out.print(n + " ");
        }
    }

    // Método principal que inicia el proceso
    public static void mergeSort(int[] arr) {
        if (arr.length <= 1) return; // caso base

        int mid = arr.length / 2;

        // Dividir el arreglo en dos mitades
        int[] izquierda = new int[mid];
        int[] derecha = new int[arr.length - mid];

        for (int i = 0; i < mid; i++) izquierda[i] = arr[i];
        for (int i = mid; i < arr.length; i++) derecha[i - mid] = arr[i];

        // Ordenar cada mitad recursivamente
        mergeSort(izquierda);
        mergeSort(derecha);

        // Combinar las dos mitades ya ordenadas
        merge(arr, izquierda, derecha);
    }

    // Combina dos subarreglos ordenados
    public static void merge(int[] arr, int[] izq, int[] der) {
        int i = 0, j = 0, k = 0;

        // Mientras haya elementos en ambas mitades
        while (i < izq.length && j < der.length) {
            if (izq[i] < der[j]) {
                arr[k++] = izq[i++];
            } else {
                arr[k++] = der[j++];
            }
        }

        // Copiar los elementos restantes
        while (i < izq.length) arr[k++] = izq[i++];
        while (j < der.length) arr[k++] = der[j++];
    }
    
}
