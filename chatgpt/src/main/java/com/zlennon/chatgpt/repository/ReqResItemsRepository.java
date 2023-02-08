package com.zlennon.chatgpt.repository;

import com.zlennon.chatgpt.model.ReqResItemsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReqResItemsRepository   extends CrudRepository<ReqResItemsEntity, Long>{

    @Query(value = "SELECT * FROM req_res_items",nativeQuery=true)
    List<ReqResItemsEntity> findA();
}
