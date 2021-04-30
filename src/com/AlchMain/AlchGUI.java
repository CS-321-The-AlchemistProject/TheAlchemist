package AlchMain;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;

public class AlchGUI extends Canvas implements Runnable, MouseListener, MouseMotionListener,
        ActionListener {

    public static final int DISPLAY_WIDTH = 1000;
    public static final int DISPLAY_HEIGHT = 750;

    public static final int SHIFT_X = 10;
    public static final int SHIFT_Y = 0;

    public static final int UNIVERSE_WIDTH = 640;
    public static final int UNIVERSE_HEIGHT = 480;

    private Thread thread;
    private boolean running = false;
    Universe universe;
    ChemicalDatabase cd;
    Color[][] color_matrix;
    int mx;
    int my;
    boolean drawing = false;
    public static String brush_chem = "H2O";
    public static double brush_temp = 273.15;

    public AlchGUI() {
        universe = new Universe(UNIVERSE_WIDTH, UNIVERSE_HEIGHT);

        addMouseListener(this);
        addMouseMotionListener(this);



        cd = new ChemicalDatabase();
        cd.initialize_db();

        color_matrix = new Color[UNIVERSE_WIDTH][UNIVERSE_HEIGHT];
        for (int n = 0; n < UNIVERSE_WIDTH; n += 1) {
            for (int m = 0; m < UNIVERSE_HEIGHT; m += 1) {
                color_matrix[n][m] = parse_color_from_droplet(universe.get_droplet(n, m));
            }
        }

        new UniverseDisplay(DISPLAY_WIDTH, DISPLAY_HEIGHT, "The Alchemist's Sandbox", this);
    }

    public Color parse_color_from_droplet(Droplet droplet) {
        if (droplet == null) {
            return new Color(0, 0, 0);
        }
        String color_string = droplet.get_chem_type().get_color();
        String delims = "[ rgb(),]+";
        String[] split_color = color_string.split(delims);
        int[] color_values = new int[3];
        for (int n = 0; n < 3; n += 1) {
            if (split_color[n] != "") {
                color_values[n] = Integer.parseInt(split_color[n]);
            }
        }
        return new Color(color_values[0], color_values[1], color_values[2]);
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta -= 1;
            }
            if (running) {
                render();
            }
            frames += 1;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick() {
        if (drawing) {
            for (int n = mx - 15; n < mx + 15; n += 1) {
                for (int m = my - 15; m < my + 15; m += 1) {
                    if (n > SHIFT_X+1 && n < UNIVERSE_WIDTH+SHIFT_X-2
                            && m > SHIFT_Y+1 && m < UNIVERSE_HEIGHT+SHIFT_Y-2) {
                        if (cd.search(brush_chem) != -1) {
                            universe.set_droplet(n - SHIFT_X, m - SHIFT_Y, new Droplet(
                                    cd.get_chemical(cd.search(brush_chem)), brush_temp, 1));
                        }
                        else {
                            universe.set_droplet(n - SHIFT_X, m - SHIFT_Y, null);
                        }
                    }
                }
            }
        }

       universe.update_universe();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        render_color_matrix(g);

        g.dispose();
        bs.show();
    }

    public void render_color_matrix(Graphics g) {
        for (int n = 0; n < UNIVERSE_WIDTH; n += 1) {
            for (int m = 0; m < UNIVERSE_HEIGHT; m += 1) {
                Color temp_color = parse_color_from_droplet(universe.get_droplet(n, m));
                if (temp_color != color_matrix[n][m]) {
                    color_matrix[n][m] = temp_color;
                    g.setColor(color_matrix[n][m]);
                    g.fillRect(n + SHIFT_X, m + SHIFT_Y, 1, 1);
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        mx = e.getX();
        my = e.getY();
        drawing = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mx = e.getX();
        my = e.getY();
        drawing = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mx = e.getX();
        my = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mx = e.getX();
        my = e.getY();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        String s = e.getActionCommand();
//        if (s.equals("submit1")) {
//            this.brush_chem = UniverseDisplay.text1.getText();
//        }
//        else if (s.equals("submit2")) {
//            this.brush_temp = Double.parseDouble(UniverseDisplay.text2.getText());
//        }
    }

//    public void set_brush_chem(String new_value) { this.brush_chem = new_value; }
//    public void set_brush_temp(double new_value) { this.brush_temp = new_value; }

    public static void main(String[] args) {
        new AlchGUI();
    }
}
