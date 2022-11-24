package com.example.cardealer.model.dto.importDto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name="parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartImportWrapperDto {
  @XmlElement(name = "part")
  private List<PartImportDto> parts;
}
