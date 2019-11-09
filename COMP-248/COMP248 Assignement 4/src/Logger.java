/**
 * 
 * Logger class for debug
 * 
 */

 public class Logger    {

    public static final int logLevelError = 0;
    public static final int logLevelWarnning = 1;
    public static final int logLevelInfo = 2;
    private int logLevel = 2;
    private boolean DEBUG_MODE = false;

    Logger()    {}

    Logger(int level)    {
        logLevel = level;
    }

    public void log(String message) 
    {
        if (logLevel >= logLevelInfo)
            System.out.println("[INFO]  " + message);
    } 
    
    public void warn(String message) 
    {
        if (logLevel >= logLevelWarnning)
            System.out.println("[WARNNING]  " + message);
    }

    public void error(String message) 
    {
        if (logLevel >= logLevelError)
            System.out.println("[ERROR]  " + message);
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

    public void debugMethodes(String command) {

        if (command.equalsIgnoreCase("!"))


    }

 }