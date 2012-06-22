package theorycraft;

import java.math.BigDecimal;
import java.math.MathContext;

import theorycraft.utils.CalcHelper;
import theorycraft.utils.Constants;

public class Formula {


	
	private static final BigDecimal ONE_H_WEAPON_MOD = BigDecimal.valueOf(1);
	private static final BigDecimal TWO_H_WEAPON_MOD = BigDecimal.valueOf(1.2);
	private static final BigDecimal DUAL_WEAPON_MOD = BigDecimal.valueOf(1.15);
	
	/**
	 * Calcule l'esquive en fonction de la dextérité</br>
	 * Dex range ->	% esquive par point </br>
	 * 1-100	 -> 0.100 </br>
	 * 101-500   ->	0.025 </br>
	 * 501-1000  ->	0.020 </br>
	 * 1001-???? ->	0.010 </br>
	 * 
	 * @param dex la dextérité du perso
	 */
	public static BigDecimal dodge(Integer dex){
		if(dex == null) return BigDecimal.valueOf(0);
		if(dex <= 100)
			return BigDecimal.valueOf(dex).divide(BigDecimal.valueOf(1000));
		if(dex <= 500)
			return BigDecimal.valueOf(.1).add(
					(BigDecimal.valueOf(dex).subtract(BigDecimal.valueOf(100))).divide(BigDecimal.valueOf(4000)));
		if(dex <= 1000)
			return BigDecimal.valueOf(.2).add(
					(BigDecimal.valueOf(dex).subtract(BigDecimal.valueOf(500))).divide(BigDecimal.valueOf(5000)));
		return BigDecimal.valueOf(.3).add(
				(BigDecimal.valueOf(dex).subtract(BigDecimal.valueOf(1000))).divide(BigDecimal.valueOf(100000)));
	}
	
	/**
	 * Calcule les points de vie : </br>
	 * lvl 01 à 34 -> (36 + lvl*4 + vita*10)* (1+bonusLife) </br>
	 * lvl 35 à 60 -> (36 + lvl*4 + vita*(lvl-25)) * (1+bonusLife) </br>
	 * 
	 * @param lvl le niveau du perso
	 * @param vita la vitalité du perso
	 * @param bonusLife les bonus de vie du perso (+x% de vie)
	 */
	public static BigDecimal hp(Integer lvl, Integer vita, BigDecimal bonusLife){
		Integer baseHp;
		if(lvl < 35) 
			baseHp = 36 + lvl*4 + vita*10;
		else
			baseHp = 36 + lvl*4 + vita*(lvl-25);
		return BigDecimal.valueOf(baseHp).multiply(BigDecimal.valueOf(1).add(bonusLife));
	}
	
	/**
	 * Natural resist + loot bonus
	 * 1 intel = 0.1 natural resist
	 */
	public static BigDecimal resist(Integer intel, Integer lootBonus){
		return BigDecimal.valueOf(intel).multiply(BigDecimal.valueOf(.1)).add(BigDecimal.valueOf(lootBonus));
	}
	
	/**
	 * Reduction dmg par l'armure = Armor / (50 × Monster Level + Armor)
	 */
	public static BigDecimal dmgReducArmor(Integer lvlMonster, Integer armor) {
		BigDecimal bdArmor = BigDecimal.valueOf(armor);
		return bdArmor.divide(
				BigDecimal.valueOf(lvlMonster).multiply(BigDecimal.valueOf(50))
						.add(bdArmor), MathContext.DECIMAL32);
	}

	/**
	 * Dmg reduction resistance = resist / (5 × lvlMonster + resist)
	 * @param lvlMonster
	 * @param resist
	 * @return
	 **/
	public static BigDecimal  dmgReducResit(Integer lvlMonster, BigDecimal resist){
		return resist.divide(
				BigDecimal.valueOf(lvlMonster).multiply(BigDecimal.valueOf(5))
				.add(resist), MathContext.DECIMAL32);
	}
	
	
	/**
	 * 
	 * EHP = HP / (1-DR) = HP / [(1 - DRa) × (1 - DRr) × (1 -DRo)]
	 *		DR : Damage Reduction totale
	 *		DR = 1 - [(1 - DRa) × (1 - DRr) × (1 -DRo)]
	 *		DRa : Damage Reduction from armor 
	 *		DRr : Damage Reduction from resistances
	 *		DRo : Damage Reduction from other sources
	 *		DRa = Armor / (Armor + (50 x mLvl))
	 *		DRr = Resistance / (Resistance + (5 x mLvl))
	 * 
	 * */
	public static BigDecimal eHP(BigDecimal hp, BigDecimal dra, BigDecimal drr, BigDecimal dro){
		BigDecimal calcInter= (BigDecimal.valueOf(1).subtract(dra))
								.multiply(BigDecimal.valueOf(1).subtract(drr))
								.multiply(BigDecimal.valueOf(1).subtract(dro));
		return hp.divide(calcInter, MathContext.DECIMAL32);
	}
	
	
	
	/**
	 * Calcule le dps </br>
	 * 
	 * @param dmgMin
	 *            dommage minimum
	 * @param dmgMax
	 *            dommage maximun
	 * @param attSpd
	 *            vitesse attaque
	 * @param bonusDmg
	 *            bonus de dommage de la carac principale
	 * @param typeArme
	 *            1 main, 2 mains, dual
	 * 
	 * @return dps = ((dmgMin+dmgMax)/2 )*attSpd*bonusDmg*weaponModifier
	 * 
	 * @TODO prendre en compte les critiques </br>
	 * 
	 */
	public static BigDecimal dps(Integer dmgMin, Integer dmgMax,
			BigDecimal attSpd, BigDecimal bonusDmg, Integer typeArme,
			BigDecimal pCric, BigDecimal dmgCric) {

		BigDecimal weaponMod = ONE_H_WEAPON_MOD;
		if (Constants.TWO_H_WEAPON.equals(typeArme))
			weaponMod = TWO_H_WEAPON_MOD;
		if (Constants.DUAL_WEAPON.equals(typeArme))
			weaponMod = DUAL_WEAPON_MOD;

		return CalcHelper.avgDmg(dmgMin, dmgMax).multiply(attSpd)
				.multiply(weaponMod)
				.multiply(bonusDmg)
				.multiply((dmgCric.multiply(pCric)).add(BigDecimal.valueOf(1)));
	}
	
}
