package vttp2022.paf.assessment.server;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertNotNull;

// TODO: fill in this class according to the assessment tasks
@AutoConfigureMockMvc
@SpringBootTest
class ServerApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	void userExist() {
		RequestBuilder req = MockMvcRequestBuilders.post("/task")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				.param("username","alex")
				.param("description-0","buy grocery")
				.param("priority-0","1")
				.param("dueDate-0","2022-04-30");

		MvcResult result;
		try {
			result = mvc.perform(req).andReturn();
			int status = result.getResponse().getStatus();

			String payload = result.getResponse().getContentAsString();
			Assertions.assertEquals(200, status);
			assertNotNull(payload);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void userDoesntExist() {
		RequestBuilder req = MockMvcRequestBuilders.post("/task")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				.param("username","ben")
				.param("description-0","buy grocery")
				.param("priority-0","1")
				.param("dueDate-0","2022-04-30");

		MvcResult result;
		try {
			result = mvc.perform(req).andReturn();
			int status = result.getResponse().getStatus();

			String payload = result.getResponse().getContentAsString();
			Assertions.assertEquals(200, status);
			assertNotNull(payload);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
