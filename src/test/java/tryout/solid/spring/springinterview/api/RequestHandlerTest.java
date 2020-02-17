package tryout.solid.spring.springinterview.api;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import tryout.solid.spring.springinterview.service.ScenarioValidatorService;
import tryout.solid.spring.springinterview.service.WorkFlowService;

public class RequestHandlerTest {
	public static final String BASE_URI = "/secured/commercial/handler";
	public static final String GET_URI = "/default";
	public static final String POST_URI = "/workflow";

	private MockMvc mockMvc;

	private ScenarioValidatorService scenarioValidatorService;

	private WorkFlowService workFlowService;

	public void initialize() {
		// Do Setup if requires
	}

	@Test
	public void testPost() throws Exception {
		final String requestBody = "{\"FindByCommitRollBackCd\":\"true\",\"IsIndividual\":\"true\",\"TransactionType\":\"New\",\"RollBackCd\":\"00000\",\"CloudVendor\":\"AWS\",\"IsOrganization\":\"false\",\"CommitCodeFoundInDataBase\":\"true\",\"Domain\":\"Hybrid\",\"Certificate\":\"A+ Professional\",\"CommitCode\":\"GD7\"}";
		this.mockMvc.perform(MockMvcRequestBuilders.post(BASE_URI + POST_URI).content(requestBody)
				.contentType("application/json").accept("application/json"));
	}

	@Test
	public void testPostFailure() throws Exception {
		final String requestBody = "{\"FindByCommitRollBackCd\":\"true\",\"IsIndividual\":\"true\",\"TransactionType\":\"New\",\"RollBackCd\":\"00000\",\"CloudVendor\":\"AWS\",\"IsOrganization\":\"false\",\"CommitCodeFoundInDataBase\":\"true\",\"Domain\":\"Hybrid\",\"Certificate\":\"A+ Professional\",\"CommitCode\":\"GD0\"}";
		this.mockMvc.perform(MockMvcRequestBuilders.post(BASE_URI + POST_URI).content(requestBody)
				.contentType("application/json").accept("application/json"));
	}

	@Test
	public void testGet() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get(BASE_URI + GET_URI).accept("application/json"));
	}

	@Test
	public void testGetFailure() {
		// "CommitCode & Rollback Codes are invalid"
	}
}
