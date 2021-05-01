package com.AlchMain;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class ChemicalDatabase {

	/**
	* The ChemicalDatabase constructor will create a new array list of chemical type and create a new hashmap for the chemical formulas
	*/
	public ChemicalDatabase() {
		chemical_list = new ArrayList<Chemical>();
		formulaMap = new HashMap<String, String>();
	}

	/**
	* The initialize_db method will read from a text file a line and input all the corresponding values
	*/
	public void initialize_db() {
		try{
			File f = new File("ChemicalList4.txt");
			Scanner s = new Scanner(f);
			while(s.hasNextLine()) {
				String line = s.nextLine();
				String[] tempList = line.split("[ ]+");
				Chemical entry = new Chemical();
				int i = 0;
				entry.set_formula(tempList[i++]);
				String name = tempList[i++]; 
				while(!Character.isDigit(tempList[i].charAt(0)))
					name += " " + tempList[i++];
				entry.set_name(name);
				entry.set_CAS(tempList[i++]);
				entry.set_melt_point(Double.parseDouble(tempList[i++]));
				entry.set_boil_point(Double.parseDouble(tempList[i++]));

				entry.set_sp_heat_solid(Double.parseDouble(tempList[i++]));
				entry.set_thermal_cond_solid(Double.parseDouble(tempList[i++]));
				entry.set_thermal_diff_solid(Double.parseDouble(tempList[i++]));
				entry.set_sol_density(Double.parseDouble(tempList[i++]));
			
				entry.set_sp_heat_liquid(Double.parseDouble(tempList[i++]));
				entry.set_thermal_cond_liquid(Double.parseDouble(tempList[i++]));
				entry.set_thermal_diff_liquid(Double.parseDouble(tempList[i++]));
				entry.set_liq_density(Double.parseDouble(tempList[i++]));
 
				entry.set_sp_heat_gas(Double.parseDouble(tempList[i++]));
				entry.set_thermal_cond_gas(Double.parseDouble(tempList[i++]));
				entry.set_thermal_diff_gas(Double.parseDouble(tempList[i++]));
				entry.set_gas_density(Double.parseDouble(tempList[i++]));

				String delims = "[ rgb(),]+";
				String[] split_color = tempList[i].split(delims);
				ArrayList<Integer> color_list = new ArrayList<Integer>();
				for (int n = 0; n < split_color.length; n++) {
					if (!(split_color[n].equals(""))) {
						color_list.add(Integer.parseInt(split_color[n]));
					}
				}
				entry.set_color(color_list);

				entry.set_chem_key(tempList[0]);
				//entry.set_is_insulated (tempList.get()) no info in data				

				if(formulaMap.get(tempList[0]) == null)
					formulaMap.put(tempList[0], name);			
				chemical_list.add(entry);
			}
		} catch (FileNotFoundException ex) {
			System.out.println("not found");
			ex.printStackTrace();
		}
		//sort();
		//print_list();
	}

/*
formula common_name CAS_number melting_point boiling_point specific_heat_solid thermal_conductivity_solid thermal_diffusivity_solid 
 density_solid specific_heat_liquid thermal_conductivity_liquid thermal_diffusivity_liquid 
 density_liquid specific_heat_gas thermal_conductivity_gas thermal_diffusivity_gas 
 density_gas color_code
*/
	/**
	* The sort method will call the quickSort method with inputted values for the parameter.
	*/
	public void sort() {
		quickSort(0, chemical_list.size()-1);
	}

	/**
	* The quickSort will use the quick sort algorihtm to sort throught the chemical list by the name.
	* @param low, the lowest index
	* @param high, the highest index of the list
	*/
	private void quickSort(int low, int high) {
		int i = low, j = high;
		Chemical pivot = chemical_list.get(low + (high - low) / 2);
		while (i <= j) {
	    		while ((chemical_list.get(i).get_name()).compareToIgnoreCase(pivot.get_name()) < 0) {
				i++;
	    		}
	    		while ((chemical_list.get(j).get_name()).compareToIgnoreCase(pivot.get_name()) > 0) {
				j--;
	    		}
	    		if (i <= j) {
				exchange(i, j);
				i++;
				j--;
	    		}
		}
		if (low < j) {
	    		quickSort(low, j);
		}
		if (i < high) {
	    		quickSort(i, high);
		}	
	}

	/**
	* The exchange method will swap the position of two chemicals
	* @param i, the first index
	* @param j, the second index
	*/
	private void exchange(int i, int j) {
        	Chemical temp = chemical_list.get(i);
        	chemical_list.set(i, chemical_list.get(j));
        	chemical_list.set(j, temp);
    	}
	
	/**
	* The search method will use binary search to look for the inputted chemical key
	* @param chem_key, the chemical key that will be searched for
	* @return chemical/ -1, it will return the index of the chemical or -1 if it is not found
	*/
	public int search(String chem_key) {
		if (chem_key == null) {
			return -1;
		}
		int low = 0;
		int high = chemical_list.size() - 1;
		int mid;
		while (low <= high) {
		    mid = (low + high) / 2;

		    if (chemical_list.get(mid).get_chem_key().compareTo(chem_key) < 0) {
			low = mid + 1;
		    } else if (chemical_list.get(mid).get_chem_key().compareTo(chem_key) > 0) {
			high = mid - 1;
		    } else {
			return mid;
		    }
		}
		return -1;
	}   //Returns index of target chemical else -1
	
	/**
	* The print_formulaMap method will print out the key and value for the chemicals
	*/
	public void print_formulaMap() {
		for (Map.Entry<String, String> entry : formulaMap.entrySet()) {
		    	String key = entry.getKey();
		    	String val = entry.getValue();
		    	System.out.println(key + " : " + val);
	        }
	}
	
	/**
	* The convert_formula_to_name method will take the inputted formula and return the name
	* @param chem_formula
	*/
	public String convert_formula_to_name(String chem_formula) {
		return formulaMap.get(chem_formula);	
	}
	
	/**
	* The print_list will print out the whole list
	*/
	public void print_list() {
		for(int i = 0; i < chemical_list.size(); i++)
			System.out.println(chemical_list.get(i).get_name());	
	}

	/**
	* The get_chemical method will take in an index and return the corresponding chemical
	* @param index, the index of the chemical that will be returned
	* @return chemical_list, it will return the chemical at the inputted index
	*/
	public Chemical get_chemical(int index) { return chemical_list.get(index); }

	/**
	* The set_chemical method will take in an index and a new value and will set the value at the index to the new value.
	* @param index, the index that the new chemical will be set to
	* @param new_value, the new chemical value
	*/
	public void set_chemical(int index, Chemical new_value) { chemical_list.set(index, new_value); }

	/**
	* The add_chemical method will add a chemical to the array list.
	* @param new_value, the new chemical being added
	*/
	public void add_chemical(Chemical new_value) {
		chemical_list.add(new_value);	
	}

	/**
	* The delete_chemical method will search for the inputted key and delete the corresponding item from the list
	* @param chem_key, the chemical key that will be searched for
	*/
	public boolean delete_chemical(String chem_key) {
		int k = search(chem_key);			
		chemical_list.remove(k);
		return true;	
	}

	private ArrayList<Chemical> chemical_list;
	private HashMap<String, String> formulaMap;
}
