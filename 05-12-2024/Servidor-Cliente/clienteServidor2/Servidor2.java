package clienteServidor2;

import java.io.*;
import java.net.*;

public class Servidor2 {
    public static void connect () {
        try{
        ServerSocket socket = new ServerSocket(1234);
        System.out.println("Servidor iniciado");
        while(true){
            Socket clientSocket = socket.accept();
            System.out.println("Cliente conectado" + clientSocket.getInetAddress());
            new Thread(() -> handleClient(clientSocket)).start();
        }
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }
    public static void handleClient (Socket socket) {
        try(BufferedReader reader = new BufferedReader(
            new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {
                String message;
                while((message = reader.readLine()) != null) {
                    System.out.println("Cliente: " + message

        
    }
}
