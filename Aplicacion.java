package EquiposJugadores;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class Aplicacion {
    public static void main(String[] args) {
        // Config JPA y base de datos
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EjercicioEquipos");
        EntityManager em = emf.createEntityManager();

        // Instancio las clases de los DAO
        EquipoDAO equipoDAO = new EquipoDAO(em);
        JugadorDAO jugadorDAO = new JugadorDAO(em);

        // Crear equipos
        Equipo equipo1 = new Equipo();
        equipo1.setNombre("Equipo A");
        equipo1.setEstadio("Estadio A");

        Equipo equipo2 = new Equipo();
        equipo2.setNombre("Equipo B");
        equipo2.setEstadio("Estadio B");

        equipoDAO.insertar(equipo1);
        equipoDAO.insertar(equipo2);

        // Crear jugadores
        Jugador jugador1 = new Jugador();
        jugador1.setNombre("Jugador 1");
        jugador1.setEstatura(1.85f);
        jugador1.setPeso(80.0f);
        jugador1.setEquipo(equipo1);

        Jugador jugador2 = new Jugador();
        jugador2.setNombre("Jugador 2");
        jugador2.setEstatura(1.90f);
        jugador2.setPeso(85.0f);
        jugador2.setEquipo(equipo1);

        Jugador jugador3 = new Jugador();
        jugador3.setNombre("Jugador 3");
        jugador3.setEstatura(1.78f);
        jugador3.setPeso(75.0f);
        jugador3.setEquipo(equipo2);

        jugadorDAO.insertar(jugador1);
        jugadorDAO.insertar(jugador2);
        jugadorDAO.insertar(jugador3);

        // Leer equipos
        List<Equipo> equipos = equipoDAO.obtenerTodos();
        System.out.println("Equipos:");
        for (Equipo equipo : equipos) {
            System.out.println(equipo);
        }

        // Leer jugadores
        List<Jugador> jugadores = jugadorDAO.obtenerTodos();
        System.out.println("Jugadores:");
        for (Jugador jugador : jugadores) {
            System.out.println(jugador);
        }

        // Actualizar equipo
        equipo1.setNombre("Equipo A Actualizado");
        equipoDAO.actualizar(equipo1);
        System.out.println("Equipo actualizado: " + equipoDAO.obtenerPorId(equipo1.getId()));

        // Actualizar jugador
        jugador1.setNombre("Jugador 1 Actualizado");
        jugadorDAO.actualizar(jugador1);
        System.out.println("Jugador actualizado: " + jugadorDAO.obtenerPorId(jugador1.getId()));

        // Eliminar equipo
        equipoDAO.eliminar(equipo2.getId());
        System.out.println("Equipo eliminado. Equipos restantes:");
        equipos = equipoDAO.obtenerTodos();
        for (Equipo equipo : equipos) {
            System.out.println(equipo);
        }

        // Eliminar jugador
        jugadorDAO.eliminar(jugador3.getId());
        System.out.println("Jugador eliminado. Jugadores restantes:");
        jugadores = jugadorDAO.obtenerTodos();
        for (Jugador jugador : jugadores) {
            System.out.println(jugador);
        }

        // Ordenar equipos por nombre
        List<Equipo> equiposOrdenados = em.createQuery("SELECT e FROM Equipo e ORDER BY e.nombre", Equipo.class).getResultList();
        System.out.println("Equipos ordenados por nombre:");
        for (Equipo equipo : equiposOrdenados) {
            System.out.println(equipo);
        }

        // Ordenar equipos por ID
        List<Equipo> equiposPorId = em.createQuery("SELECT e FROM Equipo e ORDER BY e.id", Equipo.class).getResultList();
        System.out.println("Equipos ordenados por ID:");
        for (Equipo equipo : equiposPorId) {
            System.out.println(equipo);
        }

        em.close();
        emf.close();
    }
}

