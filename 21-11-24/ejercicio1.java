public class ejercicio1 {
    public static void main(String[] args) {
        // Crear un hilo usando una clase anónima
        Thread hilo = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Hola desde un Hilo");
                try {
                    // Pausar el hilo durante 1 segundo
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("El hilo fue interrumpido");
                }
            }
        });

        // Iniciar el hilo
        hilo.start();
    }
}
