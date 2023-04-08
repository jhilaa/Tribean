package com.jhilaa.tribean.repository;

import com.jhilaa.tribean.model.Resource;
import com.jhilaa.tribean.model.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TagRepository extends CrudRepository<Tag, Long> {
    //TODO les méthodes qui complètent : findAll, ...
    List<Tag> findTagsByResourcesId(Long ResourceId);

    Tag findTagByName(String name);
}