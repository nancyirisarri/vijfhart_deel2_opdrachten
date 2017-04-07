package com.vijfhart.casus.tree.stat;

public class LongSummaryStatistics {
	private long count;
	private long min;
	private long max;
	private long sum;

	public long getCount() {
		return this.count;
	}

	public long getMin() {
		return this.min;
	}

	public long getMax() {
		return this.max;
	}

	public long getSum() {
		return this.sum;
	}

	public void count() {
		this.count++;
	}

	public void min(long value) {
		if (value < this.min) {
			this.min = value;
		}
	}

	public void max(long value) {
		if (value > this.max) {
			this.max = value;
		}
	}

	public void sum(long value) {
		this.sum += value;
	}

	public double average() {
		return this.sum / this.count;
	}

	public void add(long value) {
		count();
		min(value);
		max(value);
		sum(value);
		average();
	}

	public void combine(LongSummaryStatistics lss) {
		this.count += lss.getCount();
		this.min = this.min <= lss.getMin() ? this.min : lss.getMin();
		this.max = this.max <= lss.getMax() ? this.max : lss.getMax();
		this.sum += lss.getSum();
	}
}