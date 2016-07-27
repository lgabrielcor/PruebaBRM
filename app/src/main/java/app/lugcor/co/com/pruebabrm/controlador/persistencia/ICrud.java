package app.lugcor.co.com.pruebabrm.controlador.persistencia;

import java.util.List;

/**
 * Created by luisgabrielcorredorcombita on 27/07/16.
 */
public interface ICrud {
    boolean insertar(Object o);
    Object ObtenerItem(String id);
    List<Object> obtenerTodosLosItems();
    List<Object> obtenerTodosLosItemsPorID(String id);
    boolean actualizarItem(Object nuevo, Object anterior);
    boolean borrarItem(Object elemento);
    boolean borrarTodosLosItems();
}
