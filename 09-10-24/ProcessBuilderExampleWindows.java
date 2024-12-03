import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class ProcessBuilderExampleWindows {

    public static void main(String[] args) {
        // Crea el proceso para ejecutar el comando "dir"
        ProcessBuilder processBuilder = new ProcessBuilder();
        
        // El comando para Windows debe ser "cmd.exe" con el argumento "/c" seguido de "dir"
        processBuilder.command("cmd.exe", "/c", "dir");

        try {
            // Inicia el proceso
            Process process = processBuilder.start();
            
            // Captura la salida del proceso
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            
            // Imprime cada línea de la salida
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Espera a que el proceso termine y obtiene el código de salida
            int exitCode = process.waitFor();
            System.out.println("\nEl proceso terminó con el código: " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
