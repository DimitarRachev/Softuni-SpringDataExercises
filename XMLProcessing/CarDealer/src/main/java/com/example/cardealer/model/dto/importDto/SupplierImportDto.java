package com.example.cardealer.model.dto.importDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name="supplier")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierImportDto {
  @XmlAttribute(name = "name")
  private String name;
@XmlAttribute(name = "is-importer")
  private Boolean isImporter;
}
