/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package echoserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Lenovo
 */
public class EchoServer {

    static int port = 8080;
    static String ip = "localhost";
    
    public static void handleClient(Socket s) throws IOException {
        Scanner scan = new Scanner(s.getInputStream());
        PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
        String msg = "";
        while (!msg.equals("STOP")) {
            msg = scan.nextLine();
        pw.println(msg);
        }

        scan.close();
        pw.close();
        s.close();
    }
    public static void main(String[] args) throws IOException {
       if (args.length == 2) {
            ip = args[0];
            port = Integer.parseInt(args[1]);
        }
        ServerSocket ss = new ServerSocket();
        ss.bind(new InetSocketAddress(ip, port));
        System.out.println("Server Started Listening on " + port + "bound to " + ip);
        while (true) {
            Socket socket = ss.accept();
            handleClient(socket);
            System.out.println("new Client connected");
        }
    }
    }
    

