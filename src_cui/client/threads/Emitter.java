package client.threads;

import client.Client;
import message.Message;
import message.Auteur;

import java.util.Scanner;
import java.io.IOException;
import java.io.ObjectOutputStream;


/** 
 * Thread s'occupant d'écouter l'entrée au clavier de l'utilisateur et d'envoyer 
 * les messages au serveur.
 */
public class Emitter implements Runnable
{
	private boolean running;

	private Auteur auteur;

	private ObjectOutputStream out;
	private Client client;


	public Emitter( Client client, Auteur auteur )
	{
		this.running = true;
		this.auteur = auteur;

		this.client = client;
		try { this.out = new ObjectOutputStream( this.client.getSocket().getOutputStream() ); }
		catch( IOException e ) { e.printStackTrace(); }
	}

	public void setRunning( boolean r ) { this.running = r; }


	public void run()
	{
		Scanner sc = new Scanner( System.in );
		String msg = null;
		
		while ( this.running )
		{
			msg = sc.nextLine();
			try
			{
				this.out.writeObject( new Message( this.auteur, msg ) );
				this.out.reset(); 
			}
			catch( IOException e ) { e.printStackTrace(); sc.close(); }
		}
	}
}
