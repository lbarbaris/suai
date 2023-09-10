import java.util.Random;

public class PlayfairCode {
    private char[][] key = new char[5][5];
    private char[] keyletter;




    public void setKey(String word){
        word = word.replaceAll(" ","");
        char[] KeyWord = word.toCharArray();
        int j = 0;
        int k = 0;
        boolean trigger;
        keyletter = new char[KeyWord.length];
        for (int i = 0; i < KeyWord.length; i++){
            trigger = false;
            keyletter[i] = KeyWord[i];
            for (int l = 0; l < 5; l++){
                for (int m = 0; m < 5; m++){
                    if (key[l][m] == KeyWord[i]){
                        trigger = true;
                    }
                }
            }
            if (!trigger){
                key[k][j] = KeyWord[i];
                if (j == 4){
                    j = 0;
                    k++;
                }
                else j++;
            }


        }
        fill(k,j);
    }




    private void fill(int startI, int startJ) {
        char letter = 65;
        int i = startI;
        int j = startJ;
        while (i < key[0].length ) {

            boolean trigger = true;
            while (j < key.length) {
                for (char c : keyletter) {
                    if (letter == c) {
                        trigger = false;
                        break;
                    }
                }
                if (letter == 74){
                    trigger = false;
                }
            if (trigger){
                key[i][j] = letter;
                letter++;
                j++;
            }
            else{
                letter++;
            }
                trigger = true;

            }
            i++;
            j = 0;
        }

    }


    public String cipher(String msg){
        msg = msg.replaceAll(" ","");
        char[]message = msg.toCharArray();
        for (int i = 0; i< message.length; i++){
            if (message[i] == 74){
                message[i] = 73;
            }
        }
        boolean skip;
        String sb = new String();
        int i = 0;
        char first;
        char second;
        boolean Itrigger = false;
        int FirstIndexIKey = 0;
        int FirstIndexJKey = 0;
        int SecondIndexJKey = 0;
        int SecondIndexIKey = 0;

        while (i != message.length){
            if (i>=message.length){
                break;
            }
            skip = false;


            first = message[i];
            if (i!= message.length - 1){
                second = message[i+1];
            }
            else{
                second = 'X';
            }
            if (first == second){
                second = 'X';
                Itrigger = true;
            }
            for (int j = 0; j < 5; j++){
                for (int k = 0; k < 5; k++){
                    if (key[j][k]==first){
                        FirstIndexIKey = j;
                        FirstIndexJKey = k;
                    }
                    if (key[j][k]==second){
                        SecondIndexJKey = k;
                        SecondIndexIKey = j;
                    }
                }
            }
            if ((FirstIndexIKey == SecondIndexIKey) && !skip){
                if (FirstIndexJKey == 4) {
                FirstIndexJKey = 0;
                }
                else FirstIndexJKey++;
                if (SecondIndexJKey == 4){
                    SecondIndexJKey = 0;
                } else  SecondIndexJKey++;
                skip = true;
            }

            if ((FirstIndexJKey == SecondIndexJKey) && !skip){
                if (FirstIndexIKey == 4) {
                    FirstIndexIKey = 0;
                }
                else FirstIndexIKey++;
                if (SecondIndexIKey == 4){
                    SecondIndexIKey = 0;
                } else  SecondIndexIKey++;
                skip = true;
            }

            if ((FirstIndexJKey != SecondIndexJKey) &&(FirstIndexIKey != SecondIndexIKey) && !skip){
                int replace = FirstIndexJKey;
                FirstIndexJKey = SecondIndexJKey;
                SecondIndexJKey = replace;
                skip = true;
            }


            sb = sb + key[FirstIndexIKey][FirstIndexJKey] + key[SecondIndexIKey][SecondIndexJKey];
            if (Itrigger){
                i++;
            }
            else{
                i+=2;
            }
            Itrigger = false;
        }
        return sb;
    }


    public String deCipher(String CipMessage){
        char[]message = CipMessage.toCharArray();
        String sb = new String();
        int i = 0;
        char first;
        char second;
        boolean skip;
        boolean Itrigger = false;
        int FirstIndexIKey = 0;
        int FirstIndexJKey = 0;
        int SecondIndexJKey = 0;
        int SecondIndexIKey = 0;

        while (i != message.length) {
            if (i >= message.length) {
                break;
            }
            skip = false;


            first = message[i];
            if (i != message.length - 1) {
                second = message[i + 1];
            } else {
                second = 'X';
            }
            if (first == second) {
                second = 'X';
                Itrigger = true;
            }
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    if (key[j][k] == first) {
                        FirstIndexIKey = j;
                        FirstIndexJKey = k;
                    }
                    if (key[j][k] == second) {
                        SecondIndexJKey = k;
                        SecondIndexIKey = j;
                    }
                }
            }
            if ((FirstIndexIKey == SecondIndexIKey) && !skip) {
                if (FirstIndexJKey == 0) {
                    FirstIndexJKey = 4;
                } else FirstIndexJKey--;
                if (SecondIndexJKey == 0) {
                    SecondIndexJKey = 4;
                } else SecondIndexJKey--;
                skip = true;
            }

            if ((FirstIndexJKey == SecondIndexJKey) && !skip) {
                if (FirstIndexIKey == 0) {
                    FirstIndexIKey = 4;
                } else FirstIndexIKey--;
                if (SecondIndexIKey == 0) {
                    SecondIndexIKey = 4;
                } else SecondIndexIKey--;
                skip = true;
            }

            if ((FirstIndexJKey != SecondIndexJKey) && (FirstIndexIKey != SecondIndexIKey) && !skip) {
                int replace = FirstIndexJKey;
                FirstIndexJKey = SecondIndexJKey;
                SecondIndexJKey = replace;
                skip = true;
            }


            sb = sb + key[FirstIndexIKey][FirstIndexJKey] + key[SecondIndexIKey][SecondIndexJKey];
            if (Itrigger) {
                i++;
            } else {
                i += 2;
            }
            Itrigger = false;

        }
        return sb;
    }


    public final void random(){
        Random r = new Random();
        String rKey = new String();
        int val = (int) ((Math.random() * (25) + 0));
        for (int i=0; i<val; i++){
            char let = (char)(r.nextInt(26) + 'A');
            rKey = rKey + let;
        }
        System.out.println(rKey);
        setKey(rKey);
    }



    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < key[0].length; i++) {
            for (int j = 0; j < key.length; j++) {
                sb.append(key[i][j]);
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
