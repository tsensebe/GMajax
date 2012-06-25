package controllers;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import models.Loot;
import models.Perso;
import models.Spell;
import models.TestFormula;
import models.utils.LootUtil;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.affichage;
import views.html.form;

public class Formulaire extends Controller {
	  
	final static Form<TestFormula> testform = form(TestFormula.class);
	
	public static Result edit(Perso perso){
		TestFormula testformula = new TestFormula();
		testformula.lvl=perso.lvl;
		testformula.str=LootUtil.strBonus(perso.loots);
		testformula.dex=LootUtil.dexBonus(perso.loots);
		testformula.intel=LootUtil.intelBonus(perso.loots);
		testformula.vita=LootUtil.vitaBonus(perso.loots);
		testformula.bonusLife=LootUtil.lifeBonus(perso.loots);
		testformula.armor=LootUtil.armorBonus(perso.loots);
		testformula.dmgMin=LootUtil.dmgMin(perso.loots);
		testformula.dmgMax=LootUtil.dmgMax(perso.loots);
		testformula.attSpd=BigDecimal.ONE;
		testformula.attSpdBonus=LootUtil.attSpd(perso.loots);
		testformula.resistAll = LootUtil.resistBonusAll(perso.loots);
		testformula.resistPhys = LootUtil.resistBonusPhys(perso.loots);
		testformula.resistArcane = LootUtil.resistBonusArcane(perso.loots);
		testformula.resistCold = LootUtil.resistBonusCold(perso.loots);
		testformula.resistFire = LootUtil.resistBonusFire(perso.loots);
		testformula.resistLightning = LootUtil.resistBonusLightning(perso.loots);
		testformula.resistPoison = LootUtil.resistBonusPoison(perso.loots);
		
		return ok(form.render(testform.fill(testformula)));
	}
	
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
		testformula.resistPhys=0;
		testformula.resistAll=0;
		testformula.resistArcane=0;
		testformula.resistCold=0;
		testformula.resistFire=0;
		testformula.resistLightning=0;
		testformula.resistPoison=0;
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
		loot.resistPhys =testformula.resistPhys;
		loot.resistAll =testformula.resistAll;
		loot.resistArcane =testformula.resistArcane;
		loot.resistCold =testformula.resistCold;
		loot.resistFire =testformula.resistFire;
		loot.resistLightning =testformula.resistLightning;
		loot.resistPoison =testformula.resistPoison;
		loot.description = "Truc de la chouette du test";
		perso.loots = Arrays.asList(loot);
		List<Spell> spells = Spell.all();
		perso.spells = new HashSet<Spell>();
		for(Spell spell:spells) perso.spells.add(spell);
		loot.save();
	    perso.save();
	    return redirect((controllers.routes.Application.affichagerId(perso.id)));
	}
	
}
