public class pruebaHilos2  implements Runnable{
    
    private Thread hilo  = null;

    public pruebaHilos2 (String name ) {
        this.hilo = new Thread(this,name);
        this.hilo.start();
    }
    
    public void run(){
        for (int i = 0; i <=10; i++) {
            System.out.println(this.hilo.getName() + " el contador " + i);
            /*try{
                sleep((long) Math.random() + 1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }*/
        }
    }

    public void ejecutarHilo(){
        this.hilo.start();
    }
    
}
    
