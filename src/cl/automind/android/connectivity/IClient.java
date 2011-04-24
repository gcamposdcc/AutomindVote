package cl.automind.android.connectivity;

import cl.automind.android.utils.ExportableObject;
/**
 * Interfaz para el puente Java-C#
 * @author Guillermo
 *
 */
public interface IClient {
	/**
	 * Exporta el objeto
	 * @param geoObject 
	 */
	public String exportObject(ExportableObject geoObject);
	/**
	 * Importa el objeto
	 * @return
	 */
	public ExportableObject importObject();
}
