package com.example.logdesensitization;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class LogDesensitizationTest {

   // private static final Logger log = LoggerFactory.getLogger(LogDesensitizationTest.class);

    @Test
    public void testDes(){
        log.info("身份证：{}，werw：{}，sdfsdgg：{}","62242619991225232X","张三","18810963333");
        log.info("pwd：{}","/vc/teller/v1/login_参数解密后：{\"pwd\":\"111111\",\"accesstoken\":\"accesstoken\",\"imChannelList\":\"[\\\"qq\\\",\\\"qq_in\\\"]\",\"uid\":\"203_2\",\"baseURL\":\"http://10.10.80.104:6071\",\"refreshtoken\":\"token\",\"appKey\":\"tzb\",\"utype\":2,\"uType\":2}");
        log.info("身份证"+"62242619991225232X");
        log.error("ree：{}，werw：{}，sdfsdgg：{},call:{}","62242619991225232X","张三","18810963333","0i1CRW2RImoxxcXD1664259898555");
    }
}
