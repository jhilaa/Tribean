package com.jhilaa.tribean.service;

import com.jhilaa.tribean.dto.Mapper;
import com.jhilaa.tribean.dto.responseDto.ResourceResponseWithTagResponsesListDto;
import com.jhilaa.tribean.dto.responseDto.TagResponseWithResourceResponsesListDto;
import com.jhilaa.tribean.model.Resource;
import com.jhilaa.tribean.model.Tag;
import com.jhilaa.tribean.repository.ResourceRepository;
import com.jhilaa.tribean.repository.TagRepository;
import com.jhilaa.tribean.repository.TagRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    Mapper mapper;

    public List<TagResponseWithResourceResponsesListDto> findAll () {
        List<TagResponseWithResourceResponsesListDto> tagResponseWithResourceResponsesListDto = new ArrayList<TagResponseWithResourceResponsesListDto>();
        for (Tag tag : tagRepository.findAll())
        {
            tagResponseWithResourceResponsesListDto.add(mapper.tagToTagResponseWithResourceResponseListDto(tag));
        }
        return tagResponseWithResourceResponsesListDto;
    }

    public TagResponseWithResourceResponsesListDto findById (Long id) {
        Tag tag = tagRepository.findById(id).get();
        return mapper.tagToTagResponseWithResourceResponseListDto(tag);
    }

    /**
     * Create a new Tag
     */
    public ResponseEntity<Object> createTag(Tag newTag) {
        Tag tag = new Tag();
        if (tagRepository.findTagByName(newTag.getName()) != null) {
            return ResponseEntity.badRequest().body("The Tag is already Present, Failed to Create new Tag");
        } else {
            tag.setName(newTag.getName());
            tag.setColor(newTag.getColor());
            tag.setResources(newTag.getResources());

            Tag savedTag = tagRepository.save(tag);
            if (tagRepository.findById(savedTag.getId()).isPresent())
                return ResponseEntity.ok("Tag Created Successfully");
            else return ResponseEntity.unprocessableEntity().body("Failed Creating Tag as Specified");
        }
    }

    /**
     * Update an Existing Tag
     */
    @Transactional
    public ResponseEntity<Object> updateTag(Tag tag) {
        if (tagRepository.findById(tag.getId()).isPresent()) {
            Tag newTag = tagRepository.findById(tag.getId()).get();
            newTag.setColor(tag.getColor());
            newTag.setName(tag.getName());
            Tag savedTag = tagRepository.save(newTag);
            if (tagRepository.findById(savedTag.getId()).isPresent())
                return ResponseEntity.accepted().body("Tag updated successfully");
            else return ResponseEntity.unprocessableEntity().body("Failed updating the tag specified");
        } else return ResponseEntity.unprocessableEntity().body("Cannot find the tag specified");
    }

    /**
     * Delete a Tag
     */
    public ResponseEntity<Object> deleteTag(Long id) {
        if (tagRepository.findById(id).isPresent()) {
            if (tagRepository.findById(id).get().getResources().size() == 0) {
                tagRepository.deleteById(id);
                if (tagRepository.findById(id).isPresent()) {
                    return ResponseEntity.unprocessableEntity().body("Failed to Delete the specified Tag");
            } else return ResponseEntity.ok().body("Successfully deleted the specified tag");
            } else return ResponseEntity.ok().body("Failed to delete,  Please delete the tags associated with this resource");
        } else
            return ResponseEntity.badRequest().body("No Records Found");
    }
}

