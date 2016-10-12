import java.util.Random;

public class Defender {
	private String printDefense;
	private int numberOfAttacksHit;
	private int numberOfAttacksBlocked;
	private int roundTracking;
	private int percentageOfHighBlocks;
	private int percentageOfLowBlocks;
	private int percentageOfMediumBlocks;
	private Random generator;

	public Defender(){
		printDefense ="";
		numberOfAttacksHit= 0;
		numberOfAttacksBlocked= 0;
		roundTracking= 0;
		percentageOfHighBlocks=0;
		percentageOfLowBlocks=0;
		percentageOfMediumBlocks=0;
		generator = new Random();
	}
		//this method will generate a value between 1-3 and determine whether its a high
	//low or medium defence. It will only random generate a defence move 20 times 
	//then it will start recognizing the attacks and be smart about the defence moves.
	public void getDefense(int rounds, String attack, ImprovedDefender improved, Defender defender)
	{
		if (roundTracking < 20) {
			improved.trackEnemyAttacks(rounds,attack, defender);
			determineDefenseMove();
			determineHitOrBlock(attack);
		}
		else{
			improved.trackEnemyAttacks(rounds, attack, defender);
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
		} else
		{
			numberOfAttacksBlocked++;
			roundTracking++;
		}
		
		System.out.printf("Round:%-5d Attacker: %-7s Defender: %s\n", roundTracking,att, printDefense);
	}
	
	//This method is to print the number of hits/blocks after the # of rounds.
	//Will also print the proportions of the defender and attacker
	public void printResults(Attacker attacker)
	{
	//This will calculate the percentage of attacks and defences(High, medium and low percentages)
		String percent= "%";
		int printpercentdefH= (percentageOfHighBlocks*100)/roundTracking;
		int printpercentdefM= (percentageOfMediumBlocks*100)/roundTracking;
		int printpercentdefL= (percentageOfLowBlocks*100)/roundTracking;
		int printpercenthigh= (attacker.getHighAttacks()*100)/roundTracking;
		int printpercentmed= (attacker.getMediumAttacks()*100)/roundTracking;
		int printpercentlow= (attacker.getLowAttacks()*100)/roundTracking;
	
		
		System.out.printf("Number of hits: %-5d Number of hits blocked: %d\n", numberOfAttacksHit, numberOfAttacksBlocked);
		System.out.printf("Attacker proportions: Low: %d%-5s High: %d%-5s Medium: %d%-5s\n", printpercentlow, percent, printpercenthigh,percent,printpercentmed,percent);
		System.out.printf("Defender proportions: Low: %d%-5s High: %d%-5s Medium: %d%-5s\n", printpercentdefL,percent, printpercentdefH,percent, printpercentdefM, percent);
	
	}
	
	public void setDefense(String move){
		printDefense = move;
	}
	
}
