public class Reaction {
    public Chemical[] get_products() { return products; }
    public Chemical[] get_reactants() { return reactants; }
    public int[] get_product_coefficients() { return product_coefficients; }
    public double get_start_energy() { return start_energy; }
    public double get_energy_released() { return energy_released; }
    public String get_reaction_key() { return reaction_key; }

    public void set_products(Chemical[] new_value) { products = new_value; }
    public void set_reactants(Chemical[] new_value) { reactants = new_value; }
    public void set_product_coefficients(int[] new_value) { product_coefficients = new_value; }
    public void set_start_energy(double new_value) { start_energy = new_value; }
    public void set_energy_released(double new_value) { energy_released = new_value; }
    public void set_reaction_key(String new_value) { reaction_key = new_value; }

    private Chemical[] products;
    private Chemical[] reactants;
    private int[] product_coefficients;
    private double start_energy;
    private double energy_released;
    private String reaction_key;
}
