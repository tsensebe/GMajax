package controllers;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import models.Loot;
import models.Perso;
import models.Spell;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.affichage;
import views.html.index;
import views.html.showAll;

public class Application extends Controller {

	public static Result index() {
		return ok(index.render("Index.html"));
	}

	public static Result affichagerPerso(String classe,Integer lvl) {
		Perso perso = new Perso(classe,lvl);
		
		Loot sword = new Loot();
		sword.description = "Sword of gros machin";
		sword.cat = Loot.ONE_H_WEAPON;
		sword.dmgMin = 30;
		sword.dmgMax = 50;
		sword.attSpd = BigDecimal.valueOf(1.2);
		sword.intel = 15;
		sword.vita = 22;
		sword.bonusLife = BigDecimal.valueOf(.1);

		perso.loots = Arrays.asList(sword);
		
		return ok(affichage.render(perso,perso.loots,perso.spells));
	}

	public static Result affichagerId(Long id) {
		Perso perso = Perso.find.byId(id);
		perso.loots = Loot.all();
		List<Spell> spells =Spell.all();
		perso.spells = new HashSet<Spell>();
		for(Spell spell:spells) perso.spells.add(spell);
		return ok(affichage.render(perso,perso.loots,perso.spells));
	}
	
	public static Result showAll(){
		return ok(showAll.render(Perso.all(),Loot.all(),Spell.all()));	
	}
	
	public static Result test(){
//		Loot loot1 = new Loot();
//		loot1.description = "Amulette bidule";
//		loot1.cat = Loot.AMULET;
//		loot1.intel = 90;
//		loot1.vita = 100;
//		loot1.resistBonus = 33;
//		loot1.save();
//		
//		Loot loot2 = new Loot();
//		loot2.description = "Ring du truc";
//		loot2.cat = Loot.AMULET;
//		loot2.intel = 30;
//		loot2.dex = 50;
//		loot2.save();
//		
//		Loot loot3 = new Loot();
//		loot3.description = "Armure de la cartona";
//		loot3.cat = Loot.TORSO;
//		loot3.armor = 400;
//		loot3.str = 66;
//		loot3.save();
		
		
//		Spell spell0 = new Spell(2,0,"Magic Missile","http://eu.battle.net/d3/fr/class/wizard/active/magic-missile",BigDecimal.valueOf(1.1),null);
//		Spell spell1 = new Spell(2,1,"Magic Missile - Charged Blast","http://eu.battle.net/d3/fr/class/wizard/active/magic-missile?runeType=a",BigDecimal.valueOf(1.43),null);
//		Spell spell2 = new Spell(2,2,"Magic Missile - Split Tire","http://eu.battle.net/d3/fr/class/wizard/active/magic-missile?runeType=b",BigDecimal.valueOf(.5),null);
//		Spell spell3 = new Spell(2,3,"Magic Missile - Penetrating Blast","http://eu.battle.net/d3/fr/class/wizard/active/magic-missile?runeType=c",BigDecimal.valueOf(1.1),null);
//		Spell spell4= new Spell(2,4,"Magic Missile - Attunement","http://eu.battle.net/d3/fr/class/wizard/active/magic-missile?runeType=d",BigDecimal.valueOf(1.1),null);
//		Spell spell5 = new Spell(2,5,"Magic Missile - Seeker","http://eu.battle.net/d3/fr/class/wizard/active/magic-missile?runeType=e",BigDecimal.valueOf(1.21),null);
//		spell0.save();
//		spell1.save();
//		spell2.save();
//		spell3.save();
//		spell4.save();
//		spell5.save();
		
		return ok("toto");
	}

}