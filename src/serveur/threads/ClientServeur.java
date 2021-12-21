package serveur.threads;

import message.Message;
import serveur.Serveur;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.Socket;
import java.net.SocketException;


public class ClientServeur implements Runnable
{
	private boolean running;

	private Serveur serveur;

	private Socket s;
	private ObjectInputStream in;
	private ObjectOutputStream out;


	public ClientServeur( Serveur serveur, Socket s )
	{
		this.running = true;
		this.serveur = serveur;
		this.s = s;
		try 
		{
			// Il est obligatoire de récupérer le OutputStream avant le InputStream 
			// sinon le programme bloque à cette étape.
			this.out = new ObjectOutputStream( this.s.getOutputStream() );
			this.in = new ObjectInputStream( this.s.getInputStream() );
		}
		catch ( IOException e ) { e.printStackTrace(); }
	}


	public void sendMessage( Message msg )
	{
		try 
		{ 
			this.out.writeObject( msg );
		}
		catch ( IOException e ) { e.printStackTrace(); }
	}


	public void run()
	{
		while ( this.running )
		{
			try
			{
				this.serveur.redirectToClients( this, (Message)this.in.readObject() );
			}
			catch( SocketException e ) 
			{ 
				System.out.println( "Connexion avec un client perdue!");
				this.serveur.removeClient( this );
				this.running = false;
			}
			catch ( IOException e ) { e.printStackTrace(); }
			catch ( ClassNotFoundException e ) { e.printStackTrace(); }
		}
	}
}
