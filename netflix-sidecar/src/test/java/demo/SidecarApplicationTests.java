package demo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.netflix.appinfo.EurekaInstanceConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SidecarApplication.class)
@WebAppConfiguration
@DirtiesContext
public class SidecarApplicationTests {

	@Autowired
	EurekaInstanceConfig instanceConfig;

	@Test
	public void contextLoads() {
	}

	@Test
	public void instanceConfigHasCorrectPort() {
		assertEquals("eureka instance config has wrong port", 8000, instanceConfig.getNonSecurePort());
	}

}
