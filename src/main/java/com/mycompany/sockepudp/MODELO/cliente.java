/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sockepudp.MODELO;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import javax.swing.JOptionPane;

/**
 *
 * @author Pablo
 */

//Comentario de prueba para el repositoria
public class cliente {

    public static void main(String[] args) {

        String sms = JOptionPane.showInputDialog("Escriba una palabra");
        

        try {

            boolean vali = true;
            while (vali) {                
                byte[] buffer = new byte[5000];
            vali= true;
            if (sms != null) {
              

                DatagramSocket socketCli = new DatagramSocket();
                byte[] mensaje = sms.getBytes();
                InetAddress host = InetAddress.getByName("127.0.0.1");
                int puerto = 9107;
                DatagramPacket packetCli = new DatagramPacket(mensaje, sms.length(), host, puerto);
                socketCli.send(packetCli);

                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
                socketCli.receive(peticion);
                String resultado = new String(peticion.getData());
                //System.out.println(resultado);
                JOptionPane.showMessageDialog(null, resultado);
                
                 sms = JOptionPane.showInputDialog("Escriba una palabra");
                 vali= true;

            } else {
                JOptionPane.showMessageDialog(null, "Acaba de cerrra el programa");
                vali = false;
            }
            }
            

            //socketCli.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* final int PUERTOSer  = 5000;
        byte[] buffer = new byte[1024];

        try {

            InetAddress direccionSer = InetAddress.getByName("localhost");
            DatagramSocket socketUDPCli = new DatagramSocket();
            
            String mensaje ="Hola servidor";
            
            buffer =mensaje.getBytes();
            
            DatagramPacket consulta = new DatagramPacket(buffer, buffer.length, direccionSer, PUERTOSer);
            
            socketUDPCli.send(consulta);
            
            DatagramPacket peticionCli = new DatagramPacket(buffer, buffer.length);
            socketUDPCli.receive(consulta);
            
            mensaje = new String(consulta.getData());
            
            System.out.println(mensaje);
            
            socketUDPCli.close();
            

        } catch (Exception e) {
        }*/
    }

}
