package models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;

import models.utils.LootUtil;
import play.db.ebean.Model;
import scala.reflect.generic.Trees.This;

@Entity
public class Perso extends Model{
	
	public static final Integer MAIN_CARAC_STR = 0;
	public static final Integer MAIN_CARAC_DEX = 1;
	public static final Integer MAIN_CARAC_INTEL = 2;
	
	public static final String BARBARE = "barbare";
	public static final String MOINE = "moine";
	public static final String SORCIER = "sorcier";
	public static final String FETICHEUR = "feticheur";
	public static final String CHASSEUR = "chasseur";
	
	public static final List<String> classes = Arrays.asList(BARBARE,CHASSEUR,FETICHEUR,MOINE,SORCIER);
	
	public static Finder<Long,Perso> find = new Finder(Long.class, Perso.class);

	@Id
	public Long id;
	public Integer lvl;
	public Integer str;
	public Integer dex;
	public Integer intel;
	public Integer vita;
	public Integer main_carac;
	public String classe;
	public BigDecimal resistBonus = new BigDecimal(0);
	public BigDecimal crit = BigDecimal.valueOf(0.05);
	public BigDecimal dmgCrit = BigDecimal.valueOf(0.5);
	public Set<Spell> spells;
	public List<Loot> loots;
	
	public Perso(String classe,Integer lvl){
		this.classe = classe;
		this.lvl = lvl;
		this.spells = new HashSet<Spell>();
		
		if(BARBARE.equals(classe)) {
			this.main_carac = MAIN_CARAC_STR;
			this.resistBonus = new BigDecimal(.3);
		}
		if(MOINE.equals(classe)){
			this.main_carac = MAIN_CARAC_DEX;
			this.resistBonus = new BigDecimal(.3);
		}
		if(CHASSEUR.equals(classe)){
			this.main_carac = MAIN_CARAC_DEX;
		}
		if(SORCIER.equals(classe)){
			this.main_carac = MAIN_CARAC_INTEL;	
		}
		if(FETICHEUR.equals(classe)){
			this.main_carac = MAIN_CARAC_INTEL;
		}
		initCarac();
	}
	
	/**
	 * Vita = 7 + 2*lvl
	 * Carac principale = 7 + 3*lvl
	 * Carac secondaire = 7 + 1*lvl
	 */
	public void initCarac(){
		vita = lvl*2 + 7;
		if(MAIN_CARAC_STR.equals(this.main_carac)) str = lvl*3 + 7;
		else str = lvl + 7;
		if(MAIN_CARAC_DEX.equals(this.main_carac)) dex = lvl*3 + 7;
		else dex = lvl + 7;
		if(MAIN_CARAC_INTEL.equals(this.main_carac)) intel = lvl*3 + 7;
		else intel = lvl + 7;
	}
	
	/**
	 * Force effective (avec loot)
	 */
	public Integer eStr(){
		return str + LootUtil.strBonus(loots);
	}
	
	/**
	 * Dex effective (avec loot)
	 */
	public Integer eDex(){
		return dex + LootUtil.dexBonus(loots);
	}
	
	/**
	 * Intel effective (avec loot)
	 */
	public Integer eIntel(){
		return intel + LootUtil.intelBonus(loots);
	}
		
	/**
	 * Vitalité effective (avec loot)
	 */
	public Integer eVita(){
		return vita + LootUtil.vitaBonus(loots);
	}
			
	/**
	 * Armor = force + loot
	 */
	public Integer armor(){
		return eStr() + LootUtil.armorBonus(loots);
	}
  	
	/**
	 * Bonus de dmg en fct de la caract principale
	 * 1pt = 10%
	 */
	public BigDecimal bonusDmg(){
		BigDecimal mainCarac = BigDecimal.valueOf(eStr());
		if(MAIN_CARAC_INTEL.equals(main_carac))
			mainCarac = BigDecimal.valueOf(eIntel());
		if(MAIN_CARAC_DEX.equals(main_carac))
			mainCarac = BigDecimal.valueOf(eDex());
		return mainCarac.multiply(BigDecimal.valueOf(.1));
	}
	
	public static List<Perso> all() {
		return find.all();
	}

	public static void create(Perso perso) {
		perso.save();
	}

	public static void delete(Long id) {
		find.ref(id).delete();
	}
	
}
