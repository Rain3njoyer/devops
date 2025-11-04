package com.napier.devops;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class App {
    public static void main(String[] args) {
        System.out.println("App starting...");

        try {
            // connect to mongo container named mongo-dbserver on default port 27017
            MongoClient mongoClient = new MongoClient("mongo-dbserver", 27017);

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
