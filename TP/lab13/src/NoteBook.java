import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NoteBook {
    private HashMap<Long, String> numbers = new HashMap<>();
    private FileReader fir;
    private FileWriter fiw;


    public NoteBook(){
        try{
            loadFromTextFile("load.txt");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public synchronized void add (String name, Long number){
        numbers.put(number, name);
    }

    public synchronized HashMap<Long, String> getNumbers(){
        return this.numbers;
    }

    public synchronized void reset(){
        numbers.clear();
    }

    public synchronized void saveToTextFile(String filename) throws IOException {
        fiw =  new FileWriter(filename);
        for (Map.Entry<Long, String> entry : numbers.entrySet()) {
            String write =  "+" + entry.getKey() + ", " + entry.getValue() + '\n';
            fiw.write(write);
        }
        fiw.close();
    }

    public synchronized void delete(Long number, String name){
        numbers.remove(number, name);
    }



    public synchronized void replace(Long number, Long replace){
        numbers.put(replace, numbers.get(number));
        numbers.remove(number);
    }

    public void loadFromTextFile(String filename) throws  IOException {
        fir =  new FileReader(filename);
        boolean numbertrigger = false;
        boolean nametrigger = false;
        StringBuilder readname = new StringBuilder();
        StringBuilder readnumber = new StringBuilder();
        int cur;
        Long val;
        while ((cur = fir.read()) != -1) {
            if ((char) cur == '+') {
                numbertrigger = true;
            }
            if ((char) cur == ' ') {
                numbertrigger = false;
                nametrigger = true;
            }

            if (numbertrigger && (char) cur != '+' && (char) cur != ',' && (char) cur != ' ') {
                readnumber.append((char) cur);
            }
            if (nametrigger && (char) cur != ' '  && (char) cur != '\n') {
                readname.append((char) cur);
            }
            if ((char) cur == '\n') {
                nametrigger = false;

                val = Long.parseLong(readnumber.toString());
                numbers.put(val, readname.toString());
                readname = new StringBuilder();
                readnumber = new StringBuilder();
            }
        }
    }
}
