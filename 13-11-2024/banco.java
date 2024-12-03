public class banco {

    // recurso compartido
    int saldo = 600;

    public banco() {

    }

    private synchronized  void comprar (int precio) {
        if (this.saldo > 0) {
            System.out.println("Tramitar compra ...");
            this.saldo = this.saldo - precio;
            System.out.println("Compra tramitada con exito");
            System.out.println("El saldo después de la compra es: " + this.saldo);
        }
        
    }

    

    public static void main(String[] args) {
        banco nubiMallow = new banco();
        Cliente c1 = new Cliente(nubiMallow, "Cliente 1");
        Cliente c2 = new Cliente(nubiMallow, "Cliente 2");
        Cliente c3 = new Cliente(nubiMallow, "Cliente 3");

        c1.start();
        c2.start();
        c3.start();
        
    }
}