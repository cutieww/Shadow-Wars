import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * Waves class is as a role of enemy manager
 * which generates all enemies
 */
public class Waves {
	private BasicEnemy basicEnemy;
	private SineEnemy sineEnemy;
	private BasicShooter basicShooter;
	private Boss boss;

	/** String for every line in the file */
	private String text;
	/** a character for checking if the line in flies are comments */
	private char c;
	
	/** All the enemy starting point Y */
	public static final float ENEMY_STARTING_Y = -64;

	/**
     * Read file and generator all the enemies
     */
	public Waves() {
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		//fine the file that needs to be used
        File file = new File("res/waves.txt");
        //read in all the files
        try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //use while loop to generates all the enemies
        while(scanner.hasNextLine()) {
        		text = scanner.nextLine();
        		c = text.charAt(0);
        		//if the line is comment, get the next line
        		if (c =='#') {
        			text = scanner.nextLine();
        		}
        		//put all the words from text into an array 
        		String line[] = text.split(",");
        		//CASE 1: When it is BasicEnemy
        		if (line[0].equals("BasicEnemy")) {
				basicEnemy = new BasicEnemy("res/basic-enemy.png",Float.parseFloat(line[1]),
						ENEMY_STARTING_Y,Integer.parseInt(line[2]));
				World.getInstance().addSprites(basicEnemy);
				}
        		//CASE 2: When it is SineEnemy
        		if (line[0].equals("SineEnemy")) {
        			sineEnemy = new SineEnemy("res/sine-enemy.png",Float.parseFloat(line[1]),
        					ENEMY_STARTING_Y,Integer.parseInt(line[2]));
        			World.getInstance().addSprites(sineEnemy);
        		}
        		//CASE 3: When it is BasicShooter
        		if (line[0].equals("BasicShooter")) {
        			basicShooter = new BasicShooter("res/basic-shooter.png",Float.parseFloat(line[1]),
        					ENEMY_STARTING_Y,Integer.parseInt(line[2]));
        			World.getInstance().addSprites(basicShooter);
        		}
        		//CASE 4: When it is Boss
        		if (line[0].equals("Boss")) {
        			boss = new Boss("res/boss.png",Float.parseFloat(line[1]),ENEMY_STARTING_Y,
        					Integer.parseInt(line[2]));
        			World.getInstance().addSprites(boss);
        		}
        }
	}
}
