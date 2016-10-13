import java.util.Scanner;
public class Manager
{
	private int numberOfRounds;
	private int highAttacks;
	private int lowAttacks;
	private int mediumAttacks;
	public Manager(){
		numberOfRounds = 0;
		highAttacks = 0;
		lowAttacks = 0;
		mediumAttacks = 0;
	}
	public void startFightSimulation()
	{	
		//ask for user input
		System.out.print("How many rounds do you want(1-100)? ");
		numberOfRounds= askUserInput();
		System.out.print("What probability of high attacks do you want? ");
		highAttacks= askUserInput();		
		System.out.print("What probability of low attacks do you want? ");
		lowAttacks= askUserInput();
		System.out.print("What probability of medium attacks do you want? ");
		mediumAttacks= askUserInput();
		
		//instantiates the Attacker class and Defender class
		Attacker attacker = new Attacker();
		Defender defender = new Defender();
		ImprovedDefender improvedDefender = new ImprovedDefender();
		validateUserInput(highAttacks,lowAttacks,mediumAttacks, attacker);
		
		//
		while (numberOfRounds > 0)
		{
			attacker.generateAttack(highAttacks,lowAttacks,mediumAttacks);
			defender.getDefense(numberOfRounds, attacker.getAttack(), improvedDefender, defender);
			numberOfRounds--;
		}
		defender.printResults(attacker);
	}
	//This method is the check and see if the user entered invalid information
	// and will set it to default percentages if the user did enter a value>100 or less than
	// 100.
	public void validateUserInput(int highAttackPercentage, int lowAttackPercentage, int mediumAttackPercentage, Attacker attacker)
	{
		int sum = highAttackPercentage + lowAttackPercentage + mediumAttackPercentage;
		if ((sum) != 100)
		{
			System.out.println("The percentage of attacks you've entered do not equal 100%.");
			System.out.println("By default, your attacks will now have a equal probability of each attack(33% each).");
			highAttacks=attacker.getDefaultHighAttack();
			lowAttacks=attacker.getDefaultLowAttack();
			mediumAttacks=attacker.getDefaultMediumAttack();
			System.out.println("You have " + highAttacks + "% high attacks.");
			System.out.println("You have " + lowAttacks + "% low attacks.");
			System.out.println("You have " + mediumAttacks + "% medium attacks.");
		}
	}
	
	public int askUserInput(){
		Scanner scanner= new Scanner(System.in);
		return scanner.nextInt();
	}
	
	public void validateRounds(){
		//this if statement is to ensure that the rounds are in range of 1-100. 
		if ((numberOfRounds<1) || (numberOfRounds>100)){
			numberOfRounds=10;
			System.out.print("The rounds you have entered is not in range, by default the rounds is set to 10.");
		}
	}
	
	public int getLowAttacks(){
		return lowAttacks;
	}
	
	public int getMediumAttacks(){
		return mediumAttacks;
	}
	
	public int getHighAttacks(){
		return highAttacks;
	}
	
	
	
}