package gui;

import message.Message;

import javax.swing.JPanel;

import java.awt.TextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class EnvoiMessage extends JPanel
{
	private YChat fnt;

	private TextArea txt;


	public EnvoiMessage( YChat fnt )
	{
		this.fnt = fnt;

		this.txt = new TextArea( 4, 178 );
		this.txt.addKeyListener( new KeyAdapter() {
			public void keyReleased( KeyEvent e )
			{
				if ( e.getKeyCode() == KeyEvent.VK_ENTER )
				{
					fnt.envoyerMessage( new Message( txt.getText() ) );
					txt.setText( "" );
				}
			}
		});

		this.add( this.txt );
	}
}
