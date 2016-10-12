import java.util.Scanner;
import java.util.Random;
public class Attacker
{
	private int SUM;
	private String typeAttack;
	private Random generator;

	// default values for SUM and typeAttack
	public Attacker()
	{
		SUM=0;
		typeAttack="";
		generator = new Random();
	}
		
		
	//This method generates a random move depending on what the user inputs as the 
	// percentages. 
	public void generateAttack(int highAttackProbability, int lowAttackProbability, int mediumAttackProbability)
	{
		int attack = generator.nextInt(99) + 1;
		determineAttack(highAttackProbability, lowAttackProbability, mediumAttackProbability, attack);
	}
	
	public void determineAttack(int highAttack, int lowAttack, int mediumAttack, int attack){
		if (attack > 0 && attack < highAttack)
			typeAttack= "High";
		else if (attack > (highAttack) && attack < (highAttack + lowAttack))
			typeAttack= "Low";
		else if (attack > (highAttack+lowAttack) && attack < 100)
			typeAttack= "Medium";
	}

	//This method is the check and see if the user entered invalid information
	// and will set it to default percentages if the user did enter a value>100 or less than
	// 100.
	public void validateUserInput(int highAttackPercentage, int lowAttackPercentage, int mediumAttackPercentage)
	{
		SUM = highAttackPercentage + lowAttackPercentage + mediumAttackPercentage;
		if (SUM != 100)
		{
			System.out.println("The percentage of attacks you've entered do not equal 100%.");
			System.out.println("By default, your attacks will now have a equal probability of each attack(33% each).");
			highAttackPercentage = 33;
			lowAttackPercentage = 33;
			mediumAttackPercentage = 34;
			System.out.println("You have " + highAttackPercentage + "% high attacks.");
			System.out.println("You have " + lowAttackPercentage + "% low attacks.");
			System.out.println("You have " + mediumAttackPercentage + "% medium attacks.");
		}
	}
	
	//Returns the type of attack
	public String getAttack()
	{
		return(typeAttack);
	}
	
	//Returns the default value of low
	public int getDefaultLowAttack()
	{
		return(33);
	}
	//Returns the default value of high
	public int getDefaultHighAttack()
	{
		return(33);
	}
	//Returns the default value of medium
	public int getDefaultMediumAttack()
	{
		return(34);
	}
}
