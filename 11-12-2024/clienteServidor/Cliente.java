package clienteServidor;

import java.io.*;   // Importar clases para entrada y salida de datos
import java.net.*;  // Importar clases para trabajar con sockets y redes

public class Cliente {
    public static void main(String[] args) {
        try {
            // PISTA: Crear un socket para conectarse al servidor. 
            // Usa la dirección IP del servidor ("127.0.0.1" para localhost) y el puerto (5000).
            Socket clienteSocket = new Socket("127.0.0.1", 5000);
            
            // PISTA: Crear un flujo de salida para enviar datos al servidor
            DataOutputStream flujoSalida = new DataOutputStream(clienteSocket.getOutputStream());
            
            // PISTA: Crear un flujo de entrada para recibir datos del servidor
            DataInputStream flujoEntrada = new DataInputStream(clienteSocket.getInputStream());
            
            // PISTA: Enviar un mensaje al servidor utilizando el flujo de salida
            String mensaje = "Hola, servidor!";
            
            // PISTA: Leer la respuesta del servidor usando el flujo de entrada
            String respuesta = flujoEntrada.readUTF();
            
            // PISTA: Imprimir la respuesta del servidor en la consola
            System.out.println("Respuesta del servidor: " + respuesta);
            
        } catch (IOException e) {
            // PISTA: Manejar posibles errores de entrada/salida
            e.printStackTrace();
        }
    }
}
