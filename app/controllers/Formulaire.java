package controllers;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import models.Loot;
import models.Perso;
import models.Spell;
import models.TestFormula;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.affichage;
import views.html.form;

public class Formulaire extends Controller {
	  
	final static Form<TestFormula> testform = form(TestFormula.class);
	
	public static Result initForm(){
		TestFormula testformula = new TestFormula();
		testformula.lvl=60;
		testformula.str=0;
		testformula.dex=0;
		testformula.intel=0;
		testformula.vita=0;
		testformula.bonusLife=BigDecimal.valueOf(0);
		testformula.armor=0;
		testformula.dmgMin=0;
		testformula.dmgMax=0;
		testformula.attSpd=BigDecimal.valueOf(1);
		testformula.attSpdBonus=BigDecimal.valueOf(0);
		testformula.resistBonus=0;
		return ok(form.render(testform.fill(testformula)));
	}
	
	public static Result submitForm(){
		TestFormula testformula = testform.bindFromRequest().get();
		Perso perso = new Perso(testformula.classe,testformula.lvl);
		Loot loot = new Loot();
		loot.armor = testformula.armor;
		loot.str = testformula.str;
		loot.dex = testformula.dex;
		loot.intel = testformula.intel;
		loot.vita = testformula.vita;
		loot.dmgMin = testformula.dmgMin;
		loot.dmgMax = testformula.dmgMax;
		loot.bonusLife = testformula.bonusLife;
		loot.attSpd = testformula.attSpd;
		loot.attSpdBonus = testformula.attSpdBonus;
		loot.description = "Truc de la chouette du test";
		perso.loots = Arrays.asList(loot);
		List<Spell> spells = Spell.all();
		perso.spells = new HashSet<Spell>();
		for(Spell spell:spells) perso.spells.add(spell);
		return ok(affichage.render(perso,perso.loots,perso.spells));
	}
	
}
