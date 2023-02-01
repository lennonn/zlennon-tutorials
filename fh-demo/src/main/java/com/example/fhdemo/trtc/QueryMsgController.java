package com.example.fhdemo.trtc;

import com.alibaba.fastjson.JSONObject;
import com.example.fhdemo.trtc.tencentyun.TLSSigAPIv2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api/im")
public class QueryMsgController {
    @Autowired
    RestTemplate restTemplate;
    @Value("${trtc.im.url}")
    private String url;
    @Value("${trtc.im.sdkappid}")
    private Long sdkappid;
    @Value("${trtc.im.key}")
    private String  key;
    @Value("${trtc.im.identifier}")
    private String  identifier;


    @GetMapping(value = "/getRoamMsg")
    public ResponseEntity<String> getRoamMsg() {
        String userSig= getUserSig();
        HttpHeaders headers = new HttpHeaders();
        Map<String, Object> params= new HashMap<>();
        params.put("Operator_Account", "1_cus_q4067417");
        params.put("Peer_Account", "1_usr_q40641");
        params.put("MaxCnt", 100);
        params.put("MinTime", 1653420700);
        params.put("MaxTime", 1683424300);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(params);
        String path="/v4/openim/admin_getroammsg";
        Map<String, Object> urlParams = new HashMap<>();
        urlParams.put("sdkappid", sdkappid);
        urlParams.put("identifier", identifier);
        urlParams.put("usersig", userSig);
        ResponseEntity<String> forEntity = restTemplate.exchange(url + path
                + "?sdkappid={sdkappid}&identifier={identifier}&usersig={usersig}&random=99999999&contenttype=json",HttpMethod.POST,request, String.class, urlParams);
        log.info("msg:{}",forEntity.getBody());
        return forEntity;
    }

    public String getUserSig(){
        TLSSigAPIv2 api = new TLSSigAPIv2(sdkappid, key);
        String userSig = api.genUserSig("vc_system", 1800 * 86400);
        log.info("usersig==>>:\r\n{}",userSig);
        return userSig;
    }
}
