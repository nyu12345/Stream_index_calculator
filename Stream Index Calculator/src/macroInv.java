import javax.swing.*;

public class macroInv {
	private String name; 
	private String image; 
	private int groupNum; 
	private JCheckBox cb;
	
	public macroInv(String n, String i, int g) { 
		name = n; 
		image = i; 
		groupNum = g; 
		cb = new JCheckBox("Found");
	} 
	public String getName() { 
		return name;
	} 
	public String getImage() { 
		return image;
	}
	public int getGroupNum() { 
		return groupNum;
	}
	public JCheckBox getCheckBox() { 
		return cb;
	}
}
