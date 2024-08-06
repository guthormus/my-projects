/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl2_project;

/**
 *
 * @author Mohamed Hassan
 */
public class Bill {
	protected int priceOfChair;
	protected int priceOfTable;
	protected int NOC;
	protected int NOT;
	protected double a1;
	protected double a2;

	public Bill(int NOC, int NOT) {
		this.priceOfChair = 10;
		this.priceOfTable = 20;
		this.NOC = NOC;
		this.NOT = NOT;
		this.a1 = (NOC * 10);
		this.a2 = (NOT * 20);
	}

	@Override
	public String toString() {
		return "\nBill : \n" + "price Of Chair=" + priceOfChair + "\nprice Of Table=" + priceOfTable + "\n"+ "Total Price = " + NOC + "*" + priceOfChair + "+" + NOT + "*" + priceOfTable + "=" + (a1 + a2);
	}

}
