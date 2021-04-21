public class Chemical {
    
    /**
     * The get_name method will return the chemical name
     * @return is the chemical name
     */
    public String get_name() { return chem_name; }
    
    /**
     * The get_formula method will return the chemical formula
     * @return is the chemical formula
     */
    public String get_formula() { return chem_formula; }
        
    /**
     * The get_thermal_diff method will return the thermal diffusivity of the chemical
     * @return 
     */
    public double get_thermal_diff() { return thermal_diffusivity; }
        
    /**
     * The get_melt_point method will return the melting point of the chemical in Fahrenheit
     * @return is the melting point
     */
    public double get_melt_point() { return melt_point; }
        
    /**
     * The get_boil_point method will return the boiling point of the chemical in Fahrenheit
     * @return is the boiling point
     */
    public double get_boil_point() { return boil_point; }
        
    /**
     * The get_gas_density method will return the density of the chemical in its gaseous state
     * @return is the gas density
     */
    public double get_gas_density() { return gas_density; }
        
    /**
     * The get_liq_density method will return the density of the chemical in its liquid state
     * @return is the liquid density
     */
    public double get_liq_density() { return liq_density; }
        
    /**
     * The get_sol_density method will return the density of the chemical in its solid state
     * @return is the solid density
     */
    public double get_sol_density() { return sol_density; }
        
    /**
     * The get_color method will return the color of the chemical
     * @return is the color
     */
    public int get_color() { return color; }
        
    /**
     * The get_chem_key method will return the database key associated with the chemical
     * @return chemical key
     */
    public String get_chem_key() { return chem_key; }
        
    /**********************************************************************
     * The get_is_insulated method will return 
     * @return 
     */
    public boolean get_is_insulated() { return is_insulated; }
   
    /**
     * The set_name method will set the chemical name to the new one inputted
     *  @param new_value is the new chemical name
     */
    public void set_name(String new_value) { chem_name = new_value; }
        
    /**
     * The set_formula method will set the chemical formula to the new one inputted
     *  @param new_value is the new chemical formula
     */
    public void set_formula(String new_value) { chem_formula = new_value; }
        
    /**
     * The set_thermal_diff will set the thermal diffusivity to the new one inputted
     * @param new_value is the new thermal diffusivity
     */
    public void set_thermal_diff(double new_value) { thermal_diffusivity = new_value; }
        
    /**
     * The set_melt_point method will set the melting point to the new one inputted
     * @param new_value is the new melting point
     */
    public void set_melt_point(double new_value) { melt_point = new_value; }
        
    /**
     * The set_boil_point method will set the boiling point to the new one inputted
     * @param new_value is the new boiling point
     */
    public void set_boil_point(double new_value) { boil_point = new_value; }
        
    /**
     * The set_gas_density method will set the gas chemical density to the new one inputted
     * @param new_value is the new gas density
     */
    public void set_gas_density(double new_value) { gas_density = new_value; }
        
    /**
     * The set_liq_density method will set the liquid chemical density to the new one inputted
     * @param new_value is the new liquid density 
     */
    public void set_liq_density(double new_value) { liq_density = new_value; }
        
    /**
     * The set_sol_density method will set the solid chemical density to the new one inputted
     * @param new_value is the new solid density
     */
    public void set_sol_density(double new_value) { sol_density = new_value; }
        
    /**
     * The set_color method will set the chemical color to the new one inputted
     * @param new_value is the new chemical color
     */
    public void set_color(int new_value) { color = new_value; }
        
    /**
     * The set_chem_key method will set the chemical key to the new one inputted
     * @param new_value is the new chemical key
     */
    public void set_chem_key(String new_value) { chem_key = new_value; }
        
    /************************************************************************
     * The set_is_insulated method will set
     * @param new_value
     */
    public void set_is_insulated (boolean new_value) { is_insulated = new_value; }
    
    //Declaration of variables 
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
