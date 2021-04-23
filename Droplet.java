public class Droplet {
    
    /**
    * The update_state method will compare the temperature to the melting points and boiling points to determine what state the chemical should be in.
    */
    public void update_state() {    //Could probably combine w/ update_density()
        if (temperature < chem_type.get_melt_point())
            current_state = State.solid;
        if (temperature < chem_type.get_boil_point() && temperature >= chem_type.get_melt_point())
            current_state = State.liquid;
        if (temperature >= chem_type.get_boil_point())
            current_state = State.gas;
    }
    
    /**
    * The update_density method will check the chemical's state and give it the corresponding density for it.
    */
    public void update_density() {
        if (current_state == State.solid)
            density = chem_type.get_sol_density();
        if (current_state == State.liquid)
            density = chem_type.get_liq_density();
        if (current_state == State.gas)
            density = chem_type.get_gas_density();
    }
    
    /**
    * The update_temp will
    */
    public void update_temp() {     //^^^
        temperature = temperature + dTemp;
    }
    //public Droplet clone() {}
    /**
    * The copy_into will take the inputted droplet and set its variables equal to the one calling the function
    * @param droplet, the droplet that will have everything copied into
    */
    public void copy_into(Droplet droplet) {
        droplet.set_chem_type(chem_type);
        droplet.set_temperature(temperature);
        droplet.set_density(density);
        droplet.set_current_state(current_state);
        droplet.set_dTemp(dTemp);
        droplet.set_has_moved(has_moved);
        droplet.set_has_reacted(has_reacted);
        droplet.set_num_compressed(num_compressed);
    }

    //getters
    /**
    * The get_chem_type method will return what type of chemical the droplet is
    * @return chem_type, the type of chemical
    */ 
    public Chemical get_chem_type() { return chem_type; }
    
    /**
    * The get_temperature method will return the temperature of the droplet
    * @return temperature, the current temperature of the droplet
    */
    public double get_temperature() { return temperature; }
    
    /**
    * The get_density method will return the density of the droplet
    * @return density, the current density of the droplet
    */
    public double get_density() { return density; }
    
    /**
    * The get_current_state method will return the state(solid, liquid, gas) of the droplet
    * @return current_state, the state the droplet is in
    */
    public State get_current_state() { return current_state; }
    
    /**
    * The get_dTemp will return 
    * @return dTemp
    */
    public double get_dTemp() { return dTemp; }
    
    /**
    * The get_has_moved method will return true or false to whether the droplet has moved
    * @return has_moved, true/false
    */
    public boolean get_has_moved() { return has_moved; }
    
    /**
    * The get_has_reacted method will return true/false to whether the droplet has reacted
    * @return has_reacted, true/false
    */
    public boolean get_has_reacted() { return has_reacted; }
    
    /**
    * The get_num_compressed method will 
    */
    public int get_num_compressed() { return num_compressed; }

    //setters
    
    /**
    * The set_chem_type method will change the value of the chemical type
    * @param new_value, is the new type of chemical
    */
    public void set_chem_type(Chemical new_value) { chem_type = new_value; }
    
    /**
    * The set_temperature method will change the value of the temperature variable.
    * @param new_value, the new value of temperature
    */
    public void set_temperature(double new_value) { temperature = new_value; }
    
    /**
    * The set_density method will change the value of the density variable.
    * @param new_value, the new value of the density
    */
    public void set_density(double new_value) { density = new_value; }
    
    /**
    * The set_current_state method will change the value of the current_state variable.
    * @param new_value, the new state of the chemical
    */
    public void set_current_state(State new_value) { current_state = new_value; }
    
    /**
    * The set_dTemp method will change the value of the dtemp variable.
    * @param new_value, the change in temperature
    */
    public void set_dTemp(double new_value) { dTemp = new_value; }
    
    /**
    * The set_has_moved method will change the value of the has_moved variable.
    * @param new_value, true/false
    */
    public void set_has_moved(boolean new_value) { has_moved = new_value; }
    
    /**
    * The set_has_reacted method will change the value of the has_reacted variable.
    * @param new_value, true/false
    */
    public void set_has_reacted(boolean new_value) { has_reacted = new_value; }
    
    /**
    * The set_num_compressed method will change the value of the num_compressed variable.
    * @param new_value, the number of blocks compressed into one pixel
    */
    public void set_num_compressed(int new_value) { num_compressed = new_value; }

    private Chemical chem_type;
    private double temperature;
    private double density;
    private State current_state;
    private double dTemp;
    private boolean has_moved;
    private boolean has_reacted;
    private int num_compressed;
}
