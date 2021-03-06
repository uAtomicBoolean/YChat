package message;

import java.io.Serializable;

/**
 * Object contenant les informations de l'auteur d'un message.
 */
public class Auteur implements Serializable
{
	private String nom;


	public Auteur( String nom )
	{
		this.nom = nom;
	}


	public String getNom() { return this.nom; }


	public String toString()
	{
		return this.nom;
	}
}
