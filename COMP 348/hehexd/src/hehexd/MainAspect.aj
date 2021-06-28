/**
 *
 */

package hehexd;

privileged public aspect  MainAspect	{

	declare parents: Server implements IServer;

	public int Server.totalNoOfPings()	{
		return this.pings;
	}
	
	public void Server.reset()	{
		this.pings = 0;
	}
}