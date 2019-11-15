package classes;
/**
 * 
 * This file contains the Logger class to help with debugging
 * 
 */

import java.util.Scanner;

 public class Logger    {

    public static final int logLevelError = 0;
    public static final int logLevelWarnning = 1;
    public static final int logLevelInfo = 2;
    private int logLevel = logLevelInfo;
    private boolean DEBUG_MODE = false;
    private boolean ALLOW_COLOR = false;

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

    public Logger()    {}

    public Logger(int level)    {
        logLevel = level;
    }

    public void log(String message) 
    {
        String totalMessage;
        if (ALLOW_COLOR) 
            totalMessage = ANSI_GREEN + "[INFO]" + ANSI_RESET + "\t" + message;
        else
            totalMessage =  "[INFO]\t" + message;

        // Print
        if (logLevel >= logLevelInfo)   {
            System.out.println(totalMessage);
        }

        // Add to buffer
        buffer = push(buffer, totalMessage);
    } 
    
    public void warn(String message) 
    {
        String totalMessage;
        if (ALLOW_COLOR) 
            totalMessage = ANSI_YELLOW + "[WARNNING]" + ANSI_RESET + "\t" + message;
        else
            totalMessage =  "[WARNNING]\t" + message;

        // Print
        if (logLevel >= logLevelWarnning)   {
            System.out.println(totalMessage);
        }

        // Add to buffer
        buffer = push(buffer, totalMessage);
    }

    public void error(String message) 
    {
        String totalMessage;
        if (ALLOW_COLOR) 
            totalMessage = ANSI_RED + "[ERROR]" + ANSI_RESET + "\t" + message;
        else
            totalMessage =  "[ERROR]\t" + message;

        // Print
        if (logLevel >= logLevelError)  {
            System.out.println(totalMessage);
        }

        // Add to buffer
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

        // See if console supports ANSI escape colors or not
        if (System.console() != null && System.getenv().get("TERM") != null) {
            ALLOW_COLOR = true;
        } else {
            ALLOW_COLOR = false;
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
            } else if (command.equalsIgnoreCase("!help"))   {

                System.out.println("Command list:");
                System.out.println("\t!board\t\t: displays the current game board with players positions.");
                System.out.println("\t!players\t\t: displays the info of all the players in the game.");
                System.out.println("\t!dice\t\t: displays the current Dice object status.");
                System.out.println("\t!history\t\t: displays the debugger buffer.");
                System.out.println("\t!exit\t\t: exit the application.");
                System.out.println("\t!exit\t\t: continue to the next round of the game.");
                System.out.println("\t!help\t\t: displays all the command list with their usage.");

            } else  {
                this.error("no such command exists (" + command + "). ");
            }

            // PAUSE
            this.warn("The debugger has prevented the game from going to next round.");

            if (ALLOW_COLOR)
                System.out.print("Type " + ANSI_BLUE + "!continue " + ANSI_RESET + "to continue... ");
            else
                System.out.print("Type !continue to continue... ");

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