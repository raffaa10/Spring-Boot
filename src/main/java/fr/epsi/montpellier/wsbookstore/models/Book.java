package fr.epsi.montpellier.wsbookstore.models;

public class Book {

    private String isbn;
    private long publisherId;
    private String title;

  public Book(String isbn, long publisherId, String title) {
    this.isbn = isbn;
    this.publisherId = publisherId;
    this.title = title;
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

}
