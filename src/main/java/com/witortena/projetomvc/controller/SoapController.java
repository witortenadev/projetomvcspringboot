package com.witortena.projetomvc.controller;

import com.witortena.projetomvc.DTO.SoapDTO;
import com.witortena.projetomvc.model.Soap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/soap")
public class SoapController {

    private final List<Soap> soaps = new ArrayList<>();

    public SoapController() {
    }

    @GetMapping
    public List<Soap> readSoaps() {
        return soaps;
    }

    @GetMapping("/{id}")
    public Optional<Soap> readSoapById(@PathVariable(name = "id") int id) {
        return soaps.stream()
                .filter(soap -> soap.getId() == id)
                .findFirst();
    }

    @PutMapping("/{id}")
    public Optional<Soap> updateSoap(@PathVariable int id, @RequestBody SoapDTO updatedSoap) {
        Optional<Soap> soapToUpdate = soaps.stream()
                .filter(soap -> soap.getId() == id)
                .findFirst();

        soapToUpdate.ifPresent(soap -> {
            soap.setName(updatedSoap.getName());
            soap.setDescription(updatedSoap.getDescription());
            soap.setPrice(updatedSoap.getPrice());
        });

        return soapToUpdate;
    }

    @PostMapping
    public Soap createSoap(@RequestBody SoapDTO soap) {
        Soap newSoap = new Soap((soaps.size() + 1), soap.getName(), soap.getDescription(), soap.getPrice());
        soaps.add(newSoap);
        return newSoap;
    }
}