package com.vijfhart.casus.tree;

//public interface LevelComparable<E extends Node<E>> { //meer beperkt
public interface LevelComparable<E> {

	public int compareLevelTo(E node);

}
