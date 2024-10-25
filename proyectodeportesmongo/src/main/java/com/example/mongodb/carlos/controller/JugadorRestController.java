package com.example.mongodb.carlos.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.mongodb.carlos.Entity.Jugador;
import com.example.mongodb.carlos.exception.NotFoundException;
import com.example.mongodb.carlos.Repository.JugadorRepository;



@RestController
@RequestMapping(value = "/api/jugadores")
public class JugadorRestController {
	
	 @Autowired
	    private JugadorRepository jugadorRepository;
	 
	 @GetMapping("/")
	    public List<Jugador> getAllJugador() {
	        return jugadorRepository.findAll();
	    }

	    @GetMapping("/{id}")
	    public Jugador getJugadorById(@PathVariable String id) {
	        return jugadorRepository.findById(id).orElseThrow(() -> new NotFoundException("Jugador no encontrado"));
	    }

	    @PostMapping("/")
	    public Jugador saveJugador(@RequestBody Map<String, Object> body) {
	        ObjectMapper mapper = new ObjectMapper();
	        Jugador jugador = mapper.convertValue(body, Jugador.class);
	        return jugadorRepository.save(jugador);
	    }

	    @PutMapping("/{id}")
	    public Jugador updateJugador(@PathVariable String id, @RequestBody Map<String, Object> body) {
	        ObjectMapper mapper = new ObjectMapper();
	        Jugador jugador = mapper.convertValue(body, Jugador.class);
	        jugador.setId(id);
	        return jugadorRepository.save(jugador);
	    }

	    @DeleteMapping("/{id}")
	    public Jugador deleteJugador(@PathVariable String id) {
	    	Jugador jugador = jugadorRepository.findById(id).orElseThrow(() -> new NotFoundException("Jugador no encontrado"));
	        jugadorRepository.deleteById(id);
	        return jugador;
	    }
	 

}
