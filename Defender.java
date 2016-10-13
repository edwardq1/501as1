import java.util.Random;

public class Defender {
	private String printDefense;
	private int numberOfAttacksHit;
	private int numberOfAttacksBlocked;
	private int roundTracking;
	private int numberOfHighBlocks;
	private int numberOfLowBlocks;
	private int numberOfMediumBlocks;
	private Random generator;

	public Defender(){
		printDefense ="";
		numberOfAttacksHit= 0;
		numberOfAttacksBlocked= 0;
		roundTracking= 0;
		numberOfHighBlocks=0;
		numberOfLowBlocks=0;
		numberOfMediumBlocks=0;
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
			improvedDefenseMove(attack);
			determineHitOrBlock(attack);
		}
	}
	
	public void improvedDefenseMove(String attack){
		if (attack == "High")
			numberOfHighBlocks++;
		else if (attack == "Low")
			numberOfLowBlocks++;
		else if (attack == "Medium")
			numberOfMediumBlocks++;
	}
	
	public void determineDefenseMove(){
		int defense = generator.nextInt(4-1) + 1;
		switch(defense){
		case 1: defense = 1;
				printDefense = "High";
				numberOfHighBlocks++;
				break;
		case 2: defense = 2;
				printDefense = "Low";
				numberOfLowBlocks++;
				break;
		case 3: defense = 3;
				printDefense = "Medium";
				numberOfMediumBlocks++;
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
		numberOfHighBlocks = (numberOfHighBlocks*100)/roundTracking;
		numberOfMediumBlocks= (numberOfMediumBlocks*100)/roundTracking;
		numberOfLowBlocks= (numberOfLowBlocks*100)/roundTracking;
		int highAttack= (attacker.getHighAttacks()*100)/roundTracking;
		int mediumAttack = (attacker.getMediumAttacks()*100)/roundTracking;
		int lowAttack = (attacker.getLowAttacks()*100)/roundTracking;
	
		
		System.out.printf("Number of hits: %-5d Number of hits blocked: %d\n", numberOfAttacksHit, numberOfAttacksBlocked);
		System.out.printf("Attacker proportions: Low: %d%-5s High: %d%-5s Medium: %d%-5s\n", lowAttack, percent, highAttack,percent,mediumAttack,percent);
		System.out.printf("Defender proportions: Low: %d%-5s High: %d%-5s Medium: %d%-5s\n", numberOfLowBlocks,percent, numberOfHighBlocks,percent, numberOfMediumBlocks, percent);
	
	}
	
	public void setDefense(String move){
		printDefense = move;
	}
	public int getNumberOfAttacksBlocked(){
		return numberOfAttacksBlocked;
	}
	public int getNumberOfLowAttacksBlocked(){
		return numberOfLowBlocks;
	}
	public int getNumberOfHighAttacksBlocked(){
		return numberOfHighBlocks;
	}
	public int getNumberOfMediumAttacksBlocked(){
		return numberOfMediumBlocks;
	}
}
