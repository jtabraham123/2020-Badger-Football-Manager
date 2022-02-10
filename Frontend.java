// --== CS400 File Header Information ==--
// Name: Jack Abraham
// Email: jtabraham@wisc.edu
// Team: HC
// Role: Frontend
// TA: Na Li
// Lecturer: Florian
import java.util.Scanner;
/**
 * This class models the front end of the 2020 Badger Roster application by printing prompts
 * to direct the user to the 7 commands listed.
 * @author Jack Abraham
 *
 */
public class Frontend {

	private static Backend b; //backend object
	
	/**
	 * Main method directs users to enter a command number directed by the menu. 
	 * The numbers 1-7 respond to different commands in the application.
	 * If the user doesnt enter an integer, they are returned to the menu.
	 * Switch statement models the different methods that are called for each command.
	 * @param Scanner s - a new scanner object to take user input
	 *
	 */
	public static void main(String[] args) {
		b = new Backend();
		Scanner s = new Scanner(System.in);
		System.out.println("Welcome to your 2020 Badger Football Roster!");
		int cmd = 0;
		while (cmd != 8) {
			menu();
			if (s.hasNextInt()) { // takes integer as command
				cmd = s.nextInt();
				s.nextLine();
			} else { // if the user doesn't enter an integer
				cmd = 0;
				menu();
				System.out.println("Enter a number between 1 and 7, this time!");
				s.nextLine();
				if (s.hasNextInt()) {
					cmd = s.nextInt();
				}
			}
			String playerName = null;
			// gets a name from the user
			if (cmd < 7 && cmd != 0) {
				playerName = findName(s);
			}
			// For these commands, we must make sure the user entered name is on the roster
			if (cmd == 1 || cmd == 3 || cmd ==4 || cmd == 5) {
				while(b.searchTeam(playerName) == null) {
					System.out.println("Enter a player on the team");
					System.out.println(b.getRoster());
					playerName = findName(s);
				}
			}
			// to make a player a starter, we must make sure the player is not a starter already
			if (cmd == 4) {
				while(b.searchTeam(playerName).getIsStarter()) {
					System.out.println("Enter a player that is not already a starter.");
					playerName = findName(s);
				}
			}
			// directs the user to a method based on command
			switch (cmd) {
			case 1: // Check Player stats
				getPlayerStats(playerName);
				break;
			case 2: // Check if player is on team
				playerSearch(playerName);
				break;
			case 3: // Changes the number of a player
				System.out.println("What number would you like to change " + playerName + " to?");
				while (!s.hasNextInt()) {
					System.out.println("Enter a number.");
					s.nextLine();
				}
				int newNum = s.nextInt(); // new number of the player
				changePlayerNum(playerName, newNum);
				break;
			case 4: // Move a player to the starting lineup
				System.out.println("Enter in a player to move down from starting lineup.");
				System.out.println(seeStarters());
				String playerDown = null;
				playerDown = findName(s); //player that is moving down
				while (!seeStarters().contains(playerDown)) {
					System.out.println("Enter a player on the starting lineup.");
					System.out.println(seeStarters());
					playerDown = findName(s);
				}
				moveToStartingLineup(playerName, playerDown);
				break;
			case 5: // Add yards
				System.out.println("How many yards would you like to add?");
				while (!s.hasNextInt()) {
					System.out.println("Enter a number.");
					s.nextLine();
				}
				int yards = s.nextInt();
				addYards(playerName, yards);
				break;
			case 6: // Add a teammate
				System.out.println("What is the players new number?");
				while (!s.hasNextInt()) {
					System.out.println("Enter a number.");
					s.nextLine();
				}
				int playerNum = s.nextInt();
				s.nextLine();
				System.out.println("What is the players position?");
				String playerPos = s.nextLine();
				addTeammate(playerName, playerNum, playerPos);
				break;
			case 7: // Print the starting lineup
				System.out.println(seeStarters());
				returnMenu();
			default:
				break;
			}
		}
	}

	/**
	 * Calls backend's get stats method and prints the statistics. Then sends return message.
	 *
	 */
	public static void getPlayerStats(String playerName) {
		System.out.println(b.getStats(playerName));
		returnMenu();
	}

	/**
	 * Calls backend's searchTeam method to check if the player is on the team. If player is/isnt
	 * on the team, a respective message is printer. Then sends return message. 
	 */
	public static void playerSearch(String playerName) {
		if (b.searchTeam(playerName) == null) { 
			System.out.println(playerName + " is not on the team");
			returnMenu();
		}
		else System.out.println(playerName + " is on the team");
		returnMenu();
	}
	
