package com.example.finalProject;

import com.example.finalProject.model.Client;
import com.example.finalProject.model.Portfolio;
import com.example.finalProject.model.Position;
import com.example.finalProject.repository.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FinalProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinalProjectApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunnerBean(ClientRepository clientRepository) {
        return args -> {
            Client client = new Client();
            client.setFirstName("Jonas");
            client.setLastName("Jonaitis");
            client.setEmail("Jonas@gmail.com");

            Portfolio portfolio1 = new Portfolio();
            portfolio1.setName("Portfolio1");
            portfolio1.setClient(client);

            Position position1 = new Position();
            position1.setTicker("amzn");
            position1.setPurchasePrice(130.54);
            position1.setPurchaseDate(LocalDate.of(2021, 10,12));
            position1.setShares(10);
            position1.setPortfolio(portfolio1);



            Position position2 = new Position();
            position2.setTicker("tsla");
            position2.setPurchasePrice(130.54);
            position2.setPurchaseDate(LocalDate.of(2021, 10,12));
            position2.setShares(10);
            position2.setPortfolio(portfolio1);

            portfolio1.setPositions(Arrays.asList(position1, position2));

            Portfolio portfolio2 = new Portfolio();
            portfolio2.setName("Portfolio2");
            portfolio2.setClient(client);

            Position position3 = new Position();

            position3.setTicker("tsla");
            position3.setPurchasePrice(130.54);
            position3.setPurchaseDate(LocalDate.of(2021, 10,12));
            position3.setShares(10);
            position3.setPortfolio(portfolio2);




            Position position4 = new Position();
            position4.setTicker("tsla");
            position4.setPurchasePrice(130.54);
            position4.setPurchaseDate(LocalDate.of(2021, 10,12));
            position4.setShares(10);
            position4.setPortfolio(portfolio2);

            portfolio2.setPositions(Arrays.asList(position3, position4));

            List<Portfolio> portfolios = new ArrayList<>();
            portfolios.add(portfolio1);
            portfolios.add(portfolio2);


            client.setPortfolio(portfolios);


            clientRepository.save(client);

        };


    }
}
