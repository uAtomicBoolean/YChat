package gui;

import message.Message;
import client.Client;

import javax.swing.JFrame;

import java.awt.BorderLayout;


public class YChat extends JFrame 
{
	private AffichageMessages pnlAffMessages;
	private EnvoiMessage pnlEnvMessage;

	private Client client;


	public YChat()
	{
		this.setSize( 1280, 900 );
		this.setTitle( "YChat" );
		this.setLayout( new BorderLayout() );

		this.client = new Client( this );

		this.pnlAffMessages = new AffichageMessages( this );
		this.pnlEnvMessage = new EnvoiMessage( this );

		this.add( this.pnlAffMessages, BorderLayout.NORTH );    
		this.add( this.pnlEnvMessage, BorderLayout.SOUTH );
		

		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setVisible( true );
	}


	public void updateMessage( Message msg )
	{
		this.pnlAffMessages.updateMessage( msg );
	}


	public void envoyerMessage( Message msg )
	{
		this.client.envoyerMessage( msg );
	}


	public static void main( String[] args )
	{
		new YChat();
	}
}
