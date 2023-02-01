package com.zlennon.chatgpt.repository;

import com.zlennon.chatgpt.model.ReqResItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReqResItemsRepository   extends CrudRepository<ReqResItemsEntity, Long>{

}
