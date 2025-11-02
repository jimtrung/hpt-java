import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        /* NOTE: 
            - Cổng của Server và Client phải giống nhau
            - Thiết bị của Server và Client phải kết nối vào cùng 1 mạng
        */
        int port = 1234;

        /* Tạo "máy quét" để đọc dữ liệu đầu vào */
        Scanner sc = new Scanner(System.in); 

        /* Yêu cầu client nhập địa chỉ IP của server */
        System.out.print("Nhập IP của server: ");
        String host = sc.next();  

        // host = "192.168.1.103";

        /* Tạo kết nối với server */
        Socket socket = new Socket(host, port);

        /* Tạo luồng đọc và ghi để trao đổi dữ liệu với server */
        BufferedReader in = new BufferedReader( new InputStreamReader(socket.getInputStream(), "UTF-8"));
        BufferedWriter out = new BufferedWriter( new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));

        /* Nhập và gửi dữ liệu */
        System.out.print("Nhập a: ");
        double a = sc.nextDouble();

        out.write(a + "\n");
        out.flush();

        System.out.print("Nhập b: ");
        double b = sc.nextDouble();

        out.write(b + "\n");
        out.flush();

        /* Nhận kết quả */
        String result = in.readLine(); 
        System.out.println("Server trả về: " + result);

        /* Đóng kết nối socket và scanner */
        socket.close();
        sc.close();
    }
}
