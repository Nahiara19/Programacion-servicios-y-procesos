public class ejercicio2 {
    public static void main(String[] args) {
        // Crear el primer hilo que cuenta cada 0.5 segundos
        Thread hiloRapido = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                System.out.println("Hilo Rápido: " + i);
                try {
                    Thread.sleep(500); // Pausa de 0.5 segundos
                } catch (InterruptedException e) {
                    System.out.println("Hilo Rápido interrumpido");
                }
            }
        });

        // Crear el segundo hilo que cuenta cada 1 segundo
        Thread hiloMedio = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                System.out.println("Hilo Medio: " + i);
                
                try {
                    Thread.sleep(1000); // Pausa de 1 segundo
                } catch (InterruptedException e) {
                    System.out.println("Hilo Medio interrumpido");
                }
            }
        });

        // Crear el tercer hilo que cuenta cada 2 segundos
        Thread hiloLento = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                System.out.println("Hilo Lento: " + i);
                try {
                    Thread.sleep(2000); // Pausa de 2 segundos
                } catch (InterruptedException e) {
                    System.out.println("Hilo Lento interrumpido");
                }
            }
        });

        // Iniciar los tres hilos
        hiloRapido.start();
        hiloMedio.start();
        hiloLento.start();
    }
}
