package fr.delmath.utils;

import com.google.gson.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bukkit.Bukkit;

import java.util.Calendar;

public class history {
    // ...

    public static void addBlock(String fichier, String coordonnee, String date, String blockAvant, String blockApres, String entityType, String playerName) {
        try {
            File file = new File(fichier);

            // Vérifier si le fichier JSON existe
            if (!file.exists()) {
                // Créer le fichier s'il n'existe pas
                file.createNewFile();
            }

            // Lire le contenu du fichier JSON existant dans une structure de données
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(new FileReader(file));
            JsonObject jsonRoot = jsonElement.isJsonObject() ? jsonElement.getAsJsonObject() : new JsonObject();

            // Vérifier si la clé "coordonnee" existe déjà
            if (jsonRoot.has(coordonnee)) {
                addModification(fichier, coordonnee, date, blockAvant, blockApres, entityType, playerName);
                return;
            } else {
                // Si la clé "coordonnee" n'existe pas, la créer et ajouter le premier objet
                JsonArray block = new JsonArray();
                JsonObject newblock = new JsonObject();
                newblock.addProperty("date", date);
                newblock.addProperty("base block", blockAvant);
                newblock.addProperty("new block", blockApres);
                newblock.addProperty("entity", entityType);
                newblock.addProperty("name", playerName);
                block.add(newblock);
                jsonRoot.add(coordonnee, block);
            }

            // Écrire la structure JSON mise à jour dans le fichier
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileWriter writer = new FileWriter(file);
            gson.toJson(jsonRoot, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean blockExists(String datafile, String location) {
        try {
            File file = new File(datafile);

            // Vérifier si le fichier JSON existe
            if (!file.exists()) {
                return false;
            }

            // Lire le contenu du fichier JSON
            JsonElement jsonElement = new JsonParser().parse(new FileReader(file));

            // Vérifier si le fichier est vide
            if (jsonElement.isJsonNull()) {
                return false;
            }

            JsonObject jsonObject = jsonElement.getAsJsonObject();

            // Vérifier si la clé "location" existe dans le fichier JSON
            return jsonObject.has(location);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void addModification(String fichier, String coordonnee, String date, String blockAvant, String blockApres, String entityType, String playerName) {
        try {
            File file = new File(fichier);

            // Vérifier si le fichier JSON existe
            if (!file.exists()) {
                // Créer le fichier s'il n'existe pas
                file.createNewFile();
            }

            // Lire le contenu du fichier JSON existant dans une structure de données
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(new FileReader(file));
            JsonObject jsonRoot = jsonElement.isJsonObject() ? jsonElement.getAsJsonObject() : new JsonObject();

            // Vérifier si la clé "coordonnee" existe déjà
            if (jsonRoot.has(coordonnee)) {
                JsonArray blockList = jsonRoot.get(coordonnee).getAsJsonArray();

                // Créer un nouvel objet pour la modification
                JsonObject newModification = new JsonObject();
                newModification.addProperty("date", date);
                newModification.addProperty("base block", blockAvant);
                newModification.addProperty("new block", blockApres);
                newModification.addProperty("entity", entityType);
                newModification.addProperty("name", playerName);

                // Ajouter le nouvel objet à la liste d'objets existants
                blockList.add(newModification);
            } else {
                // Si la clé "coordonnee" n'existe pas, la créer et ajouter le premier objet
                addBlock(fichier, coordonnee, date, blockAvant, blockApres, entityType, playerName);
                return;
            }

            // Écrire la structure JSON mise à jour dans le fichier
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileWriter writer = new FileWriter(file);
            gson.toJson(jsonRoot, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<ModifyBlock> parseModifyBlocks(String jsonFilePath, String duration) {
        List<ModifyBlock> modifyBlocks = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode rootNode = objectMapper.readTree(new File(jsonFilePath));

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Calendar calendar = Calendar.getInstance();

            // Parsing de la durée au format "XhYminZs"
            int hours = 0;
            int minutes = 0;
            int seconds = 0;

            String[] numbers = duration.split("[hms]");

            for (int i = 0; i < numbers.length; i++) {
                int value = Integer.parseInt(numbers[i]);
                if (i < numbers.length - 1) {
                    if (duration.contains("h") && i == 0) {
                        hours = value;
                    } else if (duration.contains("m") && i == 0) {
                        minutes = value;
                    } else if (duration.contains("s") && i == 0) {
                        seconds = value;
                    }
                } else {
                    if (duration.endsWith("h")) {
                        hours = value;
                    } else if (duration.endsWith("m")) {
                        minutes = value;
                    } else if (duration.endsWith("s")) {
                        seconds = value;
                    }
                }
            }

            // Calcul de la date cible
            calendar.add(Calendar.SECOND, -seconds);
            calendar.add(Calendar.MINUTE, -minutes);
            calendar.add(Calendar.HOUR, -hours);
            Date targetDate = calendar.getTime();

            Bukkit.broadcastMessage(targetDate.toString());

            for (JsonNode locationNode : rootNode) {
                String coordonnee = locationNode.toString();
                ModifyBlock lastValidModifyBlock = null;

                for (JsonNode blockChange : locationNode) {
                    String dateStr = blockChange.get("date").asText();
                    Date date = dateFormat.parse(dateStr);

                    if (targetDate.before(date)) {
                        String blockAvant = blockChange.get("base block").asText();
                        lastValidModifyBlock = new ModifyBlock(coordonnee, blockAvant);
                    }
                }

                if (lastValidModifyBlock != null) {
                    modifyBlocks.add(lastValidModifyBlock);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return modifyBlocks;
    }


}

