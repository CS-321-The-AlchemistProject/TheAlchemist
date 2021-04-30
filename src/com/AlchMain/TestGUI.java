package AlchMain;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;

public class TestGUI extends Canvas implements Runnable, MouseListener, MouseMotionListener {

    public static final int WIDTH = 640;
    public static final int HEIGHT = WIDTH / 12 * 9;

    private Thread thread;
    private boolean running = false;
    int[][] grid;
    int mx;
    int my;
    boolean drawing = false;

    public TestGUI() {
        grid = new int[WIDTH-50][HEIGHT-75];
        for (int n = 0; n < WIDTH-50; n += 1) {
            for (int m = 0; m < HEIGHT-75; m += 1) {
                grid[n][m] = 0;
            }
        }

        addMouseListener(this);
        addMouseMotionListener(this);

        new TestDisplay(WIDTH, HEIGHT, "The Alchemist", this);
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
                    if (n > 26 && n < WIDTH-27 && m > 26 && m < HEIGHT-27-25) {
                        grid[n-25][m-25] = 3;
                    }
                }
            }
        }

        for (int n = 0; n < WIDTH-50; n += 1) {
            for (int m = 0; m < HEIGHT-75; m += 1) {


//                if (grid[n][m] == 0) {
//                    grid[n][m] = 1;
//                }
//                else if (grid[n][m] == 1) {
//                    grid[n][m] = 2;
//                }
//                else if (grid[n][m] == 2) {
//                    grid[n][m] = 0;
//                }
            }
        }
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        render_matrix(g);

        g.dispose();
        bs.show();
    }

    public void render_matrix(Graphics g) {
        for (int n = 0; n < WIDTH-50; n += 1) {
            for (int m = 0; m < HEIGHT-75; m += 1) {
                if (grid[n][m] == 0) {
                    g.setColor(new Color(0, 0, 0));
                    g.fillRect(n + 25, m + 25, 1, 1);
                }
                else if (grid[n][m] == 1) {
                    g.setColor(new Color(200, 0, 0));
                    g.fillRect(n + 25, m + 25, 1, 1);
                }
                else if (grid[n][m] == 2) {
                    g.setColor(new Color(0, 180, 200));
                    g.fillRect(n + 25, m + 25, 1, 1);
                }
                else if (grid[n][m] == 3) {
                    g.setColor(new Color(0, 250, 100));
                    g.fillRect(n + 25, m + 25, 1, 1);
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

    public static void main(String[] args) {
        new TestGUI();
    }
}

