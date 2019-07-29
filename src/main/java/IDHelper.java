import java.io.*;

public class IDHelper {
    public static final File IDFOLDER = new File(System.getProperty("user.dir") + "\\Servers\\ID");
    public static final File CHANNELCATEGORYFILE = new File(IDFOLDER + "0");
    public static final File CHANNELRULESFILE = new File(IDFOLDER + "1");
    public static final File CHANNELANNOUNCEMENTSFILE = new File(IDFOLDER + "2");
    public static final File CHANNELEVENTSFILE = new File(IDFOLDER + "3");
    public static final File CHANNELPOLLSSFILE = new File(IDFOLDER + "4");
    public static final File CHANNELBOTSCOMMANDFILE = new File(IDFOLDER + "5");
    public static final int nFILE = 6;
    //MOAN Server Info
    public long SERVER = 603271708077457419l;
    public long CHANNELCATEGORY = 0l;
    public long CHANNELRULES = 0l;
    public long CHANNELANNOUNCEMENTS = 0l;
    public long CHANNELEVENTS = 0l;
    public long CHANNELPOLLSS = 0l;
    public long CHANNELBOTSCOMMAND = 0l;

    //ROLE ID
    public long ROLEADMINISTRATOR = 604687603383664640l;
    public long ROLESUPERMODERATOR = 605342123323293697l;
    public long ROLEMODERATOR = 605342198644604949l;
    public long ROLEMEMBER = 604866605297434666l;
    public long ROLENONMEMBER = 605342267468677120l;

    public IDHelper() {
        if (!IDFOLDER.exists() && !IDFOLDER.mkdir()) {
            System.out.println("Couldn't create " + IDFOLDER.getAbsolutePath());
        } else {
            readIDFile();
        }
    }

    private void readIDFile() {
        for (int i = 0; i < nFILE; i++) {
            File file = new File(IDFOLDER + Integer.toString(i));
            long ID = 0l;
            try {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                try {
                    ID = Long.parseLong(br.readLine());
                } catch (NumberFormatException e) {
                    ID = 0l;
                }
                br.close();
                fr.close();
            } catch (java.io.IOException e) {
            }
            switch (i) {
                case 1:
                    CHANNELCATEGORY = ID;
                case 2:
                    CHANNELRULES = ID;
                case 3:
                    CHANNELANNOUNCEMENTS = ID;
                case 4:
                    CHANNELEVENTS = ID;
                case 5:
                    CHANNELPOLLSS = ID;
                case 6:
                    CHANNELBOTSCOMMAND = ID;
            }
        }
    }

    public boolean writeIDFile(String fileID, long ID) {
        try {
            File file = new File(IDFOLDER + "\\" + fileID);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(Long.toString(ID));
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println(e + "\nCouldn't write down ID file " + fileID);
            return false;
        }
        return true;
    }
}



