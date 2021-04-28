package AlchMain;

import java.util.*;
import java.lang.Math;
 
public class Universe {
    //Functional methods
    
    /**
    * the get_neighbor method will use the inputted shifts to find the particle at those coordinates
    * @param shift_x will add its value to the current x coordinate.
    * @param shift_y will add its value to the current y coordinate.
    * @return the Droplet that is located at those coordinates
    */
    public Droplet get_neighbor(int shift_x, int shift_y) {
        return universe[current_x + shift_x][current_y + shift_y];
    }
    
    /**
    * The update_universe method will update for every frame and call the methods in the class.
    */
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
                universe[current_x][current_y].update_state_dependancies();
            }
        }
        for (current_x = 0; current_x < screen_width; current_x++) {    //Updates position of all droplets
            for (current_y = 0; current_y < screen_height; current_y++) {
                fall();
            }
        }
    }
    
    /**
    * The calculate_dTemp method will go through different conditions and calculate the correct change in temperature.
    * @return the value of the change of temperature
    */
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

        return (universe[current_x][current_y].get_chem_type().get_thermal_diff_solid() / inv_dx_squared)
                * (top_temp + bottom_temp + left_temp + right_temp
                - 4 * universe[current_x][current_y].get_temperature()) * dt;
    }
 
 /***************************************************************
 */
    public void react(int x, int y) {
        Droplet above = universe[x][y+1];
        Droplet below = universe[x][y-1];
        Droplet left = universe[x-1][y];
        Droplet right = universe[x+1][y];
        Droplet center = universe[x][y];

        double above_temp = above.get_temperature() + center.get_temperature();
        double below_temp = below.get_temperature() + center.get_temperature();
        double left_temp = left.get_temperature() + center.get_temperature();
        double right_temp = right.get_temperature() + center.get_temperature();
        double center_temp = center.get_temperature();

        String above_reactants = center.get_chem_type().get_formula()
                + above.get_chem_type().get_formula();
        String below_reactants = center.get_chem_type().get_formula()
                + below.get_chem_type().get_formula();
        String left_reactants = center.get_chem_type().get_formula()
                + left.get_chem_type().get_formula();
        String right_reactants = center.get_chem_type().get_formula()
                + right.get_chem_type().get_formula();

        ArrayList<Reaction> above_reactions = get_reaction(above_reactants);
        ArrayList<Reaction> below_reactions = get_reaction(below_reactants);
        ArrayList<Reaction> left_reactions = get_reaction(left_reactants);
        ArrayList<Reaction> right_reactions = get_reaction(right_reactants);

        double temp_R_ln_k = 0.0;
        double max_R_ln_k_above = -1.0;
        int index_of_max_above = 0;
        for (int n; n < above_reactions.size(); n++) {
            temp_R_ln_k = above_reactions.get(n).get_delta_entropy()
                    - above_reactions.get(n).get_delta_enthalpy() / above_temp;
            if (temp_R_ln_k > max_R_ln_k_above) {
                max_R_ln_k_above = temp_R_ln_k;
                index_of_max_above = n;
            }
        }
        double max_R_ln_k_below = -1.0;
        int index_of_max_below = 0;
        for (int n; n < below_reactions.size(); n++) {
            temp_R_ln_k = below_reactions.get(n).get_delta_entropy()
                    - below_reactions.get(n).get_delta_enthalpy() / below_temp;
            if (temp_R_ln_k > max_R_ln_k_below) {
                max_R_ln_k_below = temp_R_ln_k;
                index_of_max_below = n;
            }
        }
        double max_R_ln_k_left = -1.0;
        int index_of_max_left = 0;
        for (int n; n < left_reactions.size(); n++) {
            temp_R_ln_k = left_reactions.get(n).get_delta_entropy()
                    - left_reactions.get(n).get_delta_enthalpy() / left_temp;
            if (temp_R_ln_k > max_R_ln_k_left) {
                max_R_ln_k_left = temp_R_ln_k;
                index_of_max_left = n;
            }
        }
        float max_R_ln_k_right = -1.0;
        int index_of_max_right = 0;
        for (int n; n < right_reactions.size(); n++) {
            temp_R_ln_k = right_reactions.get(n).get_delta_entropy()
                    - right_reactions.get(n).get_delta_enthalpy() / right_temp;
            if (temp_R_ln_k > max_R_ln_k_right) {
                max_R_ln_k_right = temp_R_ln_k;
                index_of_max_right = n;
            }
        }

        ArrayList<float> = max_R_ln_k_s;
        ArrayList<Reaction> = possible_reactions;
        max_R_ln_k_s.add(max_R_ln_k_above);
        possible_reactions.add(above_reactants.get(index_of_max_above);
        max_R_ln_k_s.add(max_R_ln_k_below);
        possible_reactions.add(below_reactants.get(index_of_max_below);
        max_R_ln_k_s.add(max_R_ln_k_left);
        possible_reactions.add(left_reactants.get(index_of_max_left);
        max_R_ln_k_s.add(max_R_ln_k_right);
        possible_reactions.add(right_reactants.get(index_of_max_right);
        Reaction chosen_reaction;
        double max_R_ln_k = -1.0;
        int chesen_identifier = -1;
        for (int n = 0; n < 4; n++) {
            if (max_R_ln_k_s.get(n) > max_R_ln_k) {
                max_R_ln_k = max_R_ln_k_s.get(n);
                chosen_reaction = possible_reactions.get(n);
                chosen_identifier = n;
            }
        }

        if (max_R_ln_k <= 0) {
            return;
        }
        else {
            Chemical product1 = chosen_reaction.get_products.get(0);
            if (chosen_reaction.get_products.size() > 1) {
                Chemical product2 = chosen_reaction.get_products.get(1);
                int num_compressed_1 = chosen_reaction.get_product_coefficients().get(0);
                int num_compressed_2 = chosen_reaction.get_product_coefficients().get(1);
            }
            else {
                Chemical product2 = chosen_reaction.get_products.get(0);
                int num_compressed_1 = Math.floor(chosen_reaction.get_product_coefficients().get(0) / 2);
                int num_compressed_2 = chosen_reaction.get_product_coefficients().get(0) - num_compressed_1;
            }
            if (chosen_identifier == 0) {
                universe[x][y] = new Droplet(product1, center_temp, num_compressed_1);
                universe[x][y+1] = new Droplet(product2, above_temp, num_compressed_2);
                double new_center_temp = center_temp + 0.5 * chosen_reaction.get_delta_enthaply()
                        / universe[x][y].get_specific_heat();
                double new_above_temp = above_temp + 0.5 * chosen_reaction.get_delta_enthaply()
                        / universe[x][y+1].get_specific_heat();
                universe[x][y].set_temperature(new_center_temp);
                universe[x][y+1].set_temperature(new_above_temp);
            }
            else if (chosen_identifier == 1) {
                universe[x][y] = new Droplet(product1, center_temp, num_compressed_1);
                universe[x][y-1] = new Droplet(product2, below_temp, num_compressed_2);
                double new_center_temp = center_temp + 0.5 * chosen_reaction.get_delta_enthaply()
                        / universe[x][y].get_specific_heat();
                double new_below_temp = below_temp + 0.5 * chosen_reaction.get_delta_enthaply()
                        / universe[x][y-1].get_specific_heat();
                universe[x][y].set_temperature(new_center_temp);
                universe[x][y-1].set_temperature(new_below_temp);
            }
            else if (chosen_identifier == 2) {
                universe[x][y] = new Droplet(product1, center_temp, num_compressed_1);
                universe[x-1][y] = new Droplet(product2, left_temp, num_compressed_2);
                double new_center_temp = center_temp + 0.5 * chosen_reaction.get_delta_enthaply()
                        / universe[x][y].get_specific_heat();
                double new_left_temp = left_temp + 0.5 * chosen_reaction.get_delta_enthaply()
                        / universe[x-1][y].get_specific_heat();
                universe[x][y].set_temperature(new_center_temp);
                universe[x-1][y].set_temperature(new_left_temp);
            }
            else if (chosen_identifier == 3) {
                universe[x][y] = new Droplet(product1, center_temp, num_compressed_1);
                universe[x+1][y] = new Droplet(product2, right_temp, num_compressed_2);
                double new_center_temp = center_temp + 0.5 * chosen_reaction.get_delta_enthaply()
                        / universe[x][y].get_specific_heat();
                double new_right_temp = right_temp + 0.5 * chosen_reaction.get_delta_enthaply()
                        / universe[x+1][y].get_specific_heat();
                universe[x][y].set_temperature(new_center_temp);
                universe[x+1][y].set_temperature(new_right_temp);
            }
        }
    }
    
    /**
    * The is_empty method will check if the inputted coordinates are empty.
    * @param x is the x axis coordinate
    * @param y is the y axis coordinate 
    * @return true/false
    */
    public boolean is_empty(int x, int y) { return universe[x][y] == null; }
    
 /**
 * The swap method will swap two droplets positions
 * @param x_1, the x coordinate of the first droplet
 * @param y_1, the y coordinate of the first droplet
 * @param x_2, the x coordinate of the second droplet
 * @param y_2, the y coordinate of the second droplet
 */
    public void swap(int x_1, int y_1, int x_2, int y_2) {
        Droplet temp = new Droplet(H20, 100);    //Idk if this works or not
        universe[x_1][y_1].copy_into(temp);
        universe[x_2][y_2].copy_into(universe[x_1][y_1]);
        temp.copy_into(universe[x_2][y_2]);
    }
    
    /**
    * The fall method will be called each frame to determine if the pixel should be moved. It will first check the pixel's state to determine the movement pattern.
    * Once that is determined it will check the surrounding pixels to see if they are empty and move to an empty location. It then marks a boolean as true to prevent
    * it from being moved more than once per frame.
    */
    public void fall() {
     //Just in case, all the DisplacementY are just replacing 1. So if we need to change it back start with that.
        //we have gravity for acceleration
        //Velocity = initial Velocity - acceleration * time
        //Velocity = 0 - 10 *time
        long Time = System.currentTimeMillis();
        long Velocity = 10*Time;
        long Disp = 1/2*Velocity*Time;
        //convert to integer
        int DisplacementY =(int) Disp;
       if (universe[current_x][current_y].get_current_state() == State.solid) {
            //Solid movement rules. Consult video and articles I sent over discord. 
            boolean BelowEmpty = is_empty(current_x, current_y - 1); // variable that checks the below block
            if(BelowEmpty == true){
                //copies the current droplet into the new position
                universe[current_x][current_y].copy_into(universe[current_x][current_y - DisplacementY]);
                universe[current_x][current_y - DisplacementY].set_has_moved(true); // marks that it has been move
                set_droplet(current_x, current_y, null); //makes the current spot empty
            }
            else if (universe[current_x][current_y - 1].get_density() > universe[current_x][current_y].get_density()){
                //it will check the right and left lower blocks to see if empty
                boolean belowEmptyRight = is_empty(current_x + 1, current_y - 1);
                boolean belowEmptyLeft = is_empty(current_x - 1, current_y - 1);
              if(belowEmptyLeft == true){
                  //moves the block left if it is empty and the one below is full
                  universe[current_x][current_y].copy_into(universe[current_x - 1][current_y - DisplacementY]);
                  universe[current_x - 1][current_y - DisplacementY].set_has_moved(true);
                  set_droplet(current_x, current_y, null);
              }
              else if(belowEmptyRight == true){
                  // if the right side is empty the block will move there.
                  universe[current_x][current_y].copy_into(universe[current_x + 1][current_y - DisplacementY]);
                  universe[current_x][current_y - DisplacementY].set_has_moved(true);
                  set_droplet(current_x, current_y, null);
              }
            else if (universe[current_x][current_y].get_density() > universe[current_x][current_y - 1].get_density()){
                //we need to swap the blocks (I need to check if this work)
                Droplet temp = get_droplet(current_x, current_y - 1);
                universe[current_x][current_y].copy_into(universe[current_x][current_y -1]);
                set_droplet(current_x, current_y, temp);
                universe[current_x][current_y].set_has_moved(true);
                universe[current_x][current_y - 1].set_has_moved(true);
            }
           //if the solid does not meet any of the requirements it will stay in the same spot
        }
        else if (universe[current_x][current_y].get_current_state() == State.liquid) {
            //Liquid movement rules. Consult video I sent over discord.
            //Liquids will check all the below and to the sides
            // I will need to add a random integer to prevent it from only moving left first
         //we use the same gravity for the y axis but we need to find it for x
            int SpreadRate = 3;
            long DispX = 1/2 * SpreadRate * Time;
            int DisplacementX = (int) DispX;
            boolean BelowEmptyLiquid = is_empty(current_x, current_y - 1); // variable that checks the below block 
            if(BelowEmptyLiquid == true){
                universe[current_x][current_y].copy_into(universe[current_x][current_y - DisplacementY]);
                universe[current_x][current_y - DisplacementY].set_has_moved(true); // marks that it has been move
                set_droplet(current_x, current_y, null); //makes the current spot empty
            }
            else if (universe[current_x][current_y].get_density() < universe[current_x][current_y - 1].get_density()){
                boolean BelowEmptyLeftL = is_empty(current_x -1, current_y - 1);
                boolean BelowEmptyRightL = is_empty(current_x +1, current_y -1);
                boolean LeftLiquid = is_empty(current_x - 1, current_y);
                boolean RightLiquid = is_empty(current_x + 1, current_y);
                //above gets whether all the surrounding blocks are empty
                if(BelowEmptyLeftL == true){
                    universe[current_x][current_y].copy_into(universe[current_x - 1][current_y - DisplacementY]);
                    set_droplet(current_x, current_y, null);
                    universe[current_x - 1][current_y - DisplacementY].set_has_moved(true);
                }
                else if(BelowEmptyRightL ==true){
                    universe[current_x][current_y].copy_into(universe[current_x + 1][current_y - DisplacementY]);
                    universe[current_x + 1][current_y - DisplacementY].set_has_moved(true);
                    set_droplet(current_x, current_y, null);
                }
                else if(LeftLiquid == true){
                 for(int i =0; i< SpreadRate; i++){
                   LeftLiquid = is_empty(current_x - 1, current_y);
                   if(LeftLiquid == true){
                     universe[current_x][current_y].copy_into(universe[current_x - 1][current_y]);
                     universe[current_x - 1][current_y].set_has_moved(true);
                     set_droplet(current_x, current_y, null);
                   }
                   else{
                     break;
                   }
                  }
                }
                else if (RightLiquid == true){
                    for(int i =0; i< SpreadRate; i++){
                        RightLiquid = is_empty(current_x + 1, current_y);
                        if(RightLiquid == true){
                            universe[current_x][current_y].copy_into(universe[current_x + 1][current_y]);
                            universe[current_x + 1][current_y].set_has_moved(true);
                            set_droplet(current_x, current_y, null);
                        }
                        else{
                            break;
                        }
                    }
                }
            }
            else if (universe[current_x][current_y].get_density() > universe[current_x][current_y].get_density()){
                //this means it needs to be swapped
                Droplet temp = get_droplet(current_x, current_y - 1);
                universe[current_x][current_y].copy_into(universe[current_x][current_y -1]);
                set_droplet(current_x, current_y, temp);
                universe[current_x][current_y].set_has_moved(true);
                universe[current_x][current_y - 1].set_has_moved(true);
            }
            //if it does not meet any of these conditions it will stay still
        }
        else if (universe[current_x][current_y].get_current_state() == State.gas) {
            //Gas movement rules. Moves in random direction. If we feelin spicy, we could make them
            //move more if their temperature is higher.
            int randomInteger = (int)Math.floor(Math.random()*(8-1+1)+1);
            switch(randomInteger){
             
             //will decide which random direction
                case 1:
                    //up
                    if(is_empty(current_x, current_y +1)){
                        universe[current_x][current_y].copy_into(universe[current_x][current_y + 1]);
                        universe[current_x][current_y + 1].set_has_moved(true);
                        set_droplet(current_x, current_y, null);
                        break;
                    }
                case 2:
                    //up right
                    if(is_empty(current_x + 1, current_y + 1)){
                        universe[current_x][current_y].copy_into(universe[current_x + 1][current_y + 1]);
                        set_droplet(current_x, current_y, null);
                        universe[current_x + 1][current_y + 1].set_has_moved(true);
                        break;
                    }
                case 3:
                    //up left
                    if(is_empty(current_x - 1, current_y + 1)){
                        universe[current_x][current_y].copy_into(universe[current_x - 1][current_y + 1]);
                        set_droplet(current_x, current_y, null);
                        universe[current_x - 1][current_y +1].set_has_moved(true);
                        break;
                    }
                case 4:
                    //right
                    if(is_empty(current_x + 1, current_y)){
                        universe[current_x][current_y].copy_into(universe[current_x + 1][current_y]);
                        set_droplet(current_x, current_y, null);
                        universe[current_x + 1][current_y].set_has_moved(true);
                        break;
                    }
                case 5:
                    //left
                    if(is_empty(current_x - 1, current_y)){
                        universe[current_x][current_y].copy_into(universe[current_x - 1][current_y]);
                        set_droplet(current_x, current_y, null);
                        universe[current_x - 1][current_y].set_has_moved(true);
                        break;
                    }
                case 6:
                    //below
                    if(is_empty(current_x, current_y - 1)){
                        universe[current_x][current_y].copy_into(universe[current_x][current_y -1]);
                        set_droplet(current_x, current_y, null);
                        universe[current_x][current_y - 1].set_has_moved(true);
                        break;
                    }
                case 7:
                    //below right
                    if(is_empty(current_x + 1, current_y - 1)){
                        universe[current_x][current_y].copy_into(universe[current_x + 1][current_y - 1]);
                        set_droplet(current_x,current_y,null);
                        universe[current_x + 1][current_y - 1].set_has_moved(true);
                        break;
                    }
                case 8:
                    //below left
                    if(is_empty(current_x - 1, current_y - 1)){
                        universe[current_x][current_y].copy_into(universe[current_x - 1][current_y - 1]);
                        set_droplet(current_x, current_y, null);
                        universe[current_x - 1][current_y - 1].set_has_moved(true);
                        break;
                //default:
                     //break;
                     
                    }
            }
            
        }
    }
    }
    
    /**
    * The displace method will spread the chemicals out if there are more than one in a single pixel
    * @param x, the coordinate in the x-axis
    * @param y, the coordinate in the y-axis
    */
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
                    break;
                case 2:
                    //right
                    if (is_empty(x, y)){
                    universe[x][y] = universe[x+1][y];
                    }
                    break;
                case 3:
                    //down
                    if (is_empty(x, y)){
                    universe[x][y] = universe[x][y-1];
                    }
                    break;
                case 4:
                    //left
                    if (is_empty(x, y)){
                    universe[x][y] = universe[x-1][y];
                    }
                    break;
                default:
                    break;
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
 /************************************************************************
 */
    public void add_material() {
        //Replace empty spaces within brush radius with target chemical
    }
 
 /************************************************************************
 */
    public void remove_material() {
        //Set all non empty spaces within bush radius to empty type. Also change
        //other variables to default values.
    }
 /****************************************************************************
 */
    public void generate_atmosphere() {
        //Randomly generate atmosphere based on composition percentages,
        //initial temp of atmosphere, and initial density of atmosphere.

        //For each empty space, atmospheric density determines the chance that a droplet is generated
        //there. Then composition percentages determine which chemical is generated there
        //based on weighted random selection.
    }

    //Getters
    
    /**
    * The get_droplet method will use the inputted coordinates and return the droplet at that location.
    * @param x is the coordinate value for the x-axis.
    * @param y is the coordinate value for the y-axis.
    * @return the droplet at the coordinate values.
    */
    public Droplet get_droplet(int x, int y) { return universe[x][y]; }
    
    /**
    * The get_cursor_x method will return the mouse position on the x-axis.
    * @return cursor_x the mouse position on the x-axis.
    */
    public int get_cursor_x() { return cursor_x; }
    
    /**
    * The get_cursor_y method will return the mouse position on the y-axis.
    * @return cursor_y the mouse position on the y-axis.
    */
    public int get_cursor_y() { return cursor_y; }
    
    /**
    * The get_current_x will return the value of the current coordinate on the x-axis.
    * @return current_x the current x coordinate
    */
    public int get_current_x() { return current_x; }
    
    /**
    * The get_current_y will return the value of the current coordinate on the y-axis.
    * @return current_y the current y coordinate
    */
    public int get_current_y() { return current_y; }
    
    /**
    * The get_g method will return the value of the variable g.
    * @return g, the value of g
    */
    public double get_g() { return g; }
    
    /**
    * The get_pressure_constant method will return the value of the constant pressure.
    * @return pressure_constant's value
    */
    public double get_pressure_constant() { return pressure_constant; }
    
    /**
    * The get_dt method will return the value of the variable dt
    * @return dt, the value of the variable dt
    */
    public double get_dt() { return dt; }
    /**
    * The get_dx method will return the value of the variable dx
    * @return dx, the value of the variable dx
    */
    public double get_dx() { return dx; }
    /**
    * The get_inv_dx_squared method will return the value of the variable inv_dx_squared
    * @return inv_dx_squared, the value of the variable inv_dx_squared
    */
    public double get_inv_dx_squared() { return inv_dx_squared; }

    //Setters
    
    /**
    * The set_droplet method will take the inputted coordinate and will have the droplet at that position equal to the new droplet.
    * @param x, the coordinate on the x-axis
    * @param y, the coordinate on the y-axis
    * @param new_value, the new droplet
    */
    public void set_droplet(int x, int y, Droplet new_value) { universe[x][y] = new_value; }
    
    /**
    * The set_cursor_x method will make the variable cursor_x equal the new inputted value.
    * @param new_value, the new mouse position on the x-axis
    */
    public void set_cursor_x(int new_value) { cursor_x = new_value; }
    
    /**
    * The set_cursor_y method will make the variable cursor_y equal the new inputted value.
    * @param new_value, the new mouse position on the y-axis
    */
    public void set_cursor_y(int new_value) { cursor_y = new_value; }
    
    /**
    * The set_current_x method will make the variable current_x equal the new inputted value.
    * @param new_value, the new coordinate on the x-axis
    */
    public void set_current_x(int new_value) { current_x = new_value; }
    
    /**
    * The set_current_y method will make the variable current_y equal the new inputted value.
    * @param new_value, the new coordinate on the y-axis
    */
    public void set_current_y(int new_value) { current_y = new_value; }
    
    /**
    * The set_g method will change the value of the variable g.
    * @param new_value, the new value of the g variable
    */
    public void set_g(double new_value) { g = new_value; }
    
    /**
    * The set_pressure_constant method will make the variable pressure_constant equal the new inputted value.
    * @param new_value, the new pressure constant
    */
    public void set_pressure_constant(double new_value) { pressure_constant = new_value; }
    
    /**
    * The set_dt method will make the variable dt equal the new inputted value.
    * @param new_value, the new dt value
    */
    public void set_dt(double new_value) { dt = new_value; }
    
    /**
    * The set_dx method will take the new value and set the variable dx equal to it.
    * It will change the value of the inv_dx_squared variable as well.
    * @param new_value, it is the new value of dx variable
    */
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
