package com.vijfhart.casus.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class NodeTree<E extends Node<E>> implements Tree<E> {

	private List<E> nodeList = new ArrayList<>();

	public void add(E node) {
		nodeList.add(node);
	}

	public TreeIterator<E> treeIterator() {
		return iterator();
	}

	public TreeIterator<E> iterator() throws IllegalStateException, UnsupportedOperationException {

		return new TreeIterator<E>() {
			Iterator<E> iterator = nodeList.iterator();
			E current;
			E startNode;

			{
				Comparator comparator = new NameNode();

				Collections.sort(nodeList, comparator);

				this.iterator = nodeList.iterator();
			}

			public String path(NodeString nodeString, String separator) {
				StringBuilder sb = new StringBuilder();

				sb.append(separator);
				sb.append(this.current);

				if (this.current != null) {
					E parent = this.current.getParent();

					while (parent != null && descendantOfStartWith(parent) || parent == startNode) {
						sb.insert(0, parent);

						sb.insert(0, separator);

						parent = parent.getParent();
					}
				}

				return sb.toString();

			}

			public int level() {

				int level = 0;

				if (startNode != null && current.equals(startNode)) {
					return level;
				}

				if (current.getParent() == null) {
					return level;
				}

				E parent = current.getParent();
				level = +1;

				while (parent.getParent() != null) {

					if (startNode != null) {
						if (descendantOfStartWith(parent)) {
							level++;
						}
					} else {
						level++;
					}

					parent = parent.getParent();
				}

				return level;

			}

			public void startWith(E startNode) {
				if (this.current != null) {
					throw new IllegalStateException("Cannot execute method, next() has been called.");
				}

				List<E> newList = new ArrayList<>();

				this.startNode = startNode;

				while (this.iterator.hasNext()) {

					E curr = this.iterator.next();

					if (descendantOfStartWith(curr) | curr == this.startNode) {
						newList.add(curr);
					}

				}

				this.iterator = newList.iterator();

			}

			private boolean descendantOfStartWith(E node) {

				if (node.compareLevelTo(startNode) > 0)
					return true;

				return false;
			}

			public boolean isLeaf() {
				return true;
			}

			public void orderSiblingsBy(Comparator comparator) {
				comparator = comparator;
			};

			public TreeIterator<E> treeIterator() {
				return (TreeIterator) iterator;
			}

			public void remove() {
				if (this.current == null) {
					throw new IllegalStateException("Cannot execute method, next() has not been called.");
				}

				// in case of trouble, see not implemented point on 4.3.4
				if (this.current.isLeaf()) {
					iterator.remove();
				} else {
					throw new UnsupportedOperationException("Cannot remove, it is not a leaf.");
				}

			}

			// Iterate through list, and keep current element in instance
			// variable.
			public E next() {

				current = iterator.next();
				return current;
			}

			public boolean hasNext() {
				return iterator.hasNext();
			}

		};

	}

}
