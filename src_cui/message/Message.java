package message;

import utils.Ansii;

import java.io.Serializable;


/**
 * Classe contenant les informations d'un envoyé par un client. 
 */
public class Message implements Serializable
{
	private Auteur auteur;
	private String msgContent;


	public Message( Auteur auteur, String msgContent )
	{
		this.auteur = auteur;
		this.msgContent = msgContent;
	}

	public Message( String msgContent ) { this( null, msgContent ); }

	
	/* ------------------------------------------------------ */
	/* METHODES                                               */
	/* ------------------------------------------------------ */
	public Auteur getAuteur() { return this.auteur; }
	public String getContent() { return this.msgContent; }


	/**
	 * Permet de définir l'auteur du message. Si l'auteur n'est pas définit, 
	 * alors sa valeur restera null.
	 * @param pseudo Le pseudo de l'auteur.
	 * @param couleur La couleur de l'auteur pour l'affichage dans la console (code ANSII).
	 */
	public void setAuteur( String pseudo, String couleur )
	{
		this.auteur = new Auteur( pseudo, couleur );
	}


	public String toString()
	{
		String res = this.auteur != null ? (this.auteur + " : ") : "";
		return res + Ansii.RESET + this.msgContent;
	}
}
