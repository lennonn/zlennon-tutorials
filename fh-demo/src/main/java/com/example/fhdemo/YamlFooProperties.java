package com.example.fhdemo;

import com.alibaba.fastjson.util.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Configuration
public class YamlFooProperties {

    @Value("${sm2.key.public.path}")
    private String sm2KeyPublic;
    @Value("${sm2.key.private.path}")
    private String sm2KeyPrivate;

    public String getSm2KeyPublic() {
        return sm2KeyPublic;
    }

    public void setSm2KeyPublic(String sm2KeyPublic) {
        this.sm2KeyPublic = sm2KeyPublic;
    }

    public String getSm2KeyPrivate() {
        return sm2KeyPrivate;
    }

    public void setSm2KeyPrivate(String sm2KeyPrivate) {
        this.sm2KeyPrivate = sm2KeyPrivate;
    }

    public  String readFileToString(String path) throws IOException {
        File file = new File(path);
        FileInputStream fis = null;
        try {
            if (!file.exists()) {
                throw new FileNotFoundException("sm2私钥文件不存在:" + path);
            }
            fis = new FileInputStream(file);
            return new BufferedReader(new InputStreamReader(fis)).lines()
                    .parallel().collect(Collectors.joining("\n"));
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
    }
}
