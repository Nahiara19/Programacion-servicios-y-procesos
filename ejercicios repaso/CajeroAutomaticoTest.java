import java.util.concurrent.*;

class CajeroAutomatico {
    private int buffer = 0;
    private final int capacidad = 100; // Capacidad máxima
    private final Semaphore semaforoCajero = new Semaphore(1);
    private final Semaphore espacioDisponible = new Semaphore(capacidad);
    private final Semaphore efectivoDisponible = new Semaphore(0);

    public void reponerEfectivo(int cantidad) throws InterruptedException {
        espacioDisponible.acquire(cantidad); // Espera si no hay espacio
        semaforoCajero.acquire();
        buffer += cantidad;
        System.out.println("El sistema repone " + cantidad + " en el cajero. Total: " + buffer);
        semaforoCajero.release();
        efectivoDisponible.release(cantidad); // Libera el efectivo disponible
    }

    public void retirarEfectivo(int cantidad) throws InterruptedException {
        efectivoDisponible.acquire(cantidad); // Espera si no hay efectivo suficiente
        semaforoCajero.acquire();
        buffer -= cantidad;
        System.out.println("Cliente retira " + cantidad + ". Total restante: " + buffer);
        semaforoCajero.release();
        espacioDisponible.release(cantidad); // Libera el espacio
    }
}

public class CajeroAutomaticoTest {
    public static void main(String[] args) throws InterruptedException {
        CajeroAutomatico cajero = new CajeroAutomatico();

        // Hilos productores (repositorio de efectivo)
        Thread productor = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    cajero.reponerEfectivo(20);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Hilos consumidores (clientes)
        Thread consumidor = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    cajero.retirarEfectivo(20);
                    Thread.sleep(1500);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        productor.start();
        consumidor.start();

        productor.join();
        consumidor.join();
    }
}
