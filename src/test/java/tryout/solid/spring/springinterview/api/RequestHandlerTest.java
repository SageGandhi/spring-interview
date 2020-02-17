package tryout.solid.spring.springinterview.api;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.when;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import tryout.solid.spring.springinterview.service.ScenarioValidatorService;
import tryout.solid.spring.springinterview.service.WorkFlowService;

@RunWith(SpringRunner.class)
@WebMvcTest(RequestHandler.class)
@AutoConfigureWebMvc
@WebAppConfiguration
public class RequestHandlerTest {
	public static final String BASE_URI = "/secured/commercial/handler";
	public static final String GET_URI = "/default";
	public static final String POST_URI = "/workflow";

	// Setup, Mocked/Stubbed
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ScenarioValidatorService scenarioValidatorService;
	@MockBean
	private WorkFlowService workFlowService;

	@Rule
	public ExpectedException exception =ExpectedException.none();

	@Before
	public void initialize() {
		// Do Setup if requires
	}

	@Test
	public void testPost() throws Exception {
		final String requestBody = "{\"FindByCommitRollBackCd\":\"true\",\"IsIndividual\":\"true\",\"TransactionType\":\"New\",\"RollBackCd\":\"00000\",\"CloudVendor\":\"AWS\",\"IsOrganization\":\"false\",\"CommitCodeFoundInDataBase\":\"true\",\"Domain\":\"Hybrid\",\"Certificate\":\"A+ Professional\",\"CommitCode\":\"GD7\"}";
		this.mockMvc.perform(MockMvcRequestBuilders.post(BASE_URI + POST_URI).content(requestBody)
				.contentType("application/json").accept("application/json"))
		.andExpect(MockMvcResultMatchers.status().isOk());
		//Assert & Verify
	}

	@Test
	public void testPostFailure() throws Exception {
		final String requestBody = "{\"FindByCommitRollBackCd\":\"true\",\"IsIndividual\":\"true\",\"TransactionType\":\"New\",\"RollBackCd\":\"00000\",\"CloudVendor\":\"AWS\",\"IsOrganization\":\"false\",\"CommitCodeFoundInDataBase\":\"true\",\"Domain\":\"Hybrid\",\"Certificate\":\"A+ Professional\",\"CommitCode\":\"GD0\"}";
		exception.expectMessage("WorkFlow Not Defined Properly");
		exception.expectCause(any(IllegalArgumentException.class));
		// Assert WorkFlow Not Defined Properly
		this.mockMvc.perform(MockMvcRequestBuilders.post(BASE_URI + POST_URI).content(requestBody)
				.contentType("application/json").accept("application/json"))
		.andExpect(MockMvcResultMatchers.status().is5xxServerError());
	}

	@Captor
	private ArgumentCaptor<HashMap<String,String>> captor;

	@Test
	public void testGet() throws Exception {
		when(workFlowService.getDetailsFromDatabase(captor.capture())).thenReturn(new HashMap<>());
		this.mockMvc.perform(MockMvcRequestBuilders.get(BASE_URI + GET_URI).accept("application/json"))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testGetFailure() {
		// "CommitCode & Rollback Codes are invalid"
	}
}
