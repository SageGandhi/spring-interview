package tryout.solid.spring.springinterview.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcRepository implements DatabaseRepository{
	@Autowired
	private JdbcTemplate jdbcTemplate;

	//commit_rollback Table
	public Map<String, String> findByCommitCdRollBackCode(String commitCode,String rollbackCode) {
		Map<String,String> result = new HashMap<>();
		Map<String,Object> output = jdbcTemplate.queryForMap("select commitCode,rollbackCode from commit_rollback where "
				+ "commitCode=:commitCode and rollbackCode=:rollbackCode",
				commitCode,rollbackCode);
		if(output.containsKey("commitCode")&&output.containsKey("rollbackCode")) {
			output.clear();
			result.put("FindByCommitRollBackCd", "true");
		}else {
			throw new RuntimeException("CommitCode & Rollback Codes are invalid");
		}
		return result;
	}
	//Transaction Table
	public Map<String,String> findAllTransactionRecord(Map<String, String> idRequest) {
		List<Map<String, Object>> output = jdbcTemplate.queryForList("select * from transaction_all");
		idRequest.put("RecordsFound", String.valueOf(output.size()));
		return idRequest;
	}
	//certification table
	public Map<String,String> findDomainSpecificCertification(Map<String, String> idRequest) {
		String certificate = idRequest.get("Certificate");
		String domain = idRequest.get("Domain");

		List<Map<String, Object>> output = jdbcTemplate.queryForList(
				"select percentile_score from certification where transaction_type='N' and certificate=:certificate and"
				+ "domain=:domain",certificate,domain);

		double avg = 0;
		if(output!=null && !output.isEmpty()) {
			for(Map<String,Object> map: output) {
				avg+= Double.valueOf((String)map.get("percentile_score"));
			}
			avg= avg/output.size();
		}

		idRequest.put("AvgCertificationPrecentile", String.valueOf(avg));
		return idRequest;
	}
	@Override
	public Map<String, String> findDifferentCertification(Map<String, String> idRequest) {
		// TODO Auto-generated method stub
		return null;
	}
}
