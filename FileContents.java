package textprocessing;
import java.util.Queue;
import java.util.LinkedList;

public class FileContents {
    private Queue<String> queue;
    private int registerCount = 0;
    private boolean closed = false;
    private final int maxFiles;
    private final int maxChars;
    
    public FileContents(int maxFiles, int maxChars) {
        queue = new LinkedList<String>();
        this.maxFiles = maxFiles;
        this.maxChars = maxChars;
    }
    
    public synchronized void registerWriter() {
        registerCount++;
    }
    
    public synchronized void unregisterWriter() {
        registerCount--;
        if(registerCount == 0) closed = true;
        notifyAll();
    }
    
    public synchronized void addContents(String contents) {
        while(queue.size() >= maxFiles || contents.length() > maxChars){
            try{
                if(queue.size() == 0) break;
                wait();
            } catch (InterruptedException exc) {}
        }
        if(!closed){
            queue.offer(contents);
            notifyAll();
        }
    }
    
    public synchronized String getContents() {
        String contents = null;
        while((contents = queue.poll()) == null && !closed){
            try{
                wait();
            } catch (InterruptedException exc) {}
        }
        notifyAll();
        return contents;
    }
}
