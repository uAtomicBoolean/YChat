package client;

import gui.YChat;
import client.threads.Recepter;
import message.Message;

import java.net.Socket;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class Client
{
	private YChat fnt;

	private Socket cnx;
	private ObjectOutputStream out;
	private Thread threadIn;


	public Client( YChat fnt )
	{
		this.fnt = fnt;

		try { this.cnx = new Socket( "localhost", 6000 ); }
		catch( IOException e ) { e.printStackTrace(); }

		try { this.out = new ObjectOutputStream( this.cnx.getOutputStream() ); }
		catch( IOException e ) { e.printStackTrace(); }

		this.threadIn = new Thread( new Recepter( this ) );
		this.threadIn.start();
	}


	public Socket getConnexion() { return this.cnx; }
	public YChat getFnt() { return this.fnt; }


	public void envoyerMessage( Message msg )
	{
		try { this.out.writeObject( msg ); }
		catch( IOException e ) { e.printStackTrace(); }
	}
}
