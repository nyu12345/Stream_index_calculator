
import java.io.IOException;

public class IARunner {
	
	

	public static void main(String args[]) throws IOException{
        GraphicsManager main = new GraphicsManager();
        main.makeFrame();
        main.streamSetup();
        main.tabManage(); 
        main.speciesPage();
        main.reportList();
        main.setVisible(); 
    }
	
}
