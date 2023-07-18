package co.com.auth_back.auth_back.utils;

public class RequestUtil {

    /**
     * @param requestUri URI desde donde se hizo la peticion
     * @param part La parte de la URI que queremos obtener
     * @return la cadena de texto contenido en esa parte de la URI
     */
    public static String getPartUrI(String requestUri,int part){
        String[] partsUri=requestUri.split("/");
        return partsUri[part];
    }
}
