package textprocessing;

public class FileReader extends Thread{
    private FileNames fn;
    private FileContents fc;
    
    public FileReader(FileNames fn, FileContents fc){
        this.fn = fn;
        this.fc = fc;
    }
    
    public void run(){
        String name;
        fc.registerWriter();
        while ((name = fn.getName()) != null){
            fc.addContents(Tools.getContents(name));
        }
        fc.unregisterWriter();
    }
}
