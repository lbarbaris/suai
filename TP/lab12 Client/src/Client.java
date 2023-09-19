import java.io.*;
import java.net.*;
public class Client {
    private final ClientRecieve CR = new ClientRecieve();
    private final ClientSend CS = new ClientSend();
    private static Socket CSocket;

    public Client(String h, int p){
        try {
            CSocket = new Socket(h, p);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private static class ClientSend extends Thread{
        @Override
        public void run(){
            try{
                PrintWriter out = new PrintWriter(CSocket.getOutputStream(), true);
                BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
                while (true){
                    String fromUser = inFromUser.readLine();
                    if (fromUser != null) {
                        out.println(fromUser);
                    }
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    
    private static class ClientRecieve extends Thread {
        @Override
        public void run (){
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(CSocket.getInputStream()));
                String fromServer;
                while ((fromServer = in.readLine()) != null) {
                    System.out.println(fromServer);
                }
                }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    
    public void run() {
        CR.start();
        CS.start();
        try{
            CR.join();
            CS.join();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
