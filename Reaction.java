import java.util.ArrayList;

public class Reaction {
    
    /**
     * The get_products method will traverse the array list and return the products
     * @return is the products that were found
     */    
    public ArrayList<Chemical> get_products() { return products; }
    
    /**
     * The get_reactants method will traverse the array list and return the chemicals
     * @return is the reactants that were found
     */     
    public ArrayList<Chemical> get_reactants() { return reactants; }
    
    /**
     * The get_product_coefficients method will traverse the array list and return the product coefficients 
     * @return is the product coefficients that were found
     */         
    public ArrayList<Integer> get_product_coefficients() { return product_coefficients; }
    
    /**
     * The get_delta_enthalpy method will return the desired delta enthalpy
     * @return is the delta enthalpy that was found
     */         
    public double get_delta_enthalpy() { return delta_enthalpy; }
    
    /**
     * The get_delta_entropy method will return the desired delta entropy
     * @return is the delta entropy that was found
     */        
    public double get_delta_entropy() { return delta_entropy; }
    
    /**
     * The get_reaction_key method will return the desired reaction key
     * @return is the reaction key that was found
     */      
    public String get_reaction_key() { return reaction_key; }

    //Setters
    
    /**
     * The set_products method will set the products to the new value inputted
     * @return is the delta entropy that was found
     */  
    public void set_products(ArrayList<Chemical> new_value) { products = new_value; }
    public void set_reactants(ArrayList<Chemical> new_value) { reactants = new_value; }
    public void set_product_coefficients(ArrayList<Integer> new_value) { product_coefficients = new_value; }
    public void set_delta_enthalpy(double new_value) { delta_enthalpy = new_value; }
    public void set_delta_entropy(double new_value) { delta_entropy = new_value; }
    public void set_reaction_key(String new_value) { reaction_key = new_value; }

    private ArrayList<Chemical> products;
    private ArrayList<Chemical> reactants;
    private ArrayList<Integer> product_coefficients;
    private double delta_enthalpy;
    private double delta_entropy;
    private String reaction_key;
}
