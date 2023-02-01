package com.example.fhdemo.common;

import org.junit.jupiter.api.Test;

public class HttpURLDownloadTest {

    @Test
    public void testDownload() {
        String url = "http://10.10.80.114:6061/record/media/20220816FXU4a35ZwBccZbKp_1660619468874.mp4";
        String outPath="E:\\vedio\\test2.mp4";
        int i = HttpURLDownload.download(url, outPath);
        System.out.println("file length: " + i);
    }
}
