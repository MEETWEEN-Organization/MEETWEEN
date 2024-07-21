package meetween.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class MeetweenApplicationTests {
	@Autowired
	private DataSource dataSource;

	@Test
	void contextLoads() {
		try (Connection connection = dataSource.getConnection()) {
			System.out.println("Database URL: " + connection.getMetaData().getURL());
			System.out.println("Database User: " + connection.getMetaData().getUserName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
//
//	@Test
//	void contextLoads() {
//
//	}
}
