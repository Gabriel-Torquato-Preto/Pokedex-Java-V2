package br.bosch.ExercicoSpring.utils;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosNomeTipo(@JsonAlias("name") String nome ) {

}
