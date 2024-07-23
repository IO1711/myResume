package com.bilolbek.myResume.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.bilolbek.myResume.api.model.Contact;
import com.bilolbek.myResume.repositories.ContactRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Transactional
    public String saveContact(Contact contact){
        System.out.println("Received: "+contact);
        contactRepository.save(contact);
        return "success";
    }


    @Transactional
    public String getContact(){
        Iterable<Contact> allContact = contactRepository.findAll();

        List<Contact> listOfContact = StreamSupport.stream(allContact.spliterator(), false).collect(Collectors.toList());

        Contact readyContact = listOfContact.get(0);

        try {
            String contactJson = objectMapper.writeValueAsString(readyContact);
            return contactJson;
        } catch (JsonProcessingException e) {
            return "Error in Json processing in getContact";
        }
    }



}
