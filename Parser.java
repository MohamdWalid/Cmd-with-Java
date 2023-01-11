/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 *
 * @author Dell
 */
public class Parser {

    int redirectMark;
    String redirectPath;
    String[] AllInput;
    ArrayList args = new ArrayList();
    String cmd;
    String[] commands = new String[15];
    int[] ArgNum = new int[14];

    public Parser() {
        commands[0] = "cp";//ArgNum[0]=2;
        commands[1] = "cd";//ArgNum[1]=1;
        commands[2] = "ls";//ArgNum[2]=1;
        commands[3] = "cat";//ArgNum[0]=2;
        commands[4] = "more";//ArgNum[0]=2;
        commands[5] = "mkdir";//ArgNum[0]=2;
        commands[6] = "rmdir";//ArgNum[0]=2;
        commands[7] = "mv";//ArgNum[0]=2;
        commands[8] = "rm";//ArgNum[0]=2;
        commands[9] = "args";//ArgNum[0]=2;
        commands[10] = "date";//ArgNum[0]=2;
        commands[11] = "help";//ArgNum[0]=2;
        commands[12] = "pwd";//ArgNum[0]=2;
        commands[13] = "clear";//ArgNum[0]=2;
        commands[14] = "echo";//ArgNum[0]=2;

    }

    public boolean ReDirectionParser(String input) throws FileNotFoundException {
        redirectMark = 1;
        String[] InputArray;
        InputArray = input.split(" ");
        redirectPath = InputArray[(InputArray.length - 1)];
        for (String command : commands) {
            if (command.equalsIgnoreCase(InputArray[0])) {
                cmd = InputArray[0];
                for (int i = 1; i < (InputArray.length) - 1; i++) {
                    if (!(InputArray[i].equalsIgnoreCase(">")) || !(InputArray[i].equalsIgnoreCase(">>"))) {
                        args.add(InputArray[i]);
                    }
                }
                return true;
            }
        }
        cmd="error";
        System.out.println("Command is incorrect");

        return false;
    }

    public boolean parse(String input) {
        redirectMark = 0;
        AllInput = input.split(" ");

        for (String command : commands) {
            if (command.equalsIgnoreCase(AllInput[0])) {
                cmd = AllInput[0];
                for (int i = 1; i < AllInput.length; i++) {
                    args.add(AllInput[i]);
                }

                return true;
            }
        }
        cmd="error";
        System.out.println("Command is incorrect");
        return false;
    }

    public String getCmd() {
        return cmd;
    }

    public ArrayList getArguments() {
        return args;
    }

}
