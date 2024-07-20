package com.bilolbek.myResume.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bilolbek.myResume.api.model.Hobbies;
import com.bilolbek.myResume.repositories.HobbiesRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@Service
public class HobbiesService {

    @Autowired
    private HobbiesRepository hobbiesRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Transactional
    public String setHobbies(Hobbies hobbies){
        hobbiesRepository.save(hobbies);
        return "success";
        
    }

    @Transactional
    public String getAllHobbies(){
        

        Iterable<Hobbies> findAllHobbies = hobbiesRepository.findAll();

        try{
            String allHobbiesAsJson = objectMapper.writeValueAsString(findAllHobbies);
            return allHobbiesAsJson;
        }
        catch(JsonProcessingException e){
            return "Error in making JSON in getAllHobbies";
        }
    }
}
