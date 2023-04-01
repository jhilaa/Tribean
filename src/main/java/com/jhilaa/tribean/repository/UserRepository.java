package com.jhilaa.tribean.repository;

import com.jhilaa.tribean.model.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserInfo, Long> {
UserInfo findOneByEmail(String email);
}

