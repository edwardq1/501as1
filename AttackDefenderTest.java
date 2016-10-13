import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;

public class AttackDefenderTest {
	private AttackDefender ad = new AttackDefender();
	/*
	 * tests the probability of a high attack
	 * at 100%
	 */
	@Test
	public void testHighAttack() {
		ad = new AttackDefender();
		ad.probability(100, 0, 0);	
		assertEquals(ad.getAttack(), "High");
	}
	/*
	 * tests the probability of a low attack
	 * at 100%
	 */
	@Test
	public void testLowAttack(){
		ad.probability(0, 100, 0);	
		assertEquals(ad.getAttack(), "Low");
	}
	/*
	 * tests the probability of a medium attack
	 * at 100%
	 */
	@Test
	public void testMediumAttack(){
		ad.probability(0, 0, 100);
		assertEquals(ad.getAttack(), "Medium");
	}
	@Test
	public void testForZeroPercentageLowAttack(){
		ad.probability(50, 0, 50);
		assertNotEquals(ad.getAttack(), "Low");
	}
	
	@Test
	public void testForZerPercentageMediumAttack(){
		ad.probability(50, 50, 0);
		assertNotEquals(ad.getAttack(), "Medium");
	}
	
	@Test
	public void testForDefaultValues(){
		ad.check(50, 50, 40);
		assertEquals(ad.mediumchance(), 34);
		assertEquals(ad.lowchance(), 33);
		assertEquals(ad.highchance(), 33);
	}
}
