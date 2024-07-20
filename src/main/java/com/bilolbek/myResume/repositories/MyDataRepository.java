package com.bilolbek.myResume.repositories;

import org.springframework.data.repository.CrudRepository;
import com.bilolbek.myResume.api.model.MyData;

public interface MyDataRepository extends CrudRepository<MyData, Integer> {

}
