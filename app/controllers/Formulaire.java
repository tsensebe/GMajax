package controllers;

import models.Perso;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.affichage;
import views.html.form;

public class Formulaire extends Controller {
	  
	final static Form<Perso> persoForm = form(Perso.class);
	
	public static Result initForm(){
		Perso perso = new Perso(Perso.SORCIER,60);
		return ok(form.render(persoForm.fill(perso)));
	}
	
	public static Result submitForm(){
		Perso perso = persoForm.bindFromRequest().get();
		perso.initCarac();
		return ok(affichage.render(perso,perso.loots,perso.spells));
	}
	
}
