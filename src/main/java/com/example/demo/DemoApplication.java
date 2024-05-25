package com.example.demo;

import com.example.demo.config.DbConfig;
import com.example.demo.config.H2Config;
import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
		DbConfig dbConfig = (DbConfig) context.getBean("h2config");

//		//JDBC
//		//App -> JDBC -> Driver -> H2 Data Base
//      // 1 создаем таблицу
//		String sql = "CREATE TABLE IF NOT EXISTS person (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR, age INT)";
//		try(
//				java.sql.Connection connection = java.sql.DriverManager.getConnection("jdbc:h2:file:./DB", "sa", "sa");
//				java.sql.Statement statement = connection.createStatement())
//
//		{
//			System.out.println(statement.executeUpdate(sql));
//		} catch(
//				java.sql.SQLException e)
//
//		{
//			throw new RuntimeException(e);
//		}

		// 2 - заполняем таблицу

		PersonRepository repository = new PersonRepository(dbConfig);
//		PersonRepository repository = new PersonRepository(new H2Config());
//		repository.save(new Person("Иванов Иван Иванович", 33 ));
//		repository.save(new Person("Сидоров Сидор Сидорович", 23 ));
//		repository.save(new Person("Петров Петр Петрович", 12 ));
		repository.findAll().forEach(System.out::println);
		System.out.println();
		System.out.println(repository.findById(2));

	}
}
