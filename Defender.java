import java.util.Random;

public class Defender {
	private String printDefense;
	private int numberOfAttacksHit;
	private int numberOfAttacksBlocked;
	private int roundTracking;
	private int trackEnemyHighAttack;
	private int trackEnemyLowAttack;
	private int trackEnemyMediumAttack;
	private int percentageOfHighAttacks;
	private int percentageOfLowAttacks;
	private int percentageOfMediumAttacks;
	private int percentageOfHighBlocks;
	private int percentageOfLowBlocks;
	private int percentageOfMediumBlocks;
	
	public Defender(){
		printDefense ="";
		numberOfAttacksHit= 0;
		numberOfAttacksBlocked= 0;
		roundTracking= 0;
		trackEnemyHighAttack=0;
		trackEnemyMediumAttack=0;
		trackEnemyLowAttack=0;
		percentageOfHighAttacks=0;
		percentageOfLowAttacks=0;
		percentageOfMediumAttacks=0;
		percentageOfHighBlocks=0;
		percentageOfLowBlocks=0;
		percentageOfMediumBlocks=0;
	}
		//this method will generate a value between 1-3 and determine whether its a high
	//low or medium defence. It will only random generate a defence move 20 times 
	//then it will start recognizing the attacks and be smart about the defence moves.
	public void getDefense(int rounds, String attack)
	{
		if (roundTracking < 20)
		{
			Random generator = new Random();
			int defense = generator.nextInt(4-1) + 1;
			if (defense == 1)
			{
				printDefense = "High";
				percentageOfHighBlocks++;
	
			}
		
			if (defense == 2)
			{
				printDefense = "Low";
				percentageOfLowBlocks++;
	
			}
		
			if (defense == 3)
			{
				printDefense = "Medium";
				percentageOfMediumBlocks++;
			}
			determineHitOrBlock(attack);
			trackEnemyAttacks(rounds,attack);
		}
		else
		{
			trackEnemyAttacks(rounds, attack);
			determineHitOrBlock(attack);
			if (attack == "High")
			{
				percentageOfHighBlocks++;
			}
			if (attack == "Low")
			{
				percentageOfLowBlocks++;
			}
			if (attack == "Medium")
			{
				percentageOfMediumBlocks++;
			}
		}
		
	}
	
	
	
	//This counts the number of hits/blocks and prints out what round, attack and defence
	//move is made.
	public void determineHitOrBlock(String att)
	{
		if (printDefense != att)
		{	
			numberOfAttacksHit= numberOfAttacksHit + 1;
			roundTracking=roundTracking + 1;
			if ("High" == att)
			{
				percentageOfHighAttacks++;
			}
			if ("Low" == att)
			{
				percentageOfLowAttacks++;
			}
			if ("Medium" == att)
			{
				percentageOfMediumAttacks++;
			}
		}
		else
		{
			numberOfAttacksBlocked= numberOfAttacksBlocked + 1;
			roundTracking=roundTracking + 1;
			if ("High" == att)
			{
				percentageOfHighAttacks++;
			}
			if ("Low" == att)
			{
				percentageOfLowAttacks++;
			}
			if ("Medium" == att)
			{
				percentageOfMediumAttacks++;
			}
		}
		
		System.out.printf("Round:%-5d Attacker: %-7s Defender: %s\n", roundTracking,att, printDefense);
	
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
		{
			trackEnemyLowAttack=(trackEnemyLowAttack*100)/20;
			trackEnemyMediumAttack=(trackEnemyMediumAttack*100)/20;
			trackEnemyHighAttack=(trackEnemyHighAttack*100)/20;
			Random generator = new Random();
			int defense = generator.nextInt(99) + 1;
			if (defense>0 && defense<trackEnemyHighAttack)
			{
				printDefense= "High";
			}
			else if (defense>trackEnemyHighAttack && defense<(trackEnemyHighAttack+trackEnemyLowAttack))
			{
				printDefense= "Low";
	
			}
			else if (defense>(trackEnemyHighAttack+trackEnemyLowAttack) && defense<(trackEnemyHighAttack+trackEnemyLowAttack+trackEnemyMediumAttack))
			{
				printDefense = "Medium";
			}
			trackEnemyHighAttack=0;
			trackEnemyLowAttack=0;
			trackEnemyMediumAttack=0;
		
		}
	
		else if (attack == "High")
		{
			trackEnemyHighAttack++;
		}
		else if (attack == "Low")
		{
			trackEnemyLowAttack++;
		}
		else if (attack == "Medium")
		{
			trackEnemyMediumAttack++;
		}
			
	
	
	}

}
