/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl2_project;

/**
 *
 * @author Mohamed Hassan
 */
public class Event {
  protected String name;
  protected String date;
  protected String place;
  protected int numberOfChairs;
  protected int numberOfTables;
  protected int state;

  public Event(String name, String date, String place, int numberOfChairs, int numberOfTables) {
    this.name = name;
    this.date = date;
    this.place = place;
    this.numberOfChairs = numberOfChairs;
    this.numberOfTables = numberOfTables;
    this.state = -1;
  }

  Event() {
    this.state = -1;
  }

  @Override
  public String toString() {
    return "name:" + name + "\ndate:" + date + "\nplace:" + place + "\nnumberOfChairs:" + numberOfChairs+ "\nnumberOfTables:" + numberOfTables + "\nstate:" + state;
  }
}
