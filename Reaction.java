public class Reaction {
    //getters
    /**
    * The get_products will return an array of the products.
    * @return products, an array of the products
    */
    public Chemical[] get_products() { return products; }
    /**
    * The get_reactants will return an array of the reactants.
    * @return reactants, array of the reactants
    */
    public Chemical[] get_reactants() { return reactants; }
    /**
    * The get_product_coefficients will return an array of the coefficients.
    * @return product_coefficients, the coefficients of the products
    */
    public int[] get_product_coefficients() { return product_coefficients; }
    /**
    * The get_start_energy will return the value of variable.
    * @return start_energy, the value of the starting energy
    */
    public double get_start_energy() { return start_energy; }
    /**
    * The get_energy_released will return the variable energy released.
    * @return energy_released, the value of the eneregy released
    */
    public double get_energy_released() { return energy_released; }
    /**
    * The get_reaction_key will return the variable the reaction_key.
    * @return reaction_key, the value of the variable reaction_key
    */
    public String get_reaction_key() { return reaction_key; }

    //setters
    /**
    * The set_products will take the inputted value and set the product equal to it.
    * @param new_value, the new chemical
    */
    public void set_products(Chemical[] new_value) { products = new_value; }
    /**
    * The set_reactants will take the inputted value and set the reactant equal to it.
    * @param new_value, the new chemical
    */
    public void set_reactants(Chemical[] new_value) { reactants = new_value; }
    /**
    * The set_product_coefficients will take the inputted value and set the variable product_coefficient equal to it.
    * @param new_value, the value of the new product coefficient
    */
    public void set_product_coefficients(int[] new_value) { product_coefficients = new_value; }
    /**
    * The set_start_energy will take the inputted value and set the variable start_energy equal to it.
    * @param new_value, the value of the new start energy
    */
    public void set_start_energy(double new_value) { start_energy = new_value; }
    /**
    * The set_energy_released will take the inputted value and set the variable energy_released equal to it.
    * @param new_value, the value of the new energy released
    */
    public void set_energy_released(double new_value) { energy_released = new_value; }
    /**
    * The set_reaction_key will take the inputted value and set the variable reaction_key equal to it.
    * @param new_value, the value of the new reaction key
    */
    public void set_reaction_key(String new_value) { reaction_key = new_value; }

    private Chemical[] products;
    private Chemical[] reactants;
    private int[] product_coefficients;
    private double start_energy;
    private double energy_released;
    private String reaction_key;
}
