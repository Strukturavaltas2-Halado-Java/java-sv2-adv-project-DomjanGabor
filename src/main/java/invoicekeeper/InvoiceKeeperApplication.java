package invoicekeeper;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InvoiceKeeperApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvoiceKeeperApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public OpenAPI defineOpenApi() {
		return new OpenAPI()
				.info(new Info().version("1.0").title("Invoice Keeper API")
						.description("Company and invoice register API"));
	}
}
