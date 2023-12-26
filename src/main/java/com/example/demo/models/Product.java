package com.example.demo.models;

public class Product {
  private int serialNumber;
  private String name;
  private String vendor;
  private String category;
  private double price;
  private int remainingQuantity;

  public Product(int serialNumber, String name, String vendor, String category, double price, int remainingQuantity) {
    this.serialNumber = serialNumber;
    this.name = name;
    this.vendor = vendor;
    this.category = category;
    this.price = price;
    this.remainingQuantity = remainingQuantity;
  }

  public int getSerialNumber() {
    return serialNumber;
  }

  public String getName() {
    return name;
  }

  public String getVendor() {
    return vendor;
  }

  public String getCategory() {
    return category;
  }

  public double getPrice() {
    return price;
  }

  public int getRemainingQuantity() {
    return remainingQuantity;
  }

  public void setSerialNumber(int serialNumber) {
    this.serialNumber = serialNumber;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setVendor(String vendor) {
    this.vendor = vendor;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public void setRemainingQuantity(int remainingQuantity) {
    this.remainingQuantity = remainingQuantity;
  }

  public String toString() {
    return "Product{" +
      "serialNumber=" + serialNumber +
      ", name='" + name + '\'' +
      ", vendor='" + vendor + '\'' +
      ", category='" + category + '\'' +
      ", price=" + price +
      ", remainingQuantity=" + remainingQuantity +
      '}';
  }
}
