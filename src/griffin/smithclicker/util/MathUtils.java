package griffin.smithclicker.util;

import java.math.BigInteger;

public class MathUtils {
	
	/**
	 * 
	 * @param val
	 * @param percent such as 75 = .75 or 75%
	 * @return val + % of val
	 */
	public static BigInteger addPercentGained(BigInteger val, BigInteger percent) {
		return val.multiply(percent).divide(new BigInteger("100")).add(val);
	}
	
}
