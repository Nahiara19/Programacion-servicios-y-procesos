import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        String serverAddress = "192.168.56.1"; // Dirección del servidor
        int port = 12345; // Puerto del servidor

        try (Socket socket = new Socket(serverAddress, port)) {
            System.out.println("Conectado al servidor");

            // Flujos de entrada y salida
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String salida = "";
            while (salida != "FIN") {
                // Enviar mensaje al servidor
                Scanner sc = new Scanner(System.in);
                System.out.println("Mensaje al Cliente");
                String message = sc.nextLine();
                out.println(message);
                System.out.println("Mensaje enviado: " + message);

                // Leer respuesta del servidor
                salida = in.readLine();
                System.out.println("Respuesta del servidor: " + salida);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
