import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.coobird.thumbnailator.Thumbnails;//A library created by coobird 
public class GraphicsManager implements ActionListener, WindowListener{
	private JPanel panel;
	private JFrame frame;
	private Stream stream; 
	private JTabbedPane tp;
	private JFileChooser chooser;
	private String fileName;
	private Map<JCheckBox,macroInv> cbGetMacroInv;
	private FileNameExtensionFilter filter; 
	private JLabel imageLabel;
	private boolean isImage = false;
	private String rep = "";
	public GraphicsManager() { 
		filter = new FileNameExtensionFilter("JPG, GIF, PNG Images", "jpg", "gif", "png");//Choosing image file
		chooser = new JFileChooser();
		panel = new JPanel(); 
		frame = new JFrame("Stream Index Calculator");//title of JFrame
		stream = new Stream();
		cbGetMacroInv = new HashMap<>();
		JTabbedPane ntp = new JTabbedPane(); 
		tp = ntp;
		fileName = "";
		imageLabel = new JLabel();
	}
	public void makeFrame() { 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.addWindowListener(this);
	}
	public void streamSetup() { 
		//Asks for stream name
	    String streamName = JOptionPane.showInputDialog(frame, "What is the name of this Stream?");
	    stream.setName(streamName);
        //Add buttons 
        JButton addLocation = new JButton("Add Location");
        JButton calculateHealth = new JButton("Calculate Health");
        calculateHealth.addActionListener(this); 
        calculateHealth.setActionCommand("3");
        JButton addImage = new JButton("Add Image");
        panel.setLayout(new FlowLayout());
        panel.add(addLocation);
        panel.add(calculateHealth); 
        panel.add(addImage);
        addLocation.addActionListener(this);  
        addLocation.setActionCommand("1");
        addImage.addActionListener(this);  
        addImage.setActionCommand("4");
        //health display 
        JLabel homeHealthLabel = new JLabel();
		homeHealthLabel = stream.getHealthLabel();
        homeHealthLabel.setBounds(360, 548, 81, 16);
        panel.add(homeHealthLabel);
	}
	public void chooseImage() throws IOException { 
		//Chooses image
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
        	fileName = chooser.getSelectedFile().getAbsolutePath();
        }
        Image img = ImageIO.read(new File(fileName));
 	    Image newImage = img.getScaledInstance(800,500, Image.SCALE_SMOOTH); //Scales image
 	    ImageIcon icon = new ImageIcon(newImage);
 	    imageLabel.setBackground(Color.BLACK);
 	    //markerButton 
 	    if(isImage == false) { 
 	    	JButton markerButton = new JButton("Add Marker"); 
 	 	    markerButton.addActionListener(this);
 	 	    markerButton.setActionCommand("marker");
 	 	    panel.add(markerButton);
 	 	    isImage = true;
 	    }
		panel.add(imageLabel);
	    imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    imageLabel.setBackground(new Color(238, 238, 238));
 	    imageLabel.setIcon(icon);
	}
	public void tabManage() { 
		//adds panel to JTabbedPane
		tp.addTab(stream.getName(), panel);
        frame.getContentPane().add(tp); 
	} 
	public void speciesPage() throws IOException { 
		JPanel Species = new JPanel();
        Species.setLayout(new BoxLayout(Species, BoxLayout.Y_AXIS));
        //Adds Species name label and image
        for(int i = 1; i <= 20; i++) { 
        	JPanel speciesPanel = new JPanel(new BorderLayout());
            JLabel name = new JLabel();
            name.setFont(new Font("Serif", Font.PLAIN, 30));
            JLabel picture = new JLabel();
            JLabel space = new JLabel(" ");
            space.setFont(new Font("Serif", Font.PLAIN, 90));
            File fileName2 = new File(stream.getSpeciesList().get(i).getImage());
            BufferedImage pic = ImageIO.read(fileName2);
            BufferedImage newPic = Thumbnails.of(pic).scale(0.5).asBufferedImage();//thumbnailator library created by coobird imported from Stack Overflow 
    	    ImageIcon icn = new ImageIcon(newPic);
    	    picture.setIcon(icn);
        	name.setText(stream.getSpeciesList().get(i).getName());
        	speciesPanel.add(name, BorderLayout.CENTER);
        	speciesPanel.add(picture, BorderLayout.SOUTH);
        	Species.add(speciesPanel); 
        	Species.add(space);
        } 
        //Allows scrolling in the panel and changes scroll speed
        JScrollPane scrollFrame = new JScrollPane(Species);
        scrollFrame.getVerticalScrollBar().setUnitIncrement(12);
        tp.addTab("Macroinvertabrates", scrollFrame); 
	} 
	public void setVisible() { //Makes frame visible
		frame.setVisible(true);  
	} 
	public void addLocation() {//Adds a location tab to frame
	    String locationName = JOptionPane.showInputDialog(frame, "What is the name of this Location?");
	    Location newLocation = new Location();
	    newLocation.setName(locationName);
	    JLabel locationHealthLabel = newLocation.getHealthLabel();
	    JLabel locationDateLabel = newLocation.getDateLabel();
	    JLabel locationTempLabel = newLocation.getTempLabel();
		stream.getLocationList().add(newLocation);
		JLabel nameLabel = new JLabel(newLocation.getName()); 
		//Location screen buttons
		JButton calcHealth = new JButton("Calculate Health");
		JButton setTemp = new JButton("Set Temperature");
		JButton setDate = new JButton("Set Date");
		calcHealth.addActionListener(this); 
		calcHealth.setActionCommand("2");
		setTemp.addActionListener(this); 
		setTemp.setActionCommand("5");
		setDate.addActionListener(this); 
		setDate.setActionCommand("6");
		nameLabel.setFont(new Font("Serif", Font.PLAIN, 30));
		locationHealthLabel.setFont(new Font("Serif", Font.PLAIN, 30));
		locationDateLabel.setFont(new Font("Serif", Font.PLAIN, 30));
		locationTempLabel.setFont(new Font("Serif", Font.PLAIN, 30));
		JPanel Species = new JPanel();
		JPanel namePanel = new JPanel();
		JPanel healthPanel = new JPanel();
		JPanel tempPanel = new JPanel();
		JPanel datePanel = new JPanel();
		namePanel.add(nameLabel); 
        healthPanel.add(locationHealthLabel); 
        datePanel.add(locationDateLabel);  
        tempPanel.add(locationTempLabel); 
        Species.add(namePanel, BorderLayout.NORTH);
		Species.add(healthPanel, BorderLayout.NORTH);
		Species.add(datePanel, BorderLayout.NORTH);
		Species.add(tempPanel, BorderLayout.NORTH);
		Species.add(calcHealth, BorderLayout.NORTH);
		Species.add(setDate, BorderLayout.NORTH);
		Species.add(setTemp, BorderLayout.NORTH);
        Species.setLayout(new BoxLayout(Species, BoxLayout.Y_AXIS));
        //Adds checkboxes
        for(int i = 0; i < 20; i++) { 
			newLocation.getCheckBoxList().get(i).addActionListener(this);
			newLocation.getCheckBoxList().get(i).setActionCommand(stream.getSpeciesList().get(i + 1).getName());
			cbGetMacroInv.put(newLocation.getCheckBoxList().get(i), stream.getSpeciesList().get(i + 1));
		}
        for(int i = 1; i <= 20; i++) { 
        	JPanel speciesPanel = new JPanel(new BorderLayout());
            JLabel name = new JLabel();
            name.setFont(new Font("Serif", Font.PLAIN, 30));
            JLabel space = new JLabel(" ");
            space.setFont(new Font("Serif", Font.PLAIN, 90));
        	name.setText(stream.getSpeciesList().get(i).getName());
        	speciesPanel.add(name, BorderLayout.NORTH);
        	speciesPanel.add(newLocation.getCheckBoxList().get(i-1), BorderLayout.CENTER);
        	Species.add(speciesPanel); 
        	Species.add(space);
        } 
		JScrollPane scrollFrame = new JScrollPane(Species);
		scrollFrame.getVerticalScrollBar().setUnitIncrement(12);
		tp.addTab(newLocation.getName(), scrollFrame);
	}
	public void reportList() throws FileNotFoundException { //Saves a report
		JPanel report = new JPanel();
		Scanner scan = new Scanner(new FileReader("Report.txt")); 
	    while (scan.hasNextLine()) { 
	      rep = rep + "\n" + scan.nextLine(); 
	    } 
		JTextArea reportText = new JTextArea(rep);
		report.add(reportText);
		JScrollPane SF = new JScrollPane(report);
		SF.getVerticalScrollBar().setUnitIncrement(12);
		tp.addTab("Report List", SF);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("1")) { 
			this.addLocation();
		}
		if(e.getActionCommand().equals("2")) { 
			Location currentLoc = new Location();
			currentLoc = stream.getLocationList().get(tp.getSelectedIndex()-3);
			currentLoc.calculateHealth();
			
		}
		if(e.getActionCommand().equals("3")) { 
			stream.calculateHealth();
		}
		if(e.getActionCommand().equals("5")) { 
			String temp = JOptionPane.showInputDialog(frame, "What was the temperature at this location?");
			Location currentLoc = new Location();
			currentLoc = stream.getLocationList().get(tp.getSelectedIndex()-3);
			currentLoc.setTemp(temp);
		}
		if(e.getActionCommand().equals("6")) { 
			String date = JOptionPane.showInputDialog(frame, "What was date of the measurement at this location?");
			Location currentLoc = new Location();
			currentLoc = stream.getLocationList().get(tp.getSelectedIndex()-3);
			currentLoc.setDate(date);
		}
		if(e.getActionCommand().equals("4")) { 
			try {
				this.chooseImage();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getActionCommand().equals("marker")) { 
			String markerName = JOptionPane.showInputDialog(frame, "What is the name of this Marker?"); 
			//MyDraggableComponent is a class borrowed from stackoverflow by ivan_ivanovich_ivanoff
	        MyDraggableComponent mc = new MyDraggableComponent(markerName);
	    	imageLabel.add(mc);
		}
		if(e.getActionCommand().equals("Water Penny")) { 
			Location currentLoc = new Location();
			currentLoc = stream.getLocationList().get(tp.getSelectedIndex()-3);
			if(((JCheckBox)(e.getSource())).isSelected()) { 
				currentLoc.addToSpecificList(stream.getSpeciesList().get(1));
			}
			else if(currentLoc.getSpecificList().contains(stream.getSpeciesList().get(1))){ 
				currentLoc.removeFromSpecificList(stream.getSpeciesList().get(1));
			} 
			
		}
		if(e.getActionCommand().equals("Mayfly Nymph")) { 
			Location currentLoc = new Location();
			currentLoc = stream.getLocationList().get(tp.getSelectedIndex()-3);
			if(((JCheckBox)(e.getSource())).isSelected()) { 
				currentLoc.addToSpecificList(stream.getSpeciesList().get(2));
			}
			else if(currentLoc.getSpecificList().contains(stream.getSpeciesList().get(2))){ 
				currentLoc.removeFromSpecificList(stream.getSpeciesList().get(2));
			} 
			
		}
		if(e.getActionCommand().equals("Stonefly Nymph")) { 
			Location currentLoc = new Location();
			currentLoc = stream.getLocationList().get(tp.getSelectedIndex()-3);
			if(((JCheckBox)(e.getSource())).isSelected()) { 
				currentLoc.addToSpecificList(stream.getSpeciesList().get(3));
			}
			else if(currentLoc.getSpecificList().contains(stream.getSpeciesList().get(3))){ 
				currentLoc.removeFromSpecificList(stream.getSpeciesList().get(3));
			} 
			
		}
		if(e.getActionCommand().equals("Dobsonfly Larvae")) { 
			Location currentLoc = new Location();
			currentLoc = stream.getLocationList().get(tp.getSelectedIndex()-3);
			if(((JCheckBox)(e.getSource())).isSelected()) { 
				currentLoc.addToSpecificList(stream.getSpeciesList().get(4));
			}
			else if(currentLoc.getSpecificList().contains(stream.getSpeciesList().get(4))){ 
				currentLoc.removeFromSpecificList(stream.getSpeciesList().get(4));
			} 
			
		}
		if(e.getActionCommand().equals("Caddisfly Larvae")) { 
			Location currentLoc = new Location();
			currentLoc = stream.getLocationList().get(tp.getSelectedIndex()-3);
			if(((JCheckBox)(e.getSource())).isSelected()) { 
				currentLoc.addToSpecificList(stream.getSpeciesList().get(5));
			}
			else if(currentLoc.getSpecificList().contains(stream.getSpeciesList().get(5))){ 
				currentLoc.removeFromSpecificList(stream.getSpeciesList().get(5));
			} 
			
		}
		if(e.getActionCommand().equals("Rifle Beetle Adult")) { 
			Location currentLoc = new Location();
			currentLoc = stream.getLocationList().get(tp.getSelectedIndex()-3);
			if(((JCheckBox)(e.getSource())).isSelected()) { 
				currentLoc.addToSpecificList(stream.getSpeciesList().get(6));
			}
			else if(currentLoc.getSpecificList().contains(stream.getSpeciesList().get(6))){ 
				currentLoc.removeFromSpecificList(stream.getSpeciesList().get(6));
			} 
			
		}
		if(e.getActionCommand().equals("Gilled Snail")) { 
			Location currentLoc = new Location();
			currentLoc = stream.getLocationList().get(tp.getSelectedIndex()-3);
			if(((JCheckBox)(e.getSource())).isSelected()) { 
				currentLoc.addToSpecificList(stream.getSpeciesList().get(7));
			}
			else if(currentLoc.getSpecificList().contains(stream.getSpeciesList().get(7))){ 
				currentLoc.removeFromSpecificList(stream.getSpeciesList().get(7));
			} 
			
		}
		if(e.getActionCommand().equals("Damselfly Nymph")) { 
			Location currentLoc = new Location();
			currentLoc = stream.getLocationList().get(tp.getSelectedIndex()-3);
			if(((JCheckBox)(e.getSource())).isSelected()) { 
				currentLoc.addToSpecificList(stream.getSpeciesList().get(8));
			}
			else if(currentLoc.getSpecificList().contains(stream.getSpeciesList().get(8))){ 
				currentLoc.removeFromSpecificList(stream.getSpeciesList().get(8));
			} 
			
		}
		if(e.getActionCommand().equals("Dragonfly Nymph")) { 
			Location currentLoc = new Location();
			currentLoc = stream.getLocationList().get(tp.getSelectedIndex()-3);
			if(((JCheckBox)(e.getSource())).isSelected()) { 
				currentLoc.addToSpecificList(stream.getSpeciesList().get(9));
			}
			else if(currentLoc.getSpecificList().contains(stream.getSpeciesList().get(9))){ 
				currentLoc.removeFromSpecificList(stream.getSpeciesList().get(9));
			} 
			
		}
		if(e.getActionCommand().equals("Crane Fly Nymph")) { 
			Location currentLoc = new Location();
			currentLoc = stream.getLocationList().get(tp.getSelectedIndex()-3);
			if(((JCheckBox)(e.getSource())).isSelected()) { 
				currentLoc.addToSpecificList(stream.getSpeciesList().get(10));
			}
			else if(currentLoc.getSpecificList().contains(stream.getSpeciesList().get(10))){ 
				currentLoc.removeFromSpecificList(stream.getSpeciesList().get(10));
			} 
			
		}
		if(e.getActionCommand().equals("Beetle Larvae")) { 
			Location currentLoc = new Location();
			currentLoc = stream.getLocationList().get(tp.getSelectedIndex()-3);
			if(((JCheckBox)(e.getSource())).isSelected()) { 
				currentLoc.addToSpecificList(stream.getSpeciesList().get(11));
			}
			else if(currentLoc.getSpecificList().contains(stream.getSpeciesList().get(11))){ 
				currentLoc.removeFromSpecificList(stream.getSpeciesList().get(11));
			} 
			
		}
		if(e.getActionCommand().equals("Crayfish")) { 
			Location currentLoc = new Location();
			currentLoc = stream.getLocationList().get(tp.getSelectedIndex()-3);
			if(((JCheckBox)(e.getSource())).isSelected()) { 
				currentLoc.addToSpecificList(stream.getSpeciesList().get(12));
			}
			else if(currentLoc.getSpecificList().contains(stream.getSpeciesList().get(12))){ 
				currentLoc.removeFromSpecificList(stream.getSpeciesList().get(12));
			} 
			
		}
		if(e.getActionCommand().equals("Scud")) { 
			Location currentLoc = new Location();
			currentLoc = stream.getLocationList().get(tp.getSelectedIndex()-3);
			if(((JCheckBox)(e.getSource())).isSelected()) { 
				currentLoc.addToSpecificList(stream.getSpeciesList().get(13));
			}
			else if(currentLoc.getSpecificList().contains(stream.getSpeciesList().get(13))){ 
				currentLoc.removeFromSpecificList(stream.getSpeciesList().get(13));
			} 
			
		}
		if(e.getActionCommand().equals("Clam")) { 
			Location currentLoc = new Location();
			currentLoc = stream.getLocationList().get(tp.getSelectedIndex()-3);
			if(((JCheckBox)(e.getSource())).isSelected()) { 
				currentLoc.addToSpecificList(stream.getSpeciesList().get(14));
			}
			else if(currentLoc.getSpecificList().contains(stream.getSpeciesList().get(14))){ 
				currentLoc.removeFromSpecificList(stream.getSpeciesList().get(14));
			} 
			
		}
		if(e.getActionCommand().equals("Sowbug")) { 
			Location currentLoc = new Location();
			currentLoc = stream.getLocationList().get(tp.getSelectedIndex()-3);
			if(((JCheckBox)(e.getSource())).isSelected()) { 
				currentLoc.addToSpecificList(stream.getSpeciesList().get(15));
			}
			else if(currentLoc.getSpecificList().contains(stream.getSpeciesList().get(15))){ 
				currentLoc.removeFromSpecificList(stream.getSpeciesList().get(15));
			} 
			
		}
		if(e.getActionCommand().equals("Blackfly Larvae")) { 
			Location currentLoc = new Location();
			currentLoc = stream.getLocationList().get(tp.getSelectedIndex()-3);
			if(((JCheckBox)(e.getSource())).isSelected()) { 
				currentLoc.addToSpecificList(stream.getSpeciesList().get(16));
			}
			else if(currentLoc.getSpecificList().contains(stream.getSpeciesList().get(16))){ 
				currentLoc.removeFromSpecificList(stream.getSpeciesList().get(16));
			} 
			
		}
		if(e.getActionCommand().equals("Aquatic Worm")) { 
			Location currentLoc = new Location();
			currentLoc = stream.getLocationList().get(tp.getSelectedIndex()-3);
			if(((JCheckBox)(e.getSource())).isSelected()) { 
				currentLoc.addToSpecificList(stream.getSpeciesList().get(17));
			}
			else if(currentLoc.getSpecificList().contains(stream.getSpeciesList().get(17))){ 
				currentLoc.removeFromSpecificList(stream.getSpeciesList().get(17));
			} 
			
		}
		if(e.getActionCommand().equals("Midge Larvae")) { 
			Location currentLoc = new Location();
			currentLoc = stream.getLocationList().get(tp.getSelectedIndex()-3);
			if(((JCheckBox)(e.getSource())).isSelected()) { 
				currentLoc.addToSpecificList(stream.getSpeciesList().get(18));
			}
			else if(currentLoc.getSpecificList().contains(stream.getSpeciesList().get(18))){ 
				currentLoc.removeFromSpecificList(stream.getSpeciesList().get(18));
			} 
			
		}
		if(e.getActionCommand().equals("Pouch Snail")) { 
			Location currentLoc = new Location();
			currentLoc = stream.getLocationList().get(tp.getSelectedIndex()-3);
			if(((JCheckBox)(e.getSource())).isSelected()) { 
				currentLoc.addToSpecificList(stream.getSpeciesList().get(19));
			}
			else if(currentLoc.getSpecificList().contains(stream.getSpeciesList().get(19))){ 
				currentLoc.removeFromSpecificList(stream.getSpeciesList().get(19));
			} 
			
		}
		if(e.getActionCommand().equals("Leech")) { 
			Location currentLoc = new Location();
			currentLoc = stream.getLocationList().get(tp.getSelectedIndex()-3);
			if(((JCheckBox)(e.getSource())).isSelected()) { 
				currentLoc.addToSpecificList(stream.getSpeciesList().get(20));
			}
			else if(currentLoc.getSpecificList().contains(stream.getSpeciesList().get(20))){ 
				currentLoc.removeFromSpecificList(stream.getSpeciesList().get(20));
			} 
			
		}
		
	}
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		try
        {//Report Structure
			rep = stream.getName() + "\n_______________________" + "\n   Overall Health: " + stream.getHealth() + 
		            "\n   Locations\n"; 
			for(int i = 0; i < stream.getLocationList().size(); i++) { 
				Location temp = stream.getLocationList().get(i);
				rep = rep + "     - " + temp.getName()
				+ "\n         Date of Report: " + temp.getDate()
				+ "\n         Temperature at Location: " + temp.getTemperature() 
				+ "\n         Health of Location: " + temp.getHealth()
				+ "\n         Species Found";
				for(int j = 0; j < temp.getSpecificList().size(); j++) { 
					rep = rep + "\n          - " + temp.getSpecificList().get(j).getName();
				} 
				rep = rep + "\n";
			}
			rep = rep + "\n\n\n\n\n";
            FileWriter file = new FileWriter("Report.txt", true);
            file.write(rep) ;
            file.close();
        }
        catch ( IOException e1 )
        {
            System.out.println("error");
        }
	}
}
