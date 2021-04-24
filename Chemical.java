public class Chemical {
    
    /**
    * The get_name method will return the name of the chemical.
    * @return chem_name, the name of the chemical
    */
    public String get_name() { return chem_name; }
    
    /**
    * The get_formula method will return the formula of the chemical.
    * @return chem_formula, the formula of the chemical
    */
    public String get_formula() { return chem_formula; }

    /**
    * The get_thermal_diff_solid method will return the value of the thermal diffusivity of the solid.
    * @return thermal_diffusivity_solid, the value of the thermal diffusivity
    */
    public double get_thermal_diff_solid() { return thermal_diffusivity_solid; }
    /**
    * The get_thermal_diff_liquid method will return the value of the thermal diffusivity of the liquid.
    * @return thermal_diffusivity_liquid, the value of the thermal diffusivity
    */
    public double get_thermal_diff_liquid() { return thermal_diffusivity_liquid; }
    /**
    * The get_thermal_diff_gas method will return the value of the thermal diffusivity of the gas.
    * @return thermal_diffusivity_gas, the value of the thermal diffusivity
    */
    public double get_thermal_diff_gas() { return thermal_diffusivity_gas; }

    /**
    * The get_thermal_cond_solid method will return the value of the thermal conductivity of the solid.
    * @return thermal_conductivity_solid, the value of the thermal conductivity
    */
    public double get_thermal_cond_solid() { return thermal_conductivity_solid; }
    /**
    * The get_thermal_cond_liquid method will return the value of the thermal conductivity of the liquid.
    * @return thermal_conductivity_liquid, the value of the thermal conductivity
    */
    public double get_thermal_cond_liquid() { return thermal_conductivity_liquid; }
    /**
    * The get_thermal_cond_gas method will return the value of the thermal conductivity of the gas.
    * @return thermal_conductivity_gas, the value of the thermal conductivity
    */
    public double get_thermal_cond_gas() { return thermal_conductivity_gas; }

    /**
    * The get_sp_heat_solid method will return the value of the specific heat of the solid.
    * @return specific_heat_solid, the value of the specific heat of the solid
    */
    public double get_sp_heat_solid() { return specific_heat_solid; }
    /**
    * The get_sp_heat_liquid method will return the value of the specific heat of the liquid.
    * @return specific_heat_liquid, the value of the specific heat of the liquid
    */
    public double get_sp_heat_liquid() { return specific_heat_liquid; }
    /**
    * The get_sp_heat_gas method will return the value of the specific heat of the gas.
    * @return specific_heat_gas, the value of the specific heat of the gas
    */
    public double get_sp_heat_gas() { return specific_heat_gas; }
    
    /**
    * The get_melt_point method will return the value of the melting point for the chemical.
    * @return melt_point, the value of the melting point
    */
    public double get_melt_point() { return melt_point; }
    /**
    * The get_boil_point method will return the value of the boiling point for the chemical.
    * @return boil_point, the value of the boiling point
    */
    public double get_boil_point() { return boil_point; }
    /**
    * The get_gas_density method will return the value of the density for the gas.
    * @return gas_density, the value of the density
    */
    public double get_gas_density() { return gas_density; }
    /**
    * The get_liq_density method will return the value of the density for the liquid.
    * @return liq_density, the value of the density for the liquid
    */
    public double get_liq_density() { return liq_density; }
    /**
    * The get_sol_density method will return the value of the density for the solid.
    * @return sol_density, the value of the density for the solid
    */
    public double get_sol_density() { return sol_density; }
    /**
    * The get_color method will return the color of the chemical.
    * @return color, the color of the chemical
    */
    public String get_color() { return color; }
    /**
    * The get_chem_key method will return the chemical key of the chemical.
    * @return chem_key, the key of the chemical
    */
    public String get_chem_key() { return chem_key; }
    /**
    * The get_CAS method will return the CAS(unique numerical identifier) of the chemical.
    * @return CAS_num, the the unique numerical identifier of the chemical
    */
    public String get_CAS() { return CAS_num; }
    /**
    * The get_is_insulated method will return a true or false depending if the chemical is a barrier.
    * @return is_insulated, true/false
    */
    public boolean get_is_insulated() { return is_insulated; }

    /**
    * The set_name method will change the chemical's name to the inputted value.
    * @param new_value, the new name for the chemical
    */
    public void set_name(String new_value) { chem_name = new_value; }
    /**
    * The set_formula method will change the chemical's formula to the inputted value.
    * @param new_value, the new formula for the chemical
    */
    public void set_formula(String new_value) { chem_formula = new_value; }

    /**
    * The set_thermal_diff_solid method will change the solid's thermal diffusivity to the inputted value.
    * @param new_value, the new value for the thermal diffusivity
    */
    public void set_thermal_diff_solid(double new_value) { thermal_diffusivity_solid = new_value; }
    /**
    * The set_thermal_diff_liquid method will change the liquid's thermal diffusivity to the inputted value.
    * @param new_value, the new value for the thermal diffusivity
    */
    public void set_thermal_diff_liquid(double new_value) { thermal_diffusivity_liquid = new_value; }
    /**
    * The set_thermal_diff_gas method will change the value of the thermal diffusivity for the gas to the inputted value.
    * @param new_value, the new value for the thermal diffusivity
    */
    public void set_thermal_diff_gas(double new_value) { thermal_diffusivity_gas = new_value; }

    /**
    * The set_thermal_cond_solid method will change the solid's thermal conductivity to the inputted value.
    * @param new_value, the new value for the thermal conductivity
    */
    public void set_thermal_cond_solid(double new_value) { thermal_conductivity_solid = new_value; }
    /**
    * The set_thermal_cond_liquid method will change the liquid's thermal conductivity to the inputted value.
    * @param new_value, the new value for the thermal conductivity
    */
    public void set_thermal_cond_liquid(double new_value) { thermal_conductivity_liquid = new_value; }
    /**
    * The set_thermal_cond_gas method will change the value of thermal conductivity for the gas to the inputted value.
    * @param new_value, the new value for the thermal conductivity
    */
    public void set_thermal_cond_gas(double new_value) { thermal_conductivity_gas = new_value; }

    /**
    * The set_sp_heat_solid method will change the solid's specific heat to the inputted value.
    * @param new_value, the new value for the specific heat
    */
    public void set_sp_heat_solid(double new_value) { specific_heat_solid = new_value; }
    /**
    * The set_sp_heat_liquid method will change the liquid's specific heat to the inputted value.
    * @param new_value, the new value for the specific heat
    */
    public void set_sp_heat_liquid(double new_value) { specific_heat_liquid = new_value; }
    /**
    * The set_sp_heat_gas method will change the specific heat of the gas to the inputted value.
    * @param new_value, the new value for the specific heat
    */
    public void set_sp_heat_gas(double new_value) { specific_heat_gas = new_value; }
    /**
    * The set_melt_point method will change the value of the melting point of the chemical to the inputted value.
    * @param new_value, the new value for the melting point
    */
    public void set_melt_point(double new_value) { melt_point = new_value; }
    /**
    * The set_boil_point method will change the value of the boiling point of the chemical to the inputted value.
    * @param new_value, the new value for the boiling point
    */
    public void set_boil_point(double new_value) { boil_point = new_value; }

    /**
    * The set_gas_density method will change the value of the density of the gas to the inputted value.
    * @param new_value, the new value for the density
    */
    public void set_gas_density(double new_value) { gas_density = new_value; }
    /**
    * The set_liq_density method will change the value of the density of the liquid to the inputted value.
    * @param new_value, the new value for the density
    */
    public void set_liq_density(double new_value) { liq_density = new_value; }
    /**
    * The set_sol_density method will change the value of the density of the solid to the inputted value.
    * @param new_value, the new value for the density
    */
    public void set_sol_density(double new_value) { sol_density = new_value; }

    /**
    * The set_color method will change the color of the chemical to the inputted color.
    * @param new_value, the new color
    */
    public void set_color(String new_value) { color = new_value; }
    /**
    * The set_chem_key method will change the key of the chemical to the inputted value.
    * @param new_value, the new key of the chemical
    */
    public void set_chem_key(String new_value) { chem_key = new_value; }
    /**
    * The set_is_insulated will change the value of the variable is_insulated, which determines if the block is a barrier or not.
    * @param new_value, true/false
    */
    public void set_is_insulated (boolean new_value) { is_insulated = new_value; }
    /**
    * The set_CAS method will change the CAS number of the chemical to the inputted value.
    * @param new_value, the new value for the CAS number
    */
    public void set_CAS (String new_value) { CAS_num = new_value; }


    //variables
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
