import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Map;
import java.util.Iterator;

public class ReactionDatabase {

	public ReactionDatabase() {
		reactions = new HashMap<String, ArrayList<Reaction>>();
	}

	public boolean find(String reaction_key) { 
		return reactions.containsKey(reaction_key); 
	}

	public void initialize_db() {
		try {
			ChemicalDatabase cdb = new ChemicalDatabase();
			cdb.initialize_db();
			File f = new File("ReactionList.txt");
			Scanner s = new Scanner(f);
			while(s.hasNextLine()) {
				Reaction entry = new Reaction();
				ArrayList<Chemical> reactants = new ArrayList<Chemical>();
				ArrayList<Chemical> products = new ArrayList<Chemical>();
				ArrayList<Integer> product_coefficients = new ArrayList<Integer>();
				String line = s.nextLine();
				String[] tempList = line.split(" ");
				String key = "";
				String[] reactString = tempList[1].split("\\+");
				String[] prodString = tempList[2].split("\\+");
				int i = 3;
				int coeff1 = -1;
				int coeff2 = -1;
				if(tempList[i].charAt(2) == ',') {
					coeff1 = Character.getNumericValue(tempList[i++].charAt(1));
					coeff2 = Character.getNumericValue(tempList[i++].charAt(0));
				}
				else
					coeff1 = Character.getNumericValue(tempList[i++].charAt(1));
				if(coeff1 != -1)
					product_coefficients.add(coeff1);
				if(coeff2 != -1)
					product_coefficients.add(coeff2);

				for(int j = 0 ; j < reactString.length; j++) {
					key += reactString[j];
					Chemical c = cdb.get_chemical(cdb.search(cdb.convert_formula_to_name(reactString[j])));				
					reactants.add(c);		
				}
				for(int j = 0 ; j < prodString.length; j++) {
					Chemical c = cdb.get_chemical(cdb.search(cdb.convert_formula_to_name(prodString[j])));
					products.add(c);	
				}
			
				entry.set_reaction_key(key);
				entry.set_reactants(reactants);
				entry.set_products(products);
				entry.set_product_coefficients(product_coefficients);

				entry.set_delta_enthalpy(Double.parseDouble(tempList[i++]));
				entry.set_delta_entropy(Double.parseDouble(tempList[i++]));

				if(!reactions.containsKey(key)) {
					reactions.put(key, new ArrayList<Reaction>());
				}
				reactions.get(key).add(entry);
						
			}
		} catch (FileNotFoundException ex) {
			System.out.println("not found");
		}
	}
/*
reaction/key reactants+list products+list [products, coefficients] delta_enthalpy delta_entropy
the formatting in the reaction list is in the style of how it'll be formatted in the list
data entry is separated by one white space
each line is one reaction/chemical
*/
	public ArrayList<Reaction> get_reaction(String reaction_key) { 
		return reactions.get(reaction_key); 
	}

	public void set_reaction(String reaction_key, ArrayList<Reaction> new_value) { 
		if(delete_reaction(reaction_key)) 
			reactions.put(reaction_key, new_value);	
	}

	public void add_reaction(String reaction_key, Reaction new_value) { 
		if(reactions.get(reaction_key) == null) {		
			reactions.put(reaction_key, new ArrayList<Reaction>());
		}
		reactions.get(reaction_key).add(new_value);
	}

	public boolean delete_reaction(String reaction_key) {
		if(reactions.get(reaction_key) == null) 		
			return false;
		reactions.remove(reaction_key);
		return true;
	}

	public void print_db() {
		for(Map.Entry<String, ArrayList<Reaction>> entry : reactions.entrySet()) {		
			String key = entry.getKey();
			ArrayList<Reaction> val = entry.getValue();
			for(int i = 0; i < val.size(); i++) {
				ArrayList<Chemical> reactants = (val.get(i)).get_reactants();
				ArrayList<Chemical> products = (val.get(i)).get_products();
				ArrayList<Integer> coeff = (val.get(i)).get_product_coefficients();
				System.out.println("\n" + key + ":");
				System.out.print("Reactants: ");
				for(int j = 0; j < reactants.size(); j++) {
					System.out.print((reactants.get(j)).get_formula() + " ");
 
				}
				System.out.print("Products: ");
				for(int j = 0; j < products.size(); j++) {
					System.out.print((products.get(j)).get_formula() + " ");
				}	
				System.out.print("coeffs: [ ");
				for(int j = 0; j < coeff.size(); j++) {
					System.out.print(coeff.get(j) + " ");
				}
				System.out.print("] Enthalpy: " + val.get(i).get_delta_enthalpy());
				System.out.print(" Entropy: " + val.get(i).get_delta_entropy());
			}
		}
			
	}		

	private HashMap<String, ArrayList<Reaction>> reactions;
}
