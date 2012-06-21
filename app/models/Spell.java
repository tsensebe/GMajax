package models;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;
import theorycraft.utils.Constants;

@Entity
public class Spell extends Model {
	
	@Id
	public Long id;
	public Integer cat;
	public Integer rune;
	public String description;
	public String toolKitLink;
	public BigDecimal dmgMultiplicator;
	public BigDecimal dotTime;
	
	public Spell(Integer cat, Integer rune, String description, 
			String toolKitLink, BigDecimal dmgMultiplicator,
			BigDecimal dotTime){
		super();
		this.cat = cat;
		this.rune = rune;
		this.description = description;
		this.toolKitLink = toolKitLink;
		this.dmgMultiplicator = dmgMultiplicator;
		this.dotTime = dotTime;
	}
	
	public Boolean isPassif(Spell spell){
		return Constants.SPELL_TYPE_PASSIF.equals(cat);
	}
	
	public Boolean isDot(){
		return Constants.SPELL_TYPE_DOT.equals(cat);
	}
	
	public Boolean isDirectDmg(){
		return Constants.SPELL_TYPE_DIRECT_DMG.equals(cat);
	}
	
	public static Finder<Long,Spell> find = new Finder(Long.class, Spell.class);
	
	public static List<Spell> all() {
		return find.all();
	}

	public static void create(Spell spell) {
		spell.save();
	}

	public static void delete(Long id) {
		find.ref(id).delete();
	}

}
