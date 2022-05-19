package tokyo.boblennon.bcnjob22;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.extern.slf4j.Slf4j;
import tokyo.boblennon.bcnjob22.application.RoleApplicationImp;
import tokyo.boblennon.bcnjob22.application.UserApplicationImp;
import tokyo.boblennon.bcnjob22.core.execptions.BadRequestException;
import tokyo.boblennon.bcnjob22.core.mappers.dtos.PostUserDto;
import tokyo.boblennon.bcnjob22.domain.Role;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "JOBarcelona '22", version = "1.0", description = "REST API for User management"))
@SecurityScheme(name = "bcn22", scheme = "Bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
@Slf4j
public class BcnJob22Application {

	public static void main(String[] args) {
		SpringApplication.run(BcnJob22Application.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder (){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserApplicationImp userApplicationImp, RoleApplicationImp roleApplicationImp) {
		return args -> {
			try {
				// We add testing Roles and 'admin' User to the database
				roleApplicationImp.addRole((new Role("ROLE_USER")));
				roleApplicationImp.addRole((new Role("ROLE_ADMIN")));

				userApplicationImp.addUser(new PostUserDto("jobx_admin", "admin@jobx.com", "aJOBX90_min25k.", new ArrayList<>()));

				userApplicationImp.addRoleToUser("jobx_admin", "ROLE_ADMIN");
			} catch (BadRequestException e) {		// Catches a "BadRequestException" which is caused when the Roles or User added before are duplicated in the database
				log.error("{} - {}", e.getMessage(), e.getExceptions().toString());
			}
		};
	}
}
