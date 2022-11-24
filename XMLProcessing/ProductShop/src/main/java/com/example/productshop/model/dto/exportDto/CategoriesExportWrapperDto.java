package com.example.productshop.model.dto.exportDto;

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
@XmlRootElement(name = "categories")
public class CategoriesExportWrapperDto {

  @XmlElement(name = "category")
  private List<CategoryInfoExportDto> categories;

  public CategoriesExportWrapperDto() {
    categories = new ArrayList<CategoryInfoExportDto>();
  }
}
