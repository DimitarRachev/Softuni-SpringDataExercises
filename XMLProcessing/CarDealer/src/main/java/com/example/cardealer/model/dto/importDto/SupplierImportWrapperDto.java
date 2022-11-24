package com.example.cardealer.model.dto.importDto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "suppliers")
public class SupplierImportWrapperDto {
  @XmlElement(name = "supplier")
  private List<SupplierImportDto> suppliers;

  public SupplierImportWrapperDto() {
    suppliers = new ArrayList<>();
  }
}
