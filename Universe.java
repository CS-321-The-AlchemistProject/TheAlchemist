import java.util.*;

public class Universe {
    //Functional methods
    public Droplet get_neighbor(int shift_x, int shift_y) {
        return universe[current_x + shift_x][current_y + shift_y];
    }
    public void update_universe() {
        for (current_x = 0; current_x < screen_width; current_x++) {    //Primes all droplets for update
            for (current_y = 0; current_y < screen_height; current_y++) {
                universe[current_x][current_y].set_has_moved(false);
                universe[current_x][current_y].set_has_reacted(false);
            }
        }
        for (current_x = 0; current_x < screen_width; current_x++) {    //Reacts all droplets
            for (current_y = 0; current_y < screen_height; current_y++) {
                react();
            }
        }
        for (current_x = 0; current_x < screen_width; current_x++) {    //Handles most displacement events
            for (current_y = 0; current_y < screen_height; current_y++) {
                displace(current_x, current_y);
            }
        }
        for (current_x = 0; current_x < screen_width; current_x++) {    //Calculates dTemp_dt's
            for (current_y = 0; current_y < screen_height; current_y++) {
                universe[current_x][current_y].set_dTemp(calculate_dTemp());
            }
        }
        for (current_x = 0; current_x < screen_width; current_x++) {    //Updates temps of all droplets
            for (current_y = 0; current_y < screen_height; current_y++) {
                universe[current_x][current_y].update_temp();
            }
        }
        for (current_x = 0; current_x < screen_width; current_x++) {    //Updates state & density of droplets
            for (current_y = 0; current_y < screen_height; current_y++) {
                universe[current_x][current_y].update_state();
                universe[current_x][current_y].update_density();
            }
        }
        for (current_x = 0; current_x < screen_width; current_x++) {    //Updates position of all droplets
            for (current_y = 0; current_y < screen_height; current_y++) {
                fall();
            }
        }
    }
    public double calculate_dTemp() {
        double top_temp;
        double bottom_temp;
        double left_temp;
        double right_temp;

        if (get_neighbor(0, 1).get_chem_type().get_is_insulated())
            top_temp = universe[current_x][current_y].get_temperature();
        else
            top_temp = get_neighbor(0, 1).get_temperature();

        if (get_neighbor(0, -1).get_chem_type().get_is_insulated())
            bottom_temp = universe[current_x][current_y].get_temperature();
        else
            bottom_temp = get_neighbor(0, 1).get_temperature();

        if (get_neighbor(-1, 0).get_chem_type().get_is_insulated())
            left_temp = universe[current_x][current_y].get_temperature();
        else
            left_temp = get_neighbor(0, 1).get_temperature();

        if (get_neighbor(1, 0).get_chem_type().get_is_insulated())
            right_temp = universe[current_x][current_y].get_temperature();
        else
            right_temp = get_neighbor(0, 1).get_temperature();

        return (universe[current_x][current_y].get_chem_type().get_thermal_diff() / inv_dx_squared)
                * (top_temp + bottom_temp + left_temp + right_temp
                - 4 * universe[current_x][current_y].get_temperature()) * dt;
    }
    public void react() {
        //Check if any neighbors react with current particle from reaction database.

        //If no, do nothing.

        //If yes, add each possible reaction to a list.

        //Choose reaction randomly based off of probability of it occurring (determined from
        //energy released (the more energy released the higher the chance that reaction
        //is selected)).

        //Change chem_type of the droplets that reacted to the new type.

        //Update temperature of both new droplets based of the energy released/absorbed.

        //Update num_compressed of both droplets based on balanced reaction equation.
    }
    public boolean is_empty(int x, int y) { return universe[x][y] == null; }
    public void swap(int x_1, int y_1, int x_2, int y_2) {
        Droplet temp = null;    //Idk if this works or not
        assert false;
        universe[x_1][y_1].copy_into(temp);
        universe[x_2][y_2].copy_into(universe[x_1][y_1]);
        temp.copy_into(universe[x_2][y_2]);
    }
    public void fall() {
        if (universe[current_x][current_y].get_current_state() == State.solid) {
            //Solid movement rules. Consult video and articles I sent over discord.
        }
        else if (universe[current_x][current_y].get_current_state() == State.liquid) {
            //Liquid movement rules. Consult video I sent over discord.
        }
        else {
            //Gas movement rules. Moves in random direction. If we feelin spicy, we could make them
            //move more if their temperature is higher.
        }
    }
    public void displace(int x, int y) {
        int max = 1;
        int min = 4;
        
        if (universe[x][y].get_num_compressed() > 1) {
            
            //Randomly pick direction (up(1), down(3), left(4),  right(2))
            int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
            System.out.println(random_int);
            Droplet temp_compressed = universe[x][y];
            
            switch (random_int) {
                case 1:
                    //up
                    if (is_empty(x, y)){
                        universe[x][y] = universe[x][y+1];
                    }
                case 2:
                    //right
                    if (is_empty(x, y)){
                    universe[x][y] = universe[x+1][y];
                    }
                case 3:
                    //down
                    if (is_empty(x, y)){
                    universe[x][y] = universe[x][y-1];
                    }
                case 4:
                    //left
                    if (is_empty(x, y)){
                    universe[x][y] = universe[x-1][y];
                    }
            }
            
            //Then move all contacting droplets over in that direction
            //(if not possible pick new direction) (also set has_moved for
            //each droplet moved this way to true).
            universe[x][y].set_has_moved(true);
            
            //Then create copy(?) of compressed droplet in recently opened up space
            //(has_moved for this newly created droplet will be true).
            temp_compressed.set_has_moved(true);
            
            //Then set num_compressed of recently created droplet to
            //num_compressed of the original droplet minus 1, and set
            //num compressed of original droplet to 1.
            temp_compressed.set_num_compressed(universe[x][y].get_num_compressed()-1);
            universe[x][y].set_num_compressed(1);
            
            //Then call displace() on the droplet that was created.
            displace(x, y);
        }
    }
    public void add_material() {
        //Replace empty spaces within brush radius with target chemical
    }
    public void remove_material() {
        //Set all non empty spaces within bush radius to empty type. Also change
        //other variables to default values.
    }
    public void generate_atmosphere() {
        //Randomly generate atmosphere based on composition percentages,
        //initial temp of atmosphere, and initial density of atmosphere.

        //For each empty space, atmospheric density determines the chance that a droplet is generated
        //there. Then composition percentages determine which chemical is generated there
        //based on weighted random selection.
    }

