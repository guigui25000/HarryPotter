package com.example.harrypotter.api;

import com.example.harrypotter.personagem.Casa;

import com.example.harrypotter.personagem.Personagem;
import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class ConsumirAPI {

    private final String url = "https://api-harrypotter.herokuapp.com/";
    Gson gson = new Gson();

    public Personagem sortingHat() {
        RestTemplate restTemplate = new RestTemplate();
        var response = restTemplate.getForEntity(url + "sortinghat", String.class);
        return gson.fromJson(response.getBody(), Personagem.class);
    }

    public Casa findHouse(Personagem personagem) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url + "house/" + personagem.getIdhouse(), String.class);
        return gson.fromJson(response.getBody(), Casa.class);
    }

}
