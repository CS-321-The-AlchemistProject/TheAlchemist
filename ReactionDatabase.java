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

	private HashMap<String, ArrayList<Reaction>> reactions;
}
