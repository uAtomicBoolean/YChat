package client;

import message.Auteur;
import utils.Ansii;

import java.util.Scanner;

import client.threads.Emitter;
import client.threads.Recepter;

import java.io.IOException;
import java.net.Socket;


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

	private Auteur auteur;


	public Client( String addr, int port, String nomAuteur, String couleur )
	{
		String coulAnsii = Ansii.getColor( couleur );
		this.auteur = new Auteur( nomAuteur, coulAnsii == null ? Ansii.WHITE_FG : coulAnsii );

		try
		{
			this.s = new Socket( addr, port );
			this.out = new Emitter( this, this.auteur );
			this.in = new Recepter( this );

			new Thread( this.in ).start();
			new Thread( this.out ).start();
		}
		catch( IOException e ) { e.printStackTrace(); }
	}


	public Client() { this( "localhost", 6000, "Anonyme", "blanc" ); }
	public Client( String auteur ) { this( "localhost", 6000, auteur, "blanc" ); }
	public Client( String auteur, String couleur ) { this( "localhost", 6000, auteur, couleur ); }


	/* ------------------------------------------------------ */
	/* METHODES                                               */
	/* ------------------------------------------------------ */
	public Socket getSocket() { return this.s; }


	/**
	 * Arrête le client en stoppant les Threads.
	 */
	public void stopClient()
	{
		this.in.setRunning( false );
		this.out.setRunning( false );

		// On force la sortie avec System.exit() pour éviter que le programme ne reste
		// ouvert suite à un input bloquant.
		System.exit( 0 );
	}


	public static void main( String args[] )
	{
		if ( args.length == 1 && args[0].equals( "-help" ) )
		{
			System.out.println( 
				Ansii.BOLD + Ansii.UNDERLINE + "Commandes de lancement du client :\n" + Ansii.RESET +
				"\tjava client.Client pseudo\n" +
				"\tjava client.Client pseudo couleur\n" +
				Ansii.BOLD + Ansii.UNDERLINE + "\nListe des couleurs disponibles :\n" + Ansii.RESET +
				"\trouge, noir, vert, bleu, cyan, jaune, magenta, gris, blanc (par défaut).\n"
			);
			return;
		}
		else
			System.out.println( Ansii.RED_FG + "Pour connaitre les arguments, utiliser '-help'!" + Ansii.RESET );

		if ( args.length == 2 && args[0] instanceof String && args[1] instanceof String )
			new Client( args[0], args[1] );
		else if ( args.length == 1 && args[0] instanceof String )
			new Client( args[0] );
		else
			new Client();
	}
}
