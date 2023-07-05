package cat.babot.datamanager;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class DBManagement {
    private MongoClient client;
    private MongoDatabase db;
    private final MongoCollection <Document> col;

    public DBManagement() {
        client = MongoClients.create("mongodb+srv://suneito:KnZXH2hhn3samVf0@cluster-sms.6pu4f5v.mongodb.net/?retryWrites=true&w=majority");
        db = client.getDatabase("Smsotp");
        col = db.getCollection("recived");
    }

    public void insertData(Document document) {
        col.insertOne(document);
    }

}