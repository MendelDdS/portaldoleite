/**
 * 
 */
package models.organizador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import models.Dica;

/**
 * @author Notebook
 *
 */
public interface Organizador {
	public List<Dica> sort(List<Dica> dicas);
	
}
