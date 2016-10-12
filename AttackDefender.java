import java.util.Random;
public class Attacker
{
	private String typeAttack;
	private int numberOfHighAttacks;
	private int numberOfLowAttacks;
	private int numberOfMediumAttacks;
	private Random generator;

	// default values for SUM and typeAttack
	public Attacker()
	{
		typeAttack="";
		numberOfHighAttacks=0;
		numberOfLowAttacks=0;
		numberOfMediumAttacks=0;
		generator = new Random();
	}
		
		
	//This method generates a random move depending on what the user inputs as the 
	// percentages. 
	public void generateAttack(int highAttackProbability, int lowAttackProbability, int mediumAttackProbability)
	{
		int attack = generator.nextInt(99) + 1;
		determineAttack(highAttackProbability, lowAttackProbability, mediumAttackProbability, attack);
		incrementAttack();
	}
	
	public void determineAttack(int highAttack, int lowAttack, int mediumAttack, int attack){
		if (attack > 0 && attack < highAttack)
			typeAttack= "High";
		else if (attack > (highAttack) && attack < (highAttack + lowAttack))
			typeAttack= "Low";
		else if (attack > (highAttack+lowAttack) && attack < 100)
			typeAttack= "Medium";
	}

	public void incrementAttack(){
		if ("High" == typeAttack)
			numberOfHighAttacks++;
		else if ("Low" == typeAttack)
			numberOfLowAttacks++;
		else
			numberOfMediumAttacks++;
	}
	
	public int getLowAttacks()
	{
		return(numberOfLowAttacks);
	}
	public int getHighAttacks()
	{
		return(numberOfHighAttacks);
	}
	public int getMediumAttacks()
	{
		return(numberOfMediumAttacks);
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
