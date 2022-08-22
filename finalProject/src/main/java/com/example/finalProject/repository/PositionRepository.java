package com.example.finalProject.repository;

import com.example.finalProject.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {

//    @Query(value = "SELECT * FROM POSITION WHERE PORTFOLIO_ID = ?1",
//            nativeQuery = true)
//    List<Position> findByPortfolioId(Long id);

    Optional<List<Position>> findByPortfolioId(Long id);



}
