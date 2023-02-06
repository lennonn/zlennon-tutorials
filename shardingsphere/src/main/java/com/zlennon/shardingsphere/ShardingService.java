package com.zlennon.shardingsphere;

import com.zlennon.chatgpt.model.ReqResItemsEntity;
import com.zlennon.chatgpt.repository.ReqResItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ShardingService {

    @Autowired
    ReqResItemsRepository reqResItemsRepository;

    public void shardingInsert(String req,String res){
        ReqResItemsEntity resItemsEntity = new ReqResItemsEntity();
        resItemsEntity.setApi("/v1/completions");
        resItemsEntity.setReqInfo(req);
        resItemsEntity.setResInfo(res);
        resItemsEntity.setType(1);
        reqResItemsRepository.save(resItemsEntity);
    }

}
