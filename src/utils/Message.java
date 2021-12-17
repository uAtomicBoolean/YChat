package utils;

import client.Client;

import java.io.Serializable;


public class Message implements Serializable
{
	private Client auteur;
	private String msgContent;


	public Message( Client auteur, String msgContent )
	{
		this.auteur = auteur;
		this.msgContent = msgContent;
	}


	public Client getAuteur() { return this.auteur; }
	public String getContent() { return this.msgContent; }


	public String toString()
	{
		String res = this.auteur != null ? (Ansii.CYAN_FG + this.auteur + " : " + Ansii.RESET) : "";
		return res + Ansii.RESET + this.msgContent;
	}
}
