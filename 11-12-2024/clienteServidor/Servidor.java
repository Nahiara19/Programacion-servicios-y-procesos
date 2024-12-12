package clienteServidor;

import java.io.*;   // Importar clases para la entrada y salida de datos
import java.net.*;  // Importar clases para trabajar con sockets y redes

public class Servidor {
    public static void main(String[] args) {
        try {
            // PISTA: Crea un servidor que escuche en un puerto (por ejemplo, 5000)
            ServerSocket servidor = new ServerSocket(5000);
            
            System.out.println("Servidor escuchando en el puerto 5000...");
            
            while (true) {
                // PISTA: Aceptar la conexión de un cliente usando el objeto ServerSocket
                Socket cliente = servidor.accept();
                
                System.out.println("Cliente conectado");

                // PISTA: Crear un flujo de entrada para recibir datos del cliente
                DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());
                
                // PISTA: Crear un flujo de salida para enviar datos al cliente
                DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());
                
                // PISTA: Leer un mensaje del cliente utilizando el flujo de entrada
                String mensaje = flujoEntrada.readUTF();
                
                // PISTA: Imprimir el mensaje recibido en la consola
                System.out.println("Mensaje recibido del cliente: " + mensaje);
                
                // PISTA: Responder al cliente enviando un mensaje a través del flujo de salida
                flujoSalida.writeUTF("Mensaje recibido con éxito");
                
            }
        } catch (IOException e) {
            // PISTA: Manejar posibles errores de entrada/salida
            e.printStackTrace();
        }
    }
}