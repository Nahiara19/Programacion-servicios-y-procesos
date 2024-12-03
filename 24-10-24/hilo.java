public class hilo implements Runnable {

    public hilo(Object object) {
        //TODO Auto-generated constructor stub
    }

    public void run(){
            try{
                Thread.sleep(1000);
                synchronized(this){
                    wait(1000);
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        
    }

    public static void main (String[] args) throws Exception {
        Thread h1 = new Thread(new hilo(null));
        h1.start();
        System.out.println("Estado despúes del star: " + h1.getState());
        Thread.sleep(500);
        System.out.println("Estado durante el sleep: " + h1.getState());
        Thread.sleep(1000);
        System.out.println("Estado durante el wait: " + h1.getState());
        h1.join();
        System.out.println("Estado fin: " + h1.getState());
    }


}
