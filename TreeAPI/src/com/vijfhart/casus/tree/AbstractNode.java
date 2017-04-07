package com.vijfhart.casus.tree;

import java.util.SortedSet;
import java.util.TreeSet;

class AbstractNode<E extends Node<E>> implements Node<E> {
	private String naam;
	private E parentNode;
	boolean isLeaf;
	int childCount;

	public String getName() {
		return this.naam;
	}

	public E getParent() {
		return this.parentNode;
	}

	public int compare(E node1, E node2) {
		return node1.compareLevelTo(node2);
	}

	public int compareTo(E node) {
		if (getParent() == null)
			return -1;

		if (getParent() == node)
			return 1;

		if (getParent() == node.getParent()) {
			SortedSet set = new TreeSet();
			set.add(this);
			set.add(node);
			if (set.first() == this) {
				return -1;
			} else {
				return 1;
			}
		} else if (this.compareLevelTo(node) == 0) {
			((AbstractNode) this.getParent()).compareTo(node.getParent());
		}

		if (this.getParent() != node & node.getParent() != this & this.compareLevelTo(node) != 0) {
			if (((AbstractNode) this.getParent()).compareLevelTo(((AbstractNode) node).getParent()) < (node.getParent())
					.compareLevelTo(this.getParent())) {
				((AbstractNode) this.getParent()).compareTo(node);
			} else {
				((AbstractNode) node.getParent()).compareTo(this);
			}
		}

		return 0;
	}

	public int compareLevelTo(E node) {

		if (node.getParent() == null)
			return 1;
		if (getParent() == null)
			return -1;
		if ((getParent()).equals(node.getParent())) {
			return 0;
		}

		return (getParent()).compareLevelTo(node.getParent());

	}

	public void setParent(E node) {

		if (isDescendant(node) == false) {
			((AbstractNode) this.getParent()).childCount -= 1;

			this.parentNode = node;

			((AbstractNode) node).isLeaf = false;
			((AbstractNode) node).childCount += 1;
		}
	}

	private boolean isDescendant(E node) {

		if (node.compareLevelTo((E) this) > 0)
			return true;

		return false;
	}

	// A node without children.
	public boolean isLeaf() {

		if (this.childCount == 0)
			return true;
		return false;
	}

}
