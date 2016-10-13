import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;

public class TestCases {
	private Defender defender = new Defender();
	private Attacker attacker = new Attacker();
	private Manager manager = new Manager();
	
	@Test
	public void testHighAttack() {
		// testing before the generate attack method if equals to 0
		assertEquals(attacker.getHighAttacks(), 0);
		attacker.generateAttack(100, 0, 0);
		assertEquals(attacker.getAttack(), "High");
		// testing if the number of high attacks are incrementing
		assertEquals(attacker.getHighAttacks(), 1);
	}
	
	@Test
	public void testLowAttack(){
		// testing before the generate attack method if equals to 0
		assertEquals(attacker.getLowAttacks(), 0);
		attacker.generateAttack(0, 100, 0);
		assertEquals(attacker.getAttack(), "Low");
		// testing if the number of low attacks are incrementing
		assertEquals(attacker.getLowAttacks(), 1);
	}
	
	@Test
	public void testMediumAttack(){
		// testing before the generate attack method if equals to 0
		assertEquals(attacker.getMediumAttacks(), 0);
		attacker.generateAttack(0, 0, 100);
		assertEquals(attacker.getAttack(), "Medium");
		// testing if the number of medium attacksa re incrementing
		assertEquals(attacker.getMediumAttacks(), 1);
	}
	
	@Test
	public void testMultipleHighAttacks(){
		/* testing if number of high attacks 
	 	   are incrementing even if there are
		   0% chance of it generating a high
		   attack.
		 */
		attacker.generateAttack(100, 0, 0);
		attacker.generateAttack(0, 100, 0);
		attacker.generateAttack(100, 0, 0);
		attacker.generateAttack(0, 75, 25);
		attacker.generateAttack(0, 25, 75);
		attacker.generateAttack(0, 50, 50);
		attacker.generateAttack(0, 55, 45);
		attacker.generateAttack(100, 0, 0);
		attacker.generateAttack(100, 0, 0);
		assertEquals(attacker.getHighAttacks(), 4);
	}
	
	@Test
	public void testMultipleMediumAttacks(){
		/* testing if number of medium attacks 
	 	   are incrementing even if there are
		   0% chance of it generating a medium
		   attack. 
		 */
		attacker.generateAttack(100, 0, 0);
		attacker.generateAttack(0, 0, 100);
		attacker.generateAttack(50, 50, 0);
		attacker.generateAttack(0, 0, 100);
		attacker.generateAttack(95, 5, 0);
		attacker.generateAttack(0, 0, 100);
		attacker.generateAttack(99, 1, 0);
		attacker.generateAttack(0, 0, 100);
		attacker.generateAttack(0, 0, 100);
		assertEquals(attacker.getMediumAttacks(), 5);
	}
	
	@Test
	public void testMultipleLowAttacks(){
		/* testing if number of low attacks 
	 	   are incrementing even if there are
		   0% chance of it generating a low
		   attack. 
		 */
		attacker.generateAttack(100, 0, 0);
		attacker.generateAttack(0, 100, 0);
		attacker.generateAttack(0, 100, 0);
		attacker.generateAttack(0, 0, 100);
		attacker.generateAttack(99, 0, 1);
		attacker.generateAttack(0,100, 0);
		attacker.generateAttack(0, 100, 0);
		attacker.generateAttack(25, 0, 75);
		attacker.generateAttack(75, 0, 25);
		assertEquals(attacker.getLowAttacks(), 4);
	}
	
	@Test
	public void testNumberOfTotalBlocks(){
		defender.setDefense("High");
		defender.determineHitOrBlock("High");
		defender.determineHitOrBlock("Low");
		defender.determineHitOrBlock("High");
		defender.setDefense("Medium");
		defender.determineHitOrBlock("Medium");
		defender.setDefense("Low");
		defender.determineHitOrBlock("Low");
		defender.determineHitOrBlock("Low");
		assertEquals(defender.getNumberOfAttacksBlocked(), 5);
	}
	
	// testing if the tracking of high attacks blocked are working
	@Test
	public void testNumberOfHighBlocks(){
		defender.improvedDefenseMove("High");
		defender.improvedDefenseMove("Medium");
		defender.improvedDefenseMove("High");
		defender.improvedDefenseMove("High");
		defender.improvedDefenseMove("Low");
		assertEquals(defender.getNumberOfHighAttacksBlocked(), 3);
	}
	
	@Test
	public void testNumberOfLowBlocks(){
		defender.improvedDefenseMove("Low");
		defender.improvedDefenseMove("Low");
		defender.improvedDefenseMove("Low");
		defender.improvedDefenseMove("Low");
		defender.improvedDefenseMove("Low");
		assertEquals(defender.getNumberOfLowAttacksBlocked(), 5);
	}
	
	@Test
	public void testNumberOfMediumBlocks(){
		defender.improvedDefenseMove("Medium");
		defender.improvedDefenseMove("Medium");
		defender.improvedDefenseMove("Medium");
		defender.improvedDefenseMove("Medium");
		defender.improvedDefenseMove("Medium");
		assertEquals(defender.getNumberOfMediumAttacksBlocked(), 5);
	}
	
	@Test
	public void testDefaultValues(){
		// value exceeds 100, therefore must be set to default values
		manager.validateUserInput(100, 0, 100, attacker);
		assertEquals(manager.getLowAttacks(), 33);
		assertEquals(manager.getHighAttacks(), 33);
		assertEquals(manager.getMediumAttacks(), 34);
	}
	
	@Test
	public void testCorrectAttackValues(){
		System.out.println("***** Insert rounds = 1 to test *****");
		System.out.println("***** High attacks = 20");
		System.out.println("***** Low attacks = 30");
		System.out.println("***** Medium attacks = 50");

		manager.startFightSimulation();
		assertEquals(manager.getLowAttacks(), 30);
		assertEquals(manager.getHighAttacks(), 20);
		assertEquals(manager.getMediumAttacks(), 50);
	}
	
	
	
	
	
	
	
	
}
