package com.pksheldon4.webhooks.validatingadmission;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pksheldon4.webhooks.validatingadmission.model.AdmissionReview;
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
class ValidatingAdmissionControllerTest {

    private final ObjectMapper mapper = new ObjectMapper();
    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        ValidatingAdmissionController controller = new ValidatingAdmissionController(mapper);
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void verifyTestJson(@Value("classpath:AdmissionReview-request.json") Resource admissionReviewRequest) throws Exception {
        AdmissionReview admissionReview = mapper.readValue(admissionReviewRequest.getInputStream().readAllBytes(), AdmissionReview.class);
        System.out.println(admissionReview.toString());
    }

    @Test
    public void validateReturnsSuccessfully(@Value("classpath:AdmissionReview-request.json") Resource admissionReviewRequest) throws Exception {

        AdmissionReview admissionReview = mapper.readValue(admissionReviewRequest.getInputStream().readAllBytes(), AdmissionReview.class);

        mvc.perform(MockMvcRequestBuilders.post("/validate")
            .content(admissionReviewRequest.getInputStream().readAllBytes())
            .accept(MediaType.APPLICATION_JSON) //Response Type
            .contentType(MediaType.APPLICATION_JSON)) //Request Type
            .andExpect(status().isOk())
            .andDo(print()) //prints out request/response information. Useful in debugging.
            .andExpect(jsonPath("$.apiVersion", is(admissionReview.getApiVersion())))
            .andExpect(jsonPath("$.kind", is(admissionReview.getKind())));

    }
}