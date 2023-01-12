/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sockepudp.MODELO;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Pablo
 */
public class servidor {

    public static void main(String[] args) {

        HashMap<String, String> map = new HashMap<String, String>();

        map.put("Java", "Es el lenguaje de programacion mas usado");
        map.put("Cuenca", "Una de las ciudades mas importantes del Ecuador conocida como las atenas del Ecuador");
        map.put("Guayakill", "Ciudad mas peligrosa del pais");
        map.put("Aucas", "Actual campeon de la LigaPro");
        map.put("Barcelona", "Perdio con el Aucas");
        map.put("Software", "Parte logica de un ordenador");
        map.put("Hardware", "Parte fisica de un ordenador");
        map.put("CPU", "Unidad Central de Proceso");
        map.put("Real Madrid", "Equipo español de futbol. actual campeon de la UEFA CL ademas de ser el mas ganador de dicho torneo, unico equipo que posee el titulo de mejor equipo del siglo");

        try {

            DatagramSocket socketServer = new DatagramSocket(9107);

            byte[] buffer = new byte[5000];

            while (true) {

                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
                socketServer.receive(peticion);
                /*System.out.println("DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
                socketServer.receive(peticion);ip cliente: " + peticion.getAddress());
                System.out.println("puerto: "+ peticion.getPort());
                System.out.println("mensaje del cliente: "+ new String(peticion.getData(),0,peticion.getLength()));
                 */

                String valor2 = new String(peticion.getData(), 0, peticion.getLength());

                int val = 0;

                for (Map.Entry<String, String> entry : map.entrySet()) {

                    final String palabra = entry.getKey();

                    if (valor2.equalsIgnoreCase(palabra)) {

                        int puerto = peticion.getPort();
                        InetAddress host = peticion.getAddress();
                        String conp = entry.getValue();
                        byte[] respuesta = conp.getBytes();
                        DatagramPacket packetCli = new DatagramPacket(respuesta, conp.length(), host, puerto);
                        socketServer.send(packetCli);

                        val = 0;
                        break;

                    } else {
                        val = 1;

                    }

                }

                if (val == 1) {
                    int puerto = peticion.getPort();
                    InetAddress host = peticion.getAddress();
                    String conp = "Palabra no encontrada, ingrese otra";
                    byte[] respuesta = conp.getBytes();
                    DatagramPacket packetCli = new DatagramPacket(respuesta, conp.length(), host, puerto);
                    socketServer.send(packetCli);
                }

            }

        } catch (Exception e) {
        }

        /*final int PUERTO = 5000;
        byte[] buffer = new byte[1024];

        try {

            System.out.println("INICIANDO EL SERVIDOR");
            DatagramSocket socketUDP = new DatagramSocket(PUERTO);

            DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
            socketUDP.receive(peticion);
            System.out.println("Recibiendo la informacion del cliente");

            String mensaje = new String(peticion.getData());
            System.out.println(mensaje);

            int puertoCliente = peticion.getPort();
            InetAddress direccion = peticion.getAddress();

            mensaje = "Hola amigoo";
            buffer = mensaje.getBytes();

            DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, direccion, puertoCliente);

            System.out.println("Enviando la informacion al cliente");
            socketUDP.send(respuesta);

        } catch (Exception e) {
        }*/
    }

}
