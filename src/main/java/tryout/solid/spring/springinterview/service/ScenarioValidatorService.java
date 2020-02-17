package tryout.solid.spring.springinterview.service;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class ScenarioValidatorService {

	public void validateRequest(Map<String, String> idRequest) {
		//check for mandatory keys
		if(!idRequest.containsKey("TransactionType")) {
			throw new IllegalArgumentException("TransactionType is manadatory");
		}
		if(!idRequest.containsKey("IsIndividual")||!idRequest.containsKey("IsOrganization")) {
			throw new IllegalArgumentException("Certified Must Be Individual Or Organization");
		}
		if(!idRequest.containsKey("CommitCode")) {
			throw new IllegalArgumentException("CommitCode is manadatory");
		}
		//checkfor conditional mandatory based on scenario FindByCommitRollBackCd/CommitCodeFoundInDataBase
		if(!idRequest.containsKey("CommitCodeFoundInDataBase")) {
			throw new IllegalArgumentException("CommitCode Invalid, Not found in database");
		}
	}

}
