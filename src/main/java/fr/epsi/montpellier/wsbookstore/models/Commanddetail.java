package fr.epsi.montpellier.wsbookstore.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "commanddetail")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Commanddetail {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  //private long commandId;
  @Length(min = 13, max = 13, message = "Le code ISBN est de 13 caractères")
  private String bookIsbn;
  @Min(1)
  private double unitprice;
  @Min(1)
  @Max(100)
  private long quantity;
  private double discount;



  //Provoque une ambiguouité et une erreur -> boocle infini


  @ManyToOne
  private Command command;


  @Override
  public String toString() {
    return String.format("Commanddetail=[id:%d, commandId:%d, bookIsbn:%s]", id, command.getId(), bookIsbn);
  }

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
