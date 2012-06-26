package controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import models.Loot;
import models.Perso;
import models.Spell;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.affichage;
import views.html.form;
import views.html.index;
import views.html.showAll;

public class Application extends Controller {

	public final static Form<Perso> persoForm = form(Perso.class);
	public final static Form<Loot> lootForm = form(Loot.class);
	
	public static Result index() {	
		return ok(index.render(Perso.all()));
	}

	public static Result show(Long id) {
		Perso perso = Perso.find.byId(id);
		return ok(affichage.render(perso,perso.loots,null));
	}
	
	public static Result showAll(){
		return ok(showAll.render(Perso.all(),Loot.all(),Spell.all()));	
	}
	
	public static Result init(){
		Perso perso = new Perso();
		perso.lvl = 60;
		perso.loots = new ArrayList<Loot>();
		Loot loot = new Loot("Truc de la chouette");
		perso.loots.add(loot);
		return ok(form.render(persoForm.fill(perso)));
	}
	
	public static Result submitForm(){
		Perso perso = persoForm.bindFromRequest().get();
		perso.initCarac();
		perso.save();
		perso.loots.get(0).perso=perso;
		perso.loots.get(0).save();
	    return redirect((controllers.routes.Application.show(perso.id)));
	}
	
	public static Result fork(Long id){
		Perso perso = Perso.find.byId(id);
		return ok(form.render(persoForm.fill(perso)));
	}
	
	public static Result delete(Long id){
		Perso perso = Perso.find.byId(id);
		if(perso != null){
			for(Loot loot:perso.loots) loot.delete();
			perso.delete();
		}
		return redirect((controllers.routes.Application.index()));
	}

}