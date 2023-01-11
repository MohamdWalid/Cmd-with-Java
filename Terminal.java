/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;

/**
 *
 * @author Dell
 */
public class Terminal {


    public String currentDirectory = "F:\\\\";

    public void CopyDirectory(File src1, File dest1) throws IOException {
        if (src1.isDirectory()) {
            if (!dest1.exists()) {
                dest1.mkdir();

            }
            String MyFiles[] = src1.list();
            for (String F : MyFiles) {
                File srci = new File(src1, F);
                File desti = new File(dest1, F);
                CopyDirectory(srci, desti);
            }
        } else {
            CopyFile(src1, dest1);
        }
    }

    public void CopyFile(File src1, File dest1) throws FileNotFoundException, IOException {
        // try{
        FileInputStream srcFile = new FileInputStream(src1);
        FileOutputStream destFile = new FileOutputStream(dest1);
        byte[] container = new byte[1024];
        int fileLength;
        while ((fileLength = srcFile.read(container)) > 0) {
            destFile.write(container, 0, fileLength);
        }
    }

    public void cp(String MysourcePath, String MydestinationPath) throws IOException {

        File source = new File(MysourcePath);
        File dest = new File(MydestinationPath);
        if (source.exists()) {
            CopyDirectory(source, dest);
        }
        else{System.out.println("Source Path not Exist");}
    }

    public void cd(String CurrentDir) {
        currentDirectory = CurrentDir;
    }

    public void pwd() {
        System.out.println(currentDirectory);
    }

    public void ls(String location) throws IOException {
        File loc = new File(location);
        if (loc.exists()) {
            String MyFiles[] = loc.list();
            for (String F : MyFiles) {
                System.out.println(F);
            }
        } else {
            System.out.println("File not Exist");
        }
    }

    public void delete(File source) {
        if (source.isDirectory()) {
            if (source.list().length == 0) {
                source.delete();
            } else {
                File myFiles[] = source.listFiles();
                for (File F : myFiles) {
                    delete(F);
                }
                if (source.list().length == 0) {
                    source.delete();
                }
            }
        } else {
            source.delete();
        }
    }

    public void rm(String sourcePath) {
        File src = new File(sourcePath);
        if (src.exists()) {
            delete(src);
        } else {
            System.out.println("File not Exist");
        }

    }

    public void mv(String sourcePath, String destinationPath) throws IOException {
        cp(sourcePath, destinationPath);
        rm(sourcePath);
    }

    public void cat(ArrayList paths) throws FileNotFoundException, IOException {

        if (paths.size() == 1) {
            File location = new File((String) paths.get(0));
            Scanner textContent = new Scanner(location);
            while (textContent.hasNextLine()) {
                System.out.println(textContent.nextLine());
            }
        } else if (paths.size() > 1) {
            String AllFileContent = "";
            for (int i = 0; i < paths.size(); i++) {
                File location = new File((String) paths.get(i));
                Scanner textContent = new Scanner(location);
                while (textContent.hasNextLine()) {
                    AllFileContent = AllFileContent.concat(textContent.nextLine() + "\n");
                }
            }
            FileWriter writeToNewFile = new FileWriter((String) paths.get(0));
            writeToNewFile.write(AllFileContent);
            writeToNewFile.close();
        }
    }

    public void date() {
        Date currentDate = new Date();
        System.out.println(currentDate);
    }

    public static void clear() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }

    public void mkdir(String location) {
        File dir = new File(location);
        if (!dir.exists()) {
            dir.mkdir();
        } else {
            System.out.println("The Directory is already Exist");
        }
    }

    public void rmdir(String location) {
        File dir = new File(location);
        if (dir.exists()) {
            dir.delete();
        } else {
            System.out.println("The Directory is not Exist");
        }
    }

    public void args(String command) {
        if (command.equalsIgnoreCase("args cd")) {
            System.out.println("arg1: New Directory");
        } else if (command.equalsIgnoreCase("args ls")) {
            System.out.println("arg1: LocationPath");
        } else if (command.equalsIgnoreCase("args cp")) {
            System.out.println("arg1: SourcePath, arg2: DestinationPath");
        } else if (command.equalsIgnoreCase("args cat")) {
            System.out.println("arg1: Path1, arg2: Path2");
        } else if (command.equalsIgnoreCase("args mkdir")) {
            System.out.println("arg1: LocationPath");
        } else if (command.equalsIgnoreCase("args rmdir")) {
            System.out.println("arg1: LocationPath");
        } else if (command.equalsIgnoreCase("args  mv")) {
            System.out.println("arg1: SourcePath, arg2: DestinationPath");
        } else if (command.equalsIgnoreCase("args rm")) {
            System.out.println("arg1: LocationPath");
        } else if (command.equalsIgnoreCase("args date")) {
            System.out.println("No Argument");
        } else if (command.equalsIgnoreCase("args help")) {
            System.out.println("No Argument");
        } else if (command.equalsIgnoreCase("args pwd")) {
            System.out.println("No Argument");
        } else if (command.equalsIgnoreCase("args clear")) {
            System.out.println("No Argument");
        } else {
            System.out.println("Wrong Command");
        }
    }

    public void help() {
        System.out.println("args: List all command arguments.");
        System.out.println("cd: Change the current Directory.");
        System.out.println("ls: Disply all files and directories names on specific path.");
        System.out.println("cp: Copy File.");
        System.out.println("cat: Concatnate files or Disply content of file.");
        System.out.println("mkdir: Create new Dirctory.");
        System.out.println("rmdir: Delete Empty Directory.");
        System.out.println("mv: Move File.");
        System.out.println("rm: Delete Specified File.");
        System.out.println("date: Current date/time.");
        System.out.println("help: Disply All Commands functions.");
        System.out.println("pwd: Disply current Directory.");
        System.out.println("clear: Clear the current terminal screen.");
        System.out.println("exit: Stop all.");
    }
    public void echo(String print11){
        System.out.println(print11);
    }

    public void more(String location) throws FileNotFoundException {
        File loc = new File(location);
        if (loc.exists()) {
            Scanner textContent = new Scanner(loc);
            while (textContent.hasNextLine()) {
                System.out.println(textContent.nextLine());

            }
        } else {
            System.out.println("File Not Exist");
        }
    }
}
