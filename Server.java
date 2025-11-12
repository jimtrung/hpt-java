import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {
        int port = 1234; // 2 mũ 16

        String serverIP = InetAddress.getLocalHost().getHostAddress();
        System.out.println("Server chạy trên IP: " + serverIP + ", cổng: " + port);

        ServerSocket serverSocket = new ServerSocket(port);

        while (true) {
            System.out.println("Server chờ kết nối của client...");  
            Socket clientSocket = serverSocket.accept();
            
            String clientIP = clientSocket.getInetAddress().toString();
            System.out.println("Client kết nối: " + clientIP); 

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"));

            double a = Double.parseDouble(in.readLine());
            System.out.println("[" + clientIP + "] Gửi a = " + a);
            
            double b = Double.parseDouble(in.readLine());
            System.out.println("[" + clientIP + "] Gửi b = " + b);

            String result;
            if (a == 0) {
                result = (b == 0) ? "Vô số nghiệm" : "Vô nghiệm";
            } else {
                double x = -b / a;
                result = "Nghiệm x = " + x;
            }

            System.out.println("[" + serverIP + "] Gửi kết quả: " + result); 
            out.write(result + "\n"); 
            out.flush();
            
            clientSocket.close();
            System.out.println("[" + clientIP + "] Kết nối đã đóng.\n");
        }
    }
}
