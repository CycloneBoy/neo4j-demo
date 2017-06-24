package com.cycloneboy;

import com.cycloneboy.domain.Actor;
import com.cycloneboy.domain.Movie;
import com.cycloneboy.domain.Role;
import com.cycloneboy.repositories.ActorRepository;
import com.cycloneboy.repositories.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.util.Assert;

@SpringBootApplication
@EntityScan(basePackages = "com.cycloneboy.domain")
@EnableNeo4jRepositories
public class Neo4jDemoApplication {
	private static Logger logger= LoggerFactory.getLogger(Neo4jDemoApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(Neo4jDemoApplication.class, args);
	}

	@Bean
	CommandLineRunner demo(MovieRepository movieRepository,ActorRepository actorRepository){
		return args ->{
			movieRepository.deleteAll();
			actorRepository.deleteAll();

			Movie matrix1 = new Movie();
			matrix1.setTitle("The Matrix");
			matrix1.setYear("1999-03-31");

			Movie matrix2 = new Movie();
			matrix2.setTitle("The Matrix Reloaded");
			matrix2.setYear("2003-05-07");

			Movie matrix3 = new Movie();
			matrix3.setTitle("The Matrix Revilutions");
			matrix3.setYear("2003-10-27");

			Actor keanu = new Actor();
			keanu.setName("Keanu Reeves");

			Actor laurence = new Actor();
			laurence.setName("Laurence Fishburne");

			Actor carrieanne = new Actor();
			carrieanne.setName("Carrie-Anne Moss");

			matrix1.addRole(keanu,"Neo");
			matrix1.addRole(laurence,"Morpheus");
			matrix1.addRole(carrieanne,"Trinity");
			movieRepository.save(matrix1);

			matrix2.addRole(keanu,"Neo");
			matrix2.addRole(laurence,"Morpheus");
			matrix2.addRole(carrieanne,"Trinity");
			movieRepository.save(matrix2);

			matrix3.addRole(keanu,"Neo");
			matrix3.addRole(laurence,"Morpheus");
			matrix3.addRole(carrieanne,"Trinity");
			movieRepository.save(matrix3);

			logger.info("Before linking up with Neo4j...");

			Movie movie = movieRepository.findByTitle("The Matrix");

			logger.info("===movie=== movie:{},{}",movie.getTitle(),movie.getYear());
			for (Role role : movie.getRoles()) {
				logger.info("====== actor:{},role:{}", role.getActor().getName(),role.getRole());
			}
		};
	}
}
