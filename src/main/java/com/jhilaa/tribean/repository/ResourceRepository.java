package com.jhilaa.tribean.repository;

import com.jhilaa.tribean.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ResourceRepository extends CrudRepository<Resource, Long> {
    //TODO les méthodes qui complètent : findAll, ...
    List<Resource> findResourcesByTagsId(Long tagId);

    List<Resource> findByTitleContaining (String string);

}



