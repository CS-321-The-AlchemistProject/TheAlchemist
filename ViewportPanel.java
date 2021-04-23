import javax.swing.JPanel;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class ViewportPanel extends JPanel{

    int GWidth= 800;
    int GHeight= 600;

    ViewportPanel() {
        this.setPreferredSize(new Dimension(GWidth, GHeight));
    }
        public void paint(Graphics AlchRender){
            Graphics2D AlchViewport = (Graphics2D) AlchRender;
            /*PUT CODE FOR RENDERING IN VIEWPORT HERE*/
            //AlchViewport.DRAWFUNCTION(details)

        }
    }

