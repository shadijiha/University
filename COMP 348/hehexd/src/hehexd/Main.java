package hehexd;

public class Main {

    public static void main(String[] args) {
        IServer s = (IServer)(Object)new Server();
        s.ping();
        s.reset(); // pings to be reset to 0
        s.ping();  
        s.ping(); 
        System.out.println("Number of pings: " + s.totalNoOfPings()); 
        // 2 to be printed
    }

}