    //Getters
    public Droplet get_droplet(int x, int y) { return universe[x][y]; }
    public int get_cursor_x() { return cursor_x; }
    public int get_cursor_y() { return cursor_y; }
    public int get_current_x() { return current_x; }
    public int get_current_y() { return current_y; }
    public double get_g() { return g; }
    public double get_pressure_constant() { return pressure_constant; }
    public double get_dt() { return dt; }
    public double get_dx() { return dx; }
    public double get_inv_dx_squared() { return inv_dx_squared; }

    //Setters
    public void set_droplet(int x, int y, Droplet new_value) { universe[x][y] = new_value; }
    public void set_cursor_x(int new_value) { cursor_x = new_value; }
    public void set_cursor_y(int new_value) { cursor_y = new_value; }
    public void set_current_x(int new_value) { current_x = new_value; }
    public void set_current_y(int new_value) { current_y = new_value; }
    public void set_g(double new_value) { g = new_value; }
    public void set_pressure_constant(double new_value) { pressure_constant = new_value; }
    public void set_dt(double new_value) { dt = new_value; }
    public void set_dx(double new_value) {
        dx = new_value;
        inv_dx_squared = 1 / (dx * dx);
    }

    //Variables
    private Droplet[][] universe;
    private int cursor_x;
    private int cursor_y;
    private int current_x;
    private int current_y;
    private int screen_width; //Temporary variables
    private int screen_height; //Temporary variables
    private static double g;
    private static double pressure_constant;
    private static double dt;
    private static double dx;
    public static double inv_dx_squared;
}
