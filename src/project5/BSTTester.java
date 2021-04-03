package project5;

import java.text.DecimalFormat;
import java.util.Iterator;

import project5.Location;
import project5.Meteorite;
import test.TestObjects;

public class BSTTester < T extends Comparable <T> >{

	public static <T> void main(String[] args) {
		BST tree = new BST();
		tree.add(10);
		tree.add(5);
		tree.add(21);
		tree.add(1);
		tree.add(9);
		tree.add(17);
		//tree.add(11);
		tree.add(30);
		tree.add(11);
		tree.add(27);
		tree.add(35);
		tree.remove(10);
		System.out.println(tree.root.right);
		}
		
	}
	
