import java.io.File;

public class Initializing {
    public Initializing() {
        folderInit();
    }

    private void folderInit() {
        Thread t = new Thread() {
            //Servers Folder
            public void run() {
                File folder = new File(System.getProperty("user.dir") + "\\Servers");
                if (!folder.isDirectory()) {
                    folder.mkdir();
                }
            }
        };
        t.start();
    }
}
