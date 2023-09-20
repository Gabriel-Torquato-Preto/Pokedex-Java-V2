package br.bosch.ExercicoSpring.services;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultarApi {

    String url = "https://pokeapi.co/api/v2/pokemon/";

    public String apiRequest(String nomePokemon) throws IOException, InterruptedException {

        String requestUrl = url+nomePokemon;

        HttpClient client = HttpClient.newBuilder().build();

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(requestUrl)).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();


    }


}
