import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        // Scanner UTF-8 để nhập tiếng Việt không lỗi
        Scanner sc = new Scanner(System.in, "UTF-8");

        System.out.print("Nhập IP máy server: ");
        String host = sc.nextLine();
        int port = 1234;

        Socket socket = new Socket(host, port);

        // Dùng UTF-8 cho socket stream
        BufferedReader in = new BufferedReader(
            new InputStreamReader(socket.getInputStream(), "UTF-8"));
        BufferedWriter out = new BufferedWriter(
            new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));

        System.out.print("Nhập a: ");
        double a = sc.nextDouble();
        System.out.print("Nhập b: ");
        double b = sc.nextDouble();

        // Gửi dữ liệu
        out.write(a + "\n");
        out.write(b + "\n");
        out.flush();

        // Nhận kết quả
        String result = in.readLine();
        System.out.println("Server trả về: " + result);

        socket.close();
        sc.close();
    }
}
