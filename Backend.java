// --== CS400 File Header Information ==--
// Name: Caroline Machart
// Email: machart@wisc.edu
// Team: HC 
// TA: Na Li
// Lecturer: Florian Heimerl
// Notes to Grader: 

/**
 * Stores Player objects in a RedBlackTree and helps get access to the roster.
 * 
 * @author Caroline Machart
 *
 */
public class Backend {
	private RedBlackTree<Player> team; //RedBlackTree that holds players
	
	/**
	 * The no argument constructor that instantiates the team. 
	 *  
	 */
	public Backend() {
		team = Data.Roster();
	}
	
	/**
	 * Uses the RedBlackTree.toString() to print the Players in team. 
	 * 
	 * @return the team as a string. 
	 */
	public String getRoster() {
		return "Roster: " + team.toString();
	}
	
	/**
	 * Calls searchHelper() to get the player associated with name.  
	 * 
	 * @param name The name of the Player you're searching for.  
	 * @return Player if player was found. Otherwise, null.
	 */
	public Player searchTeam(String name) {
		return searchHelper(name, team.root);
	}
	
	/**
	 * Recursively looks through tree to find the name. Helper method
	 * for search method. 
	 * 
	 * @param name The name of the Player you're searching for. 
	 * @param curr The current Player node that you're recursing on. 
	 * @return Player if player was found. Otherwise, null.
	 */
	public Player searchHelper(String name, RedBlackTree.Node<Player> curr) {
		if (curr == null) { //player not found
			return null;
		}
		else if (curr.data.getName().equals(name)) { //base case
			return curr.data;
		}
		else {
			if (name.compareTo(curr.data.getName()) < 0) { //recurses on leftChild
				return searchHelper(name, curr.leftChild);
			}
			else { //recurses on rightChild
				return searchHelper(name, curr.rightChild); 
			}
		}
	}
	
	/**
	 * Gets the statistics of the player. 
	 * 
	 * @param name Name of the player to get stats on. 
	 * @return String representation of the player's stats.
	 */
	public String getStats(String name){
		if (searchTeam(name) == null) {
			return "Player not found";
		}
		else {
			return searchTeam(name).getStats();
		}
	}
	
	/**
	 * Changes the statistics of the player. 
	 * 
	 * @param name Name of the player to get stats on. 
	 * @param avgYards the average yards of the player
	 * @param totYards the total yards of the player
	 */
	public void changeStats(String name, double avgYards, double totYards){
		if (searchTeam(name) != null) {
			searchTeam(name).setStats(avgYards, totYards);
		}
	}
	
	/**
	 * Changes the status of the player. 
	 *
	 * @param name Name of player in which to change status.
	 * @param isStarter boolean value which expresses whether player is a starter.
	 */
	public void makeStarter(String name, boolean isStarter) {
		if (searchTeam(name) != null) {
			searchTeam(name).setIsStarter(isStarter);
		}
	}
	
	/**
	 * Changes the roster number of the Player. 
	 * 
	 * @param name Name of the player. 
	 * @param number Roster number of the player. 
	 */
	public void changeNumber(String name, int number) {
		searchTeam(name).setNumber(number);
	}
	
	/**
	 * Adds player to the team by utilizing RedBlackTree.insert(). 
	 * 
	 * @param name Name of the player to add.
	 * @param number Number of the player to add.
	 * @param pos Position of the player to add.
	 * @param isStarter boolean value which expresses whether player is starter.
	 */
	public void addMember(String name, int number, String pos, boolean isStarter) {
		Player playerToAdd = new Player(name, number, pos, isStarter);
		team.insert(playerToAdd);
	}
	
	/**
	 * Helper method that returns a string representation of 
	 * the starting line up. 
	 * 
	 * @param curr The current Player node in team
	 * @return The starting lineup as a string.  
	 */
	public String startingLineUpHelper(RedBlackTree.Node<Player> curr) {
		String s = "";
		if(curr.rightChild != null){ 
			s += startingLineUpHelper(curr.rightChild);
		}
		if (curr.data.getIsStarter()) {
				s += curr.data.toString() + "\n";
		}
		if(curr.leftChild != null){
			s += startingLineUpHelper(curr.leftChild);
		}
		return s;
	}
	
	/**
	 * Calls startingLineUpHelper to get starting lineup 
	 * and returns string representation of starting Lineup. 
	 * 
	 * @return The starting lineup as a string.  
	 */
	public String getStartingLineUp() {
		return "Starting Lineup: " 
				+ "\n" + startingLineUpHelper(team.root);
	}
	
}
