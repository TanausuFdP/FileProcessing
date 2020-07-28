package textprocessing;
import java.util.HashMap;

public class FileProcessor extends Thread{
    private FileContents fc;
    private WordFrequencies wf;
    
    public FileProcessor(FileContents fc, WordFrequencies wf){
        this.fc = fc;
        this.wf = wf;
    }
    
    public void run(){
        String contents = null;
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        while((contents = fc.getContents()) != null){
            String[] words = contents.split("[^A-Za-z0-9ñÑáéíóúÁÉÍÓÚüÜ]+");
            for(String word : words){
                map.put(word, map.containsKey(word) ? map.get(word)+1 : 1);
            }
        }
        wf.addFrequencies(map);
    }
}
