import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private int port;
    private static int countofmembers = 1;
    private static ArrayList<ClientThread> members = new ArrayList<>();
    private static String message;
    public Server(int p){
        port = p;
    }

    private static class ClientThread extends Thread{
        private Socket ClientSocket;
        private String name;
        private PrintWriter out;
        private BufferedReader in;

        private ClientThread(Socket s, String n){
            ClientSocket = s;
            try{
                name = n;
                out = new PrintWriter(ClientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        public void run(){
            try{

                while ((message = in.readLine()) != null){
                    if (message.indexOf("@name") == 0){
                        boolean nametrigger = false;
                        for (int i=0; i < members.size(); i++){
                           System.out.println(members.get(i).getname());
                           System.out.println(message);
                            if (message.indexOf(members.get(i).getname())==6){
                                out.println("name already exist, try again with new name");
                                nametrigger = true;
                                break;
                            }
                        }
                        if (!nametrigger)
                            setname(message.substring(6));
                    } else if ((message.indexOf("@senduser") == 0)) {
                        boolean sendtrigger = false;
                        message = message.substring(10);
                        for (int i=0; i < members.size(); i++){
                            if (message.indexOf(members.get(i).getname())==0){
                                members.get(i).send(name + ": " + message.substring(members.get(i).getname().length() + 1));
                                sendtrigger = true;
                                break;
                            }
                        }
                        if (!sendtrigger){
                            out.println("user doesn't exist, try again");
                        }
                    } else{
                        for (int i=0; i < members.size(); i++){
                            members.get(i).send(name + ": " + message);
                        }
                    }

                }
            }
            catch (Exception e) {
                //e.printStackTrace();
            }

        }

        private void send (String message){
            try{
                out.println(message);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        private String getname (){
            return name;
        }

        private void setname (String n){
            name = n;
        }
    }

    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true){

                Socket clientSocket = serverSocket.accept();

                ClientThread client = new ClientThread(clientSocket, "name" + countofmembers);
                System.out.println("added new " + countofmembers + " user!");
                members.add(client);
                members.get(countofmembers-1).start();
                countofmembers++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
