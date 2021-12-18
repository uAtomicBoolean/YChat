package serveur;

import utils.Ansii;
import utils.Message;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;

import java.util.ArrayList;


/**
 * Classe principale du serveur.
 * Le serveur se lance avec des paramètres par défauts qui correspondent avec
 * ceux des clients, mais il peut prendre des paramètres supplémentaire pour les changer.
 */
public class Serveur
{
	private ServerSocket ss;

	private ArrayList<Socket> clients;


	public Serveur()
	{
		this.clients = new ArrayList<Socket>();
		try 
		{ 
			this.ss = new ServerSocket( 6000 );
		}
		catch( IOException e ) { e.printStackTrace(); }

		this.run();
	}


	public void run()
	{
		Socket client = null;
		while ( true )
		{
			try
			{
				client = this.ss.accept();
				
				// Envoi de la bannière du serveur.
				new ObjectOutputStream( client.getOutputStream() ).writeObject( new Message( null, Ansii.YELLOW_FG + Ansii.BLINK + 
					"_____.___._________   ___ ___    ________________\n" +
					"\\__  |   |\\_   ___ \\ /   |   \\  /  _  \\__    ___/\n" +
					" /   |   |/    \\  \\//    ~    \\/  /_\\  \\|    |\n" +
					" \\____   |\\     \\___\\    Y    /    |    \\    |\n" +
					" / ______| \\______  /\\___|_  /\\____|__  /____|\n" +
					" \\/               \\/       \\/         \\/\n" + Ansii.RESET
				));

				this.clients.add( client );
				this.receiver( client );
			}
			catch( IOException e ) { e.printStackTrace(); }
		}
	}


	/**
	 * Thread écoutant un client pour ensuite renvoyer le message reçu aux autres clients.
	 * @param client Le socket de la connexion avec le client.
	 */
	public void receiver( Socket client )
	{
		new Thread( new Runnable() {
			public void run()
			{
				ObjectInputStream in;
				try 
				{
					in = new ObjectInputStream( client.getInputStream() );
					while ( true )
					{
						sendToClients( client, (Message)in.readObject() );
					}
				}
				catch ( IOException e ) { e.printStackTrace(); }
				catch ( ClassNotFoundException e ) { e.printStackTrace(); }
			}
		}).start();
	}


	/**
	 * Envoi à tout les clients qui ne sont pas le client 'sender' le message passé en paramètre.
	 * @param sender Le socket de la connexion avec l'envoyeur du message.
	 * @param msg Le message à transférer aux autres clients.
	 */
	public void sendToClients( Socket sender, Message msg )
	{
		for ( Socket client: this.clients )
		{
			if ( client != sender )
			{
				try { new ObjectOutputStream( client.getOutputStream() ).writeObject( msg ); }
				catch( IOException e ) { e.printStackTrace(); }
			}
		}
	}


	public static void main( String[] args )
	{
		new Serveur();
	}
}