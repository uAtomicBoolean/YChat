package gui;

import message.Message;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.DefaultListModel;


public class AffichageMessages extends JPanel
{
	private YChat fnt;

	private DefaultListModel<Message> messList;
	private JList<Message> affMess;


	public AffichageMessages( YChat fnt )
	{
		this.fnt = fnt;

		this.messList = new DefaultListModel<Message>();

		this.affMess = new JList<Message>( this.messList );
		this.affMess.setDragEnabled( true );

		this.add( this.affMess );
	}


	public void updateMessage( Message msg )
	{
		this.messList.addElement( msg );
	}
}
