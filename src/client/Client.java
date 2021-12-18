package client;

import client.threads.Emitter;
import client.threads.Recepter;

import java.io.IOException;
import java.net.Socket;

import javax.swing.plaf.synth.SynthSeparatorUI;


/**
 * Classe du principale du Client.
 * Il permet de se connecter à un serveur et de converser avec d'autres clients.
 * Des paramètres de bases sont présents pour la connexion au serveur mais il peut prendre d'autres paramètres
 * lors du lancement.
 */
public class Client
{
	private Socket s;
	private Recepter in;
	private Emitter out;


	public Client( String addr, int port )
	{
		try
		{
			this.s = new Socket( addr, port );
			this.in = new Recepter( this );
			this.out = new Emitter( this );

			new Thread( this.in ).start();
			new Thread( this.out ).start();
		}
		catch( IOException e ) { e.printStackTrace(); }
	}


	public Client() { this( "127.0.0.1", 9000 ); }
	public Client( String addr ) { this( addr, 9000 ); }
	public Client( int port ) { this( "localhost", port ); }


	/* ------------------------------------------------------ */
	/* METHODES                                               */
	/* ------------------------------------------------------ */
	public Socket getSocket() { return this.s; }


	public static void main( String args[] )
	{
		new Client();
	}
}
