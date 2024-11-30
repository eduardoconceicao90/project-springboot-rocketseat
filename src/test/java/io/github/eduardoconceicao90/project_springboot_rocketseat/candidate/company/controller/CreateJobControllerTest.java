package io.github.eduardoconceicao90.project_springboot_rocketseat.candidate.company.controller;

import io.github.eduardoconceicao90.project_springboot_rocketseat.candidate.company.util.TestUtil;
import io.github.eduardoconceicao90.project_springboot_rocketseat.model.dto.JobDTO;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreateJobControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setUp() {
       MockMvcBuilders.webAppContextSetup(context)
               //.apply(SecurityMockMvcConfigurers.springSecurity())
               .build();
    }

    @Test
    @DisplayName("Should be able to create a new job")
    public void shouldBeAbleToCreateANewJob() throws Exception {
        var jobDTO = JobDTO.builder()
                                .description("Desenvolvedor Java com experiência em Spring Boot")
                                .benefits("Vale transporte, vale refeição")
                                .level("PLENO_TEST")
                                .build();

        JSONObject json = new JSONObject(jobDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/company/job")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json.toString())
                .header("Authorization", TestUtil.generateToken(UUID.randomUUID(),"JAVAGAS_@123#"))
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
