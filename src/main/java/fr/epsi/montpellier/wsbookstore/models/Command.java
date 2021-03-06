package fr.epsi.montpellier.wsbookstore.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="command") // On peut aussi omettre cette annotation
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Command {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  @Min(1)
  private long customerId;
  private Date orderdate;

  @OneToMany(mappedBy = "command")
  private List<Commanddetail> items;


  public Command() {
    this.items = null;
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
