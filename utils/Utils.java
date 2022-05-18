package app.utils;

import app.entities.Administrateur;
import app.entities.Assistant_De_Programme;
import app.entities.Chef_De_Classe;
import app.entities.Comptable;
import app.entities.Enseignant;
import app.entities.Responsable_Pedagogique;
import app.entities.Roles;
import app.entities.User;


public class Utils {
	public static User getUserInstance(Roles role) {
		User user = null;
		switch(role) {
		case admin: user = new Administrateur(0, null, null);
		break;
		case assistant_de_programme: user = new Assistant_De_Programme(0, null, null); 
			break;
		case chef_de_classe: new Chef_De_Classe(0, null, null, 0);
			break;
		case comptable: user = new Comptable(0,null,null);
			break;
		case enseignant: user = new Enseignant(0, null, null, 0);
			break;
		case responsable_pedagogique: user = new Responsable_Pedagogique(0, null, null);
			break;
		default:
			break;
		}
		return user;
	}
}
