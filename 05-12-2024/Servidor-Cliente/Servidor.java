import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Servidor {
    public static void main(String[] args) {
        int port = 12345; // Puerto donde el servidor escuchará

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor escuchando en el puerto " + port);

            // Aceptar una conexión
            Socket clientSocket = serverSocket.accept();
            System.out.println("Cliente conectado: " + clientSocket.getInetAddress());

            // Flujos de entrada y salida
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            String clientMessage, serverMessage;

            // Bucle de comunicación
            while (true) {
                // Leer mensaje del cliente
                clientMessage = in.readLine();
                if (clientMessage == null || clientMessage.equalsIgnoreCase("salir")) {
                    System.out.println("El cliente ha cerrado la conexión.");
                    break;
                }
                System.out.println("Cliente: " + clientMessage);

                // Enviar mensaje al cliente
                System.out.print("Servidor: ");
                serverMessage = scanner.nextLine();
                out.println(serverMessage);

                if (serverMessage.equalsIgnoreCase("salir")) {
                    System.out.println("Servidor cerrando la conexión...");
                    break;
                }
            }

            clientSocket.close();
            System.out.println("Conexión cerrada.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}