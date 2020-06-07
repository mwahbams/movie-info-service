package com.mwahba.movieinfo.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.mwahba.movieinfo.model.Movie;

@RestController
@RequestMapping("/movies")
public class MovieController {
	
	private static final Logger LOG = LoggerFactory.getLogger(MovieController.class);
	
	@Value("${server.port}")
	private String port;

    @Value("${api.key}")
    private String apiKey;
    
    @Value("${api.movieDbUrl}")
    private String movieDbUrl;

    private RestTemplate restTemplate;

    public MovieController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
		LOG.info("Start getMovieInfo from port : " + port);
        return restTemplate.getForObject(movieDbUrl + movieId + "?api_key=" + apiKey,
                        Movie.class);
    }
}
