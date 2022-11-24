package com.example.cardealer.model.dto.exportDto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierSimpleExportWrapperDto {
  @XmlElement(name = "supplier")
  private List<SupplierSimpleExportDto> suppliers;

  public SupplierSimpleExportWrapperDto() {
    suppliers = new ArrayList<SupplierSimpleExportDto>();
  }
}
