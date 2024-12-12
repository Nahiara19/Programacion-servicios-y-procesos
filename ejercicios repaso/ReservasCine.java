import java.util.concurrent.*;

class Cine {
    private int asientosDisponibles = 50; // Total de asientos disponibles
    private final Semaphore semaforoCine = new Semaphore(1);
    private final Semaphore espacioDisponible = new Semaphore(asientosDisponibles);
    private final Semaphore asientosReservados = new Semaphore(0);

    public void hacerReserva(int cantidad) throws InterruptedException {
        espacioDisponible.acquire(cantidad); // Espera si no hay asientos
        semaforoCine.acquire();
        asientosDisponibles -= cantidad;
        System.out.println("Cliente reserva " + cantidad + " asientos. Asientos restantes: " + asientosDisponibles);
        semaforoCine.release();
        asientosReservados.release(cantidad); // Libera los asientos reservados
    }

    public void confirmarReserva(int cantidad) throws InterruptedException {
        asientosReservados.acquire(cantidad); // Espera si no hay reservas
        semaforoCine.acquire();
        asientosDisponibles += cantidad;
        System.out.println("Empleado confirma " + cantidad + " reservas. Asientos disponibles: " + asientosDisponibles);
        semaforoCine.release();
        espacioDisponible.release(cantidad); // Libera los asientos disponibles
    }
}

public class ReservasCine {
    public static void main(String[] args) throws InterruptedException {
        Cine cine = new Cine();

        // Hilos productores (clientes haciendo reservas)
        Thread cliente = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    cine.hacerReserva(5);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Hilos consumidores (empleados confirmando reservas)
        Thread empleado = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    cine.confirmarReserva(5);
                    Thread.sleep(1500);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        cliente.start();
        empleado.start();

        cliente.join();
        empleado.join();
    }
}
