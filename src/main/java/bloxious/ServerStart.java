package bloxious;

import java.io.*;
import java.util.ArrayList;

public class ServerStart {
    Runtime rt = Runtime.getRuntime();
    ArrayList<String> command = new ArrayList<>();
    Log log;
    String OS = System.getProperty("os.name");
    public ServerStart(Log log) {
        this.log = log;
    }
    public void startServer(String game, String gamedir, String[] args) {
      if (OS.indexOf("win") >= 0) {
        String[] temp = {"cmd.exe","srcds.exe","-game",game,"-console","-nocrashdialog"};
        for (int i=0;i<temp.length;i++) {
        command.add(temp[i]);
        }
        for (int i=0;i<args.length;i++) {
          command.add(args[i]);
        }
        try {
          newServerRun((String[]) command.toArray(), gamedir);
        }
        catch (Exception e) {
          log.exceptionLog("Server execution Exception:", e);
          System.exit(-2);
        }

      } else if (OS.indexOf("nux") >= 0) {
        //roughly based on strings from https://stackoverflow.com/questions/8751337/execute-external-program-through-terminal-in-java
        String[] temp = {"/bin/sh","srcds.sh","-game",game,"-console","-nocrashdialog"};
        for (int i=0;i<temp.length;i++) {
          command.add(temp[i]);
        }
        for (int i=0;i<args.length;i++) {
          command.add(args[i]);
        }
        try {
          newServerRun((String[]) command.toArray(), gamedir);
        }
        catch (Exception e) {
          log.exceptionLog("Server exceution Exception:", e);
        }
      }
    }

//Roughly based on code from https://zetcode.com/java/processbuilder/
  private int newServerRun(String[] cmd, String dir) throws IOException, InterruptedException {
     ProcessBuilder processBuilder = new ProcessBuilder();
     File file = new File(dir);
     processBuilder.directory(file);
     processBuilder.command(cmd);
     Process process = processBuilder.start();

     try (BufferedReader reader = new BufferedReader(
         new InputStreamReader(process.getInputStream()))) {

         String line;

         while ((line = reader.readLine()) != null) {
             System.out.println(line);
         }
    }
    return process.waitFor();
  }
}

