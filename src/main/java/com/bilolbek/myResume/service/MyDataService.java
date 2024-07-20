package com.bilolbek.myResume.service;



import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bilolbek.myResume.api.model.MyData;
import com.bilolbek.myResume.repositories.MyDataRepository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import jakarta.transaction.Transactional;

@Service
public class MyDataService {

    @Autowired
    private MyDataRepository myDataRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Transactional
    public String saveMyData(MyData myData){

        myDataRepository.save(myData);

        return "success";
    }

    @Transactional
    public String updateMyData(MyData myData){
        MyData myData2 = myDataRepository.findById(1)
            .orElseThrow(() -> new ResourceNotFoundException("My data not found in updateMyData"));
        
        myData2.setFName(myData.getFName());
        myData2.setLName(myData.getLName());
        myData2.setDescription(myData.getDescription());
        myData2.setJobTitle(myData.getJobTitle());

        myDataRepository.save(myData2);

        return "success";
    }

    @Transactional
    public String getMyData(){
        

        MyData myData = myDataRepository.findById(1)
            .orElseThrow(() -> new ResourceNotFoundException("My data not found in getMyData"));
        
        

        try{
            String myDataAsJson = objectMapper.writeValueAsString(myData);
            return myDataAsJson;
        }
        catch(JsonProcessingException e){
            return "error in json processing in getMyData";
        }
    }

}
