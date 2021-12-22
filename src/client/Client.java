package client;

import gui.YChat;
import client.threads.Recepter;

import java.net.Socket;
import java.io.IOException;


public class Client
{
	private YChat fnt;

	private Socket cnx;
	private Thread threadIn;


	public Client( YChat fnt )
	{
		this.fnt = fnt;

		try { this.cnx = new Socket( "localhost", 6000 ); }
		catch( IOException e ) { e.printStackTrace(); }

		this.threadIn = new Thread( new Recepter( this ) );
		this.threadIn.start();
	}


	public Socket getConnexion() { return this.cnx; }
	public YChat getFnt() { return this.fnt; }
}
