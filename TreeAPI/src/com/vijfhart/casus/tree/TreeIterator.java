package com.vijfhart.casus.tree;

import java.util.*;

public interface TreeIterator<E extends Node<E>> extends Iterator<E> {

	public int level();
	
	public void startWith(E node);
	
	public boolean isLeaf();
	
	public String path(NodeString nodeString, String separator);
	
	public void orderSiblingsBy(Comparator comparator);

}
