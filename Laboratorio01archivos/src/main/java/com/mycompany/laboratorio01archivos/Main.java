/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.laboratorio01archivos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author criss
 */
public class Main {
    
    private void EscrituraSeparadorBinario(String Nombre, byte Edad, int Telefono)
    {    byte separador = '|';
        try {
            RandomAccessFile archivo = new RandomAccessFile("Agenda1.bin","rw");
            archivo.seek(archivo.length());
            archivo.writeUTF(Nombre);
            archivo.writeByte(Edad);
            archivo.writeInt(Telefono);
            archivo.writeByte(separador);
            archivo.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    private void LecturaSeperadorBinario()
    {
        try {
            RandomAccessFile archivo = new RandomAccessFile("Agenda1.bin","r");
            
            String nombre1;
            byte[] separador = new byte[1];
            byte nombre[] = new byte[30];
            byte edad=0;
            int telefono=0;
            boolean masregistros=true;

            while(masregistros==true)
            {
                try
                {
              nombre1 = archivo.readUTF();
              //archivo.read(nombre);
              edad = archivo.readByte();
              telefono = archivo.readInt();
              archivo.read(separador);
                System.out.println('\n' + "Nombre: " + new String(nombre1));
                System.out.println("Telefono: " + telefono);
                System.out.println("Edad: " + edad);
                } catch (IOException ex) {
                   masregistros = false;
                   }
            }
            archivo.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    private void EscrituraSeparadorTamano(String Nombre, byte Edad, int Telefono)
    {
        try {
            RandomAccessFile archivo = new RandomAccessFile("Agenda2.bin","rw");
            archivo.seek(archivo.length());
            archivo.writeBytes(Nombre);
            archivo.writeByte(Edad);
            archivo.writeInt(Telefono);
            archivo.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    private void LecturaSeparadorTamano()
    {
        try {
            RandomAccessFile archivo = new RandomAccessFile("Agenda2.bin","r");
            
            byte nombre[] = new byte[30];
            byte edad=0;
            int telefono=0;
            while(archivo.read(nombre) != -1){
                edad = archivo.readByte();
                telefono = archivo.readInt();
                
                System.out.println('\n' + "Nombre: " + new String(nombre));
                System.out.println("Telefono: " + telefono);
                System.out.println("Edad: " + edad);
            }

            archivo.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
     Scanner scan = new Scanner(System.in);
     Scanner scan1 = new Scanner(System.in);
     Main main  = new Main();
        char[] Nombre = new char[30];
        for(int i=0;i < 30;i++){
           Nombre[i] = '0';
        }
        byte op;
        
        do{
            System.out.println("-------- Menu Agenda --------");
            System.out.println("1.Ingresar datos a la agenda");
            System.out.println("2.Ver datos de la agenda");
            System.out.println("3.SALIR");
            System.out.println("Ingrese la opcion");
            op = scan1.nextByte();
            
            
            switch(op)
            {
                case 1:
                    scan.nextLine();
                System.out.println("Nombre: ");
                String nombre = scan.nextLine();
                String nombreV;
                char[] NombreV = new char[nombre.length()];
                for(int i=0;i<nombre.length();i++){
                    Nombre[i] = nombre.charAt(i);
                    NombreV[i] = nombre.charAt(i);
                }
                nombreV = String.valueOf(NombreV);
                nombre = String.valueOf(Nombre);
                System.out.println("Telefono:  ");
                int telefono = scan.nextInt();
                System.out.println("Edad: ");
                byte edad = scan.nextByte();
                main.EscrituraSeparadorBinario(nombreV, edad, telefono);
                main.EscrituraSeparadorTamano(nombre, edad, telefono);
                System.out.println("Los datos han sido guardados correctamente..."); 
                    break;
                    
                case 2:
                System.out.println("Los datos en la agenda con separador binario son: ");
                main.LecturaSeperadorBinario();
                System.out.println("Los datos en la agenda con separador por tamaño son: ");
                main.LecturaSeparadorTamano();
                    break;
            }       
        System.out.println("");
        }while(op != 3);
    }
}
