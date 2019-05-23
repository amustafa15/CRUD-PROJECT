package sample;

import java.net.*;
import java.io.*;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Scanner;

public class MessagingSupportController {


    private MainController mainController;
    @FXML
    private TextField UserTextField;
    @FXML
    private ListView chatPane;
    private ConnectToDb connectToDb = new ConnectToDb();
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private String dbURL = "jdbc:mysql://localhost:3306/Test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    public MessagingSupportController(MainController mainController) throws IOException {


        int number;
        String temp;
        Scanner sc = new Scanner(System.in);
        Socket s = new Socket("127.0.0.1", 1342);
        UserTextField = new TextField(s.getInputStream().toString());
        //  Scanner sc1 = new Scanner(s.getInputStream());
        System.out.println("Enter any number");

        number = sc.nextInt();
        PrintStream p = new PrintStream(s.getOutputStream());

        p.println(number + "elephant");
        temp = UserTextField.getText() + "elephad";
        //  temp = sc1.nextInt();
        System.out.println(temp);
    }
}
//    public MessagingSupport(MainController mainController) throws IOException, InterruptedException {
//        //start();
//        SupportSideMessagingSupport supportSideMessagingSupport = new SupportSideMessagingSupport(mainController);
//        InetSocketAddress crunchifyAddr = new InetSocketAddress("localhost", 3306);
//        SocketChannel crunchifyClient = SocketChannel.open(crunchifyAddr);
//
//        log("Connecting to Server on port 1111...");
//
//        ArrayList<String> companyDetails = new ArrayList<String>();
//
//        // create a ArrayList with companyName list
//        companyDetails.add("Facebook");
//        companyDetails.add("Twitter");
//        companyDetails.add("IBM");
//        companyDetails.add("Google");
//        companyDetails.add("Crunchify");
//
//        for (String companyName : companyDetails) {
//
//            byte[] message = new String(companyName).getBytes();
//            ByteBuffer buffer = ByteBuffer.wrap(message);
//            crunchifyClient.write(buffer);
//
//            log("sending: " + companyName);
//            buffer.clear();
//
//            // wait for 2 seconds before sending next message
//            Thread.sleep(2000);
//        }
//        crunchifyClient.close();
//    }

 //   public static void main() throws IOException, InterruptedException {

//        InetSocketAddress crunchifyAddr = new InetSocketAddress("localhost", 1111);
//        SocketChannel crunchifyClient = SocketChannel.open(crunchifyAddr);
//
//        log("Connecting to Server on port 1111...");
//
//        ArrayList<String> companyDetails = new ArrayList<String>();
//
//        // create a ArrayList with companyName list
//        companyDetails.add("Facebook");
//        companyDetails.add("Twitter");
//        companyDetails.add("IBM");
//        companyDetails.add("Google");
//        companyDetails.add("Crunchify");
//
//        for (String companyName : companyDetails) {
//
//            byte[] message = new String(companyName).getBytes();
//            ByteBuffer buffer = ByteBuffer.wrap(message);
//            crunchifyClient.write(buffer);
//
//            log("sending: " + companyName);
//            buffer.clear();
//
//            // wait for 2 seconds before sending next message
//            Thread.sleep(2000);
//        }
//        crunchifyClient.close();
//    }
//
//    private static void log(String str) {
//        System.out.println(str);
//    }
//}
//    public MessagingSupport(MainController mainController) throws IOException {
//        this.mainController = mainController;
////        pane.setTop(messagingBar);
////        messagingBar.getStyleClass().add("messagingBar");
//
//        connect();
//
//    }
  //  private void connect() throws IOException {
    //   SupportSideMessagingSupport supportSideMessagingSupport = new SupportSideMessagingSupport();
     //   try{
//            Socket socket = new Socket("localhost", 3306);
//            System.out.println("listening on port: " + socket.getLocalPort());
//            PrintWriter pr = new PrintWriter(socket.getOutputStream());
//            pr.println("hello");
//            pr.flush();
//            SupportSideMessagingSupport supportSideMessagingSupport = new SupportSideMessagingSupport();
//
//            InputStreamReader in = new InputStreamReader(socket.getInputStream());
//            BufferedReader br = new BufferedReader(in);
//
//            String str = br.readLine();
//            System.out.println("server: " + str);
//            DataInputStream dis = new DataInputStream(socket.getInputStream());
//            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
//
//            while(true){
//                Scanner sc = new Scanner(System.in);
//                System.out.println("Client: ");
//                String message = sc.nextLine();
//                dos.writeUTF(message);
//
//                String res = dis.readUTF();
//                System.out.println("Server said: " + res);
//                if (res.equals("bye")){
//                    break;
//                }
//            }
//        } catch (IOException ex){
//            ex.printStackTrace();
//        }
//    }
//    public void sendComment(String comment){
//
//    }
//    public void start(int port) throws IOException {
//        serverSocket = new ServerSocket(port);
//        clientSocket = new serverSocket.accept();
//        out = new PrintWriter(clientSocket.getOutputStream(), true);
//        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//        String greeting = in.readLine();
//            if ("hello server".equals(greeting)){
//                out.println("hello client");
//            } else {
//                out.println("unrecognized greeting");
//            }
//    }
//    public void stop(){
//        in.close();
//        out.close();
//        clientSocket.close();
//        serverSocket.close();
//    }


//        try{
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("/MessagingSupportLayout.fxml"));

//            secondaryStage.setTitle("Support");
//            secondaryStage.setHeight(800);
//            secondaryStage.setWidth(200);
//            Scene scene = new Scene(loader.load());
//            secondaryStage.setScene(scene);
//            secondaryStage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//}
