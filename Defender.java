import java.util.Random;

public class Defender {
	private String printDefense;
	private int numberOfAttacksHit;
	private int numberOfAttacksBlocked;
	private int roundTracking;
	private int trackEnemyHighAttack;
	private int trackEnemyLowAttack;
	private int percentageOfHighAttacks;
	private int percentageOfLowAttacks;
	private int percentageOfMediumAttacks;
	private int percentageOfHighBlocks;
	private int percentageOfLowBlocks;
	private int percentageOfMediumBlocks;
	private int highAttackPercentage;
	private int	lowAttackPercentage;
	private Random generator;

	public Defender(){
		printDefense ="";
		numberOfAttacksHit= 0;
		numberOfAttacksBlocked= 0;
		roundTracking= 0;
		trackEnemyHighAttack=0;
		trackEnemyLowAttack=0;
		percentageOfHighAttacks=0;
		percentageOfLowAttacks=0;
		percentageOfMediumAttacks=0;
		percentageOfHighBlocks=0;
		percentageOfLowBlocks=0;
		percentageOfMediumBlocks=0;
		highAttackPercentage = 0;
		lowAttackPercentage =0;
		generator = new Random();
	}
		//this method will generate a value between 1-3 and determine whether its a high
	//low or medium defence. It will only random generate a defence move 20 times 
	//then it will start recognizing the attacks and be smart about the defence moves.
	public void getDefense(int rounds, String attack)
	{
		if (roundTracking < 20) {
			trackEnemyAttacks(rounds,attack);
			determineDefenseMove();
			determineHitOrBlock(attack);
		}
		else{
			trackEnemyAttacks(rounds, attack);
			determineHitOrBlock(attack);
			improvedDefenseMove(attack);
		}
	}
	
	public void improvedDefenseMove(String attack){
		if (attack == "High")
			percentageOfHighBlocks++;
		else if (attack == "Low")
			percentageOfLowBlocks++;
		else if (attack == "Medium")
			percentageOfMediumBlocks++;
	}
	
	public void determineDefenseMove(){
		int defense = generator.nextInt(4-1) + 1;
		switch(defense){
		case 1: defense = 1;
				printDefense = "High";
				percentageOfHighBlocks++;
				break;
		case 2: defense = 2;
				printDefense = "Low";
				percentageOfLowBlocks++;
				break;
		case 3: defense = 3;
				printDefense = "Medium";
				percentageOfMediumBlocks++;
				break;
		}
	}
	
	//This counts the number of hits/blocks and prints out what round, attack and defence
	//move is made.
	public void determineHitOrBlock(String att)
	{
		if (printDefense != att){	
			numberOfAttacksHit++;
			roundTracking++;
			incrementAttack(att);
		} else
		{
			numberOfAttacksBlocked++;
			roundTracking++;
			incrementAttack(att);
		}
		
		System.out.printf("Round:%-5d Attacker: %-7s Defender: %s\n", roundTracking,att, printDefense);
	}

	public void incrementAttack(String attack){
		if ("High" == attack)
			percentageOfHighAttacks++;
		else if ("Low" == attack)
			percentageOfLowAttacks++;
		else
			percentageOfMediumAttacks++;
	}
	
	//This method is to print the number of hits/blocks after the # of rounds.
	//Will also print the proportions of the defender and attacker
	public void printResults()
	{
	//This will calculate the percentage of attacks and defences(High, medium and low percentages)
		String percent= "%";
		int printpercentdefH= (percentageOfHighBlocks*100)/roundTracking;
		int printpercentdefM= (percentageOfMediumBlocks*100)/roundTracking;
		int printpercentdefL= (percentageOfLowBlocks*100)/roundTracking;
		int printpercenthigh= (percentageOfHighAttacks*100)/roundTracking;
		int printpercentmed= (percentageOfMediumAttacks*100)/roundTracking;
		int printpercentlow= (percentageOfLowAttacks*100)/roundTracking;
	
		
		System.out.printf("Number of hits: %-5d Number of hits blocked: %d\n", numberOfAttacksHit, numberOfAttacksBlocked);
		System.out.printf("Attacker proportions: Low: %d%-5s High: %d%-5s Medium: %d%-5s\n", printpercentlow, percent, printpercenthigh,percent,printpercentmed,percent);
		System.out.printf("Defender proportions: Low: %d%-5s High: %d%-5s Medium: %d%-5s\n", printpercentdefL,percent, printpercentdefH,percent, printpercentdefM, percent);
	
	}
	
	//This method is to track the attack. It will take the number of values from low, medium
	// and high and do the math to get a percentage. Then it will do the range between them
	// and determine the highest possibility for the defensive move. 
	public void trackEnemyAttacks(int round, String attack)
	{
		if ((round%20) == 0)
			calculateImprovedBlocks();
		if (round >= 20)
			determineImprovedBlocks();
		if (attack == "High")
			trackEnemyHighAttack++;
		else if (attack == "Low")
			trackEnemyLowAttack++;
	}
	//Every 20 rounds, it will calculate a better blocks based off the previous 20 rounds
	public void calculateImprovedBlocks(){
		lowAttackPercentage=(trackEnemyLowAttack*100)/20;
		highAttackPercentage=(trackEnemyHighAttack*100)/20;
		trackEnemyHighAttack=0;
		trackEnemyLowAttack=0;
	}
	//Determine the attack based on the knowledge of the last 20 rounds
	public void determineImprovedBlocks(){
		int defense = generator.nextInt(99) + 1;
		if (defense>0 && defense<highAttackPercentage)
			printDefense= "High";
		else if (defense>highAttackPercentage && defense<(highAttackPercentage+lowAttackPercentage))
			printDefense= "Low";
		else if (defense>(highAttackPercentage+lowAttackPercentage) && defense<100)
			printDefense = "Medium";
	}

}
