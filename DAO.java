package EquiposJugadores;

import java.util.List;

public interface DAO<T> {
    List<T> obtenerTodos();
    T obtenerPorId(int id);
    void insertar(T entidad);
    void actualizar(T entidad);
    void eliminar(int id);
}


