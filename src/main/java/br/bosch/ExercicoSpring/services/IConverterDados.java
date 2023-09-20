package br.bosch.ExercicoSpring.services;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IConverterDados {
    <T> T obterDados(String json, Class<T> tClass) throws JsonProcessingException;
}
