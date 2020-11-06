import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
//This class is borrowed from ivan_ivanovich_ivanoff on stack overflow
public class MyDraggableComponent extends JComponent {

  private volatile int screenX = 0;
  private volatile int screenY = 0;
  private volatile int myX = 0;
  private volatile int myY = 0;

  public MyDraggableComponent(String s) {
    setBorder(new LineBorder(Color.BLUE, 3));
    setOpaque(true);
    setBackground(Color.RED);
    setBounds(100, 100, 20, 20);
    setToolTipText(s);
    addMouseListener(new MouseListener() {

      @Override
      public void mouseClicked(MouseEvent e) { }

      @Override
      public void mousePressed(MouseEvent e) {
        screenX = e.getXOnScreen();
        screenY = e.getYOnScreen();

        myX = getX();
        myY = getY();
      }

      @Override
      public void mouseReleased(MouseEvent e) { }

      @Override
      public void mouseEntered(MouseEvent e) { }

      @Override
      public void mouseExited(MouseEvent e) { }

    });
    addMouseMotionListener(new MouseMotionListener() {

      @Override
      public void mouseDragged(MouseEvent e) {
        int deltaX = e.getXOnScreen() - screenX;
        int deltaY = e.getYOnScreen() - screenY;

        setLocation(myX + deltaX, myY + deltaY);
      }

      @Override
      public void mouseMoved(MouseEvent e) { }

    });
  }

}