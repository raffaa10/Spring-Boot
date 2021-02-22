package fr.epsi.montpellier.wsbookstore.models;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Book {

    @Id
    private String isbn;
    @Min(1)
    private long publisherId;
    @NotBlank
    private String title;
    private Date publicationyear;
    @Min(1)
    private double price;
    private String format;
    private String edition;
    @Min(10)
    private long pages;
    private String dimensions;
    private String overview;
    private String synopsis;

    public Book() {
    }

  public Book(String isbn, long publisherId, String title, Date publicationyear, double price, String format, String edition, long pages, String dimensions, String overview, String synopsis) {
    this.isbn = isbn;
    this.publisherId = publisherId;
    this.title = title;
    this.publicationyear = publicationyear;
    this.price = price;
    this.format = format;
    this.edition = edition;
    this.pages = pages;
    this.dimensions = dimensions;
    this.overview = overview;
    this.synopsis = synopsis;
  }

  public void updateFrom(@NotNull Book book) {
    this.publicationyear = book.publicationyear;
    this.price = book.price;
    this.format = book.format;
    this.edition = book.edition;
    this.pages = book.pages;
    this.dimensions = book.dimensions;
    this.overview = book.overview;
    this.synopsis = book.synopsis;
  }

  @Override
  public String toString() {
    return String.format("Book=[isbn:%s, title:%s, publisherId:%d]", isbn, title, publisherId);
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }


  public long getPublisherId() {
    return publisherId;
  }

  public void setPublisherId(long publisherId) {
    this.publisherId = publisherId;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Date getPublicationyear() {
    return publicationyear;
  }

  public void setPublicationyear(Date publicationyear) {
    this.publicationyear = publicationyear;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getFormat() {
    return format;
  }

  public void setFormat(String format) {
    this.format = format;
  }

  public String getEdition() {
    return edition;
  }

  public void setEdition(String edition) {
    this.edition = edition;
  }

  public long getPages() {
    return pages;
  }

  public void setPages(long pages) {
    this.pages = pages;
  }

  public String getDimensions() {
    return dimensions;
  }

  public void setDimensions(String dimensions) {
    this.dimensions = dimensions;
  }

  public String getOverview() {
    return overview;
  }

  public void setOverview(String overview) {
    this.overview = overview;
  }

  public String getSynopsis() {
    return synopsis;
  }

  public void setSynopsis(String synopsis) {
    this.synopsis = synopsis;
  }
}
