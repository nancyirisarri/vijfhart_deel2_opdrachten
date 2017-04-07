package com.vijfhart.casus.tree;

public interface Node<E extends Node<E>> extends LevelComparable<E> {

	public E getParent();

	public void setParent(E node);

	public boolean isLeaf();

}
