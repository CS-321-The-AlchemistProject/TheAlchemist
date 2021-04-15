public class Droplet {
    public void update_state() {    //Could probably combine w/ update_density()
        if (temperature < chem_type.get_melt_point())
            current_state = State.solid;
        if (temperature < chem_type.get_boil_point() && temperature >= chem_type.get_melt_point())
            current_state = State.liquid;
        if (temperature >= chem_type.get_boil_point())
            current_state = State.gas;
    }
    public void update_density() {
        if (current_state == State.solid)
            density = chem_type.get_sol_density();
        if (current_state == State.liquid)
            density = chem_type.get_liq_density();
        if (current_state == State.gas)
            density = chem_type.get_gas_density();
    }
    public void update_temp() {     //^^^
        temperature = temperature + dTemp;
    }
    //public Droplet clone() {}
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

    public Chemical get_chem_type() { return chem_type; }
    public double get_temperature() { return temperature; }
    public double get_density() { return density; }
    public State get_current_state() { return current_state; }
    public double get_dTemp() { return dTemp; }
    public boolean get_has_moved() { return has_moved; }
    public boolean get_has_reacted() { return has_reacted; }
    public int get_num_compressed() { return num_compressed; }

    public void set_chem_type(Chemical new_value) { chem_type = new_value; }
    public void set_temperature(double new_value) { temperature = new_value; }
    public void set_density(double new_value) { density = new_value; }
    public void set_current_state(State new_value) { current_state = new_value; }
    public void set_dTemp(double new_value) { dTemp = new_value; }
    public void set_has_moved(boolean new_value) { has_moved = new_value; }
    public void set_has_reacted(boolean new_value) { has_reacted = new_value; }
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
