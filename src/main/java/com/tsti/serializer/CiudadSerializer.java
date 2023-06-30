/**
 * 
 */
package com.tsti.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.tsti.entidades.Ciudad;

import java.io.IOException;

public class CiudadSerializer extends JsonSerializer<Ciudad> {
    @Override
    public void serialize(Ciudad ciudad, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        // Aquí implementa la lógica de serialización personalizada según tus necesidades
        // Por ejemplo, puedes obtener el nombre de la ciudad y serializarlo
        String nombreCiudad = ciudad.getNombreCiudad();
        String provincia = ciudad.getProvincia();
        String pais = ciudad.getPais();
        
        //jsonGenerator.writeStartObject();
        jsonGenerator.writeString(nombreCiudad);
        //jsonGenerator.writeString(provincia);
        //jsonGenerator.writeString(pais);
        //jsonGenerator.writeEndObject();
    }


}
