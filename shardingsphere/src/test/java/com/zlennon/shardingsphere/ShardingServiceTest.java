package com.zlennon.shardingsphere;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ShardingServiceTest {

        @Autowired
        ShardingService shardingService;

        @Test
       public void  testShardingInsert(){
                for (int i = 0; i <100;i++){
                    JSONObject obj = new JSONObject();
                    obj.put("data",i);
                    String req = obj.toString();
                    String res = obj.toString();
                    shardingService.shardingInsert(req,res);
                }
        }
}
