import java.util.ArrayList;

public class ChemicalDatabase {

	/**
	* The ChemicalDatabase constructor will create a new array list of chemical type
	*/
	public ChemicalDatabase() {
		chemical_list = new ArrayList<Chemical>();
	}

	/**
	* The sort method will call the quickSort method with inputted parameters
	*/
	public void sort() {
		quickSort(0, chemical_list.size()-1);
	}

	/**
	* The quickSort method will use the quick sort algorithm to order the chemical list
	* @param low, the lowest index
	* @param high, the highest index
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
	* The exchange method will swap the indexes of two chemicals
	* @param i, the index of the first chemical
	* @param j, the index of the second chemical
	*/
	private void exchange(int i, int j) {
        	Chemical temp = chemical_list.get(i);
        	chemical_list.set(i, chemical_list.get(j));
        	chemical_list.set(j, temp);
    }

	/**
	* The search method will use the inputted string and search through the list for the key
	* @param chem_key, the access key for the chemical
	*/
	public int search(String chem_key) {
		int low = 0;
		int high = chemical_list.size() - 1;
		int mid;
		while (low <= high) {
		    mid = (low + high) / 2;

		    if (chemical_list.get(mid).get_name().compareTo(chem_key) < 0) {
			    low = mid + 1;
		    } 
            else if (chemical_list.get(mid).get_name().compareTo(chem_key) > 0) {
			    high = mid - 1;
		    } 
            else {
			    return mid;
		    }
		}
		return -1;
	}   //Returns index of target chemical else -1

	/**
	* The print_list method will print the whole list of chemicals out
	*/
	public void print_list() {
		for(int i = 0; i < chemical_list.size(); i++)
			System.out.println(chemical_list.get(i).get_name());	
	}

	/**
	* The get_chemical will return the chemical at a certain index
	* @param index, the position of the chemical in the list
	*/
	public Chemical get_chemical(int index) { return chemical_list.get(index); }

	/**
	* The set_chemical will change the value of the chemical at a certain index
	* @param index, the position of the chemical in the list
	* @param new_value, the value that the chemical will change to
	*/
	public void set_chemical(int index, Chemical new_value) { chemical_list.set(index, new_value); }

	/**
	* The add_chemical method will add a new chemical to the list
	* @param new_value, the new chemical being added
	*/
	public void add_chemical(Chemical new_value) {
		chemical_list.add(new_value);	
	}

	/**
	* The delete_chemical will remove a chemical from the list by searching for it using the chemical key
	* @param chem_key, the key of the chemical to be removed
	*/
	public boolean delete_chemical(String chem_key) {
		int k = search(chem_key);			
		chemical_list.remove(k);
		return true;	
	}

	private ArrayList<Chemical> chemical_list;
}
