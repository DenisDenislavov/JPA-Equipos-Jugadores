package EquiposJugadores;

import jakarta.persistence.*;
import java.util.List;

public class EquipoDAO implements DAO<Equipo> {
    private EntityManager em;

    public EquipoDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Equipo> obtenerTodos() {
        return em.createQuery("SELECT e FROM Equipo e", Equipo.class).getResultList();
    }

    @Override
    public Equipo obtenerPorId(int id) {
        return em.find(Equipo.class, id);
    }

    @Override
    public void insertar(Equipo equipo) {
        em.getTransaction().begin();
        em.persist(equipo);
        em.getTransaction().commit();
    }

    @Override
    public void actualizar(Equipo equipo) {
        em.getTransaction().begin();
        em.merge(equipo);
        em.getTransaction().commit();
    }

    @Override
    public void eliminar(int id) {
        Equipo equipo = obtenerPorId(id);
        if (equipo != null) {
            em.getTransaction().begin();
            em.remove(equipo);
            em.getTransaction().commit();
        }
    }
}
