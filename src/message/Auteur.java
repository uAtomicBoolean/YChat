package message;

import utils.Ansii;

import java.io.Serializable;

/**
 * Object contenant les informations de l'auteur d'un message.
 */
public class Auteur implements Serializable
{
	private String nom;
	private String couleur; // La couleur est une chaine de caractères ANSII.


	public Auteur( String nom, String couleur )
	{
		this.nom = nom;
		this.couleur = couleur;
	}


	public String getNom() { return this.nom; }
	public String getCouleur() { return this.couleur; }


	public String toString()
	{
		return this.couleur + this.nom + Ansii.RESET;
	}
}
