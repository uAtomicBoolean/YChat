package serveur;

import utils.Ansii;
import message.Message;
import serveur.threads.ClientServeur;

import java.io.IOException;
import java.net.ServerSocket;

import java.util.ArrayList;


/**
 * Classe principale du serveur.
 * Le serveur se lance avec des paramètres par défauts qui correspondent avec
 * ceux des clients, mais il peut prendre des paramètres supplémentaire pour les changer.
 */
public class Serveur
{
	private ServerSocket ss;

	private ArrayList<ClientServeur> clients;


	public Serveur()
	{
		this.clients = new ArrayList<ClientServeur>();
		try { this.ss = new ServerSocket( 6000 ); }
		catch( IOException e ) { e.printStackTrace(); }

		this.run();
	}


	public void run()
	{
		ClientServeur client = null;
		while ( true )
		{
			try
			{
				client = new ClientServeur( this, this.ss.accept() );
				
				// Envoi de la bannière du serveur.
				client.sendMessage( new Message( Ansii.YELLOW_FG + Ansii.BLINK + 
					"_____.___._________   ___ ___    ________________\n" +
					"\\__  |   |\\_   ___ \\ /   |   \\  /  _  \\__    ___/\n" +
					" /   |   |/    \\  \\//    ~    \\/  /_\\  \\|    |\n" +
					" \\____   |\\     \\___\\    Y    /    |    \\    |\n" +
					" / ______| \\______  /\\___|_  /\\____|__  /____|\n" +
					" \\/               \\/       \\/         \\/\n" + Ansii.RESET
				));

				this.clients.add( client );
				new Thread( client ).start();

				System.out.println( "Nouveau client connecté!" );
			}
			catch( IOException e ) { e.printStackTrace(); }
		}
	}


	/**
	 * Envoi à tout les clients qui ne sont pas le client 'sender' le message passé en paramètre.
	 * @param sender Le socket de la connexion avec l'envoyeur du message.
	 * @param msg Le message à transférer aux autres clients.
	 */
	public void sendToClients( ClientServeur sender, Message msg )
	{
		for ( ClientServeur client: this.clients )
		{
			if ( client != sender )
				client.sendMessage( msg );
		}
	}


	public static void main( String[] args )
	{
		new Serveur();
	}
}