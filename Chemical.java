public class Chemical {
    public String get_name() { return chem_name; }
    public String get_formula() { return chem_formula; }
    public double get_thermal_diff() { return thermal_diffusivity; }
    public double get_melt_point() { return melt_point; }
    public double get_boil_point() { return boil_point; }
    public double get_gas_density() { return gas_density; }
    public double get_liq_density() { return liq_density; }
    public double get_sol_density() { return sol_density; }
    public int get_color() { return color; }
    public String get_chem_key() { return chem_key; }
    public boolean get_is_insulated() { return is_insulated; }

    public void set_name(String new_value) { chem_name = new_value; }
    public void set_formula(String new_value) { chem_formula = new_value; }
    public void set_thermal_diff(double new_value) { thermal_diffusivity = new_value; }
    public void set_melt_point(double new_value) { melt_point = new_value; }
    public void set_boil_point(double new_value) { boil_point = new_value; }
    public void set_gas_density(double new_value) { gas_density = new_value; }
    public void set_liq_density(double new_value) { liq_density = new_value; }
    public void set_sol_density(double new_value) { sol_density = new_value; }
    public void set_color(int new_value) { color = new_value; }
    public void set_chem_key(String new_value) { chem_key = new_value; }
    public void set_is_insulated (boolean new_value) { is_insulated = new_value; }


    private String chem_name;
    private String chem_formula;
    private double thermal_diffusivity;
    private double melt_point;
    private double boil_point;
    private double gas_density;
    private double liq_density;
    private double sol_density;
    private int color;
    private String chem_key;
    private boolean is_insulated;
}
