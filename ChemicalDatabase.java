import java.util.ArrayList;

public class ChemicalDatabase {

	public ChemicalDatabase() {
		chemical_list = new ArrayList<Chemical>();
	}

	public void sort() {
		quickSort(0, chemical_list.size()-1);
	}

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

	private void exchange(int i, int j) {
        	Chemical temp = chemical_list.get(i);
        	chemical_list.set(i, chemical_list.get(j));
        	chemical_list.set(j, temp);
    }

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

	public void print_list() {
		for(int i = 0; i < chemical_list.size(); i++)
			System.out.println(chemical_list.get(i).get_name());	
	}

	public Chemical get_chemical(int index) { return chemical_list.get(index); }

	public void set_chemical(int index, Chemical new_value) { chemical_list.set(index, new_value); }

	public void add_chemical(Chemical new_value) {
		chemical_list.add(new_value);	
	}

	public boolean delete_chemical(String chem_key) {
		int k = search(chem_key);			
		chemical_list.remove(k);
		return true;	
	}

	private ArrayList<Chemical> chemical_list;
}
