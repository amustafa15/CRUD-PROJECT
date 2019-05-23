package sample;


//import com.mysql.cj.fabric.Server;
//import sample.MainController;
//
//import java.io.*;
//import java.net.InetSocketAddress;
//import java.net.Socket;
//import java.nio.*;
//import java.net.ServerSocket;
//import java.nio.channels.SelectionKey;
//import java.nio.channels.Selector;
//import java.nio.channels.ServerSocketChannel;
//import java.nio.channels.SocketChannel;
//import java.util.Iterator;
//import java.util.Scanner;
//import java.util.Set;
//
//public class SupportSideMessagingSupport {
//
//    private MainController mainController;
//
//    public SupportSideMessagingSupport(MainController mainController) throws IOException {
//    }
//    public static void main(String[] args) throws IOException {
//       // int number, temp;
//        int number;
//        String temp;
//        ServerSocket s1 = new ServerSocket(1342);
//        Socket ss =s1.accept();
//        Scanner sc = new Scanner(ss.getInputStream());
//        number = sc.nextInt();
//
//        temp = number + "holla";
//
//        //temp = number*2;
//
//        PrintStream p = new PrintStream(ss.getOutputStream());
//        p.println(temp);
//    }
//}
// //   @SuppressWarnings("unused")
////    public static void start() throws IOException {
////
////        System.out.println("support side opened");
////        // Selector: multiplexor of SelectableChannel objects
////        Selector selector = Selector.open(); // selector is open here
////
////        // ServerSocketChannel: selectable channel for stream-oriented listening sockets
////        ServerSocketChannel crunchifySocket = ServerSocketChannel.open();
////        InetSocketAddress crunchifyAddr = new InetSocketAddress("localhost", 3306);
////
////        // Binds the channel's socket to a local address and configures the socket to listen for connections
////        crunchifySocket.bind(crunchifyAddr);
////
////        // Adjusts this channel's blocking mode.
////        crunchifySocket.configureBlocking(false);
////
////        int ops = crunchifySocket.validOps();
////        SelectionKey selectKy = crunchifySocket.register(selector, ops, null);
////
////        // Infinite loop..
////        // Keep server running
////        while (true) {
////
////            log("i'm a server and i'm waiting for new connection and buffer select...");
////            // Selects a set of keys whose corresponding channels are ready for I/O operations
////            selector.select();
////
////            // token representing the registration of a SelectableChannel with a Selector
////            Set<SelectionKey> crunchifyKeys = selector.selectedKeys();
////            Iterator<SelectionKey> crunchifyIterator = crunchifyKeys.iterator();
////
////            while (crunchifyIterator.hasNext()) {
////                SelectionKey myKey = crunchifyIterator.next();
////
////                // Tests whether this key's channel is ready to accept a new socket connection
////                if (myKey.isAcceptable()) {
////                    SocketChannel crunchifyClient = crunchifySocket.accept();
////
////                    // Adjusts this channel's blocking mode to false
////                    crunchifyClient.configureBlocking(false);
////
////                    // Operation-set bit for read operations
////                    crunchifyClient.register(selector, SelectionKey.OP_READ);
////                    log("Connection Accepted: " + crunchifyClient.getLocalAddress() + "\n");
////
////                    // Tests whether this key's channel is ready for reading
////                } else if (myKey.isReadable()) {
////
////                    SocketChannel crunchifyClient = (SocketChannel) myKey.channel();
////                    ByteBuffer crunchifyBuffer = ByteBuffer.allocate(256);
////                    crunchifyClient.read(crunchifyBuffer);
////                    String result = new String(crunchifyBuffer.array()).trim();
////
////                    log("Message received: " + result);
////
////                    if (result.equals("Crunchify")) {
////                        crunchifyClient.close();
////                        log("\nIt's time to close connection as we got last company name 'Crunchify'");
////                        log("\nServer will keep running. Try running client again to establish new connection");
////                    }
////                }
////                crunchifyIterator.remove();
////            }
////        }
////    }
////
////    private static void log(String str) {
////        System.out.println(str);
////    }
////}
//
////    public SupportSideMessagingSupport() throws IOException {
////       // connectToServer();
////    }
////    private void connectToServer() throws IOException {
////        ServerSocket ss = new ServerSocket(57459);
////        Socket s = ss.accept();
////
////        System.out.println("client connected");
////
////        InputStreamReader in = new InputStreamReader(s.getInputStream());
////        BufferedReader br = new BufferedReader(in);
////        String str = br.readLine();
////        System.out.println("client: " + str);
////
////        PrintWriter pr = new PrintWriter(s.getOutputStream());
////        pr.println("yes");
////        pr.flush();
////        try {
////            ServerSocket serverSocket = new ServerSocket(56797);
////            System.out.println("listening at server port: " + serverSocket.getLocalPort());
////            Socket socket = serverSocket.accept();
////            System.out.println("connected");
////            DataInputStream dis = new DataInputStream(socket.getInputStream());
////            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
////
////            while (true) {
////
////                String res = dis.readUTF();
////                System.out.println("Client said: " + res);
////                if (res.equals("bye")) {
////                    break;
////                }
////            }
////            Scanner sc = new Scanner(System.in);
////            System.out.println("Server: ");
////            String message = sc.nextLine();
////            dos.writeUTF(message);
////        } catch(IOException e){
////            e.printStackTrace();
////        }
//
//
