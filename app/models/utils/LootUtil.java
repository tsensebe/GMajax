package models.utils;

import java.math.BigDecimal;
import java.util.List;

import models.Loot;

public class LootUtil {
	
	public static Boolean isOneHand(List<Loot> loots){
		if(loots == null) return false;
		Integer count = 0;
		for(Loot loot : loots)
			if(Loot.ONE_H_WEAPON.equals(loot.cat)) count++;
		return count == 1;
	}
	
	public static Boolean isTwoHand(List<Loot> loots){
		if(loots == null) return false;
		for(Loot loot : loots)
			if(Loot.TWO_H_WEAPON.equals(loot.cat)) return true;
		return false;
	}

	public static Boolean isDual(List<Loot> loots){
		if(loots == null) return false;
		Integer count = 0;
		for(Loot loot : loots)
			if(Loot.ONE_H_WEAPON.equals(loot.cat)) count++;
		return count > 1;
	}
	
	public static Integer strBonus(List<Loot> loots){
		if(loots == null) return 0;
		Integer str = 0;
		for(Loot loot: loots)
			if(loot.str != null) str += loot.str;
		return str;
	}
	
	public static Integer dexBonus(List<Loot> loots){
		if(loots == null) return 0;
		Integer dex = 0;
		for(Loot loot: loots)
			if(loot.dex != null) dex += loot.dex;
		return dex;
	}
	
	public static Integer intelBonus(List<Loot> loots){
		if(loots == null) return 0;
		Integer intel = 0;
		for(Loot loot: loots)
			if(loot.intel != null) intel += loot.intel;
		return intel;
	}
	
	public static Integer vitaBonus(List<Loot> loots){
		if(loots == null) return 0;
		Integer vita = 0;
		for(Loot loot: loots)
			if(loot.vita != null) vita += loot.vita;
		return vita;
	}
	
	/**
	 * % bonus de vie par le loot 
	 */
	public static BigDecimal lifeBonus(List<Loot> loots){
		if(loots == null) return BigDecimal.valueOf(0);
		BigDecimal bonus = BigDecimal.valueOf(0);
		for(Loot loot: loots){
			if(loot.bonusLife != null) bonus = bonus.add(loot.bonusLife);
		}
		return bonus;
	}
	
	public static Integer resistBonusPhys(List<Loot> loots){
		if(loots == null) return 0;
		Integer bonus = 0;
		for(Loot loot: loots){
			if(loot.resistPhys != null) bonus += loot.resistPhys;
			if(loot.resistAll != null) bonus += loot.resistAll;	
		}
		return bonus;
	}
	
	public static Integer resistBonusArcane(List<Loot> loots){
		if(loots == null) return 0;
		Integer bonus = 0;
		for(Loot loot: loots){
			if(loot.resistArcane != null) bonus += loot.resistArcane;
			if(loot.resistAll != null) bonus += loot.resistAll;	
		}
		return bonus;
	}
	
	public static Integer resistBonusCold(List<Loot> loots){
		if(loots == null) return 0;
		Integer bonus = 0;
		for(Loot loot: loots){
			if(loot.resistCold != null) bonus += loot.resistCold;
			if(loot.resistAll != null) bonus += loot.resistAll;	
		}
		return bonus;
	}
	
	public static Integer resistBonusFire(List<Loot> loots){
		if(loots == null) return 0;
		Integer bonus = 0;
		for(Loot loot: loots){
			if(loot.resistFire != null) bonus += loot.resistFire;
			if(loot.resistAll != null) bonus += loot.resistAll;	
		}
		return bonus;
	}
	
	public static Integer resistBonusLightning(List<Loot> loots){
		if(loots == null) return 0;
		Integer bonus = 0;
		for(Loot loot: loots){
			if(loot.resistLightning != null) bonus += loot.resistLightning;
			if(loot.resistAll != null) bonus += loot.resistAll;	
		}
		return bonus;
	}
	
	public static Integer resistBonusPoison(List<Loot> loots){
		if(loots == null) return 0;
		Integer bonus = 0;
		for(Loot loot: loots){
			if(loot.resistPoison != null) bonus += loot.resistPoison;
			if(loot.resistAll != null) bonus += loot.resistAll;	
		}
		return bonus;
	}

	public static Integer dmgMin(List<Loot> loots){
		if(loots == null) return 0;
		Integer dmg = 0;
		for(Loot loot: loots)
			if(loot.dmgMin != null) dmg += loot.dmgMin;
		if(dmg==0) return 1;
		return dmg;
	}
	
	public static Integer dmgMax(List<Loot> loots){
		if(loots == null) return 0;
		Integer dmg = 0;
		for(Loot loot: loots)
			if(loot.dmgMax != null) dmg += loot.dmgMax;
		if(dmg==0) return 1;
		return dmg;
	}
	
	/**
	 * attSpd de l'arme * (1 + attSpdBonus)
	 */
	public static BigDecimal attSpd(List<Loot> loots){
		if(loots == null) return BigDecimal.valueOf(0);
		BigDecimal attSpd = BigDecimal.valueOf(1);
		BigDecimal attSpdBonus = BigDecimal.valueOf(1);
		for(Loot loot: loots){
			if(loot.attSpd != null) attSpd = loot.attSpd;
			if(loot.attSpdBonus != null) attSpdBonus = attSpdBonus.add(loot.attSpdBonus);
		}
		return attSpd.multiply(attSpdBonus);
	}
	
	public static Integer armorBonus(List<Loot> loots){
		if(loots == null) return 0;
		Integer armor = 0;
		for(Loot loot:loots){
			if(loot.armor != null) armor += loot.armor;
		}
		return armor;
	}
}
