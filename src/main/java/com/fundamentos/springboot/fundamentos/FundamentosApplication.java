package com.fundamentos.springboot.fundamentos;

import com.fundamentos.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentos.springboot.fundamentos.bean.Mybean;
import com.fundamentos.springboot.fundamentos.component.ComponetDepency;
import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.pojo.UserPojo;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import com.fundamentos.springboot.fundamentos.service.UseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

	private ComponetDepency componetDepency;
	private Mybean myBean;
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;
	private UserRepository userRepository;
	private UseService userService;

	public FundamentosApplication(@Qualifier("componenTwoImplement") ComponetDepency componetDepency,
								  Mybean myBean,MyBeanWithDependency myBeanWithDependency,
								  MyBeanWithProperties myBeanWithProperties, UserPojo userPojo,
								  UserRepository userRepository, UseService userService){
		this.componetDepency = componetDepency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
		this.userService = userService;
	}

	public static void main(String[] args) {

		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//ejemplosAmteriores();
		saveUsersInDataBase();
		getInformationJpqlFromUser();
		saveWhithErrorTransactional();
	}

	private void saveWhithErrorTransactional(){
		User test1 = new User("TestTransactional1", "TestTransactional1@domain.com", LocalDate.now());
		User test2 = new User("TestTransactional2", "TestTransactional2@domain.com", LocalDate.now());
		User test3 = new User("TestTransactional3", "TestTransactional3@domain.com", LocalDate.now());
		User test4 = new User("TestTransactional4", "TestTransactional4@domain.com", LocalDate.now());

		List<User> users = Arrays.asList(test1,test2,test3,test4);
		userService.saveTransactional(users);

		userService.getAllUsers().stream()
				.forEach(user -> LOGGER.info("Este es el usario dentro del metodo transacional :" + user));
	}

	private void getInformationJpqlFromUser(){
		LOGGER.info("usuario con el metodo findByUserEmail"+userRepository.findByUserEmail
				("jsotto@email.com").orElseThrow(()-> new RuntimeException
				("No se encontro el usuario")));

		userRepository.findAndSort("user", Sort.by("id"))
				.stream()
				.forEach(user -> LOGGER.info("usuario con metodo sort"+ user));

		userRepository.findByName("Jonathan")
				.stream()
				.forEach(user -> LOGGER.info("Usuario con el query method"+ user));

		LOGGER.info("Usuario con metodo findEmailAndMail"+userRepository.findByEmailAndName
				("jsotto@email.com","Jonathan").orElseThrow(()-> new RuntimeException
				("No se encontro el usuario con el metodo findEmailAndMail")));

		userRepository.findByNameLike("%u%")
				.stream()
				.forEach(user -> LOGGER.info("User find by name like" + user));

		userRepository.findByNameOrEmail("null","user5@email.com")
				.stream()
				.forEach(user -> LOGGER.info("User find by name or email"+user));

		userRepository.findByBirthDateBetween(LocalDate.of(2020,3,2),
				LocalDate.of(2021,5,30))
				.stream()
				.forEach(user -> LOGGER.info("Usuarios con QueryMethod findByBirthDateBetween"+user));

		userRepository.findByNameLikeOrderByIdDesc("%user%")
				.stream()
				.forEach(user -> LOGGER.info("Usuario encontrado con nombre y ordenado"+ user));

		userRepository.findByNameContainingOrderByIdDesc("user")
				.stream()
				.forEach(user -> LOGGER.info("findByNameContainOrderByIdDesc"+ user));

		/*LOGGER.info("El usuario del named parameter es " +
				userRepository.getAllByBirthDateAndEmail(
				LocalDate.of(2022,1,31),"jsotto@email.com")
				.orElseThrow(()->new RuntimeException("No se encontro el usuario con named parametro")));*/



	}

	private void saveUsersInDataBase(){
		User user1 = new User("Jonathan","jsotto@email.com", LocalDate.of(2022,1,31));
		User user2 = new User("Saydy","sguzman@email.com", LocalDate.of(2022,11,25));
		User user3 = new User("user3","user3@email.com", LocalDate.of(2022,03,3));
		User user4 = new User("user4","user4@email.com", LocalDate.of(2020,04,5));
		User user5 = new User("user5","user5@email.com", LocalDate.of(2022,05,20));
		User user6 = new User("user6","user6@email.com", LocalDate.of(2022,06,26));
		User user7 = new User("user7","user7@email.com", LocalDate.of(2022,07,29));
		User user8 = new User("user8","user8@email.com", LocalDate.of(2022,8,1));
		User user9 = new User("user9","user9@email.com", LocalDate.of(2022,9,7));
		User user10 = new User("user10","user10@email.com", LocalDate.of(2022,12,10));
		List<User> list = Arrays.asList(user1,user2,user3,user4,user4,user5,user6,user7,user8,user9,user10);
		list.stream().forEach(userRepository::save);

	}


	private void ejemplosAmteriores(){
		componetDepency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getEmail()+ "-" + userPojo.getPassword()+ "-"+ userPojo.getAge());
		try{
			int value = 10/0;
			LOGGER.info("Mi valor :"+ value);
		} catch (Exception e){
			LOGGER.error("Esto es un error al dividir por 0" +e.getMessage());
		}
	}
}
