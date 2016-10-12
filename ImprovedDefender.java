import java.util.Random;

public class ImprovedDefender {
	private int trackEnemyHighAttack;
	private int trackEnemyLowAttack;
	private int highAttackPercentage;
	private int	lowAttackPercentage;
	private Random generator;
	public ImprovedDefender(){
		trackEnemyHighAttack = 0;
		trackEnemyLowAttack = 0;
		highAttackPercentage = 0;
		lowAttackPercentage =0;
		generator = new Random();
	}
	
	//This method is to track the attack. It will take the number of values from low, medium
	// and high and do the math to get a percentage. Then it will do the range between them
	// and determine the highest possibility for the defensive move. 
	public void trackEnemyAttacks(int round, String attack, Defender defender)
	{
		if ((round%20) == 0)
			calculateImprovedBlocks();
		if (round >= 20)
			determineImprovedBlocks(defender);
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
	public void determineImprovedBlocks(Defender defender){
		int defense = generator.nextInt(99) + 1;
		if (defense>0 && defense<highAttackPercentage)
			defender.setDefense("High");
		else if (defense>highAttackPercentage && defense<(highAttackPercentage+lowAttackPercentage))
			defender.setDefense("Low");
		else if (defense>(highAttackPercentage+lowAttackPercentage) && defense<100)
			defender.setDefense("Medium");
	}
}
