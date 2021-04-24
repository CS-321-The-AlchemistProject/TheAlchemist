public class Chemical {
    public String get_name() { return chem_name; }
    public String get_formula() { return chem_formula; }

    public double get_thermal_diff_solid() { return thermal_diffusivity_solid; }
    public double get_thermal_diff_liquid() { return thermal_diffusivity_liquid; }
    public double get_thermal_diff_gas() { return thermal_diffusivity_gas; }

    public double get_thermal_cond_solid() { return thermal_conductivity_solid; }
    public double get_thermal_cond_liquid() { return thermal_conductivity_liquid; }
    public double get_thermal_cond_gas() { return thermal_conductivity_gas; }

    public double get_sp_heat_solid() { return specific_heat_solid; }
    public double get_sp_heat_liquid() { return specific_heat_liquid; }
    public double get_sp_heat_gas() { return specific_heat_gas; }

    public double get_melt_point() { return melt_point; }
    public double get_boil_point() { return boil_point; }
    public double get_gas_density() { return gas_density; }
    public double get_liq_density() { return liq_density; }
    public double get_sol_density() { return sol_density; }
    public String get_color() { return color; }
    public String get_chem_key() { return chem_key; }
    public String get_CAS() { return CAS_num; }
    public boolean get_is_insulated() { return is_insulated; }

    public void set_name(String new_value) { chem_name = new_value; }
    public void set_formula(String new_value) { chem_formula = new_value; }

    public void set_thermal_diff_solid(double new_value) { thermal_diffusivity_solid = new_value; }
    public void set_thermal_diff_liquid(double new_value) { thermal_diffusivity_liquid = new_value; }
    public void set_thermal_diff_gas(double new_value) { thermal_diffusivity_gas = new_value; }

    public void set_thermal_cond_solid(double new_value) { thermal_conductivity_solid = new_value; }
    public void set_thermal_cond_liquid(double new_value) { thermal_conductivity_liquid = new_value; }
    public void set_thermal_cond_gas(double new_value) { thermal_conductivity_gas = new_value; }

    public void set_sp_heat_solid(double new_value) { specific_heat_solid = new_value; }
    public void set_sp_heat_liquid(double new_value) { specific_heat_liquid = new_value; }
    public void set_sp_heat_gas(double new_value) { specific_heat_gas = new_value; }

    public void set_melt_point(double new_value) { melt_point = new_value; }
    public void set_boil_point(double new_value) { boil_point = new_value; }

    public void set_gas_density(double new_value) { gas_density = new_value; }
    public void set_liq_density(double new_value) { liq_density = new_value; }
    public void set_sol_density(double new_value) { sol_density = new_value; }

    public void set_color(String new_value) { color = new_value; }
    public void set_chem_key(String new_value) { chem_key = new_value; }
    public void set_is_insulated (boolean new_value) { is_insulated = new_value; }
    public void set_CAS (String new_value) { CAS_num = new_value; }


    private String chem_name;
    private String chem_formula;
    private double thermal_diffusivity_solid;
    private double thermal_diffusivity_liquid;
    private double thermal_diffusivity_gas;
    private double thermal_conductivity_solid;
    private double thermal_conductivity_liquid;
    private double thermal_conductivity_gas;
    private double specific_heat_solid;
    private double specific_heat_liquid;
    private double specific_heat_gas;
    private double melt_point;
    private double boil_point;
    private double gas_density;
    private double liq_density;
    private double sol_density;
    private String color;
    private String chem_key;
    private boolean is_insulated;
    private String CAS_num;
}
