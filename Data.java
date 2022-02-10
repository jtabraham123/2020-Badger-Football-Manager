// --== CS400 File Header Information ==--
// Name: Ryan Meeker
// Email: rtmeeker@wisc.edu
// Team: HC
// TA: NA
// Lecturer: Gary Dahl
// Notes to Grader: none

public class Data {
    
    /**
     * adds all the players to a RedBlackTree
     * @return RedBlackTree with all the players
     */
    public static RedBlackTree<Player> Roster(){
        RedBlackTree<Player> team = new RedBlackTree<Player>();
        
        Player one = new Player("Graham Mertz", 5, "QB", true);
        Player two = new Player("Danny David III", 7, "WR", true);
        Player three = new Player("Kendric Pryor", 6, "WR", true);
        Player four = new Player("Cole Van Lanen", 71, "LT", true);
        Player five = new Player("Josh Seltzner", 70, "LG", true);
        Player six = new Player("Kayden Lyles", 76, "OC", true);
        Player seven = new Player("Logan Bruss", 60, "RG", true);
        Player eight = new Player("Tyler Beach", 65, "RT", true);
        Player nine = new Player("Jake Ferguson", 84, "TE", true);
        Player ten = new Player("Mason Stokke", 34, "FB", true);
        Player eleven = new Player("Garrett Groshek", 37, "RB", true);
        
        one.setStats(7, 73);
        two.setStats(8, 250);
        three.setStats(12, 278);
        nine.setStats(12, 407);
        ten.setStats(8, 47);
        eleven.setStats(5,194);
        
        team.insert(one);
        team.insert(two);
        team.insert(three);
        team.insert(four);
        team.insert(five);
        team.insert(six);
        team.insert(seven);
        team.insert(eight);
        team.insert(nine);
        team.insert(ten);
        team.insert(eleven);
        
        
        Player oneSub = new Player("Jalen Berger", 8, "RB", false);
        Player twoSub = new Player("Jack Coan", 17, "QB", false);
        Player threeSub = new Player("Quan Easterling", 28, "FB", false);
        Player fourSub = new Player("A.J. Abbot", 89, "WR", false);
        Player fiveSub = new Player("Stephan Bracey", 10, "WR", false);
        Player sixSub = new Player("Jack Dunn", 16, "WR", false);
        Player sevenSub = new Player("Clay Cundiff", 85, "TE", false);
        Player eightSub = new Player("Gabe Lloyd", 46, "TE", false);
        Player nineSub = new Player("Jon Dietzen", 67, "OL", false);
        
        twoSub.setStats(8, 2727);
        sixSub.setStats(4, 7);
        
        team.insert(oneSub);
        team.insert(twoSub);
        team.insert(threeSub);
        team.insert(fourSub);
        team.insert(fiveSub);
        team.insert(sixSub);
        team.insert(sevenSub);
        team.insert(eightSub);
        team.insert(nineSub);
        
        return team;
        
    }
    
    /**
     * adds all the starters to a RedBlackTree
     * @return RedBlackTree with all the starters
     */
    public static RedBlackTree<Player> Starters(){
        RedBlackTree<Player> starters = new RedBlackTree<Player>();
        
        Player one = new Player("Graham Mertz", 5, "QB", true);
        Player two = new Player("Danny David III", 7, "WR", true);
        Player three = new Player("Kendric Pryor", 6, "WR", true);
        Player four = new Player("Cole Van Lanen", 71, "LT", true);
        Player five = new Player("Josh Seltzner", 70, "LG", true);
        Player six = new Player("Kayden Lyles", 76, "OC", true);
        Player seven = new Player("Logan Bruss", 60, "RG", true);
        Player eight = new Player("Tyler Beach", 65, "RT", true);
        Player nine = new Player("Jake Ferguson", 84, "TE", true);
        Player ten = new Player("Mason Stokke", 34, "FB", true);
        Player eleven = new Player("Garrett Groshek", 37, "RB", true);
        
        one.setStats(7, 73);
        two.setStats(8, 250);
        three.setStats(12, 278);
        nine.setStats(12, 407);
        ten.setStats(8, 47);
        eleven.setStats(5,194);
        
        starters.insert(one);
        starters.insert(two);
        starters.insert(three);
        starters.insert(four);
        starters.insert(five);
        starters.insert(six);
        starters.insert(seven);
        starters.insert(eight);
        starters.insert(nine);
        starters.insert(ten);
        starters.insert(eleven);
        
        return starters;
        
    }
}
