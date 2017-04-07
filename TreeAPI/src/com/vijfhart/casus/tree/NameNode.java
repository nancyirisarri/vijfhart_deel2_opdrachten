package com.vijfhart.casus.tree;

import java.util.Comparator;

class NameNode<E extends Node<E>> extends AbstractNode<E> implements Node<E>, LevelComparable<E>, Comparator<E> {
	private String naam;
	private E parentNode;

	public NameNode() {
	}

	public NameNode(String naam) {
		this.naam = naam;
		this.parentNode = null;
	}

	public NameNode(String naam, E parentNode) {
		this.naam = naam;
		this.parentNode = parentNode;
	}

	public String getName() {
		return this.naam;
	}

	public E getParent() {
		return this.parentNode;
	}

	public boolean equals(Object o) {
		if (o != null && o instanceof NameNode) {
			NameNode node = (NameNode) o;
			if (node.getName().equals(naam) && parentNode == node.getParent()) {
				return true;
			}
		}
		return false;
	}

	public String toString() {
		return this.naam;
	}

	public boolean isLeaf() {
		return true;
	}
}
