package com.zlennon.chatgpt;

import lombok.Data;

@Data
public class ReqResItemsEntity {
    private Integer chatId;
    private String reqInfo;
    private String resInfo;
    private String api;
    private Integer type;
}
