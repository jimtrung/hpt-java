import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        int port = 1234;

        Scanner sc = new Scanner(System.in); 

        System.out.print("Nhập IP của server: ");
        String serverIP = sc.next();  

        // host = "10.43.165.169"; 

        Socket socket = new Socket(serverIP, port);

        BufferedReader in = new BufferedReader( new InputStreamReader(socket.getInputStream(), "UTF-8"));
        BufferedWriter out = new BufferedWriter( new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));

        System.out.print("Nhập a: ");
        double a = sc.nextDouble();

        out.write(a + "\n");
        out.flush();

        System.out.print("Nhập b: ");
        double b = sc.nextDouble();

        out.write(b + "\n");
        out.flush();

        String result = in.readLine(); 
        System.out.println("Server trả về: " + result);

        socket.close();
        sc.close();
    }
}
