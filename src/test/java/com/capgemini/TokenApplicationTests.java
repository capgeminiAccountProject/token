package com.capgemini;

import com.capgemini.controllers.TokenController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(TokenController.class)
public class TokenApplicationTests {

    @Test
    public void contextLoads() {
    }

}
