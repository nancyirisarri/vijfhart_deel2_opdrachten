package com.vijfhart.casus.tree.file;

import java.io.File;
import java.nio.file.Path;

import com.vijfhart.casus.tree.Node;
import com.vijfhart.casus.tree.NodeLong;

public class MyNodeLong implements NodeLong<PathNode> {

	public long get(PathNode node) {
		Path path = ((PathNode) ((Node) node)).getPath();
		return new File(path.toString()).length();
	}
}