package com.vijfhart.casus.tree;

import java.util.Iterator;

class TreeApp {

	public static void main(String args[]) {

		NameNode kraay = new NameNode("Kraay");
		NameNode pieters = new NameNode("Pieters", kraay);
		NameNode sanders = new NameNode("Sanders", pieters);
		NameNode adelaar = new NameNode("Adelaar", sanders);
		NameNode vermeulen = new NameNode("Vermeulen", pieters);
		NameNode smits = new NameNode("Smits", vermeulen);

		Tree tree = new NodeTree();
		tree.add(smits);
		tree.add(sanders);
		tree.add(adelaar);
		tree.add(vermeulen);
		tree.add(pieters);
		tree.add(kraay);

		Iterator<NameNode> i = tree.iterator();

		((TreeIterator) i).startWith(pieters);

		NodeString<NameNode> nodeName = new NodeString<NameNode>() {

			public String get(NameNode node) {
				return "";
			}

		};

		while (i.hasNext()) {

			System.out.printf("%s %s ", i.next(), ((TreeIterator) i).level());

			String s = ((TreeIterator) i).path(nodeName, "/");

			System.out.printf("%s", s);

			System.out.println();
		}

	}
}
