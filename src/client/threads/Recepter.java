package client.threads;

import client.Client;
import message.Message;

import java.io.ObjectInputStream;
import java.io.IOException;

import java.net.SocketException;


/**
 * Thread recevant les messages du serveur et les affichants dans la console.
 */
public class Recepter implements Runnable
{
	private boolean running;

	private Client client;
	private ObjectInputStream in;


	public Recepter( Client client )
	{
		this.running = true;

		this.client = client;
		try { this.in = new ObjectInputStream( this.client.getSocket().getInputStream() ); }
		catch( IOException e ) { e.printStackTrace(); }
	}


	public void setRunning( boolean r ) { this.running = r; }


	public void run()
	{
		while ( this.running )
		{
			try { System.out.println( (Message)this.in.readObject() ); }
			catch( SocketException e ) 
			{
				System.out.println( "Connexion avec le serveur perdue!" );
				this.client.stopClient();
			}
			catch( IOException e ) { e.printStackTrace(); }
			catch( ClassNotFoundException e ) { e.printStackTrace(); }
		}
	}
}
