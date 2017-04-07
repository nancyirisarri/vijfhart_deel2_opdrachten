package com.vijfhart.casus.tree.file;

import java.nio.file.Path;

import com.vijfhart.casus.tree.WrapperNode;

public class PathNode extends WrapperNode<PathNode, Path> {

	private Path path;

	PathNode(Path pathIn) {
		super(pathIn);
		this.path = pathIn;
	}

	PathNode(Path pathIn, PathNode parent) {
		super(pathIn, parent);
		this.path = pathIn;
	}

	public Path getPath() {
		return this.path;
	}

	public String toString() {
		return this.path.toString();
	}
}