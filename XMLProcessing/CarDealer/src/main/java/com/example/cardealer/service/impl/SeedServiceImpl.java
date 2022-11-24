package com.example.cardealer.service.impl;


import static com.example.cardealer.constant.Path.CARS_XML;
import static com.example.cardealer.constant.Path.CUSTOMER_XML;
import static com.example.cardealer.constant.Path.PARTS_XML;
import static com.example.cardealer.constant.Path.SUPPLIER_XML;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.example.cardealer.model.dto.importDto.CarImportWrapperDto;
import com.example.cardealer.model.dto.importDto.CustomerImportWrapperDto;
import com.example.cardealer.model.dto.importDto.PartImportWrapperDto;
import com.example.cardealer.model.dto.importDto.SupplierImportWrapperDto;
import com.example.cardealer.model.entity.Car;
import com.example.cardealer.model.entity.Customer;
import com.example.cardealer.model.entity.Part;
import com.example.cardealer.model.entity.Supplier;
import com.example.cardealer.model.enumeration.Discount;
import com.example.cardealer.service.CarService;
import com.example.cardealer.service.CustomerService;
import com.example.cardealer.service.PartService;
import com.example.cardealer.service.SaleService;
import com.example.cardealer.service.SeedService;
import com.example.cardealer.service.SupplierService;
import com.example.cardealer.util.FileManipulationFactory;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeedServiceImpl implements SeedService {
  private final ModelMapper mapper;

  private final CustomerService customerService;
  private final SupplierService supplierService;
  private final PartService partService;
  private final CarService carService;
  private final SaleService saleService;

  @Override public void seedSales() {
    if (saleService.count() == 0) {
      for (int i = 0; i < 32; i++) {
        Customer customer = customerService.getRandomCustomer();
        Car car = carService.getRandomCar();
        Double discount = Discount.getRandomDiscount();
        saleService.save(car, customer, discount);
      }
    }
  }

  @Override public void seedCars() throws IOException, JAXBException {
    if (carService.count() == 0) {
      JAXBContext context = JAXBContext.newInstance(CarImportWrapperDto.class);
      Unmarshaller unmarshaller = context.createUnmarshaller();
      FileReader reader = FileManipulationFactory.getFileReader(CARS_XML);
     ((CarImportWrapperDto) unmarshaller.unmarshal(reader))
       .getCars()
       .stream()
        .map(c -> mapper.map(c, Car.class))
        .peek(c -> c.setParts(partService.getRandomParts()))
        .forEach(carService::save);
      reader.close();
    }
  }

  @Override public void seedParts() throws IOException, JAXBException {
    if (partService.count() == 0) {
      FileReader reader = FileManipulationFactory.getFileReader(PARTS_XML);
      Unmarshaller unmarshaller = JAXBContext.newInstance(PartImportWrapperDto.class).createUnmarshaller();
        ((PartImportWrapperDto) unmarshaller.unmarshal(reader))
          .getParts()
          .stream()
        .map(p -> mapper.map(p, Part.class))
        .peek(p -> p.setSupplier(supplierService.getRandomSupplier()))
        .forEach(partService::save);
      reader.close();
    }
  }

  @Override public void seedSuppliers() throws IOException, JAXBException {
    if (supplierService.count() == 0) {
      FileReader reader = FileManipulationFactory.getFileReader(SUPPLIER_XML);
      JAXBContext context = JAXBContext.newInstance(SupplierImportWrapperDto.class);
      Unmarshaller unmarshaller = context.createUnmarshaller();
      SupplierImportWrapperDto dto = (SupplierImportWrapperDto) unmarshaller.unmarshal(reader);
      dto.getSuppliers()
        .stream()
        .map(s -> mapper.map(s, Supplier.class))
        .forEach(supplierService::save);
      reader.close();
    }
  }

  @Override public void seedCustomers() throws IOException, JAXBException {
    if (customerService.count() == 0) {
      BufferedReader reader = new BufferedReader(FileManipulationFactory.getFileReader(CUSTOMER_XML));
      Unmarshaller unmarshaller = JAXBContext.newInstance(CustomerImportWrapperDto.class).createUnmarshaller();
      ((CustomerImportWrapperDto) unmarshaller.unmarshal(reader))
        .getCustomers()
        .stream()
        .map(c -> mapper.map(c, Customer.class))
        .forEach(customerService::save);
      reader.close();
    }
  }
}
