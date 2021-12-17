package client;

import client.threads.Emitter;
import client.threads.Recepter;

import java.io.IOException;
import java.net.Socket;


// TODO ajouter un objet Auteur qui contient les informations de l'auteur.
// Pour plus tard, ajouter un objet pour pouvoir envoyer un message directement au client.
public class Client
{
	private Socket s;
	private Recepter in;
	private Emitter out;


	public Client()
	{
		try
		{
			this.s = new Socket( "localhost", 6000 );
			this.in = new Recepter( this );
			this.out = new Emitter( this );

			new Thread( this.in ).start();
			new Thread( this.out ).start();
		}
		catch( IOException e ) { e.printStackTrace(); }
	}


	public Socket getSocket() { return this.s; }


	public static void main( String args[] )
	{
		new Client();
	}
}
