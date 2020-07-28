package textprocessing;
import java.util.Map;
import java.util.HashMap;

public class WordFrequencies {
    private HashMap<String, Integer> map = new HashMap<String, Integer>();
    
    public synchronized void addFrequencies(Map<String,Integer> f){
        for(String key : f.keySet()){
            map.put(key, map.containsKey(key) ? map.get(key)+f.get(key) : f.get(key));
        }
    }
    
    public synchronized Map<String,Integer> getFrequencies(){
        return (Map<String, Integer>) map.clone();
    }
}
