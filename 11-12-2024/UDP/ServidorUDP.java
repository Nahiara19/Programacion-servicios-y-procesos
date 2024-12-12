package UDP;

import java.net.*;

public class ServidorUDP {
    public static void main(String[] args) {
        try(DatagramSocket socket = new DatagramSocket(5000)){
            System.out.println("Servidor UDP escuchando en el puerto 5000...");

            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            while(true){
                //Recibir mensaje del cliente
                socket.receive(packet);
                String mensaje = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Mensaje recibido: " + mensaje);

                // Responder al cliente
                String respuesta = "Hola, desde el servidor!";
                byte [] respuestaBytes = respuesta.getBytes();
                InetAddress clienteDireccion = packet.getAddress(); 
                int clientePuerto = packet.getPort();
                DatagramPacket respuestaPacket = new DatagramPacket(
                    respuestaBytes, respuestaBytes.length, clienteDireccion, clientePuerto);
                socket.send(respuestaPacket);
            }
        } catch (Exception e) {
            System.out.println("Error en el servidor UDP: " + e.getMessage());
        }
    }
}
