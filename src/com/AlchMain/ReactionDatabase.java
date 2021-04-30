package com.AlchMain;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Map;
import java.util.Iterator;

public class ReactionDatabase {

	/**
	* The constructor will create a hashmap of the reactions
	*/
	public ReactionDatabase() {
		reactions = new HashMap<String, ArrayList<Reaction>>();
	}

	/**
	* The find method will take in an inputted key and return the reactions that is associated with it.
	* @param reaction_key, a string that will represent the reaction key
	* @return the reaction that is represented by the inputted key
	*/
	public boolean find(String reaction_key) { 
		return reactions.containsKey(reaction_key); 
	}

	public Reaction get_throwaway_reaction() {
		return throw_away_reaction;
	}

	/**
	* The initialize_db method will read the file containing all the reactions and will create array lists to hold the information.
	* It will also set the variables equal to the corresponding information from the file.
	*/
	public void initialize_db() {
		try {
			ChemicalDatabase cdb = new ChemicalDatabase();
			cdb.initialize_db();
			File f = new File("ReactionList4.txt");
			Scanner s = new Scanner(f);
			while(s.hasNextLine()) {
				Reaction entry = new Reaction();
				ArrayList<Chemical> reactants = new ArrayList<Chemical>();
				ArrayList<Chemical> products = new ArrayList<Chemical>();
				ArrayList<Integer> product_coefficients = new ArrayList<Integer>();
				String line = s.nextLine();
//				System.out.println(line);
				String[] tempList = line.split("[ ]+");
//				for (int n = 0; n < tempList.length; n++) {
//					System.out.println(tempList[n]);
//				}
				String key = "";
				String[] reactString = tempList[1].split("[\\+]+");
				String[] prodString = tempList[2].split("[\\+]+");
//				for (int n = 0; n < reactString.length; n++) {
//					System.out.println(reactString[n]);
//				}
//				for (int n = 0; n < prodString.length; n++) {
//					System.out.println(prodString[n]);
//				}
				int i = 3;
//				int coeff1 = -1;
//				int coeff2 = -1;
//				if(tempList[i].charAt(2) == ',') {
//					coeff1 = Character.getNumericValue(tempList[i++].charAt(1));
//					coeff2 = Character.getNumericValue(tempList[i++].charAt(0));
//				}
//				else
//					coeff1 = Character.getNumericValue(tempList[i++].charAt(1));
//				if(coeff1 != -1)
//					product_coefficients.add(coeff1);
//				if(coeff2 != -1)
//					product_coefficients.add(coeff2);
				String[] coeffs_list = tempList[3].split("[\\]\\[,]+");
				System.out.println(tempList[3]);
				String[] coeffs_list2 = new String[coeffs_list.length-1];
				for (int n = 1; n < coeffs_list.length; n++) {
					coeffs_list2[n-1] = coeffs_list[n];
//					System.out.println(coeffs_list2[n-1]);
				}
				for (int n = 0; n < coeffs_list2.length; n++) {
					product_coefficients.add(Integer.parseInt(coeffs_list2[n]));
				}

				boolean null_chem = false;
				for(int j = 0 ; j < reactString.length; j++) {
					key += reactString[j];
					Chemical c = null;
					if (cdb.search(reactString[j]) != -1) {
						c = cdb.get_chemical(cdb.search(reactString[j]));
					}
					else {
						null_chem = true;
					}
					reactants.add(c);		
				}
				for(int j = 0 ; j < prodString.length; j++) {
					Chemical c = null;
					if (cdb.search(prodString[j]) != -1) {
						c = cdb.get_chemical(cdb.search(prodString[j]));
					}
					else {
						null_chem = true;
					}
					products.add(c);	
				}
				if (null_chem) {
					continue;
				}
			
				entry.set_reaction_key(key);
				entry.set_reactants(reactants);
				entry.set_products(products);
				entry.set_product_coefficients(product_coefficients);

				entry.set_delta_enthalpy(Double.parseDouble(tempList[4]));
				entry.set_delta_entropy(Double.parseDouble(tempList[5]));

				if (!(throw_away_reaction == null)) {
					throw_away_reaction = entry;
				}
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
	/**
	* The get_reaction method will return back an arraylist of the reactions with the inputted reaction key.
	* @param reaction_key, a string that will represent the reaction key
	* @return, the reactions that have the inputted reaction key
	*/
	public ArrayList<Reaction> get_reaction(String reaction_key) { 
		return reactions.get(reaction_key); 
	}

	/**
         * The set_reaction method will check if the inputted reaction key has been
         * deleted and place a new reaction value into it
         * @param reaction_key is the reaction key that will be used to find the 
         * reaction to be modified
         * @param new_value is the new reaction value
         */
	public void set_reaction(String reaction_key, ArrayList<Reaction> new_value) { 
		if(delete_reaction(reaction_key)) 
			reactions.put(reaction_key, new_value);	
	}

	/**
         * The add_reaction method will take an inputted reaction key and new value
         * and create a new reaction with the values
         * @param reaction_key is the reaction key to be used
         * @param new_value is the reaction value to be used
         */	
	public void add_reaction(String reaction_key, Reaction new_value) { 
		if(reactions.get(reaction_key) == null) {		
			reactions.put(reaction_key, new ArrayList<Reaction>());
		}
		reactions.get(reaction_key).add(new_value);
	}

	/**
         * The delete_reaction method will take an inputted reaction key and remove
         * it from the reaction list
         * @param reaction_key is the reaction key that will be used to find the 
         * reaction to be deleted
         * @return is the boolean result for the condition
         */
	public boolean delete_reaction(String reaction_key) {
		if(reactions.get(reaction_key) == null) 		
			return false;
		reactions.remove(reaction_key);
		return true;
	}

	/**
	* The print_db method will print out all the reactions and all the information that pertains to it.

	*/
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
	private Reaction throw_away_reaction;
}
