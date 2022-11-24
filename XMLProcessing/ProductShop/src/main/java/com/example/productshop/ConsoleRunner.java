package com.example.productshop;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.example.productshop.model.dto.exportDto.CategoriesExportWrapperDto;
import com.example.productshop.model.dto.exportDto.ProductExportWrapperDto;
import com.example.productshop.model.dto.exportDto.SuccessfulSellerWrapperDto;
import com.example.productshop.model.dto.exportDto.UsersAndProductsWrapperExportDto;
import com.example.productshop.service.CategoryService;
import com.example.productshop.service.ProductService;
import com.example.productshop.service.SeedService;
import com.example.productshop.service.UserService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ConsoleRunner implements CommandLineRunner {
  private final SeedService seedService;
  private final UserService userService;
  private final ProductService productService;
  private final CategoryService categoryService;

  @Override public void run(String... args) throws Exception {
    seedService.seedAll();
    //    findAllProductsBetween(500.0, 1000.0);
    //    findAllSuccessfullySellers();
    //    categoriesByProductCount();
    usersAndProducts();

  }

  private void usersAndProducts() throws JAXBException {
    JAXBContext context = JAXBContext.newInstance(UsersAndProductsWrapperExportDto.class);
    Marshaller marshaller = context.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    marshaller.marshal(userService.getUsersAndProducts(), System.out);
    //    userService
    //      .getUsersAndProducts()
    //      .stream()
    //      .map(gson::toJson)
    //      .forEach(System.out::println);
    //    System.out.println(gson.toJson(userService.getUsersAndProducts()));
  }

  private void categoriesByProductCount() throws JAXBException {
    JAXBContext context = JAXBContext.newInstance(CategoriesExportWrapperDto.class);
    Marshaller marshaller = context.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    marshaller.marshal(categoryService
      .findAllByProductCount(), System.out);
  }

  private void findAllSuccessfullySellers() throws JAXBException {
    JAXBContext context = JAXBContext.newInstance(SuccessfulSellerWrapperDto.class);
    Marshaller marshaller = context.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    marshaller.marshal(userService.findAllSuccessfullySellers(), System.out);
  }

  private void findAllProductsBetween(Double lowerBound, Double upperBound) throws JAXBException {
    JAXBContext context = JAXBContext.newInstance(ProductExportWrapperDto.class);
    Marshaller marshaller = context.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    marshaller.marshal(productService.findAllWithPriceBetweenWithNoSeller(lowerBound, upperBound), System.out);
  }
}
