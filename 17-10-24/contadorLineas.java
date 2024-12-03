import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class contadorLineas {
    public static void main(String[] args) {
        // Verifica que se haya pasado el nombre del archivo como argumento
        if (args.length != 1) {
            System.out.println("Uso: java ContadorLineasCodigo <nombre_del_archivo.java>");
            return;
        }

        String nombreArchivo = args[0];
        // Comando para contar líneas de código
        ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "find /c /v \"\" " + nombreArchivo);

        try {
            // Inicia el proceso
            Process p = pb.start();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String linea;

            // Lee la salida del comando
            while ((linea = buffer.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
} 
    

