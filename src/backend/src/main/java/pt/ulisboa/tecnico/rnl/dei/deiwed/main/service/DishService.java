package pt.ulisboa.tecnico.rnl.dei.deiwed.main.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.ulisboa.tecnico.rnl.dei.deiwed.exceptions.DeiwedException;
import pt.ulisboa.tecnico.rnl.dei.deiwed.exceptions.ErrorMessage;
import pt.ulisboa.tecnico.rnl.dei.deiwed.main.dto.DishDto;
import pt.ulisboa.tecnico.rnl.dei.deiwed.main.entity.Dish;
import pt.ulisboa.tecnico.rnl.dei.deiwed.main.repository.DishRepository;

@Service
@Transactional
public class DishService {
	@Autowired
	private DishRepository DishRepository;

	private Dish fetchDishOrThrow(long id) {
		return DishRepository.findById(id)
				.orElseThrow(() -> new DeiwedException(ErrorMessage.NO_SUCH_DISH, Long.toString(id)));
	}

	public List<DishDto> getAllDishes() {
		return DishRepository.findAll().stream()
				.map(DishDto::new)
				.collect(Collectors.toList());
	}

	public DishDto getDish(long id) {
		return new DishDto(fetchDishOrThrow(id));
	}

}
