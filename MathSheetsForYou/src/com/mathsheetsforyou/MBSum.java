package com.mathsheetsforyou;

public class MBSum {
	private int numberA;
	private int numberB;
	private int sum;
	private int difference;
	
	public int getNumberA() {
		return numberA;
	}
	
	public void setNumberA(int numberA) {
		this.numberA = numberA;
	}
	
	public int getNumberB() {
		return numberB;
	}
	
	public void setNumberB(int numberB) {
		this.numberB = numberB;
	}
	
	public int getSum() {
		return numberA + numberB;
	}

	public int getDifference() {
		return numberA - numberB;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numberA;
		result = prime * result + numberB;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MBSum other = (MBSum) obj;
		if (numberA != other.numberA)
			return false;
		if (numberB != other.numberB)
			return false;
		return true;
	}
	
	
}
