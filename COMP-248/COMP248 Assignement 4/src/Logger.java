/**
 * 
 * Logger class for debug
 * 
 */

import java.util.Scanner;

 public class Logger    {

    public static final int logLevelError = 0;
    public static final int logLevelWarnning = 1;
    public static final int logLevelInfo = 2;
    private int logLevel = logLevelInfo;
    private boolean DEBUG_MODE = false;
    String[] buffer = {};

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    Logger()    {}

    Logger(int level)    {
        logLevel = level;
    }

    public void log(String message) 
    {
        String totalMessage = ANSI_GREEN + "[INFO]" + ANSI_RESET + "\t" + message;

        if (logLevel >= logLevelInfo)   {
            System.out.println(totalMessage);
        }

        buffer = push(buffer, totalMessage);
    } 
    
    public void warn(String message) 
    {
        String totalMessage = ANSI_YELLOW + "[WARNNING]" + ANSI_RESET + "\t" + message;

        if (logLevel >= logLevelWarnning)   {
            System.out.println(totalMessage);
        }

        buffer = push(buffer, totalMessage);
    }

    public void error(String message) 
    {
        String totalMessage = ANSI_RED + "[ERROR]" + ANSI_RESET + "\t" + message;

        if (logLevel >= logLevelError)  {
            System.out.println(totalMessage);
        }

        buffer = push(buffer, totalMessage);
    }

    public void setLevel(int level) {
        logLevel = level;
    }

    public void toggleDebugMode()   {
        if (DEBUG_MODE) {
            DEBUG_MODE = false;
        } else  {
            DEBUG_MODE = true;
        }
    }

    public void debugMethodes(String command, Board b, Player[] __players, Dice d, Scanner scan) {

        String[] tempCommand = command.split("");

        System.out.println("\n");

        if (tempCommand[0].equalsIgnoreCase("!"))   {

            // Is a debug command
            if (!DEBUG_MODE)    {
                this.warn("Attempt to use debugging commands when debug mode is OFF!");
                return;
            }

            // Evaluate the command
            if (command.equalsIgnoreCase("!board")) {
                this.log(b.toStringWithPlayers(__players));
            } else if (command.equalsIgnoreCase("!players") || command.equalsIgnoreCase("!player")) {
                
                for (Player temp : __players)  {
                    this.log(temp.toString());
                }

            } else if (command.equalsIgnoreCase("!dice"))   {
                this.log(d.toString());
            } else if (command.equalsIgnoreCase("!history"))   {
                
                for (String element : buffer)  {
                    System.out.println(element);
                }

            } else if (command.equalsIgnoreCase("!exit"))   {
                System.exit(0);
            } else if (command.equalsIgnoreCase("!continue"))   {
                return;
            } else  {
                this.error("no such command exists (" + command + "). ");
            }

            // PAUSE
            this.warn("The debugger has prevented the game from going to next round.");
            System.out.print("Type " + ANSI_BLUE + "!continue " + ANSI_RESET + "to continue... ");
            String pause = scan.next();
            debugMethodes(pause, b, __players, d, scan);
        }

        return;
    }

    private String[] push(String[] array, String element)   {
        String[] temp = new String[array.length + 1];

        for (int i = 0; i < array.length; i++)  {
            temp[i] = array[i];
        }

        temp[temp.length - 1] = element;

        return temp;
    }

 }