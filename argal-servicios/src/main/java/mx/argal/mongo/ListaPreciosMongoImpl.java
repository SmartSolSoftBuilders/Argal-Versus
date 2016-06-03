package mx.argal.mongo;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
 
import java.util.Random;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;

public class ListaPreciosMongoImpl implements ListaPreciosMongo {

	public void saveDocument(DBCollection collection,HashMap map){
		BasicDBObject document = new BasicDBObject();
		Set set = map.entrySet();
		int registros=0;
		Iterator iterator = set.iterator();
		document.put("_id", new Random().nextInt());
	    while(iterator.hasNext()) {
	        Map.Entry mentry = (Map.Entry)iterator.next();
	    	//System.out.print("key: "+ mentry.getKey() + " & Value: ");
	        //System.out.println(mentry.getValue());	        
	        document.put(""+mentry.getKey(), mentry.getValue());
	        //System.out.println("num Registros:"+registros++);
	        //System.out.println(document.size());
	    }
	    collection.insert(document);
	    //System.out.println("Inserción realizada");
	    /*DBCursor cursor = collection.find();
        while(cursor.hasNext()) {
            System.out.println(cursor.next());
        } */       
	}
	
	public  void basicDBObject_Example(DBCollection collection){
	        BasicDBObject document = new BasicDBObject();
	        document.put("name", "lokesh");
	        document.put("website", "howtodoinjava.com");
	      
	        BasicDBObject documentDetail = new BasicDBObject();
	        documentDetail.put("addressLine1", "Sweet Home");
	        documentDetail.put("addressLine2", "Karol Bagh");
	        documentDetail.put("addressLine3", "New Delhi, India");
	      
	        document.put("address", documentDetail);
	      
	        collection.insert(document);
	   }
	     
	    public  void basicDBObjectBuilder_Example(DBCollection collection){
	        BasicDBObjectBuilder documentBuilder = BasicDBObjectBuilder.start()
	                .add("name", "lokesh")
	                .add("website", "howtodoinjava.com");
	          
	        BasicDBObjectBuilder documentBuilderDetail = BasicDBObjectBuilder.start()
	        .add("addressLine1", "Some address")
	        .add("addressLine2", "Karol Bagh")
	        .add("addressLine3", "New Delhi, India");
	      
	        documentBuilder.add("address", documentBuilderDetail.get());
	      
	        collection.insert(documentBuilder.get());
	    }
	     
	    public  void hashMap_Example(DBCollection collection){
	        Map<String, Object> documentMap = new HashMap<String, Object>();
	        documentMap.put("name", "lokesh");
	        documentMap.put("website", "howtodoinjava.com");
	      
	        Map<String, Object> documentMapDetail = new HashMap<String, Object>();
	        documentMapDetail.put("addressLine1", "Some address");
	        documentMapDetail.put("addressLine2", "Karol Bagh");
	        documentMapDetail.put("addressLine3", "New Delhi, India");
	      
	        documentMap.put("address", documentMapDetail);
	      
	        collection.insert(new BasicDBObject(documentMap));
	    }
	     
	    public  void parseJSON_Example(DBCollection collection){
	        String json = "{ 'name' : 'lokesh' , " +
	                        "'website' : 'howtodoinjava.com' , " +
	                        "'address' : { 'addressLine1' : 'Some address' , " +
	                                      "'addressLine2' : 'Karol Bagh' , " +
	                                      "'addressLine3' : 'New Delhi, India'}" +
	                                    "}";
	              
	        DBObject dbObject = (DBObject)JSON.parse(json);
	      
	        collection.insert(dbObject);
	    }
	    
        

}
