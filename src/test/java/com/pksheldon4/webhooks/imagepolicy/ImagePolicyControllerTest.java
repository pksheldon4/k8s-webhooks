package com.pksheldon4.webhooks.imagepolicy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pksheldon4.webhooks.imagepolicy.model.ImageReview;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
class ImagePolicyControllerTest {


    private final ObjectMapper mapper = new ObjectMapper();
    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        ImagePolicyController controller = new ImagePolicyController(mapper);
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void verifyTestJson(@Value("classpath:ImageReview-request.json") Resource imageReviewRequest) throws Exception {
        ImageReview ImageReview = mapper.readValue(imageReviewRequest.getInputStream().readAllBytes(), ImageReview.class);
        System.out.println(ImageReview.toString());
    }

    @Test
    public void validateReturnsSuccessfully(@Value("classpath:ImageReview-request.json") Resource ImageReviewRequest) throws Exception {

        ImageReview ImageReview = mapper.readValue(ImageReviewRequest.getInputStream().readAllBytes(), ImageReview.class);

        mvc.perform(MockMvcRequestBuilders.post("/check-image")
            .content(ImageReviewRequest.getInputStream().readAllBytes())
            .accept(MediaType.APPLICATION_JSON) //Response Type
            .contentType(MediaType.APPLICATION_JSON)) //Request Type
            .andExpect(status().isOk())
            .andDo(print()) //prints out request/response information. Useful in debugging.
            .andExpect(jsonPath("$.apiVersion", is(ImageReview.getApiVersion())))
            .andExpect(jsonPath("$.kind", is(ImageReview.getKind())))
            .andExpect(jsonPath("$.status.allowed", is(true)));

    }

}