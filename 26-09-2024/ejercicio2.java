import java.util.concurrent.TimeUnit;

public class ejercicio2 {
    public static void main(String[] args) {
        try {
            ProcessBuilder pb1 = new ProcessBuilder("ping", "google.com");
            ProcessBuilder pb2 = new ProcessBuilder("ping", "yahoo.com");

            Process p1 = pb1.start();
            Process p2 = pb2.start();

            // Esperar un máximo de 5 segundos para que el proceso 1 termine
            if (p1.waitFor(5, TimeUnit.SECONDS)) {
                System.out.println("El proceso 5 terminó a tiempo.");
            } else {
                System.out.println("El proceso 1 tardó demasiado.");
            }

            // Esperar un máximo de 5 segundos para que el proceso 2 termine
            if (p2.waitFor(5, TimeUnit.SECONDS)) {
                System.out.println("El proceso 2 terminó a tiempo.");
            } else {
                System.out.println("El proceso 2 tardó demasiado.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}