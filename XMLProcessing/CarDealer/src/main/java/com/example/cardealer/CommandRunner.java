package com.example.cardealer;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.example.cardealer.model.dto.exportDto.SaleDetailsDto;
import com.example.cardealer.model.dto.exportDto.CarExportWrapperDto;
import com.example.cardealer.model.dto.exportDto.CarWithPartsListWrapperDto;
import com.example.cardealer.model.dto.exportDto.CustomerExportWrapperDto;
import com.example.cardealer.model.dto.exportDto.CustomerWithSalesWrapperDto;
import com.example.cardealer.model.dto.exportDto.SaleDetailsWrapperDto;
import com.example.cardealer.model.dto.exportDto.SupplierSimpleExportWrapperDto;
import com.example.cardealer.service.CarService;
import com.example.cardealer.service.CustomerService;
import com.example.cardealer.service.SaleService;
import com.example.cardealer.service.SeedService;
import com.example.cardealer.service.SupplierService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CommandRunner implements CommandLineRunner {
  private final SeedService seedService;
  private final CustomerService customerService;
  private final SupplierService supplierService;
  private final SaleService saleService;
  private final CarService carService;

  @Override public void run(String... args) throws Exception {
    seedService.seedAll();
//    orderedCustomers();
//        getAllByMaker("Toyota");
//        getLocalSuppliers();
//        getCarsWithPartsList();
//        getTotalSalesByCustomer();
        getSalesWithDiscount();
  }

  private void getSalesWithDiscount() throws JAXBException {
    Marshaller marshaller = JAXBContext.newInstance(SaleDetailsWrapperDto.class)
      .createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    marshaller
      .marshal(saleService.getSalesWithDiscount(), System.out);
  }

  private void getTotalSalesByCustomer() throws JAXBException {
    Marshaller marshaller = JAXBContext.newInstance(CustomerWithSalesWrapperDto.class)
      .createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    marshaller
      .marshal(customerService.getTotalSalesByCustomer(), System.out);
  }

  private void getCarsWithPartsList() throws JAXBException {
    Marshaller marshaller = JAXBContext.newInstance(CarWithPartsListWrapperDto.class)
      .createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    marshaller
      .marshal(carService.getCarsWithPartsList(), System.out);
  }

  private void getLocalSuppliers() throws JAXBException {
    Marshaller marshaller = JAXBContext.newInstance(SupplierSimpleExportWrapperDto.class)
      .createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    marshaller
      .marshal(supplierService.getLocalSuppliers(), System.out);
  }

  private void getAllByMaker(String make) throws JAXBException {
    JAXBContext context = JAXBContext.newInstance(CarExportWrapperDto.class);
    Marshaller marshaller = context
      .createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    marshaller
      .marshal(carService.getAllToyotas(make), System.out);
  }

  private void orderedCustomers() throws JAXBException {
    Marshaller marshaller = JAXBContext.newInstance(CustomerExportWrapperDto.class)
      .createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    marshaller
      .marshal(customerService.findAllOrderedByBirthDateAndYoung(), System.out);
  }
}
