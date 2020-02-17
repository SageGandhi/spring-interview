package tryout.solid.spring.springinterview.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tryout.solid.spring.springinterview.service.ScenarioValidatorService;
import tryout.solid.spring.springinterview.service.WorkFlowService;

@RestController
@RequestMapping("/secured/commercial/handler")
public class RequestHandler {
	@Autowired
	private ScenarioValidatorService scenarioValidatorService;
	@Autowired
	private WorkFlowService workFlowService;

	@PostMapping(value = "/workflow", produces = { "application/xml", "application/json" }, consumes = {
			"application/xml", "application/json" })
	public ResponseEntity<Map<String, String>> postToWorkFlow(@RequestBody Map<String, String> idRequest) {
		scenarioValidatorService.validateRequest(idRequest);

		if ("New".equals(idRequest.get("TransactionType"))
				&& Boolean.valueOf(idRequest.get("IsOrganization"))
				&& Boolean.valueOf(idRequest.get("CommitCodeFoundInDataBase"))
				&& ((idRequest.get("CommitCode").contentEquals("GD7")) || (idRequest.get("CommitCode").contentEquals("GD6")))
				&& Boolean.valueOf(idRequest.get("FindByCommitRollBackCd"))) {

			workFlowService.executeScenario(idRequest);

		} else if (((idRequest.get("CommitCode").contentEquals("GD7")) || (idRequest.get("CommitCode").contentEquals("GD6"))
				|| (idRequest.get("CommitCode").contentEquals("GD2"))) && "New".equals(idRequest.get("TransactionType"))
				&& Boolean.valueOf(idRequest.get("IsIndividual"))
				&& Boolean.valueOf(idRequest.get("CommitCodeFoundInDataBase"))) {

			workFlowService.executeScenario(idRequest);

		} else if (((idRequest.get("CommitCode").contentEquals("GD7")) || (idRequest.get("CommitCode").contentEquals("GD6")))
				&& "New".equals(idRequest.get("TransactionType"))
				&& Boolean.valueOf(idRequest.get("IsIndividual"))
				&& Boolean.valueOf(idRequest.get("CommitCodeFoundInDataBase"))
				&& Boolean.valueOf(idRequest.get("FindByCommitRollBackCd"))) {

			workFlowService.executeScenario(idRequest);

		}else if("New".equals(idRequest.get("TransactionType")) && Boolean.valueOf(idRequest.get("IsOrganization"))
				&& Boolean.valueOf(idRequest.get("CommitCodeFoundInDataBase"))){

			workFlowService.executeScenario(idRequest);

		}else {
			throw new IllegalArgumentException("WorkFlow Not Defined Properly");
		}
		//Total 35 Scenarios, Sample Scenario
		//((idRequest.get("CommitCode") == "GD1") || (idRequest.get("CommitCode") == "GD2"))
		//&& "New".equals(idRequest.get("TransactionType"))
		//&& Boolean.valueOf(idRequest.get("IsOrganization"))
		//&& Boolean.valueOf(idRequest.get("CommitCodeFoundInDataBase"))
		//&& Boolean.valueOf(idRequest.get("FindByCommitRollBackCd"))
		return ResponseEntity.ok(idRequest);
	}
	@GetMapping(value = "/default", produces = { "application/xml", "application/json" })
	public ResponseEntity<Map<String, String>> getDefaultOrPassToWorkFlow() {
		final Map<String, String> idRequest = new HashMap<>();

		idRequest.putIfAbsent("Certificate", "A+ Professional");
		idRequest.putIfAbsent("CloudVendor", "AWS");
		idRequest.putIfAbsent("Domain", "Hybrid");
		idRequest.putIfAbsent("TransactionType", "New");
		idRequest.putIfAbsent("IsIndividual", "true");
		idRequest.putIfAbsent("IsOrganization", "false");
		idRequest.putIfAbsent("CommitCode", "GD0");
		idRequest.putIfAbsent("CommitCodeFoundInDataBase", "true");
		idRequest.putIfAbsent("RollBackCd", "00000");

		workFlowService.getDetailsFromDatabase(idRequest);

		return ResponseEntity.ok(idRequest);
	}
}
