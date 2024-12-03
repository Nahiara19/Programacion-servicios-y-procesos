import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class lanzadorProcesos {
    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("cmd","/c", "echo hola mundo");
        try{
            Process p = pb.start();
           BufferedReader buffer = new BufferedReader( new InputStreamReader(p.getInputStream()));
           String linea;
           while ((linea = buffer.readLine()) != null) {
            System.out.println(linea);
           }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
