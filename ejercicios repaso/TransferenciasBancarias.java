import java.util.concurrent.*;

class CuentaBancaria {
    private int saldo = 1000; // Saldo inicial
    private final Semaphore semaforo = new Semaphore(1);

    public void realizarIngreso(int cantidad) throws InterruptedException {
        semaforo.acquire();
        saldo += cantidad;
        System.out.println("Ingreso de " + cantidad + ". Saldo actual: " + saldo);
        semaforo.release();
    }

    public void realizarRetiro(int cantidad) throws InterruptedException {
        semaforo.acquire();
        if (saldo >= cantidad) {
            saldo -= cantidad;
            System.out.println("Retiro de " + cantidad + ". Saldo actual: " + saldo);
        } else {
            System.out.println("Saldo insuficiente para retirar " + cantidad);
        }
        semaforo.release();
    }
}

public class TransferenciasBancarias {
    public static void main(String[] args) throws InterruptedException {
        CuentaBancaria cuenta = new CuentaBancaria();

        // Hilos productores (transacciones entrantes)
        Thread ingreso = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    cuenta.realizarIngreso(200);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Hilos consumidores (transacciones salientes)
        Thread retiro = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    cuenta.realizarRetiro(150);
                    Thread.sleep(1500);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        ingreso.start();
        retiro.start();

        ingreso.join();
        retiro.join();
    }
}
