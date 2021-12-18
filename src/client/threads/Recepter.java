package client.threads;

import client.Client;

import utils.Message;

import java.io.ObjectInputStream;
import java.io.IOException;


/**
 * Thread recevant les messages du serveur et les affichants dans la console.
 */
public class Recepter implements Runnable
{
	private Client client;
	private ObjectInputStream in;


	public Recepter( Client client )
	{
		this.client = client;
		try { this.in = new ObjectInputStream( this.client.getSocket().getInputStream() ); }
		catch( IOException e ) { e.printStackTrace(); }
	}


	public void run()
	{
		while ( true )
		{
			try { System.out.println( (Message)this.in.readObject() ); }
			catch( IOException e ) { e.printStackTrace(); }
			catch( ClassNotFoundException e ) { e.printStackTrace(); }
		}
	}
}
