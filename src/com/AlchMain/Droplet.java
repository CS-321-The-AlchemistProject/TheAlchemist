package com.AlchMain;

public class Droplet {

    /**
    * The Droplet constructor will set the chemical, temp, and the number compressed. It will also build the base state of the droplet.
    * @param chem, the chemical type
    * @param temp, the temperature 
    * @param new_compressed, the number of compressed chemicals in a single block
    */
    public Droplet(Chemical chem, double temp, int new_compressed) {
        this.chem_type = chem;
        this.temperature = temp;
        this.has_moved = false;
        this.has_reacted = false;
        this.update_state();
        this.update_state_dependencies();
        this.num_compressed = new_compressed;

    }
    
    /**
    * The update_state method will compare the temperature to the melting points and boiling points to determine what state the chemical should be in.
    */
    public void update_state() {    //Could probably combine w/ update_density()
        if (temperature < chem_type.get_melt_point())
            this.current_state = State.solid;
        if (temperature < chem_type.get_boil_point() && temperature >= chem_type.get_melt_point())
            this.current_state = State.liquid;
        if (temperature >= chem_type.get_boil_point())
            this.current_state = State.gas;
    }
    
    /**
    * The update_density method will check the chemical's state and give it the corresponding density for it.
    */
    public void update_state_dependencies() {
        if (current_state == State.solid) {
            this.density = chem_type.get_sol_density();
            this.thermal_diffusivity = chem_type.get_thermal_diff_solid();
            this.thermal_conductivity = chem_type.get_thermal_cond_solid();
            this.specific_heat = chem_type.get_sp_heat_solid();
        }
        else if (current_state == State.liquid) {
            this.density = chem_type.get_liq_density();
            this.thermal_diffusivity = chem_type.get_thermal_diff_liquid();
            this.thermal_conductivity = chem_type.get_thermal_cond_liquid();
            this.specific_heat = chem_type.get_sp_heat_liquid();
        }
        else if (current_state == State.gas) {
            this.density = chem_type.get_gas_density();
            this.thermal_diffusivity = chem_type.get_thermal_diff_gas();
            this.thermal_conductivity = chem_type.get_thermal_cond_gas();
            this.specific_heat = chem_type.get_sp_heat_gas();
        }
    }
    
    /**
    * The update_temp will add the change of temperature to the temperature variable.
    */
    public void update_temp() {
        temperature = temperature + dTemp;
        if (temperature <= 0) {
            temperature = 0.01;
        }
        else if (temperature > 1000000) {
            temperature = 1000000;
        }
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
        droplet.set_thermal_diffusivity(thermal_diffusivity);
        droplet.set_thermal_conductivity(thermal_conductivity);
        droplet.set_specific_heat(specific_heat);
        droplet.set_current_state(current_state);
        droplet.set_dTemp(dTemp);
        droplet.set_has_moved(has_moved);
        droplet.set_has_reacted(has_reacted);
        droplet.set_num_compressed(num_compressed);
    }

    public void copy_from(Droplet other) {
        this.chem_type = other.get_chem_type();
        this.temperature = other.get_temperature();
        this.density = other.get_density();
        this.thermal_diffusivity = other.get_thermal_diffusivity();
        this.thermal_conductivity = other.get_thermal_conductivity();
        this.specific_heat = other.get_specific_heat();
        this.current_state = other.get_current_state();
        this.dTemp = other.get_dTemp();
        this.has_moved = other.get_has_moved();
        this.has_reacted = other.get_has_reacted();
        this.num_compressed = other.get_num_compressed();
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
    * The get_specific_heat method will return the specific heat of the droplet
    * @return specific_heat, the specific heat of the droplet
    */
    public double get_specific_heat() { return specific_heat; }

    /**
    * The get_thermal_diffusivity method will return the thermal diffusivity of the droplet
    * @return thermal_diffusivity, the thermal diffusivity of the droplet
    */
    public double get_thermal_diffusivity() { return thermal_diffusivity; }

    /**
    * The get_thermal_conductivity method will return the thermal conductivity of the droplet
    * @return thermal_conductivity, the thermal conductivity of the droplet
    */
    public double get_thermal_conductivity() { return thermal_conductivity; }
    
    /**
    * The get_current_state method will return the state(solid, liquid, gas) of the droplet
    * @return current_state, the state the droplet is in
    */
    public State get_current_state() { return current_state; }
    
    /**
    * The get_dTemp will return the change of temperature.
    * @return dTemp, the change in value
    */
    public double get_dTemp() { return dTemp; }
    
    /**
    * The get_has_moved method will return true or false to whether the droplet has moved.
    * @return has_moved, true/false
    */
    public boolean get_has_moved() { return has_moved; }
    
    /**
    * The get_has_reacted method will return true/false to whether the droplet has reacted.
    * @return has_reacted, true/false
    */
    public boolean get_has_reacted() { return has_reacted; }
    
    /**
    * The get_num_compressed method will return the value of the number of blocks compressed into one block.
    * @return num_compressed, the number of blocks in one block
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
    public void set_temperature(double new_value) {
        temperature = new_value;
        if (temperature <= 0) {
            temperature = 0.01;
        }
        else if (temperature > 1000000) {
            temperature = 1000000;
        }
    }
    
    /**
    * The set_density method will change the value of the density variable.
    * @param new_value, the new value of the density
    */
    public void set_density(double new_value) { density = new_value; }

    /**
    * The set_specific_heat method will change the value of the specific heat variable.
    * @param new_value, the new value of the specific_heat
    */
    public void set_specific_heat(double new_value) { specific_heat = new_value; }

    /**
    * The set_thermal_diffusivity method will change the value of the thermal_diffusivity variable.
    * @param new_value, the new value of the thermal diffusivity
    */
    public void set_thermal_diffusivity(double new_value) { thermal_diffusivity = new_value; }

    /**
    * The set_thermal_conductivity method will change the value of the thermal_conductivity variable.
    * @param new_value, the new value of the thermal conductivity
    */
    public void set_thermal_conductivity(double new_value) { thermal_conductivity = new_value; }
    
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
    private double thermal_diffusivity;
    private double thermal_conductivity;
    private double specific_heat;
    private State current_state;
    private double dTemp;
    private boolean has_moved;
    private boolean has_reacted;
    private int num_compressed;
}
