package com.example.fhdemo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.json.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
@RequestMapping("/custom")
@Slf4j
public class CustomController {

    @Value("${fileUrl}")
    String fileUrl;

    @Value("imAsynSend:true")
    private String imAsynSend;

    @RequestMapping(value = "getCustomInfo")
    @ResponseBody
    public String getCustomInfo(@RequestBody String json) {
        log.info("custom request param:{}",json);
        String fileSeparator = File.separator;
        try {
            File file = new File(fileUrl);
            // create JsonReader object
            JsonReader jsonReader = Json.createReader(new FileInputStream(file));
            // get JsonObject from JsonReader
            JSONObject  jsonObject=JSONObject.parseObject(jsonReader.readObject().toString());
            JSONObject requestParam = JSONObject.parseObject(json);
            JSONArray videoStreams = requestParam.getJSONArray("videoStreams");
            JSONObject customInfo = new JSONObject();
            JSONObject encodeParams = new JSONObject();
            JSONObject encodeParams1 = JSONObject.parseObject(jsonObject.getJSONObject("EncodeParams").toString()) ;
            encodeParams.put("videoWidth",encodeParams1.get("videoWidth"));
            encodeParams.put("videoHeight",encodeParams1.get("videoHeight"));
            encodeParams.put("videoBitrate",encodeParams1.get("videoBitrate"));
            encodeParams.put("videoFramerate",encodeParams1.get("videoFramerate"));
            encodeParams.put("videoGop",encodeParams1.get("videoGop"));
            encodeParams.put("backgroundColor",encodeParams1.get("backgroundColor"));
            encodeParams.put("audioSampleRate",encodeParams1.get("audioSampleRate"));
            encodeParams.put("audioBitrate",encodeParams1.get("audioBitrate"));
            encodeParams.put("audioChannels",encodeParams1.get("audioChannels"));
            encodeParams.put("recordMode",encodeParams1.get("recordMode"));
            customInfo.put("EncodeParams",encodeParams);

            JSONArray configArray = jsonObject.getJSONObject("LayoutParams").getJSONArray("PresetLayoutConfig");
           for (int i = 0; i <videoStreams.size() ; i++) {
               JSONObject jsonObject1 = (JSONObject) configArray.get(i);
               JSONObject  jsonObject2= (JSONObject)videoStreams.get(i);
               jsonObject2.put("LocationY",jsonObject1.get("LocationY"));
               jsonObject2.put("LocationX",jsonObject1.get("LocationX"));
               jsonObject2.put("ImageHeight",jsonObject1.get("ImageHeight"));
               jsonObject2.put("ImageWidth",jsonObject1.get("ImageWidth"));
               jsonObject2.put("Layer",jsonObject1.get("Layer"));
               jsonObject2.put("RenderMode",jsonObject1.get("RenderMode"));
            }
            JSONObject LayoutParams = new JSONObject();
            LayoutParams.put("PresetLayoutConfig",videoStreams);
            customInfo.put("LayoutParams",LayoutParams);
            JSONObject result = new JSONObject();
            result.put("code","0");
            result.put("msg","");
            result.put("data",customInfo.toString());
            log.info("custom layout response :{}",result);
            return result.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    @RequestMapping(value = "mixCallback")
    @ResponseBody
    public void mixCallback(@RequestBody String json) {
        log.info("混流结果:{}",json);
    }
}
