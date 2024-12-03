import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Streams {

    public static void main(String[] args) {
        
        String mensaje = "hola";
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        try{
            mensaje += reader.readLine();
            System.out.println(mensaje);

        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}

