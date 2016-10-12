import java.util.Scanner;
import java.util.Random;
public class AttackDefender
{
	private int SUM;
	private String typeAttack;
	private String displaydefence;
	private int d;
	private int numberofhits;
	private int numberofblocks;
	private int roundtracking;
	private String trackstring;
	private int i;
	private int track;
	private int High;
	private int Low;
	private int Medium;
	private int percenthigh;
	private int percentlow;
	private int percentmed;
	private int percentdefH;
	private int percentdefL;
	private int percentdefM;
	// default values for SUM and typeAttack
	public AttackDefender()
	{
		SUM=0;
		typeAttack="";
		displaydefence ="";
		d= 0;
		numberofhits= 0;
		numberofblocks= 0;
		roundtracking= 0;
		trackstring="";
		i=10;
		track=0;
		High=0;
		Medium=0;
		Low=0;
		percenthigh=0;
		percentlow=0;
		percentmed=0;
		percentdefH=0;
		percentdefL=0;
		percentdefM=0;
	}
		
		
	//This method generates a random move depending on what the user inputs as the 
	// percentages. 
	public void probability(int Chance, int Chance1, int Chance2)
	{
		Random generator = new Random();
		int randAttack = generator.nextInt(99) + 1;
		if (randAttack > 0 && randAttack < Chance)
		{
			typeAttack= "High";
		}
		if (randAttack > (Chance) && randAttack < (Chance + Chance1))
		{
			typeAttack= "Low";
		}
		if (randAttack > (Chance+Chance1) && randAttack < (Chance+Chance1+Chance2))
		{
			typeAttack= "Medium";
		}
	}
	


	//This method is the check and see if the user entered invalid information
	// and will set it to default percentages if the user did enter a value>100 or less than
	// 100.
	public void check(int chance, int chance1, int chance2)
	{
		SUM = chance + chance1 + chance2;
		if (SUM != 100)
		{
			System.out.println("The percentage of attacks you've entered do not equal 100%.");
			System.out.println("By default, your attacks will now have a equal probability of each attack(33% each).");
			chance = 33;
			chance1 = 33;
			chance2 = 34;
			System.out.println("You have " + chance + "% high attacks.");
			System.out.println("You have " + chance1 + "% low attacks.");
			System.out.println("You have " + chance2 + "% medium attacks.");
		}
	}
	
		//this method will generate a value between 1-3 and determine whether its a high
	//low or medium defence. It will only random generate a defence move 20 times 
	//then it will start recognizing the attacks and be smart about the defence moves.
	public void getdefence(int rounds, String attack)
	{
		if (roundtracking < 20)
		{
			Random generator = new Random();
			int Defence = generator.nextInt(4-1) + 1;
			d = Defence;
			if (d == 1)
			{
				displaydefence = "High";
				percentdefH++;

			}
		
			if (d == 2)
			{
				displaydefence = "Low";
				percentdefL++;

			}
		
			if (d == 3)
			{
				displaydefence = "Medium";
				percentdefM++;
			}
			determineHitorBlock(attack);
			track(rounds,attack);
		}
		else
		{
			track(rounds, attack);
			determineHitorBlock(attack);
			if (attack == "High")
			{
				percentdefH++;
			}
			if (attack == "Low")
			{
				percentdefL++;
			}
			if (attack == "Medium")
			{
				percentdefM++;
			}
		}
		
	}
	
	
	
	//This counts the number of hits/blocks and prints out what round, attack and defence
	//move is made.
	public void determineHitorBlock(String att)
	{
		if (displaydefence != att)
		{	
			numberofhits= numberofhits + 1;
			roundtracking=roundtracking + 1;
			trackstring= att;
			if ("High" == att)
			{
				percenthigh++;
			}
			if ("Low" == att)
			{
				percentlow++;
			}
			if ("Medium" == att)
			{
				percentmed++;
			}
		}
		else
		{
			numberofblocks= numberofblocks + 1;
			roundtracking=roundtracking + 1;
			trackstring= att;
			if ("High" == att)
			{
				percenthigh++;
			}
			if ("Low" == att)
			{
				percentlow++;
			}
			if ("Medium" == att)
			{
				percentmed++;
			}
		}
		
		System.out.printf("Round:%-5d Attacker: %-7s Defender: %s\n", roundtracking,att, displaydefence);

	}
	
	
	
	//This method is to print the number of hits/blocks after the # of rounds.
	//Will also print the proportions of the defender and attacker
	public void printResults()
	{
	//This will calculate the percentage of attacks and defences(High, medium and low percentages)
		String percent= "%";
		int printpercentdefH= (percentdefH*100)/roundtracking;
		int printpercentdefM= (percentdefM*100)/roundtracking;
		int printpercentdefL= (percentdefL*100)/roundtracking;
		int printpercenthigh= (percenthigh*100)/roundtracking;
		int printpercentmed= (percentmed*100)/roundtracking;
		int printpercentlow= (percentlow*100)/roundtracking;

		
		System.out.printf("Number of hits: %-5d Number of hits blocked: %d\n", numberofhits, numberofblocks);
		System.out.printf("Attacker proportions: Low: %d%-5s High: %d%-5s Medium: %d%-5s\n", printpercentlow, percent, printpercenthigh,percent,printpercentmed,percent);
		System.out.printf("Defender proportions: Low: %d%-5s High: %d%-5s Medium: %d%-5s\n", printpercentdefL,percent, printpercentdefH,percent, printpercentdefM, percent);

	}
	
	//This method is to track the attack. It will take the number of values from low, medium
	// and high and do the math to get a percentage. Then it will do the range between them
	// and determine the highest possibility for the defensive move. 
	public void track(int round, String attack)
	{
		
		if ((round%20) == 0)
		{
			Low=(Low*100)/20;
			Medium=(Medium*100)/20;
			High=(High*100)/20;
			Random generator = new Random();
			int Defence = generator.nextInt(99) + 1;
			track= Defence;
			if (track>0 && track<High)
			{
				displaydefence= "High";
			}
			else if (track>High && track<(High+Low))
			{
				displaydefence= "Low";

			}
			else if (track>(High+Low) && track<(High+Low+Medium))
			{
				displaydefence = "Medium";
			}
			High=0;
			Low=0;
			Medium=0;
		
		}

		else if (attack == "High")
		{
			High++;
		}
		else if (attack == "Low")
		{
			Low++;
		}
		else if (attack == "Medium")
		{
			Medium++;
		}
			
	
	
	}

	
	//Returns the type of attack
	public String getAttack()
	{
		return(typeAttack);
	}
	
	//Returns the default value of low
	public int lowchance()
	{
		return(33);
	}
	//Returns the default value of high
	public int highchance()
	{
		return(33);
	}
	//Returns the default value of medium
	public int mediumchance()
	{
		return(34);
	}
}
