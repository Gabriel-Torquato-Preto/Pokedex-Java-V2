package br.bosch.ExercicoSpring;

import br.bosch.ExercicoSpring.services.ConsultarApi;
import br.bosch.ExercicoSpring.services.ConverterDados;
import br.bosch.ExercicoSpring.utils.DadosPokemon;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.Scanner;

public class Principal {
    ConsultarApi consultarApi = new ConsultarApi();

    ConverterDados converterDados = new  ConverterDados();

    Scanner input = new Scanner(System.in);

    public void exibirMenu() throws IOException, InterruptedException {
        System.out.println("----------------------------");
        System.out.println("    Bem-vindo a pokedex");
        System.out.println("----------------------------");
        System.out.println("Digite o nome de um pokemon:");
        var nomePokemon = input.nextLine();

        String json = consultarApi.apiRequest(nomePokemon);

        JsonNode dadosPokemon = converterDados.obterDados(json, JsonNode.class);
        String type2;
        try {
            type2 = " and " + dadosPokemon.path("types").path(1).path("type").get("name").asText();
        } catch (Exception e) {
            type2 = "";
        }
        System.out.println("name:" + dadosPokemon.get("name").asText());
        System.out.println("types:" + dadosPokemon.path("types").path(0).path("type").get("name").asText()+type2);
        System.out.println("pokedex n°:" + dadosPokemon.get("id").asText());
        System.out.println("------STATS-------");
        dadosPokemon.path("stats").forEach(jsonNode -> {
            System.out.println(jsonNode.path("stat").get("name").asText());
            System.out.println(jsonNode.path("base_stat"));
        });
    }


}
