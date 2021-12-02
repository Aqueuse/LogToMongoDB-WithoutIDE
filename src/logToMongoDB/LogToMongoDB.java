package logToMongoDB;

import java.io.Console;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.awt.*;
import java.util.Date;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import org.bson.types.ObjectId;

public class LogToMongoDB {
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy H:m:s");

    public void logInDB(String message) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017/")) {
            MongoDatabase database = mongoClient.getDatabase("mongoLog");
            MongoCollection<Document> collection = database.getCollection("logs");
            try {
                collection.insertOne(new Document()
                        .append("_id", new ObjectId())
                        .append("message", message)
                        .append("date", formatter.format(new Date())));
            } catch (MongoException mongoException) {
                logInTerm("can't connect to MongoDB \\_x<");
            }
        }
    }

    public void logInTerm(String message) {
        Console console = System.console();
        if(console != null && !GraphicsEnvironment.isHeadless()) {
            try {
                Runtime.getRuntime().exec("cmd.exe /c start; echo ["+formatter.format(new Date())+"] : "+message);
            }
            catch (IOException ioException) {
                logInDB("Can't open a console, write in DB instead "+ message);
            }
        }
    }

    public void flushLogs() {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017/")) {
            MongoDatabase database = mongoClient.getDatabase("mongoLog");
            MongoCollection<Document> collection = database.getCollection("logs");
            try {
                // way more efficient that remove(query) Why ? ᕦ(ò_óˇ)ᕤ
                collection.drop();
                database.createCollection("logs");
            } catch (MongoException mongoException) {
                System.err.println("Unable to flush because " + mongoException);
            }
        }
    }
}
