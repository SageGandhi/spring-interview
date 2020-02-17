package tryout.solid.spring.springinterview.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.Test;

public class JdbcRepositoryTest {

	private JdbcRepository jdbcRepository;

	@Test
	public void databaseShouldContainCodeForCommitRollback() {
		//Arrange Data Manipulation
		//Act
		Map<String,String> output = jdbcRepository.findByCommitCdRollBackCode("100", "Xyz");
		//Assert
		assertThat(output.containsKey("FindByCommitRollBackCd"));
		assertThat(output.get("FindByCommitRollBackCd")).isEqualTo(true);
		//Verify Interaction
	}

	@Test
	public void databaseShouldNotContainCodeForCommitRollback() {
		//Arrange Data Manipulation
		//Act
		Map<String,String> output = jdbcRepository.findByCommitCdRollBackCode("100", "Xyz");
		//Assert RuntimeException & Message @CommitCode & Rollback Codes are invalid@
		//Verify Interaction
	}
}
