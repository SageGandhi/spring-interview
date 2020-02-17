package tryout.solid.spring.springinterview.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tryout.solid.spring.springinterview.repository.JdbcRepository;

@Service
public class WorkFlowService {

	@Autowired
	private JdbcRepository jdbcRepository;

	public Map<String,String> executeScenario(Map<String, String> idRequest) {
		//@TODO
		return idRequest;
	}

	public Map<String,String> getDetailsFromDatabase(Map<String, String> idRequest) {
		idRequest.putAll(jdbcRepository.findByCommitCdRollBackCode(idRequest.get("CommitCode"),idRequest.get("RollBackCode")));
		idRequest.putAll(jdbcRepository.findAllTransactionRecord(idRequest));
		idRequest.putAll(jdbcRepository.findDomainSpecificCertification(idRequest));
		return idRequest;
	}
}
