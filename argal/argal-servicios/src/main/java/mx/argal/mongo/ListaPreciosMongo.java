package mx.argal.mongo;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
 
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;

public interface ListaPreciosMongo {	
	public void saveDocument(DBCollection collection,HashMap map);
	public void basicDBObject_Example(DBCollection collection);	     
	public void basicDBObjectBuilder_Example(DBCollection collection); 
	public void hashMap_Example(DBCollection collection);	     
	public void parseJSON_Example(DBCollection collection);
}
