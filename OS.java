// mohamed walid
// Aoss Majed
// Abdelkarim eldali
// Ahmed elagbi
package os;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;
import sun.util.logging.PlatformLogger;

/**
 *
 * @author Dell
 */
public class OS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException {

        Parser p = new Parser();
        Terminal terminal = new Terminal();
        String Key = "yes";
        //System.out.println("\\s+\\|\\s+");
        while (Key.equalsIgnoreCase("yes")) {
            String myCommands[] = null;
            Scanner input = new Scanner(System.in);
            String UserInput = input.nextLine();
            if (UserInput.contains("|")) {
                myCommands = UserInput.split("\\s+\\|\\s+");
            } else {
                myCommands = UserInput.split("qqq");
            }

            for (int i = 0; i < myCommands.length; i++) {
                UserInput = myCommands[i];
                if (UserInput.contains(">") || UserInput.contains(">>")) {
                    p.ReDirectionParser(UserInput);
                } else {
                    p.redirectMark = 0;
                }
                if (!UserInput.equalsIgnoreCase("exit")) {

                    if (p.redirectMark == 0) {
                        p.parse(UserInput);
                    }
                    switch (p.cmd) {
                        case "cp": {
                            String source = (String) p.args.get(0);
                            String destination = (String) p.args.get(1);
                            p.args.clear();
                            terminal.cp(source, destination);
                            break;
                        }
                        case "cd": {
                            String source = (String) p.args.get(0);
                            p.args.clear();
                            terminal.cd(source);
                            break;
                        }
                        case "ls": {
                            String source = (String) p.args.get(0);
                            p.args.clear();
                            if (p.redirectMark == 1) {
                                PrintStream Console = new PrintStream(new File(p.redirectPath));
                                System.setOut(Console);

                                terminal.ls(source);

                            } else {
                                terminal.ls(source);
                            }

                            break;
                        }
                        case "cat": {
                            if (p.args.size() > 1) {
                            } else if (p.args.size() == 1) {
                            }
                            terminal.cat(p.args);
                            p.args.clear();
                            break;
                        }
                        case "more": {
                            String source = (String) p.args.get(0);
                            p.args.clear();
                            if (p.redirectMark == 1) {
                                PrintStream Console = new PrintStream(new File(p.redirectPath));
                                System.setOut(Console);
                                terminal.more(source);
                            } else {
                                terminal.more(source);
                            }
                            break;
                        }
                        case "mkdir": {
                            String source = (String) p.args.get(0);
                            p.args.clear();
                            terminal.mkdir(source);
                            break;
                        }
                        case "rmdir": {
                            String source = (String) p.args.get(0);
                            p.args.clear();
                            terminal.rmdir(source);
                            break;
                        }
                        case "mv": {
                            String source = (String) p.args.get(0);
                            String destination = (String) p.args.get(1);
                            p.args.clear();
                            terminal.mv(source, destination);
                            break;
                        }
                        case "rm": {
                            String source = (String) p.args.get(0);
                            p.args.clear();
                            terminal.rm(source);
                            break;
                        }
                        case "args": {
                            terminal.args(UserInput);
                            break;
                        }

                        case "date": {
                            if (p.redirectMark == 1) {
                                PrintStream Console = new PrintStream(new File(p.redirectPath));
                                System.setOut(Console);
                                terminal.date();
                            } else {
                                terminal.date();
                            }
                            break;
                        }

                        case "help": {
                            if (p.redirectMark == 1) {
                                PrintStream Console = new PrintStream(new File(p.redirectPath));
                                System.setOut(Console);
                                terminal.help();
                            } else {
                                terminal.help();
                            }
                            break;
                        }

                        case "pwd": {
                            if (p.redirectMark == 1) {
                                PrintStream Console = new PrintStream(new File(p.redirectPath));
                                System.setOut(Console);
                                terminal.pwd();
                            } else {
                                terminal.pwd();
                            }
                            break;
                        }

                        case "clear": {
                            terminal.clear();
                            break;
                        }
                        case "echo": {
                            String source = (String) p.args.get(0);
                            
                            //System.out.println(source);
                            terminal.echo(source);
                            break;
                        }
                        
                        case "error": {
                            break;
                        }
                    }
                } else {
                    Key = "no";
                }
            }
        }
    }
}
