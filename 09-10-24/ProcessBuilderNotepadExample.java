import java.io.IOException;

public class ProcessBuilderNotepadExample {

    public static void main(String[] args) {
        // Crea el proceso para abrir Notepad
        ProcessBuilder processBuilder = new ProcessBuilder("notepad.exe");

        try {
            // Inicia el proceso
            Process process = processBuilder.start();

            // Espera a que el proceso termine
            int exitCode = process.waitFor();
            if(exitCode == 0){
                System.out.println("Se ha ejecutado perfectamente \n" + "Codigo de exitCode: "+ exitCode);
                    Process process2 = processBuilder.start();
                    exitCode = process2.waitFor();
                if (exitCode == 0){
                    System.out.println("Se ha ejecutado perfectamente \n" + "Codigo de exitCode: "+ exitCode);
                }else{
                    System.err.println("Error \n" + "Codigo de exitCode"+ exitCode);
                }
            }else{
                System.err.println("Error \n" + "Codigo de exitCode"+ exitCode);
            }

            

            System.out.println("Notepad cerrado con código de salida: " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
