import java.util.concurrent.atomic.AtomicInteger;

public class hilosCarrera2  implements Runnable{
    
    // sincronizador en bloque de  código

    private AtomicInteger contador = new AtomicInteger(0);
    
    //
    public  void incrementar() {
        contador.incrementAndGet();
    }
    
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            incrementar();
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        hilosCarrera2 cc = new hilosCarrera2();
        Thread hilo1 = new Thread(cc);
        Thread hilo2 = new Thread(cc);
    
        hilo1.start();
        hilo2.start();
    
        hilo1.join();
        hilo2.join();
    
        System.out.println("Valor final del contador: " + cc.contador);
    }
}
    

