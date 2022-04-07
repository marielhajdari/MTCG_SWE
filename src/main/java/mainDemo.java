import server.ClientThread;
import user.UserManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class mainDemo {

    public static void main(String[] args) {
        int portNumber=10001;
        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            UserManager userManager=new UserManager();
            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    //System.out.println(clientSocket);
                    if (clientSocket != null) {
                        System.out.println("############################################");
                    }
                    ClientThread clientThread=new ClientThread(clientSocket,userManager);
                    clientThread.run();
                } catch (IOException | SQLException e){
                    System.out.println(e);
                }
            }
        }catch (IOException  e){
            System.out.println(e);
        }
    }
}
