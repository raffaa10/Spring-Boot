package fr.epsi.montpellier.wsbookstore.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Commanddetail {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  //private long commandId;
  private String bookIsbn;
  private double unitprice;
  private long quantity;
  private double discount;

//Provoque une ambiguouité et une erreur -> boocle infini

  @ManyToOne
  @JoinColumn(name="command_id", nullable = false)
  private Command command;

  public Command getCommand() {
    return command;
  }

  public void setCommand(Command command) {
    this.command = command;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

/*
  public long getCommandId() {
    return commandId;
  }

  public void setCommandId(long commandId) {
    this.commandId = commandId;
  }
*/

  public String getBookIsbn() {
    return bookIsbn;
  }

  public void setBookIsbn(String bookIsbn) {
    this.bookIsbn = bookIsbn;
  }


  public double getUnitprice() {
    return unitprice;
  }

  public void setUnitprice(double unitprice) {
    this.unitprice = unitprice;
  }


  public long getQuantity() {
    return quantity;
  }

  public void setQuantity(long quantity) {
    this.quantity = quantity;
  }


  public double getDiscount() {
    return discount;
  }

  public void setDiscount(double discount) {
    this.discount = discount;
  }

}
