package com.hajicor3.dslist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hajicor3.dslist.entities.GameList;

public interface GameListRepository extends JpaRepository<GameList, Long> {

}
