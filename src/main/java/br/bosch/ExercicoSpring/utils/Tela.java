package br.bosch.ExercicoSpring.utils;

import br.bosch.ExercicoSpring.services.ConsultarApi;
import br.bosch.ExercicoSpring.services.ConverterDados;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;

public class Tela extends JFrame {
    JTextField textField = new JTextField();

    JButton jButton = new JButton("Pesquisar");
    JLabel label = new JLabel("-- Bᴇᴍ-Vɪɴᴅᴏ ᴀ Pᴏᴋᴇᴅᴇx ᴇᴍ Jᴀᴠᴀ --");

    ConsultarApi consultarApi = new ConsultarApi();

    public Tela(String title) {
        super(title);
        ImageIcon icon = new ImageIcon("C:/Users/prg2ca/Desktop/ExercicoSpring/src/main/java/br/bosch/ExercicoSpring/utils/Pokebola-pokeball-png-0.png");
        this.setIconImage(icon.getImage());
        this.setSize(600, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.getContentPane().setBackground(Color.black);
        this.setLayout(null);
        textField.setSize(200, 50);
        textField.setLocation(100, 300);
        jButton.setSize(100, 50);
        jButton.setLocation(400, 300);
        jButton.addActionListener(e -> {

            try {
                Dex dex = new Dex("pokedex", textField.getText());
            } catch (JsonProcessingException | MalformedURLException | URISyntaxException ex) {
                throw new RuntimeException(ex);
            }

        });
        label.setFont(new Font("Calibri", Font.BOLD, 20));
        label.setSize(300, 200);
        label.setForeground(Color.white);
        label.setLocation(150, 20);

        this.add(label);
        this.add(textField);
        this.add(jButton);
    }

    public static class Dex extends JFrame {

        ConsultarApi consultarApi = new ConsultarApi();
        JsonNode node;

        private URL iamgeUrl;


        ConverterDados converterDados = new ConverterDados();

        public String getPokemon() {
            return pokemon;
        }

        public void setPokemon(String pokemon) {
            this.pokemon = pokemon;
        }

        private String pokemon;

        public String getJson() {
            return json;
        }

        public void setJson(String json) {
            this.json = json;
        }

        private String json;

        Dex(String title, String nomePokemon) throws JsonProcessingException, URISyntaxException, MalformedURLException {
            super(title);
            setPokemon(nomePokemon);
            this.setSize(1200, 800);
//            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setVisible(true);
            this.getContentPane().setBackground(Color.black);
            this.setLayout(null);
            JLabel label = new JLabel("aaaa");
            label.setFont(new Font("Calibri", Font.BOLD, 20));
            label.setSize(300, 200);
            label.setForeground(Color.white);
            label.setLocation(150, 20);


            Thread thread = new Thread(() -> {

                try {
                    setJson(consultarApi.apiRequest(pokemon));
                    node = converterDados.obterDados(json, JsonNode.class);
                    System.out.println(node);
                    System.out.println(node.path("sprites").get("front_shiny").asText());
                } catch (IOException | InterruptedException ex) {
                    throw new RuntimeException(ex);
                }

            });

            thread.start();
            while (thread.isAlive()) {
                System.out.println("processando...");
            }

            URI imageUrl = new URI(node.path("sprites").get("front_shiny").asText());
            ImageIcon imageIcon = new ImageIcon(imageUrl.toURL());
            ImageIcon icon = new ImageIcon("C:/Users/prg2ca/Desktop/ExercicoSpring/src/main/java/br/bosch/ExercicoSpring/utils/Pokebola-pokeball-png-0.png");
            this.setIconImage(icon.getImage());
            System.out.println(imageIcon.getIconHeight());
            imageIcon.setImage(imageIcon.getImage().getScaledInstance(600, 600, Image.SCALE_DEFAULT));
            JLabel label2 = new JLabel(imageIcon);
            JLabel label3 = new JLabel(node.get("name").asText());
            label3.setFont(new Font("Calibri", Font.BOLD, 50));
            label3.setSize(300, 200);
            label3.setForeground(Color.white);
            label3.setLocation(180, 0);
            label2.setSize(600, 600);
            label2.setLocation(20, 130);
            label2.setBackground(Color.white);
            JPanel panel = new JPanel();
            panel.setSize(400,550);
            panel.setLocation(700,20);
            panel.setLayout(null);
            String type2;
            try {
                type2 = " and " + node.path("types").path(1).path("type").get("name").asText();
            } catch (Exception e) {
                type2 = "";
            }
            JLabel label4 = new JLabel("types - " + node.path("types").path(0).path("type").get("name").asText()+type2);
            JLabel label5 = new JLabel("pokedex n°- " + node.get("id").asText());
            JLabel label6 = new JLabel("------STATS-------");
            label4.setFont(new Font("Calibri", Font.BOLD, 20));
            label6.setFont(new Font("Calibri", Font.BOLD, 20));
            label5.setFont(new Font("Calibri", Font.BOLD, 20));
            label4.setSize(300, 100);
            label5.setSize(200, 100);
            label6.setSize(200, 100);
            label4.setLocation(20,0);
            label5.setLocation(20,30);
            label6.setLocation(20,60);

            AtomicInteger y = new AtomicInteger(90);
            AtomicInteger y2= new AtomicInteger(120);

            node.path("stats").forEach(jsonNode -> {
                JLabel label7 = new JLabel(jsonNode.path("stat").get("name").asText());
                JLabel label8 = new JLabel(jsonNode.path("base_stat").asText());
                label7.setFont(new Font("Calibri", Font.BOLD, 20));
                label8.setFont(new Font("Calibri", Font.BOLD, 20));
                label7.setSize(300, 100);
                label8.setSize(300, 100);
                label7.setLocation(20, y.get());
                label8.setLocation(20, y2.get());
                panel.add(label7);
                panel.add(label8);
                y.addAndGet(70);
                y2.addAndGet(70);
                System.out.println(jsonNode.path("base_stat"));
            });

            panel.add(label4);
            panel.add(label5);
            panel.add(label6);
            this.add(label2);
            this.add(label3);
            this.add(panel);
        }


    }
}
