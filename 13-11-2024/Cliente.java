public class Cliente extends Thread {
    private banco banco = null;

    public Cliente (banco bank) {
        this.banco = bank;
    }

    @Override
    public void run(){
        banco.comprar(300);
    }
}
