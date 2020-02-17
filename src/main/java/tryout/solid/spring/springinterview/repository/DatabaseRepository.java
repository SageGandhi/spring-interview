package tryout.solid.spring.springinterview.repository;

import java.util.Map;

public interface DatabaseRepository {
	Map<String, String> findByCommitCdRollBackCode(String commitCode,String rollbackCode);

	Map<String,String> findAllTransactionRecord(Map<String, String> idRequest) ;

	Map<String,String> findDomainSpecificCertification(Map<String, String> idRequest);

	Map<String,String> findDifferentCertification(Map<String, String> idRequest);

}
