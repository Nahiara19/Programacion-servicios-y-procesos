import java.util.concurrent.*;

class CocinaRestaurante {
    private int platos = 0;  // Número de platos en la barra
    private final int capacidad = 10;  // Capacidad máxima de la barra
    private final Semaphore semaforoCocina = new Semaphore(1);
    private final Semaphore espacioDisponible = new Semaphore(capacidad);
    private final Semaphore platosDisponibles = new Semaphore(0);

    // Método para que los chefs preparen platos
    public void prepararPlato(int cantidad) throws InterruptedException {
        espacioDisponible.acquire(cantidad);  // Espera si no hay espacio
        semaforoCocina.acquire();
        platos += cantidad;
        System.out.println("El chef prepara " + cantidad + " platos. Platos en la barra: " + platos);
        semaforoCocina.release();
        platosDisponibles.release(cantidad);  // Libera los platos disponibles para los camareros
    }

    // Método para que los camareros recojan los platos
    public void recogerPlato(int cantidad) throws InterruptedException {
        platosDisponibles.acquire(cantidad);  // Espera si no hay platos disponibles
        semaforoCocina.acquire();
        platos -= cantidad;
        System.out.println("El camarero recoge " + cantidad + " platos. Platos restantes en la barra: " + platos);
        semaforoCocina.release();
        espacioDisponible.release(cantidad);  // Libera espacio en la barra
    }
}

public class CocinaRestauranteTest {
    public static void main(String[] args) throws InterruptedException {
        CocinaRestaurante cocina = new CocinaRestaurante();

        // Hilos productores (chefs preparando platos)
        Thread chef = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    cocina.prepararPlato(2);  // El chef prepara 2 platos a la vez
                    Thread.sleep(1000);  // Simula el tiempo de preparación
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Hilos consumidores (camareros recogiendo platos)
        Thread camarero = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    cocina.recogerPlato(2);  // El camarero recoge 2 platos a la vez
                    Thread.sleep(1500);  // Simula el tiempo que tarda en llevar los platos a las mesas
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        chef.start();
        camarero.start();

        chef.join();
        camarero.join();
    }
}
