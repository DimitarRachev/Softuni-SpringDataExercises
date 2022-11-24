package com.example.cardealer.model.dto.importDto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@XmlRootElement(name="customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerImportWrapperDto {
  @XmlElement(name="customer")
  private List<CustomerImportDto> customers;

  public CustomerImportWrapperDto() {
    customers = new ArrayList<CustomerImportDto>();
  }
}
