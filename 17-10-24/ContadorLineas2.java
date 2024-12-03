import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ContadorLineas2 {
    
    public ContadorLineas2(){


    }
    public void lanzarProceso(){
        ProcessBuilder pb = new ProcessBuilder("cmd","/c","find /c /v  \"\" ", "lanzadorProcesos.java");
       

        try {
           Process proceso = pb.start();
           BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
           String linea = "";
           while ((linea = reader.readLine()) != null) {
            System.out.println(linea);
           }
           reader.close();
           
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        ContadorLineas2 c = new ContadorLineas2();
        c.lanzarProceso();
        // si el metodo lanzarProceso es estatico podriamos hacer lo siguiente
        //ContadorLineas2.lanzarProceso();
    }
    
}
