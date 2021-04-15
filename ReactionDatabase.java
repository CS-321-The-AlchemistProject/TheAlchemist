public class ReactionDatabase {
    public void sort() {}
    public int search(String reaction_key) {}   //Returns index if target reaction
    public Reaction get_reaction(int index) { return reaction_list[index]; }
    public void set_reaction(int index, Reaction new_value) { reaction_list[index] = new_value; }
    public void add_reaction(Reaction new_value) {}
    public boolean delete_reaction(String chem_key) {}

    private Reaction[] reaction_list;
}
