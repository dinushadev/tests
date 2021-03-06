package demo;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@SpringBootApplication
@EnableFeignClients
public class FeignClientApplication {

	@Bean
	public ApplicationRunner runner(final RestClient client) {
		return new ApplicationRunner() {

			@Override
			public void run(ApplicationArguments args) throws Exception {
				System.err.println(client.hello());
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(FeignClientApplication.class, args);
	}
}

@FeignClient(name = "example", url = "example.com", fallback=FallbackClient.class)
interface RestClient {
	@RequestMapping(value="/", method = RequestMethod.GET)
	String hello();
}

@Component
class FallbackClient implements RestClient {
	@Override
	public String hello() {
		return "oops";
	}
}
