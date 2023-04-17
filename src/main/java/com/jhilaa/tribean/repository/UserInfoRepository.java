package com.jhilaa.tribean.repository;

import com.jhilaa.tribean.model.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends CrudRepository<UserInfo, Long> {

    //UserInfo findUserInfoByEmail(String email);
    UserInfo findOneByEmail(String email);
}

