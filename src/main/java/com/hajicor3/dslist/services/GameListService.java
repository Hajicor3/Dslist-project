package com.hajicor3.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hajicor3.dslist.dto.GameListDTO;
import com.hajicor3.dslist.entities.GameList;
import com.hajicor3.dslist.projections.GameMinProjection;
import com.hajicor3.dslist.repositories.GameListRepository;
import com.hajicor3.dslist.repositories.GameRepository;

@Service
public class GameListService {
	
	@Autowired
	private GameListRepository gameListRepository;
	
	@Autowired
	private GameRepository gameRepository;
	
	@Transactional(readOnly = true)
	public List<GameListDTO> findAll(){
		List<GameList> result = gameListRepository.findAll();
		return result.stream().map(x -> new GameListDTO(x)).toList();
	}
	
	@Transactional
	public void move(Long listId, int sourceIndex, int destinationIndex) {
		
		List<GameMinProjection> list = gameRepository.searchByList(listId);
		GameMinProjection remove = list.remove(sourceIndex);
		
		list.add(destinationIndex, remove);
		
		Integer min = sourceIndex < destinationIndex ? sourceIndex : destinationIndex;
		Integer max = sourceIndex < destinationIndex ? destinationIndex : sourceIndex;
		
		for (int i = min; i <= max; i++) {
			
			gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
		}
	}
}
