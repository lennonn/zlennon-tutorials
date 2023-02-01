package com.example.fhdemo.trtc.tencentyun;


import com.example.fhdemo.config.RestConfig;
import com.example.fhdemo.trtc.QueryMsgController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = QueryMsgController.class)
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {RestConfig.class})
@Import(QueryMsgController.class)
public class QueryMsgControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void testHomePage() throws Exception {
        mockMvc.perform(get("/api/im/getRoamMsg"))
                .andExpect(status().isOk())
                .andReturn();
    }

}
