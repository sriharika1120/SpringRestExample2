package com.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.map.introspect.JacksonAnnotationIntrospector;
import org.codehaus.jackson.type.TypeReference;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;


public class JsonUtil {

    private static ObjectMapper objectMapper;
    
    static {
    	objectMapper = new ObjectMapper();
        //objectMapper.registerModule(new GuavaModule());
        objectMapper.setAnnotationIntrospector(AnnotationIntrospector.pair(new JaxbAnnotationIntrospector(), new JacksonAnnotationIntrospector()));
    	objectMapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    	objectMapper.setSerializationInclusion(Inclusion.NON_NULL);
    }

    private JsonUtil() {
    }

    /**
     * @param jsonString
     * @param classType
     * @return
     */
    public static <T> T convertToObject(String jsonString,Class<T> classType) throws Exception{
        T obj = null;
        obj = objectMapper.readValue(jsonString, classType);
        return obj;
    }

    public static <T> T convertToObject(String jsonString, TypeReference<T> typeReference)
            throws IOException {
        return objectMapper.readValue(jsonString, typeReference);
    }

    public static String convertToString(Object obj)
            throws IOException {
        return objectMapper.writeValueAsString(obj);
    }

    /**
     * The below method is used to convert the Map<String, Object> from the JSON String response
     * @param response
     * @return
     */
    public static List<Map<String,Object>> getListOfMaps(String response) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> list = null;
        list = mapper.readValue(response, new TypeReference<List<Map<String, Object>>>() {});
        return list;
    }

    public static boolean isValidJSON(final String json) throws Exception {
        boolean valid = false;
        final JsonParser parser = new ObjectMapper().getJsonFactory()
                    .createJsonParser(json);
        while (parser.nextToken() != null) {
        }
        valid = true;

        return valid;
    }  
    
    public static ObjectMapper getJsonObjectMapper() {
    	
    	return  objectMapper;
    }
}
