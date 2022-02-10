// --== CS400 File Header Information ==--
// Name: Caroline Machart
// Email: machart@wisc.edu
// Team: HC 
// TA: Na Li
// Lecturer: Florian Heimerl
// Notes to Grader: 

/**
 * Creates Player objects and implements Comparable interface to compare 
 * players by name. 
 * 
 * @author Caroline Machart
 *
 */
public class Player implements Comparable<Player>{
	private String name; //name of player
	private int number; //player's roster number
	private String pos; //player's position
	private boolean isStarter; //whether player is starter
	private double avgYards; //the average yards of player
	private double totYards; //the total yards of player
	
	/**
	 * Player argument constructor. 
	 * 
	 * @param name The name of player.
	 * @param number The roster number of player.
	 * @param pos The player's position. 
	 * @param isStarter Whether the player is a starter. 
	 */
	public Player(String name, int number, String pos, boolean isStarter) {
		this.name = name; //name of player
		this.number = number; //number of player
		this.pos = pos; //player's position
		this.isStarter = isStarter; //indicates whether player is starting
	}
	
	/**
	 * Implementation of the comparable interface compareTo() method. 
	 * 
	 * @param o The player to compare to. 
	 * @return Negative number if o comes after, positive if it comes before and 0 
	 * 			if they're equal. 
	 */
	@Override
	public int compareTo(Player o) {
		return name.compareTo(o.name);
	}
	
	/**
	 * Setter method for isStarter. 
	 * 
	 * @param starter the value in which to change isStarter to. 
	 */
	public void setIsStarter(boolean starter) {
		this.isStarter = starter;
	}
	
	/**
	 * Setter method for number. 
	 * 
	 * @param number the roster number of the player 
	 *
	 */
	public void setNumber(int number) {
		this.number = number;
	}
	
	
	/**
	 * Returns String representation of Player object.
	 * 
	 * @return String representation of Player object.
	 */
	public String toString() {
		return name + "(" + number + ") - " + pos; 
	}
	
	/**
	 * Getter method for isStarter.
	 * 
	 * @return whether player is a starter
	 */
	public boolean getIsStarter() {
		return isStarter;
	}
	
	/**
	 * Setter method for average yards and total yards.
	 * 
	 * @param avgYards The average yards of player
	 * @param totYards The total yards of player
	 */
	public void setStats(double avgYards, double totYards) {
		this.avgYards = avgYards;
		this.totYards= totYards;
	}
	
	/**
	 * Getter method for average yards and total yards. ,
	 * 
	 * @return String representation of the stats of Player. 
	 */
	public String getStats() {
		return "Average Yards: " + avgYards + ": Total Yards: " + totYards;
	}
	
	/**
	 * Getter method for average yards.
	 * 
	 * @return int representation of the average yards of Player. 
	 */
	public double getAverageYards() {
		return avgYards;
	}
	
	/**
	 * Getter method for total yards.
	 * 
	 * @return double representation of the total yards of Player. 
	 */
	public double getTotalYards() {
		return totYards;
	}
	
	/**
	 * Getter method for position. ,
	 * 
	 * @return String representation of Players position. 
	 */
	public String getPos() {
		return "Average Yards: " + avgYards + ": Total Yards: " + totYards;
	}
	
	/**
	 * Getter method for name. 
	 * 
	 * @return The name of player. 
	 */
	public String getName() {
		return name;
	}
}