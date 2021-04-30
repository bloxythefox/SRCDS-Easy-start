package bloxious;

import java.io.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.ArrayList;

public class LoadServers {
    String workingdir = System.getProperty("user.dir");
    File[] serverFiles;
    Log log;
    ArrayList<String[]> instances = new ArrayList<>();
     public LoadServers(Log log) {
        this.log = log;
        this.serverFiles = findSTypes();
        for (int i=0;i<serverFiles.length;i++) {
            try {
            instances.add(this.parse(serverFiles[i]));
            }
            catch (Exception e) {
                log.exceptionLog("Exception in loading server instances. Exception: ", e);
            }
        }
     }
    private File[] findSTypes() {
        File file = new File(workingdir+"/servers");
        //roughly based on https://stackabuse.com/java-check-if-a-file-or-directory-exists/
        if (file.isDirectory()) {
            //Continue as normal
        }
        else {
            if (file.mkdir()) {
                log.infoLog("The server instances directory has been created. Check the github repository for examples on how to create instances.");
                System.out.println("Directory not found, created succesfully");
                System.exit(0);
            }
            else {
                log.severeLog("Failed to find or create directory. Fatal error.");
                System.out.println("Failed to find or create directory. Fatal error.");
                System.exit(-1);
            }
        }
        //https://stackoverflow.com/questions/56584081/how-to-scan-a-specific-directory-for-files-with-a-specific-extension
        return (file.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File arg0, String name) {
                return name.endsWith(".json");
            }
        }));
        
    }
    public String[] checkandParse(String path) {
        File file = new File(workingdir+"/servers"+path);
        String[] result;
        if (file.exists()) {
            try {
                result = parse(file);
            }
            catch (Exception e) {
                result = null;
                log.exceptionLog("Failed to parse file:", e);
                System.exit(-1);
            }
            return(result);
        }
        else {
            log.severeLog("Could not locate file for silent launch.");
            System.exit(-1);
            result = null;
            return(result);
        }
    }
    private String[] parse(File file) throws ParseException, FileNotFoundException, IOException {
		JSONParser parser = new JSONParser();
		Reader reader = new FileReader(file);
		
		Object jsonObj = parser.parse(reader);
		JSONObject jsonObject = (JSONObject) jsonObj;
		
		String instanceName = (String) jsonObject.get("instanceName");
		String game = (String) jsonObject.get("gameFolder");
		String args = (String) jsonObject.get("srcdsArguments");
        reader.close();
        try {
        return(new String[] {instanceName,game,args});
        }
        catch (Exception e) {
            log.exceptionLog("Failed to parse JSON file "+file.getName()+". Does this have all three variables? Follow the github example for more information.", e);
            System.exit(-1);
            return(null);
        }
	}
    public String[][] getInstances() {
        return ((String[][]) this.instances.toArray());
    }
}
