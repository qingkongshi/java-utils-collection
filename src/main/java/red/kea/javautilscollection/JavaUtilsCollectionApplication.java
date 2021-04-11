package red.kea.javautilscollection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class JavaUtilsCollectionApplication {
	public static void main(String[] args) {
		SpringApplication.run(JavaUtilsCollectionApplication.class, args);
	}
}
