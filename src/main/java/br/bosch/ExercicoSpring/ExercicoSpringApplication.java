package br.bosch.ExercicoSpring;

import br.bosch.ExercicoSpring.services.ConsultarApi;
import br.bosch.ExercicoSpring.utils.Tela;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExercicoSpringApplication implements CommandLineRunner {

	public static void main(String[] args) {
//		SpringApplication.run(ExercicoSpringApplication.class, args);
		Tela tela = new Tela("teste");
		tela.setVisible(true);
	}


	@Override
	public void run(String... args) throws Exception {
		ConsultarApi api = new ConsultarApi();
		Principal principal = new Principal();

		principal.exibirMenu();


	}
}
