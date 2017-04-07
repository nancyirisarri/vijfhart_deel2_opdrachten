package com.vijfhart.casus.tree.stat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.RecursiveTask;

import com.vijfhart.casus.tree.Node;
import com.vijfhart.casus.tree.file.MyNodeLong;
import com.vijfhart.casus.tree.file.PathNode;

public class NodeLongSummary<E extends Node<E>> extends RecursiveTask {
	private ArrayList<E> nodeList = new ArrayList<>();
	MyNodeLong nodeLong = new MyNodeLong();
	final int THRESHOLD;

	public NodeLongSummary(int value) {
		this.THRESHOLD = value;
	}

	public LongSummaryStatistics compute() {
		if (nodeList.size() <= THRESHOLD) {

			Iterator<E> treeIterator = nodeList.iterator();
			LongSummaryStatistics summary = new LongSummaryStatistics();

			while (treeIterator.hasNext()) {
				summary.add(nodeLong.get((PathNode) treeIterator.next()));
			}
			return summary;
		}

		System.out.println("forking...");
		int nodeListSize = nodeList.size();

		NodeLongSummary nls1 = new NodeLongSummary(10);
		nls1.setNodeList((nodeList).subList(0, (int) (nodeListSize / 2)));
		nls1.fork();

		NodeLongSummary nls2 = new NodeLongSummary(10);
		nls2.setNodeList((nodeList).subList((int) (nodeListSize / 2), nodeListSize - 1));
		LongSummaryStatistics lss2 = nls2.compute();

		LongSummaryStatistics lss1 = (LongSummaryStatistics) nls1.join();

		lss1.combine(lss2);

		return lss1;
	}

	public void setNodeList(List<E> list) {
		this.nodeList = new ArrayList(list);
	}

	public void setNodeList(ArrayList<E> list) {
		this.nodeList = list;
	}
}