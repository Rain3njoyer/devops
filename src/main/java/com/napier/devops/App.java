package com.napier.devops;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
    public static void main(String[] args) {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        System.out.println("App starting...");

        try {
            String host = System.getenv().getOrDefault("MONGO_HOST", "localhost");
            int port = Integer.parseInt(System.getenv().getOrDefault("MONGO_PORT", "27000"));
            MongoClient mongoClient = new MongoClient(host, port);
            MongoDatabase database = mongoClient.getDatabase("mydb");
            MongoCollection<Document> collection = database.getCollection("test");

            Document doc = new Document("name", "Dev Student")
                    .append("class", "DevOps")
                    .append("year", "2024")
                    .append("result", new Document("CW", 95).append("EX", 85));

            collection.insertOne(doc);
            Document myDoc = collection.find().first();
            System.out.println("Inserted doc: " + myDoc.toJson());

            mongoClient.close();
            System.out.println("App finished.");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
