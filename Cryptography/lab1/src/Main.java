import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        PlayfairCode a = new PlayfairCode();
        //String key = "PLAYFAIR EXAMPLE";
        //String str = "HIDE THE GOLD IN THE TREE STUMP";

        System.out.println("Welcome to playfair, enter your key");
        BufferedReader ReadString = new BufferedReader(new InputStreamReader(System.in));
        String str = new String();
        String key = new String();

        try{
            //key = ReadString.readLine();
            a.random();
            System.out.println("Enter your message");
            str = ReadString.readLine();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        //a.setKey(key);
        System.out.println(a);
        System.out.println(a.cipher(str));
        System.out.println(a.deCipher(a.cipher(str)));
    }
}