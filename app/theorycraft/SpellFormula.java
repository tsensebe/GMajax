package theorycraft;

import java.math.BigDecimal;

import theorycraft.utils.CalcHelper;

public class SpellFormula {

	/**
	 * Dmg par tir pour les sorts à dommages directs
	 * @param dmgSpellBonus
	 * @param dmgMin
	 * @param dmgMax
	 * @param dmgBonusCarac
	 * @return
	 */
	public static BigDecimal directDmgShotDmg(BigDecimal dmgSpellBonus,
			Integer dmgMin, Integer dmgMax, BigDecimal dmgBonusCarac) {
		return CalcHelper.avgDmg(dmgMin,dmgMax).multiply(dmgSpellBonus).multiply(dmgBonusCarac);

	}
	
	/**
	 * DPS (dmg par secondes) pour les sorts à dommages directs
	 * @param dmgSpellBonus
	 * @param dmgMin
	 * @param dmgMax
	 * @param attSpd
	 * @param dmgBonusCarac
	 * @return
	 */
	public static BigDecimal directDmgDps(BigDecimal dmgSpellBonus,
			Integer dmgMin, Integer dmgMax, BigDecimal attSpd, BigDecimal dmgBonusCarac) {
		return directDmgShotDmg(dmgSpellBonus,dmgMin,dmgMax,dmgBonusCarac).multiply(attSpd);
	}
	
	/**
	 * DPS (Dmg par secondes) Pour les sorts de channeling (desintegrate, etc ...)
	 * 
	 * @param dmgSpellBonus
	 * @param dmgMin
	 * @param dmgMax
	 * @param attSpd
	 * @param dmgBonusCarac
	 * @return
	 */
	public static BigDecimal channelingDps(BigDecimal dmgSpellBonus, Integer dmgMin, Integer dmgMax,
			BigDecimal attSpd, BigDecimal dmgBonusCarac) {
		return CalcHelper.avgDmg(dmgMin,dmgMax).multiply(attSpd).multiply(dmgSpellBonus).multiply(dmgBonusCarac);
	}

	/**
	 * CPS (Cout par secondes) Pour les sorts de channeling (desintegrate, etc ...)
	 * @param cout
	 * @param attSpd
	 * @return
	 */
	public static BigDecimal channelingCps(Integer cout, BigDecimal attSpd) {
		return attSpd.multiply(BigDecimal.valueOf(cout));
	}
	
	/**
	 * DPS (Dmg par secondes) Pour les sorts de type DOT
	 * @param dmgSpellBonus
	 * @param dmgMin
	 * @param dmgMax
	 * @param dmgBonusCarac
	 * @return
	 */
	public static BigDecimal dotDps(BigDecimal dmgSpellBonus,
			Integer dmgMin, Integer dmgMax, BigDecimal dmgBonusCarac){
		return CalcHelper.avgDmg(dmgMin,dmgMax).multiply(dmgSpellBonus).multiply(dmgBonusCarac);
	}
	
	/**
	 * Dmg Pour les sorts de type DOT (par cast)
	 * 
	 * @param dmgSpellBonus
	 * @param dmgMin
	 * @param dmgMax
	 * @param dmgBonusCarac
	 * @param duration
	 * @return
	 */
	public static BigDecimal dotDmg(BigDecimal dmgSpellBonus,
			Integer dmgMin, Integer dmgMax, BigDecimal dmgBonusCarac, BigDecimal duration){
		return dotDps(dmgSpellBonus,dmgMin,dmgMax,dmgBonusCarac).multiply(duration);
	}
	
	/**
	 * 
	 * @param dmgSpellBonus
	 * @param dmgMin
	 * @param dmgMax
	 * @param attSpd
	 * @param dmgBonusCarac
	 * @return
	 */
	public static BigDecimal cooldownDps(BigDecimal dmgSpellBonus,
			Integer dmgMin, Integer dmgMax,BigDecimal attSpd, BigDecimal dmgBonusCarac){
		return CalcHelper.avgDmg(dmgMin,dmgMax).multiply(attSpd).multiply(dmgSpellBonus).multiply(dmgBonusCarac);
	}
	
	/**
	 * 
	 * @param dmgSpellBonus
	 * @param dmgMin
	 * @param dmgMax
	 * @param attSpd
	 * @param dmgBonusCarac
	 * @param duration
	 * @return
	 */
	public static BigDecimal cooldownDmg(BigDecimal dmgSpellBonus,
			Integer dmgMin, Integer dmgMax,BigDecimal attSpd, BigDecimal dmgBonusCarac, BigDecimal duration){
		return cooldownDps(dmgSpellBonus,dmgMin,dmgMax,attSpd,dmgBonusCarac).multiply(duration);
	}
	
}
