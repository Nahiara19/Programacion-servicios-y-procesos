import java.util.Random;

public class JuegoHilos extends Thread {
    public static final int META = 100;
    private int posicion;
    
    public JuegoHilos(){

    }
    public void run(){
        Random random = new Random();
        while  (posicion < META) {
            int avance = random.nextInt(10);
            posicion += avance;
            //mostrarposicion()
            try {
                Thread.sleep(random.nextInt(500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String [] args) {

    }
}
