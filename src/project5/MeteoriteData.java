package project5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
/**
 * A program that represents a Meteorite Data. Can get elements in the MeteoriteData by mass withen a certain range, by year for the specific year or 
 * by finding the closest meteorite object given a location.
 * @author Richard
 */
public class MeteoriteData {
	public BST<Meteorite> tree;// = new BST<>();	
	public BST<Meteorite> massTree;// = new BST<>(new MassComparator());
	public BST<Meteorite> yearTree;// = new BST<>(new YearComparator());
	//Iterator<Meteorite> treeItr = tree.iterator();
	/**
	 * Creates a empty MeteoriteData object without taking in any parameters.
	 */
	public MeteoriteData() {
		tree = new BST<>(); 
		massTree = new BST<>(new MassComparator());
		yearTree = new BST<>(new YearComparator());
	}
	/**
	 * Gets all of the elements that have a mass within the parameter of delta.
	 * Adds all of these elements to a MeteoriteData.
	 * 
	 * 
	 * @param mass an int value that will be used to check if an object has a mass close to the parameter
	 * @param delta and int value that will be the range of acceptable masses from the mass parameter
	 * @return a MeteoriteData of all of the valid elements that have masses within the delta parameter starting from the mass parameter
	 * 		   If no elements were added the method will return null.
	 * @throws IllegalArgumentException if the mass parameter is less than 0 or if the delta parameter is less than or equal to 0
	 * 		   it will print out "This input for mass is not valid".
	 */
	public MeteoriteData getByMass(int mass, int delta) {
		MeteoriteData metData = new MeteoriteData();//(new MassComparator());
		if(mass < 0 || delta < 0)
			throw new IllegalArgumentException("This input for mass is not valid");
		if(tree.size() == 0) {
			return null;
		}
		Meteorite dummy1 = new Meteorite("a",1);
		Meteorite dummy2 = new Meteorite("ZZZZZZZZ",2);
		dummy1.setMass(mass-delta);
		dummy2.setMass(mass+delta);
		for(int i = 0; i < massTree.getRange(dummy1, dummy2).size(); i++) {
			if(massTree.getRange(dummy1, dummy2).get(i).getMass() == 0)
				continue;
			metData.add(massTree.getRange(dummy1, dummy2).get(i));
		}			
		return metData; 
	}
	/**
	 * Goes through a list and sees which objects coordinates is closest the the ones in the parameter
	 * Gets the element that is closest in the BST.
	 * 
	 * @param loc an object of Location that will be used to find the distance between the loc parameter and the objects location
	 * @return the element that is closest to the coordinates provided
	 * @throws IllegalArugmentExcepption if the loc parameter is null the exception will be thrown and 
	 * 		   the statement "location cannot be null" will be printed
	 */
	public Meteorite getByLocation(Location loc) {
		Iterator<Meteorite> itr = this.iterator();
		if(loc == null) {
			throw new IllegalArgumentException("Location cannot be null");
		}
		if(itr.hasNext() == false)
			return null;
		Meteorite current = itr.next();
		Meteorite save = current;
		double total = loc.getDistance(current.getLocation());
		while(itr.hasNext()) {
			current = itr.next();
			if(current.getLocation() == null) {
				current = itr.next();
				continue;
			}
			if(total > loc.getDistance(current.getLocation())) {
				total = (int) loc.getDistance(current.getLocation());
				save = current;
			}
		}
		return save;
		
	}
	/**
	 * Gets all the elements that have the same year as the year parameter and adds them to a MeteoriteData
	 * @param year this is the only parameter in the getByYear method
	 * @throw IllegalArgumentException if the year parameter is less than or equal to 0
	 * 		  prints out the statement "This is an invalid input for year".
	 * @return A MeteoriteData with all of the elements that have the same year as the parameter
	 * 		   If no elements were added, null will be returned
	 */
	public MeteoriteData getByYear(int year) {
		MeteoriteData metData = new MeteoriteData();//(new YearComparator());
		if(year <= 0)
			throw new IllegalArgumentException();
		if(year >= 2020)
			return metData;
		Meteorite dummy1 = new Meteorite("a",1);
		Meteorite dummy2 = new Meteorite("ZZZZZZ",2);
		dummy1.setYear(year);
		dummy2.setYear(year);
		for(int i = 0; i < yearTree.getRange(dummy1, dummy2).size(); i++) {
			metData.add(yearTree.getRange(dummy1, dummy2).get(i));
		}
		return metData;
	}
	/**
	 * Adds a meteorite to a tree sorted by natural ordering, a tree sorted by the massComparator and a tree sorted by the yearComparator
	 * @param met1 the element of type Meteorite that will be added to all three trees
	 * @throws NullPointerException if the parameter it null
	 * @return true if the element was added to all three trees or false if the element was not added to all three trees
	 */
	public boolean add(Meteorite met1) {
		if(met1 == null)
			throw new NullPointerException("Input cannot be null");
		return tree.add(met1) && massTree.add(met1) && yearTree.add(met1);
	}
	/**
	 * Checks to see if two MeteoriteDatas are the same by comparing their elements and seeing if they have the same elements in the same order.
	 * @param obj of type object that will be checked to see if it is of type MeteoriteData and then will be iterated through to see if it elements
	 * are the same as the elements in the MeteoriteData being compared to.
	 * @return true if the object has the same elements in the same order as the MeteoriteData that it is being compared to.
	 * Returns false if the object is null, or is not an instance of MeteoriteData or if the elements are not the same.
	 */
	public boolean equals(Object obj) {
		Iterator<Meteorite> itr = this.iterator();
		if(obj == null)
			return false;
		if(obj == this)
			return true;
		if(!(obj instanceof MeteoriteData))
			return false;
		MeteoriteData other = (MeteoriteData) obj;
		Iterator<Meteorite> itr2 = other.iterator();
		while(itr2.hasNext()) {
			if(itr.hasNext() == false)
				return false;
			if(!(itr2.next().equals(itr.next())))
				return false;
		}
		return true;
		
		
		
	}
	/**
	 * Iterates through the MeteoriteData and returns an iterator.
	 * @return an iterator of the tree BST
	 */
	public Iterator<Meteorite> iterator(){
		return tree.iterator();
	}
	private class Itr implements Iterator<Meteorite>{
		Iterator<Meteorite> treeItr = tree.iterator();
		@Override
		public boolean hasNext() {
			return treeItr.hasNext();
		}

		@Override
		public Meteorite next() {
			return treeItr.next();
		}
		
	}
	/**
	 * Removes the Meteorite element m from the tree, the massTree and the yearTree.
	 * @param m of type Meteorite will be the element that will be removed from the 3 trees
	 * @throws NullPointerException if the parameter it null
	 * @return true if the element is removed in all the trees or false if it is not removed from all of them.
	 */
	public boolean remove(Meteorite m) {
		if(m == null)
			throw new NullPointerException("Input cannot be null");
		return tree.remove(m) && massTree.remove(m) && yearTree.remove(m);
	}
	/**
	 * Goes through the MeteoriteData and adds the data of each element in the MeteoriteData to a string
	 * 
	 * @return a string of all the elements in the MeteoriteData 
	 * 		   Will return "There are no matching datas found" if the MeteoriteData is empty
	 */
	public String toString() {
		Iterator<Meteorite> itr = this.iterator();
		String ret = "";
		if(itr.hasNext() == false)
			return "There are no matching datas found";
		while(itr.hasNext()) {
			ret+= itr.next()+"\n";
		}
		return ret;
	}
	
}
