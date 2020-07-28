package textprocessing;
import java.util.Queue;
import java.util.LinkedList;

public class FileNames {
    private Queue<String> queue = new LinkedList<String>();
    private boolean closed = false;
    
    public synchronized void addName(String fileName) {
        queue.offer(fileName);
        notifyAll();
    }
    
    public synchronized String getName() {
        String name = null;
        while((name = queue.poll()) == null && !closed){
            try{
                wait();
            } catch (InterruptedException exc) {}
        }
        notifyAll();
        return name;
    }
    
    public synchronized void noMoreNames() {
        closed = true;
    }
}