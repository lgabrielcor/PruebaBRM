package app.lugcor.co.com.pruebabrm.controlador.persistencia;

import java.util.List;

/**
 * Created by luisgabrielcorredorcombita on 27/07/16.
 */
public class MarcaCrud implements ICrud{
    @Override
    public boolean insertar(Object o) {
        return false;
    }

    @Override
    public Object ObtenerItem(String id) {
        return null;
    }

    @Override
    public List<Object> obtenerTodosLosItems() {
        return null;
    }

    @Override
    public List<Object> obtenerTodosLosItemsPorID(String id) {
        return null;
    }

    @Override
    public boolean actualizarItem(Object nuevo, Object anterior) {
        return false;
    }

    @Override
    public boolean borrarItem(Object elemento) {
        return false;
    }

    @Override
    public boolean borrarTodosLosItems() {
        return false;
    }
}
