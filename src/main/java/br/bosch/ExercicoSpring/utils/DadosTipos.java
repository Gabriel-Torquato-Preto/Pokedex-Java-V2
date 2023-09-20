package br.bosch.ExercicoSpring.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosTipos(@JsonProperty("type")ArrayList<DadosNomeTipo> tipo) {
}
