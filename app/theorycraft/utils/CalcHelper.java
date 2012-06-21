package theorycraft.utils;

import java.math.BigDecimal;

public class CalcHelper {

	/**
	 * Dommage moyen à partir du dommage min et du dommage max
	 * @param dmgMin dommage minimum
	 * @param dmgMax dommage maximum
	 * @return dommage moyen
	 */
	public static BigDecimal avgDmg(Integer dmgMin, Integer dmgMax){
		return (BigDecimal.valueOf(dmgMin).add(BigDecimal.valueOf(dmgMax)))
		.multiply(BigDecimal.valueOf(.5));
	}
	
}
