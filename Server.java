import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {
        int port = 1234;

        // Lấy IP máy server (LAN IP)
        String serverIP = InetAddress.getLocalHost().getHostAddress();
        System.out.println("Server chạy trên IP: " + serverIP + ", cổng: " + port);

        ServerSocket serverSocket = new ServerSocket(port);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client kết nối: " + clientSocket.getInetAddress());

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            double a = Double.parseDouble(in.readLine());
            double b = Double.parseDouble(in.readLine());

            String result;
            if (a == 0) {
                result = (b == 0) ? "Vô số nghiệm" : "Vô nghiệm";
            } else {
                double x = -b / a;
                result = "Nghiệm x = " + x;
            }

            out.println(result);
            clientSocket.close();
        }
    }
}