	/**
	 * Changes players number with backend's changeNumber method, prints a confirmation message. 
	 * Then sends return message. 
	 */
	public static void changePlayerNum(String playerName, int num) {
		b.changeNumber(playerName, num);
		System.out.println(playerName + "'s number has been changed to " + num);
		returnMenu();
	}
	
	/**
	 * Moves a player to the starting lineup using and moves one player down from the starting 
	 * lineup using backends makeStarter method. Prints a confirmation and return statement.
	 */
	public static void moveToStartingLineup(String playerName, String playerDown) {
		b.makeStarter(playerName, true);
		b.makeStarter(playerDown, false);
		System.out.println(playerName + " has been added to the starting lineup");
		System.out.println(playerDown + " has been removed from the starting lineup");
		returnMenu();
	}
	
	/**
	 * Adds yards to a players statistics. Uses games as a estimate of the number of games a 
	 * player played. In theory, avgYards = (total yards)/(games), so games = total/average.
	 * It adds yards to total yards then adds a game to games as a game must be played to 
	 * gain yards. then computes new average yards with (newTotalYards)/(games). Backend's 
	 * changestats method is called with the player, new average yards and new total.
	 * Prints a confirmation and return statement.
	 * @param double games - estimate of the number of games played
	 * @param int newTotal - new Total yards after adding
	 * @param double newAvg - new average yards
	 * 
	 */
	public static void addYards(String playerName, int yards) {
		double games;
		if (b.searchTeam(playerName).getAverageYards() == 0) games = 0;
		else games = b.searchTeam(playerName).getTotalYards()/(b.searchTeam(playerName).getAverageYards());
		games = games +1;
		double newTotal = b.searchTeam(playerName).getTotalYards() + yards;
		double newAvg = newTotal/games;
		b.changeStats(playerName, newAvg, newTotal);
		System.out.println(yards + " yards added to " + playerName);
		returnMenu();
	}
	
	/**
	 * Adds a team member to the roster with the backends addMember method. Then prints a 
	 * confirmation message and return message.
	 */
	public static void addTeammate(String playerName, int playerNum, String playerPos) {
		b.addMember(playerName, playerNum, playerPos, false);
		System.out.println(playerName + " has been added to the team");
		returnMenu();
	}
	
	/**
	 * Returns the starting lineup as a string
	 * @return - the starting lineup
	 */
	public static String seeStarters() {
		return b.getStartingLineUp();
	}
	
	/**
	 * Prints a return message. User must enter a character to exit
	 */
	public static void returnMenu() {
		Scanner s = new Scanner(System.in);
		do {
			System.out.println("Press r to return");
		} while (!s.hasNextLine());
	}
	/**
	 * Takes user input to convert to a name. Concatenates first name with last name.
	 * Properly handles if user hits enter without string or if spaces/cases dont match.
	 * @param char let1 - first letter of first name
	 * @param char let2 - first letter of last name.
	 * @return String playerName - full name of the player
	 */
	public static String findName(Scanner s) {
		char let1;
		char let2;
		System.out.println("What is the first name of the player?");
		String firstName = s.nextLine().toLowerCase().trim();
		if (!firstName.equals("")) {
			let1 = Character.toUpperCase(firstName.charAt(0));
		firstName = let1 + firstName.substring(1) + " ";
		}
		System.out.println("What is the last name of the player?");
		String lastName = s.nextLine().toLowerCase().trim();
		if (!lastName.equals("")) {
		let2 = Character.toUpperCase(lastName.charAt(0));
		lastName = let2 + lastName.substring(1);
		}
		String playerName = firstName.concat(lastName);
		return playerName;
	}

	/**
	 * Menu that contains the list of commands and prompts user.
	 * 
	 */
	public static void menu() {
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		System.out.println("Commands:");
		System.out.println("Get Player Statistics: 1");
		System.out.println("Search for Player: 2");
		System.out.println("Change Player Number: 3");
		System.out.println("Move a Player to Starting Lineup: 4");
		System.out.println("Add Yards to a Player: 5");
		System.out.println("Add Teammate: 6");
		System.out.println("See Starting Lineup: 7");
		System.out.println("quit: 8");
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		System.out.println("Enter Command (1-7):");
	}



}
