package bloxious;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;

/**
 * The base of the application.
 *
 */
public class Main 
{
    public static void main( String[] args )
    {
    /**
    * http://stackoverflow.com/questions/228477/how-do-i-programmatically-determine-operating-system-in-java
    * compare to http://svn.terracotta.org/svn/tc/dso/tags/2.6.4/code/base/common/src/com/tc/util/runtime/Os.java
    * http://www.docjar.com/html/api/org/apache/commons/lang/SystemUtils.java.html
    */
        String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
        if ((OS.indexOf("mac") >= 0) || (OS.indexOf("darwin") >= 0)) {
            /* 
            *  This program is not compatible with Mac OS.
            *  You would have more issues trying to run 
            *  a dedicated server on it anyways.
            */
            bloxious.Gui.simpleErrorDialog("Incompatible OS.");
            System.exit(0);
        }
        Log syslog = new Log();
        Gui gui;
        boolean silent = false;
        String filetoRun = null;
        String[] instance;
        String gamedir;
        ConfigParse config = new ConfigParse(syslog);
        LoadServers servercfg = new LoadServers(syslog);
        ServerStart start = new ServerStart(syslog);
        for (int i=0;i<args.length;i++) {
            if (args[i].equals("-s")) {
                //Sets the flag and vairable for silent activation
                filetoRun = args[(i+2)];
                silent = true;
            }
        }
        if (silent) {
            //
            syslog.infoLog("Starting in silent mode.");
            instance = servercfg.checkandParse(filetoRun);
            gamedir = config.findExecutable(instance[1]);
            start.startServer(instance[1], gamedir, instance[2].trim().split("\\s+"));
        }
        else {
            gui = new Gui(syslog);
            String[][] fullarr = servercfg.getInstances();
            ArrayList<String> instanceNames = new ArrayList<>();
            for (int i=0;i<fullarr.length;i++) {
                instanceNames.add(fullarr[i][0]);
            }
            gui.startUI((String[]) instanceNames.toArray());
        }
    }
}
