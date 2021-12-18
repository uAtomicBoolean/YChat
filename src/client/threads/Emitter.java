package client.threads;

import client.Client;

import utils.Message;

import java.util.Scanner;
import java.io.IOException;
import java.io.ObjectOutputStream;


/** 
 * Thread s'occupant d'écouter l'entrée au clavier de l'utilisateur et d'envoyer 
 * les messages au serveur.
 */
public class Emitter implements Runnable
{
	private ObjectOutputStream out;
	private Client client;


	public Emitter( Client client )
	{
		this.client = client;
		try { this.out = new ObjectOutputStream( this.client.getSocket().getOutputStream() ); }
		catch( IOException e ) { e.printStackTrace(); }
	}


	public void run()
	{
		Scanner sc = new Scanner( System.in );
		String msg = null;
		while ( true )
		{
			msg = sc.nextLine();
			try { this.out.writeObject( new Message( this.client, msg ) ); }
			catch( IOException e ) { e.printStackTrace(); sc.close(); }
		}
	}
}
