import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {
        int port = 1234;

        /* Lấy địa chỉ IP của máy đang chạy server và in ra địa chỉ IP + port */
        String serverIP = InetAddress.getLocalHost().getHostAddress();
        System.out.println("Server chạy trên IP: " + serverIP + ", cổng: " + port);

        /* Tạo một socket server */
        ServerSocket serverSocket = new ServerSocket(port);

        System.out.println("Server chờ kết nối của client...");  
        
        while (true) {
            Socket clientSocket = serverSocket.accept();
            
            /* In ra địa chỉ IP của client vừa kết nối */
            String clientIP = clientSocket.getInetAddress().toString();  
            System.out.println("Client kết nối: " + clientIP); 


            /* Tạo luồng đọc và ghi để trao đổi dữ liệu với client */
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"));

            /* Đọc dữ liệu client gửi tới và ép kiểu sang double */
            double a = Double.parseDouble(in.readLine());
            System.out.println("[" + clientIP + "] Gửi a = " + a);
            
            double b = Double.parseDouble(in.readLine());
            System.out.println("[" + clientIP + "] Gửi b = " + b);

            /* Tính phương trình bậc nhất */
            String result;
            if (a == 0) {
                result = (b == 0) ? "Vô số nghiệm" : "Vô nghiệm";
            } else {
                double x = -b / a;
                result = "Nghiệm x = " + x;
            }

            /* In ra kết quả (.flush() đảm bảo dữ liệu được gửi đi ngay) */
            System.out.println("[" + serverIP + "] Gửi kết quả: " + result); 
            out.write(result + "\n"); 
            out.flush();
            
            /* Đóng kết nối với client */
            clientSocket.close();
            System.out.println("[" + clientIP + "] Kết nối đã đóng.\n");
        }
    }
}
