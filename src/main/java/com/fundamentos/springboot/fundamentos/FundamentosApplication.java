package com.fundamentos.springboot.fundamentos;

import com.fundamentos.springboot.fundamentos.bean.MyBean;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentos.springboot.fundamentos.component.ComponentDependency;
import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.pojo.UserPojo;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);

	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;
	@Autowired
	private MyBeanWithProperties myBeanWithProperties;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserPojo userPojo;
	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean myBean, MyBeanWithDependency myBeanWithDependency) {
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		previousExamples();
		saveUsersInDB();
		getInformationJpqlFromUser();
	}

	private void getInformationJpqlFromUser() {
		LOGGER.info("Usuario encontrado: " +
				userRepository.findByUserEmail("catorres@gmail.com")
						.orElseThrow(() -> new RuntimeException("No se encontró el usuario")));

		userRepository.findAndSort("user", Sort.by("id").descending())
				.stream()
				.forEach(user -> LOGGER.info("Ususario con método sort: " + user));
	}

	private void saveUsersInDB() {
		User user1 = new User("Wilmer","wsalquinga@gmail.com", LocalDate.of(2021, 03, 20));
		User user2 = new User("Camila","catorres@gmail.com", LocalDate.of(2021, 05, 12));
		User user3 = new User("Julie","jetorres@gmail.com", LocalDate.of(2021, 03, 30));
		User user4 = new User("user4","user4@gmail.com", LocalDate.of(2021, 02, 15));
		User user5 = new User("user5","user5@gmail.com", LocalDate.of(2021, 12, 5));
		User user6 = new User("user6","user6@gmail.com", LocalDate.of(2021, 04, 19));
		User user7 = new User("user7","user7@gmail.com", LocalDate.of(2021, 1, 7));
		User user8 = new User("user8","user8@gmail.com", LocalDate.of(2021, 06, 15));
		User user9 = new User("user9","user9@gmail.com", LocalDate.of(2021, 07, 18));
		User user10 = new User("user10","user10@gmail.com", LocalDate.of(2021, 11, 6));
		List<User> list = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10);
		list.stream().forEach(userRepository::save);
		//userRepository.saveAll(list);
	}

	private void previousExamples() {
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependencySum();
		myBeanWithDependency.printWithDependencySubs();
		myBeanWithDependency.printWithDependencyMulti();
		myBeanWithDependency.printWithDependencyDiv();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getEmail() + " - " + userPojo.getPassword() + " - " + userPojo.getAge());
		try{
			//error
			int value = 10/0;
			LOGGER.info("Mi valor: " + value);
		} catch (Exception e) {
			LOGGER.error("Esto es un error al dividir por 0 " + e.getMessage());
		}
	}
}
