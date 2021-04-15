public class ChemicalDatabase {
    public void sort() {}
    public int search(String chem_key) {}   //Returns index if target chemical
    public Chemical get_chemical(int index) { return chemical_list[index]; }
    public void set_chemical(int index, Chemical new_value) { chemical_list[index] = new_value; }
    public void add_chemical(Chemical new_value) {}
    public boolean delete_chemical(String chem_key) {}

    private Chemical[] chemical_list;
}
