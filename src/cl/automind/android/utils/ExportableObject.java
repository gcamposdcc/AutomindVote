package cl.automind.android.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

/**
 * El tipo de Objeto en el que se almacenan los resultados
 * de la busqueda de Kosmo
 * @author Guillermo
 *
 */
public class ExportableObject implements Serializable{
	private final static String SIZE = "size";
	public final static String RUT  = "rut";
	public final static String VOTE = "vote";
	private Set<String> keys;
	private HashMap <String,String> values;
	/**
	 * serialVersionUID autogenerado
	 */
	private static final long serialVersionUID = -8328834371765263518L;
	/**
	 * Crea un objeto exportable. El objeto exportable permite hacer un mapping de
	 * propiedades y sus valores tal cual como en un archivo <b>.properties</b>.
	 * @param _id el identificador del objeto, se recomienda sacarlo de SearchStorage con getID
	 * @see SearchStorage.getID();
	 */
	public ExportableObject(){
		values = new HashMap <String, String>();
		keys = values.keySet();
	}
	/**
	 * Agrega una propiedad con un valor determinado.
	 * Las propiedades se identifican por su nombre. Por lo que:</br> 
	 * <b>set(key,value);</b></br> 
	 * <b>set(key,value2);</b></br>
	 * provoca que el valor se <b>key</b> sea <b>value2</b>
	 * @param key el nombre de la propiedad
	 * @param value el valor de la propiedad
	 */
	public void set(String key, String value){
		values.put(key,value);
	}
	/**
	 * Exporta los datos actuales en este objeto
	 * el primer campo indica cuantos son los elementos que se exportan.</br>
	 * Los datos se exportan como en un archivo .properties:
	 * <b>property = value</b>
	 */
	public String[] getValues(){
		String[] output = null;
		if (values.size()>0){
			output = new String [values.size()+1];
			int count = 0;
			output[count++] = SIZE+"="+values.size();
			for (String key: keys)output[count++] = key+"="+values.get(key);
		} else {
			output = new String [values.size()];
		}
		return output;
	}
}
