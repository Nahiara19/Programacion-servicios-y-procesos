public class Contador {
    private int contador = 0;

    // Método sincronizado para incrementar el contador
    public synchronized void incrementar() {
        contador++;
    }

    // Método para obtener el valor del contador
    public int obtenerContador() {
        return contador;
    }

    public static void main(String[] args) {
        Contador c = new Contador();

        // Crear 3 hilos que incrementan el contador 100 veces cada uno
        Runnable tarea = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    c.incrementar();
                }
            }
        };

        // Crear y lanzar 3 hilos
        Thread hilo1 = new Thread(tarea);
        Thread hilo2 = new Thread(tarea);
        Thread hilo3 = new Thread(tarea);

        hilo1.start();
        hilo2.start();
        hilo3.start();

        try {
            // Esperar a que los hilos terminen
            hilo1.join();
            hilo2.join();
            hilo3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Imprimir el valor final del contador
        System.out.println("Valor final del contador: " + c.obtenerContador());
    }
}
