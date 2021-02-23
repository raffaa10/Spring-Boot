package fr.epsi.montpellier.wsbookstore.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Command {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private long customerId;
  private Date orderdate;

  @OneToMany(mappedBy = "commandId")
  private List<Commanddetail> items;


  public Command() {
  }

  public Command(Date orderdate) {
    this.orderdate = orderdate;
  }

  public Command(long id, long customerId, Date orderdate) {
    this.id = id;
    this.customerId = customerId;
    this.orderdate = orderdate;
  }

  public List<Commanddetail> getItems() {
    return items;
  }

  public void setItems(List<Commanddetail> items) {
    this.items = items;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(long customerId) {
    this.customerId = customerId;
  }


  public Date getOrderdate() {
    return orderdate;
  }

  public void setOrderdate(Date orderdate) {
    this.orderdate = orderdate;
  }

}
