import java.util.ArrayList;
import javax.swing.JLabel;
import java.util.HashMap;
import java.util.Map;
public class Stream {
	private String name;  
	private ArrayList<Location> locationList = new ArrayList();
	private String image;  
	private int healthIndex;
	private String health = "N/A";
	private JLabel healthLabel;
	private Map<Integer, macroInv> speciesList;  
	private ArrayList<macroInv> foundList;  
	public Stream() {
		healthLabel = new JLabel("Health: N/A");
		speciesList = new HashMap<>();
		foundList = new ArrayList<macroInv>();
		macroInv waterPenny = new macroInv("Water Penny","./Species Images/waterpenny1.jpg",  1); 
        macroInv mayflyNymph = new macroInv("Mayfly Nymph","./Species Images/TZKR3ZSR0H3LOL7ZULRZ6LFL9L0RULXR0H2RHH4RRH5RAL3LBL7ZVLYL2LMRYZKRNLXRDZRZDLSZBLKZDLXZHHQZCL.jpg",  1); 
        macroInv stoneflyNymph = new macroInv("Stonefly Nymph","./Species Images/7LUZ8LNZQLJHGHAHMHTHWHTHXHBHMH2ZLLOHNHAZUHZR6HAHIHBH0LAZWHFH2H9ZIL8ZZL2Z4LNZEH1HHL2ZQL4ZIHOH.jpg",  1); 
        macroInv dobsonflyLarvae = new macroInv("Dobsonfly Larvae","./Species Images/eastern_dobsonfly02.jpg",  1); 
        macroInv caddisflyLarvae = new macroInv("Caddisfly Larvae","./Species Images/CaddisLarvae.jpg",  1); 
        macroInv riffleBeetleAdult = new macroInv("Rifle Beetle Adult","./Species Images/NLHZ6LZZELKZALKZPLIR3Z7RYZ7RDZHZULQRJZFLALSZQHPROL6RQH6RALIZHH0ROZ0RFZSRWLJLTZ.jpg",  1);
        macroInv gilledSnail = new macroInv("Gilled Snail","./Species Images/gilled_snail_showing_operculum_11-08-12.jpg",  1);
        macroInv damselflyNymph = new macroInv("Damselfly Nymph","./Species Images/SQFR0QJRIQJRZQYQM0NRJKDQHQURG0YQJK1R70H0W0ORW0JQG01RHQBR7QDRQQK0KQZ0W0FRQQ3RHQ.jpg",  2);
        macroInv dragonflyNymph = new macroInv("Dragonfly Nymph","./Species Images/XL9Z5LWZKLYH4HAHXHDHMHTHLLGZ7LYH5LBZ9H1ZPHJH8LHRUHZRUH1ZEHVZGLNZ2HBZ7LHR8LAZ5LJHMHDHXHVH.jpg", 2);
        macroInv craneflyNymph = new macroInv("Crane Fly Nymph","./Species Images/4HDHMHTHEHEZXLAZSL6Z4L9ZMLNZ6HLR9H9ZUHYH8LYH6HYHGLRRWHVZ4L6Z4HRR6HBZ5LBZGLRR9H1Z9HNZZL2ZSL.jpg", 2);
        macroInv beetleLarvae = new macroInv("Beetle Larvae","./Species Images/6ZZL6ZZLNZGLNZWHRR2HAHMHOH0LPZXHPZ5LWZXLUZWHVZPHUZ9HDH9H9ZEHVHEHJHPHVZ8LRREHRR.jpg", 2);
        macroInv crayfish = new macroInv("Crayfish","./Species Images/spothanded_crayfish_7-16-15.jpg", 2);
        macroInv scud = new macroInv("Scud","./Species Images/2HBHMHYHEHRRPHUZXLFHILUZEHRRGL1Z4LAZNHTHNHZR5LAZGL6ZHLEZMHAH7H1HKLDHIHYHPHYHNH.jpg", 2); 
        macroInv clam = new macroInv("Clam","./Species Images/Asian-Clam.jpg", 2); 
        macroInv sowbug = new macroInv("Sowbug","./Species Images/aquatic_isopod_8-13-15.jpg", 2); 
        macroInv blackflyLarvae = new macroInv("Blackfly Larvae","./Species Images/ZQNRQQTR7QCRKQNR50CQ40DQM0FQX0FQHQ1RM0DQG03QU0R0W0TRW0H0QQORSQL0E0K050ARLQVRFK.jpg", 1);
        macroInv aquaticWorm = new macroInv("Aquatic Worm","./Species Images/100.jpg", 1); 
        macroInv midgeLarvae = new macroInv("Midge Larvae","./Species Images/HR9HYHWHVH5L4ZZL2ZRL2ZHL5ZZL4ZHLGZGHAHXHOH8HPZKLCHGH5Z0LVHEHHR5LWZMLUZPHWZ8LVZEHVZ9HYH7LAZ.jpg", 1);
        macroInv pouchSnail = new macroInv("Pouch Snail","./Species Images/pulmonate_snail_08-18-13.jpg", 1); 
        macroInv leech = new macroInv("Leech","./Species Images/leech.jpg", 1); 
        speciesList.put(1, waterPenny);
        speciesList.put(2, mayflyNymph);
        speciesList.put(3, stoneflyNymph);
        speciesList.put(4, dobsonflyLarvae);
        speciesList.put(5, caddisflyLarvae);
        speciesList.put(6, riffleBeetleAdult);
        speciesList.put(7, gilledSnail);
        speciesList.put(8, damselflyNymph);
        speciesList.put(9, dragonflyNymph);
        speciesList.put(10, craneflyNymph);
        speciesList.put(11, beetleLarvae);
        speciesList.put(12, crayfish);
        speciesList.put(13, scud);
        speciesList.put(14, clam);
        speciesList.put(15, sowbug);
        speciesList.put(16, blackflyLarvae);
        speciesList.put(17, aquaticWorm);
        speciesList.put(18, midgeLarvae);
        speciesList.put(19, pouchSnail);
        speciesList.put(20, leech);
	} 
	public Map<Integer, macroInv> getSpeciesList() { 
		return speciesList;
	}
	public JLabel getHealthLabel() { 
		return healthLabel;
	}
	public ArrayList<Location> getLocationList() { 
		return locationList;
	}
	public void calculateHealth() {
		healthIndex = 0;
		Location currentLoc = new Location();
		for(int i = 0; i < locationList.size(); i++) { 
			currentLoc = locationList.get(i);
			for(int j = 0; j < currentLoc.getSpecificList().size(); j++) { 
				if(foundList.contains(currentLoc.getSpecificList().get(j)) == false) { 
					foundList.add(currentLoc.getSpecificList().get(j));
				}
			}
		}
		if(foundList.size() > 0) { 
			for(int i = 0; i < foundList.size(); i++) { 
				if(foundList.get(i).getGroupNum() == 1) { 
					healthIndex = healthIndex + 3;
				}
				else if(foundList.get(i).getGroupNum() == 2) { 
					healthIndex = healthIndex + 2;
				}
				else if (foundList.get(i).getGroupNum() == 3) { 
					healthIndex = healthIndex + 1;
				}
			}
		}
		if(healthIndex > 22) { 
			health = "Excellent";
		}
		else if(healthIndex >= 17) { 
			health = "Good";
		}
		else if(healthIndex >= 11) { 
			health = "Fair";
		}
		else { 
			health = "Poor";
		}
		healthLabel.setText("Health: " + health); 
	}
	public String getImage() { 
		return image;
	}
	public String getHealth() { 
		return health;
	}
	public String getName() { 
		return name; 
	}
	public void setName(String streamName) {
		name = streamName;
	}
	        
}
