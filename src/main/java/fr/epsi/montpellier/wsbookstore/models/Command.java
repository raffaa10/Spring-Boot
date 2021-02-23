package fr.epsi.montpellier.wsbookstore.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Date;
import java.util.List;

@Entity
public class Command {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  @Min(1)
  private long customerId;
  private Date orderdate;

  @OneToMany(mappedBy = "commandId")
  private List<Commanddetail> items;


  public Command() {
  }

  public Command(long id, long customerId, Date orderdate) {
    this.id = id;
    this.customerId = customerId;
    this.orderdate = orderdate;
  }

  @Override
  public String toString() {
    return String.format("Command=[id:%d, customerId:%d, orderdate:%s]", id, customerId, orderdate.toString());
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
