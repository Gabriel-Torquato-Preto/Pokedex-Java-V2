package br.bosch.ExercicoSpring.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Objects;

public class ConverterDados implements IConverterDados{

    private final ObjectMapper mapper = new ObjectMapper();


    @Override
    public <T> T obterDados(String json, Class<T> tClass) throws JsonProcessingException {
        return mapper.readValue(json, tClass);
    }
}
