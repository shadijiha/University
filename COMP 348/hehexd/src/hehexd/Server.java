package hehexd;

public class Server {
    private int pings = 0;
    public void ping() { pings++; }
    public void pong() { pings--; }
}
