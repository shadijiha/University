package hehexd;

public interface IServer {
	void ping();
	void pong();
	int totalNoOfPings();
	void reset();
}