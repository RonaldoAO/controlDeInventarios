/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Ronaldo
 */
public class test {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(
                          new InputStreamReader(System.in));
    String[] cadena  = br.readLine().split(" "); 
        System.out.println(Integer.parseInt(cadena[0]) + Integer.parseInt(cadena[1]) );
    }
}
