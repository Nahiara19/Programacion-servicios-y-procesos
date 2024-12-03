public class pruebaHilos extends Thread {

    public pruebaHilos (String name ) {

        super(name);
    }

    public void run(){
        for (int i = 0; i <=10; i++) {
            System.out.println(this.getName() + " el contador " + i);
            try{
                sleep((long) Math.random() + 1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main (String[] args) throws Exception {
        pruebaHilos minHilo1 = new pruebaHilos("Tesla");
        pruebaHilos minHilo2 = new pruebaHilos("X");
        minHilo1.setPriority(Thread.MAX_PRIORITY);
        minHilo2.setPriority(Thread.MIN_PRIORITY);

        minHilo1.start();
        minHilo2.start();
    }


}
