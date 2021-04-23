import java.util.HashMap;
import java.util.ArrayList;

public class ReactionDatabase {
	/**
	* The find method will return the reaction that contains the inputted reaction key
	* @param reaction_key, the key to be used to search for reactions
	*/
	public boolean find(String reaction_key) { 
		return reactions.containsKey(reaction_key); 
	}
	
	/**
	* The get_reaction method will take an inputted reaction key and return an array list
	* of the reactions that contain that key.
	* @param reaction_key, the reaction key that will be used to return the corresponding reactions
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

	private HashMap<String, ArrayList<Reaction>> reactions;
}
