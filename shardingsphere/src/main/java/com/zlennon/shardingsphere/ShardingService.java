package com.zlennon.shardingsphere;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.zlennon.chatgpt.model.ReqResItemsEntity;
import com.zlennon.chatgpt.repository.ReqResItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShardingService {

    @Autowired
    ReqResItemsRepository reqResItemsRepository;

    public void shardingInsert(String req,String res,int type){
        ReqResItemsEntity resItemsEntity = new ReqResItemsEntity();
        resItemsEntity.setApi("/v1/completions");
        resItemsEntity.setReqInfo(req);
        resItemsEntity.setResInfo(res);
        resItemsEntity.setType(type);
        reqResItemsRepository.save(resItemsEntity);
    }

    public void shardingSearch(){
        Iterable<ReqResItemsEntity> all = reqResItemsRepository.findA();
        all.forEach(r->System.out.println(JSONUtil.toJsonStr(r)));
    }

}
