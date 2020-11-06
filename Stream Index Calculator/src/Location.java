import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.*;

public class Location{
	private String name;  
	private String date = "N/A"; 
	private String temperature = "N/A";
	private String health = "N/A";
	private ArrayList<macroInv> specificList;
	private int healthIndex; 
	private JPanel tab; 
	private JLabel healthLabel;
	private JLabel tempLabel;
	private JLabel dateLabel;
	private ArrayList<JCheckBox> checkBoxList;
	public Location() { 
		JPanel p = new JPanel(new BorderLayout());  
		healthLabel = new JLabel("Health: " + health);
		tempLabel = new JLabel("Temperature: " + temperature);
		dateLabel = new JLabel("Date: " + date);
		tab = p;
		specificList = new ArrayList<>();
		checkBoxList = new ArrayList<JCheckBox>();
		JCheckBox c1 = new JCheckBox("Found");    
		JCheckBox c2 = new JCheckBox("Found");    
		JCheckBox c3 = new JCheckBox("Found");    
		JCheckBox c4 = new JCheckBox("Found");    
		JCheckBox c5 = new JCheckBox("Found");    
		JCheckBox c6 = new JCheckBox("Found");    
		JCheckBox c7 = new JCheckBox("Found");    
		JCheckBox c8 = new JCheckBox("Found");    
		JCheckBox c9 = new JCheckBox("Found");    
		JCheckBox c10 = new JCheckBox("Found");    
		JCheckBox c11 = new JCheckBox("Found");    
		JCheckBox c12 = new JCheckBox("Found");    
		JCheckBox c13 = new JCheckBox("Found");    
		JCheckBox c14 = new JCheckBox("Found");    
		JCheckBox c15 = new JCheckBox("Found");    
		JCheckBox c16 = new JCheckBox("Found");    
		JCheckBox c17 = new JCheckBox("Found");    
		JCheckBox c18 = new JCheckBox("Found");    
		JCheckBox c19 = new JCheckBox("Found");    
		JCheckBox c20 = new JCheckBox("Found");   
		checkBoxList.add(c1);
		checkBoxList.add(c2);
		checkBoxList.add(c3);
		checkBoxList.add(c4);
		checkBoxList.add(c5);
		checkBoxList.add(c6);
		checkBoxList.add(c7);
		checkBoxList.add(c8);
		checkBoxList.add(c9);
		checkBoxList.add(c10);
		checkBoxList.add(c11);
		checkBoxList.add(c12); 
		checkBoxList.add(c13);
		checkBoxList.add(c14);
		checkBoxList.add(c15);
		checkBoxList.add(c16);
		checkBoxList.add(c17);
		checkBoxList.add(c18);
		checkBoxList.add(c19);
		checkBoxList.add(c20); 
	} 
	public JLabel getHealthLabel() { 
		return healthLabel;
	}
	public JLabel getDateLabel() { 
		return dateLabel;
	}
	public JLabel getTempLabel() { 
		return tempLabel;
	}
	public ArrayList<JCheckBox> getCheckBoxList() { 
		return checkBoxList;
	}
	public JPanel getTab() { 
		return tab;
	}
	public String getName() { 
		return name;
	}
	public String getTemperature() { 
		return temperature;
	}
	public String getDate() { 
		return date;
	}
	public String getHealth() { 
		return health;
	}
	public void setTemp(String t) { 
		temperature = t;
		tempLabel.setText("Temperature: " + temperature); 
	}
	public void setDate(String d) { 
		date = d;
		dateLabel.setText("Date: " + date); 
	}
	public void calculateHealth() { 
		healthIndex = 0;
		if(specificList.size() > 0) { 
			for(int i = 0; i < specificList.size(); i++) { 
				if(specificList.get(i).getGroupNum() == 1) { 
					healthIndex = healthIndex + 3;
				}
				else if(specificList.get(i).getGroupNum() == 2) { 
					healthIndex = healthIndex + 2;
				}
				else if (specificList.get(i).getGroupNum() == 3) { 
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
	public void addToSpecificList(macroInv m) { 
		specificList.add(m);
	}
	public void removeFromSpecificList(macroInv m) { 
		specificList.remove(m);
	}
	public ArrayList<macroInv> getSpecificList() { 
		return specificList;
	}
	public void setName(String locationName) {
		name = locationName;
		
	}

	public String toString() { 
		return this.name;
	}

}
