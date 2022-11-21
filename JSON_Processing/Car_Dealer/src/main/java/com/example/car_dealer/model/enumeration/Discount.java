package com.example.car_dealer.model.enumeration;

import java.util.Random;


public enum Discount {
  ZERO(0.0), FIVE(5.0), FIFTEEN(15.0), TWENTY(20.0), THIRTY(30.0), FORTY(40.0), FIFTY(50.0);


  private final Double discount;

  Discount(Double discount) {
    this.discount = discount;
  }

  public static Double getRandomDiscount() {
    Random random = new Random();
    return Discount.values()[random.nextInt(Discount.values().length)].getDiscount();
  }

  public Double getDiscount() {
    return 1 - discount/ 100 ;
  }
}
