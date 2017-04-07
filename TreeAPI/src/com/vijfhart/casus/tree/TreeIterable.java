package com.vijfhart.casus.tree;

public interface TreeIterable<E extends Node<E>> extends Iterable<E> {

	public TreeIterator<E> treeIterator();
	
}
