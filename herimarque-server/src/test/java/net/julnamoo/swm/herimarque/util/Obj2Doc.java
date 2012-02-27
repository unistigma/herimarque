package net.julnamoo.swm.herimarque.util;


import java.lang.reflect.Field;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Index;

public class Obj2Doc {

	/**
	 * Convert an anonymous object to Lucene document with reflection information
	 * It supports only primitive type 
	 * @param object
	 * @return
	 * @throws IllegalAccessException 
	 * @throws ClassNotFoundException 
	 */
	public static Document toDocument(Object object) throws IllegalAccessException, ClassNotFoundException
	{
		Document doc = new Document();
		Class<?> instance;
		
		instance = Class.forName(object.getClass().getName()); 

		// May be checking annotation at this point //

		/** Get fields of the parameter class then add the field in the document**/
		Field[] fieldList = instance.getDeclaredFields();

		for(Field field : fieldList)
		{
			// name be the field of a Lucene Document
			String name = field.getName(); 

			//check the type of the field for create a field of the document
			Class<?> type = field.getType();
			if(type.getSimpleName().contains("String"))
			{
				//check an accessibility of the field then set the value for access to the field
				if(!field.isAccessible()) field.setAccessible(true);

				if(field.get(object) == null) continue;
				// add the document field to the document
				doc.add(new org.apache.lucene.document.Field
						(name, (String) field.get(object), 
								org.apache.lucene.document.Field.Store.YES, 
								Index.ANALYZED));
			}else
			{
				//System.out.println("not primitive : " + type.getName());
			}
		}

		return doc;
	}
}
