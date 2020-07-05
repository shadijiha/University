/**
 *
 */
package com.main;

import static com.main.Account.readFile;

public class Server {

	private Network network;
	private Account[] accounts;

	public Server() {
		accounts = readFile("accounts.txt");
	}

	public void connect(Network network) {
		this.network = network;
	}


}
