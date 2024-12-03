import java.io.File;
import java.io.*;

public class ejercicioStreams {
    public static void main(String[] args) {
        File f = new File("C:\\Users\\DAM\\Desktop\\Nahiara\\Procesos y sistemas\\03-10-2024\\fichero.txt");
        try {
            FileInputStream fis = new FileInputStream(f);
            
            // Reads the first byte
            int i = fis.read();

        while(i != -1) {
            System.out.print((char)i);
            // Reads next byte from the file
            i = fis.read();
         }
         fis.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
    }

}
