package client.threads;

import client.Client;
import message.Message;

import java.io.ObjectInputStream;
import java.io.IOException;


public class Recepter implements Runnable
{
	private Client client;
	private boolean running;

	private ObjectInputStream in;


	public Recepter( Client client )
	{
		this.client = client;
		this.running = true;

		try 
		{
			this.in = new ObjectInputStream( this.client.getConnexion().getInputStream() );	
		}
		catch( IOException e ) { e.printStackTrace(); }
	}


	public void run()
	{
		while ( this.running )
		{
			try 
			{
				this.client.getFnt().updateMessage( (Message)this.in.readObject() );
			}
			catch ( IOException e ) { e.printStackTrace(); }
			catch( ClassNotFoundException e ) { e.printStackTrace(); }
		}
	}
}
