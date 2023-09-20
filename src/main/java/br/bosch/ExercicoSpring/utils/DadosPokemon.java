package br.bosch.ExercicoSpring.utils;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosPokemon(@JsonAlias("name") String nome, @JsonProperty("types") ArrayList<DadosSlotTipo> types ) {
}
