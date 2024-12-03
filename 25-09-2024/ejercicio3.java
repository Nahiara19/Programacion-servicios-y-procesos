import java.io.IOException;

public class ejercicio3 {
    public static void main(String[] args){
        try {
        String[] command2 = {"cmd", "/c", "dir", "/o"};
        ProcessBuilder pb = new ProcessBuilder(command2);
        Process p = pb.start();

        ProcessHandle processHandle = ProcessHandle.current();
        ProcessHandle.Info processInfo = processHandle.info();

        System.out.println("PID: " + processHandle.pid());
        System.out.println("Arguments: " + processInfo.arguments());
        System.out.println("Command: " + processInfo.command());
        System.out.println("Instant: " + processInfo.startInstant());
        System.out.println("Total CPU duration: " + processInfo.totalCpuDuration());
        System.out.println("User: " + processInfo.user());  
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}