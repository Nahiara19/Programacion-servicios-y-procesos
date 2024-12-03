class Buffer {
    private Integer valor = null;  // El buffer almacena un solo valor

    // Método para que el productor agregue un valor al buffer
    public synchronized void producir(int valor) throws InterruptedException {
        // Si el buffer ya tiene un valor, espera
        while (this.valor != null) {
            wait();
        }
        // Agregar el valor al buffer
        this.valor = valor;
        System.out.println("Productor ha agregado: " + valor);
        // Notificar al consumidor que puede consumir
        notify();
    }

    // Método para que el consumidor lea el valor del buffer
    public synchronized int consumir() throws InterruptedException {
        // Si el buffer está vacío, espera
        while (this.valor == null) {
            wait();
        }
        // Leer el valor del buffer
        int valorConsumido = this.valor;
        this.valor = null;  // El buffer queda vacío
        System.out.println("Consumidor ha consumido: " + valorConsumido);
        // Notificar al productor que puede producir
        notify();
        return valorConsumido;
    }
}

public class ProductorConsumidor {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();

        // Hilo productor
        Thread productor = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 5; i++) {
                        buffer.producir(i);  // El productor agrega valores al buffer
                        Thread.sleep(1000);   // Simula el tiempo para producir
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Hilo consumidor
        Thread consumidor = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 5; i++) {
                        buffer.consumir();  // El consumidor lee valores del buffer
                        Thread.sleep(1500);  // Simula el tiempo para consumir
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Iniciar los hilos
        productor.start();
        consumidor.start();

        try {
            // Esperar a que ambos hilos terminen
            productor.join();
            consumidor.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
