package gui;

import message.Message;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class AffichageMessages extends JPanel
{
	private YChat fnt;
	private JLabel affMess;


	public AffichageMessages( YChat fnt )
	{
		this.fnt = fnt;

		this.affMess = new JLabel( "no messages" );

		this.add( this.affMess );
	}


	public void updateMessage( Message msg )
	{
		this.affMess.setText( msg.toString() );
	}
}
