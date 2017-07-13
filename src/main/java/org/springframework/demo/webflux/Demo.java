package org.springframework.demo.webflux;

import reactor.core.publisher.Mono;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

public class Demo {


	public static void main(String[] args) {

		PersonRepository repository = new DummyPersonRepository();
		PersonHandler personHandler = new PersonHandler(repository);

		RouterFunction<ServerResponse> allRoutes =
				nest(path("/person"),
						route(method(GET), personHandler::allPeople)
								.and(route(method(POST).and(contentType(APPLICATION_JSON)),
										personHandler::savePerson))
								.and(route(GET("/{id}"), personHandler::getPerson)));


	}
}
