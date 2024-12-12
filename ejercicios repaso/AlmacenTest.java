import java.util.concurrent.*;

class Almacen {
    private int productos = 0;
    private final int capacidad = 10; // Capacidad máxima
    private final Semaphore semaforoAlmacen = new Semaphore(1);
    private final Semaphore espacioDisponible = new Semaphore(capacidad);
    private final Semaphore productosDisponibles = new Semaphore(0);

    public void descargarProducto(int cantidad) throws InterruptedException {
        espacioDisponible.acquire(cantidad); // Espera si no hay espacio
        semaforoAlmacen.acquire();
        productos += cantidad;
        System.out.println("El trabajador descarga " + cantidad + " productos. Total: " + productos);
        semaforoAlmacen.release();
        productosDisponibles.release(cantidad); // Libera los productos
    }

    public void recogerProducto(int cantidad) throws InterruptedException {
        productosDisponibles.acquire(cantidad); // Espera si no hay productos
        semaforoAlmacen.acquire();
        productos -= cantidad;
        System.out.println("El transportista recoge " + cantidad + " productos. Total restante: " + productos);
        semaforoAlmacen.release();
        espacioDisponible.release(cantidad); // Libera el espacio
    }
}

public class AlmacenTest {
    public static void main(String[] args) throws InterruptedException {
        Almacen almacen = new Almacen();

        // Hilos productores (trabajadores descargando productos)
        Thread trabajador = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    almacen.descargarProducto(2);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Hilos consumidores (transportistas recogiendo productos)
        Thread transportista = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    almacen.recogerProducto(2);
                    Thread.sleep(1500);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        trabajador.start();
        transportista.start();

        trabajador.join();
        transportista.join();
    }
}
