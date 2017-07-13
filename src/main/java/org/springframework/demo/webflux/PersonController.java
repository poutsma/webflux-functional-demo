package org.springframework.demo.webflux;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {

	private final PersonRepository repository;

	public PersonController(PersonRepository repository) {
		this.repository = repository;
	}

	@GetMapping
	public Flux<Person> allPeople() {
		return this.repository.allPeople();
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Void> savePerson(@RequestBody Mono<Person> personMono) {
		return this.repository.savePerson(personMono);
	}

	@GetMapping("/{id}")
	public Mono<Person> getPerson(@PathVariable int id) {
		return this.repository.getPerson(id);
	}

}
