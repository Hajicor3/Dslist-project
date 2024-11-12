package com.hajicor3.dslist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hajicor3.dslist.entities.Game;

public interface GameRepository extends JpaRepository<Game, Long> {

}
