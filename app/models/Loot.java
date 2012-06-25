package models;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class Loot extends Model{
	
	public static final Integer ONE_H_WEAPON = 0;
	public static final Integer TWO_H_WEAPON = 1;
	public static final Integer BUCLKER = 2;
	public static final Integer OFF_HAND = 3;
	public static final Integer RING = 4;
	public static final Integer AMULET = 5;
	public static final Integer HELM = 6;
	public static final Integer TORSO = 7;
	public static final Integer WAIST = 8;
	public static final Integer LEGS =  9;
	public static final Integer BOOTS = 10;
	public static final Integer PAULDRONS = 11;
	public static final Integer WRISTS = 12;
	public static final Integer HANDS = 13;
		
	@Id
	public Long id;
	public String description;
	public Integer cat;
	public Integer str;
	public Integer dex;
	public Integer intel;
	public Integer vita;
	public BigDecimal bonusLife;
	public Integer armor;
	public Integer dmgMin;
	public Integer dmgMax;
	public BigDecimal attSpd;
	public BigDecimal attSpdBonus;
	public Integer resistPhys;
	public Integer resistAll;
	public Integer resistArcane;
	public Integer resistCold;
	public Integer resistFire;
	public Integer resistLightning;
	public Integer resistPoison;

	
	
	
	public static Finder<Long,Loot> find = new Finder(Long.class, Loot.class);
	
	public static List<Loot> all() {
		return find.all();
	}

	public static void create(Loot loot) {
		loot.save();
	}

	public static void delete(Long id) {
		find.ref(id).delete();
	}
	
}
