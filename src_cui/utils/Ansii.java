package utils;


/**
 * Juste une classe contenant des codes ANSII pour l'affichage dans la console.
 */
public class Ansii 
{
	public static final String RESET = "\033[0m";
	public static final String BOLD = "\033[1m";
	public static final String FAINT = "\033[2m";
	public static final String ITALIC = "\033[3m";
	public static final String UNDERLINE = "\033[4m";
	public static final String BLINK = "\033[5m";
	public static final String CROSSED_OUT = "\033[9m";
	public static final String DOUBLE_UNDERLINE = "\033[21m";
	public static final String BLACK_FG = "\033[30m";
	public static final String BLACK_BG = "\033[40m";
	public static final String RED_FG = "\033[31m";
	public static final String RED_BG = "\033[41m";
	public static final String GREEN_FG = "\033[32m";
	public static final String GREEN_BG = "\033[42m";
	public static final String YELLOW_FG = "\033[33m";
	public static final String YELLOW_BG = "\033[43m";
	public static final String BLUE_FG = "\033[34m";
	public static final String BLUE_BG = "\033[44m";
	public static final String MAGENTA_FG = "\033[35m";
	public static final String MAGENTA_BG = "\033[45m";
	public static final String CYAN_FG = "\033[36m";
	public static final String CYAN_BG = "\033[46m";
	public static final String WHITE_FG = "\033[37m";
	public static final String WHITE_BG = "\033[47m";
	public static final String GRAY_FG = "\033[90m";
	public static final String GRAY_BG = "\033[100m";
	public static final String BRIGHT_RED_FG = "\033[91m";
	public static final String BRIGHT_RED_BG = "\033[101m";
	public static final String BRIGHT_GREEN_FG = "\033[92m";
	public static final String BRIGHT_GREEN_BG = "\033[102m";
	public static final String BRIGHT_YELLOW_FG = "\033[93m";
	public static final String BRIGHT_YELLOW_BG = "\033[103m";
	public static final String BRIGHT_BLUE_FG = "\033[94m";
	public static final String BRIGHT_BLUE_BG = "\033[104m";
	public static final String BRIGHT_MAGENTA_FG = "\033[95m";
	public static final String BRIGHT_MAGENTA_BG = "\033[105m";
	public static final String BRIGHT_CYAN_FG = "\033[96m";
	public static final String BRIGHT_CYAN_BG = "\033[106m";
	public static final String BRIGHT_WHITE_FG = "\033[97m";
	public static final String BRIGHT_WHITE_BG = "\033[107m";


	/**
	 * Renvoie une chaine de caractère contenant la couleur affilié à la chaine passée en paramètre.
	 * Si la couleur demandée n'existe pas, alors 'null' est renvoyé.
	 * @param couleur Une chaine de caractère indiquant la couleur voulue.
	 * @return Le code ANSII de la couleur.
	 */
	public static String getColor( String couleur )
	{
		switch ( couleur.toLowerCase() )
		{
			case "noire": return BLACK_FG;
			case "rouge": return RED_FG;
			case "vert": return GREEN_FG;
			case "jaune": return YELLOW_FG;
			case "bleu": return BLUE_FG;
			case "magenta": return MAGENTA_FG;
			case "cyan": return CYAN_FG;
			case "blanc": return WHITE_FG;
			case "gris": return GRAY_FG;
		}
		return null;
	}
}
