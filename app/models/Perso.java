package models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import models.utils.LootUtil;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

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
	
	@Id
	public Long id;
	@Required
	public String classe;
	@Required
	public String nom;
	@Required
	public Integer lvl;
	
	public Integer str;
	public Integer dex;
	public Integer intel;
	public Integer vita;
	public Integer main_carac;
	public BigDecimal resistBonus;

	@OneToMany
	public List<Loot> loots;
	
	public Perso(){
		super();
	}
	
	public Perso(String classe,Integer lvl, Loot...loots ){
		this.classe = classe;
		this.lvl = lvl;
		this.loots = new ArrayList<Loot>();
		for(Loot loot : loots) this.loots.add(loot);
		initCarac();
	}
	
	/**
	 * Vita = 7 + 2*lvl
	 * Carac principale = 7 + 3*lvl
	 * Carac secondaire = 7 + 1*lvl
	 */
	public void initCarac(){
		this.resistBonus = BigDecimal.ZERO;
		this.vita = this.lvl*2 + 7;

		if(BARBARE.equals(classe)) {
			this.main_carac = MAIN_CARAC_STR;
			this.resistBonus = new BigDecimal(.3);
		}
		if(MOINE.equals(classe)){
			this.main_carac = MAIN_CARAC_DEX;
			this.resistBonus = new BigDecimal(.3);
		}
		
		if(CHASSEUR.equals(classe) )
			this.main_carac = MAIN_CARAC_DEX;
		
		if(SORCIER.equals(classe) || FETICHEUR.equals(classe))
			this.main_carac = MAIN_CARAC_INTEL;

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
	
	/**
	 * % chance de critique de base
	 */
	public BigDecimal critBase(){
		return BigDecimal.valueOf(0.05);
	}
	
	/**
	 * % bonus de dmg en cas de critique de base
	 */
	public BigDecimal dmgCritBase(){
		return BigDecimal.valueOf(0.5);
	}
	
	public static Finder<Long,Perso> find = new Finder(Long.class, Perso.class);
	public static List<Perso> all() {return find.all();}
	public static void create(Perso perso) {perso.save();}
	public static void delete(Long id) {find.ref(id).delete();}
	
}
