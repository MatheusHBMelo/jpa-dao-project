package com.theuz.model.dao.impl;

import com.theuz.model.dao.PilotoDao;
import com.theuz.model.entities.Equipe;
import com.theuz.model.entities.Piloto;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PilotoDaoImpl implements PilotoDao {
    private EntityManager em;

    public PilotoDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void insert(Piloto piloto) {
        if (piloto == null) {
            throw new IllegalArgumentException("O objeto piloto não pode ser nulo.");
        } else {
            try {
                em.getTransaction().begin();
                em.persist(piloto);
                em.getTransaction().commit();
            } catch (RuntimeException e) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                e.printStackTrace();
            } finally {
                em.close();
            }
        }
    }

    @Override
    public void update(Piloto piloto) {
        if (piloto == null) {
            throw new IllegalArgumentException("O objeto piloto não pode ser nulo.");
        } else {
            try {
                Piloto pilotoPersistido = em.find(Piloto.class, piloto.getId());
                if (pilotoPersistido == null) {
                    throw new NoResultException("O piloto não existe no banco de dados.");
                } else {
                    if (piloto.getName() == null) {
                        piloto.setName(pilotoPersistido.getName());
                    }
                    if (piloto.getDataNascimento() == null) {
                        piloto.setDataNascimento(pilotoPersistido.getDataNascimento());
                    }
                    if (piloto.getEquipe() == null) {
                        piloto.setEquipe(pilotoPersistido.getEquipe());
                    }
                    em.getTransaction().begin();
                    em.merge(piloto);
                    em.getTransaction().commit();
                }
            } catch (RuntimeException e) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                e.printStackTrace();
            }
        }
    }

    @Override
    public void remove(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("O id não pode ser nulo.");
        } else {
            try {
                Piloto pilotoPersistido = em.find(Piloto.class, id);
                if (pilotoPersistido != null) {
                    em.getTransaction().begin();
                    em.remove(pilotoPersistido);
                    em.getTransaction().commit();
                } else {
                    throw new NoResultException("O piloto não existe no banco de dados.");
                }
            } catch (RuntimeException e) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                e.printStackTrace();
            }
        }
    }

    @Override
    public Piloto findById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("O id não pode ser nulo.");
        } else {
            try {
                Piloto pilotoPersistido = em.find(Piloto.class, id);
                if (pilotoPersistido != null) {
                    return pilotoPersistido;
                } else {
                    throw new NoResultException("O piloto não existe no banco de dados.");
                }
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<Piloto> findByEquipe(Equipe equipe) {
        try {
            List<Piloto> list;
            Map<Integer, Equipe> map = new HashMap<>();

            TypedQuery<Piloto> query = em.createQuery("SELECT p FROM Piloto p WHERE equipe_id_equipe = :id", Piloto.class);
            query.setParameter("id", equipe.getId());

            list = query.getResultList();
            if (list != null) {
                for (Piloto piloto : list) {
                    Equipe equipe2 = piloto.getEquipe();
                    if (equipe2 != null) {
                        int equipeId = equipe2.getId();
                        if (map.containsKey(equipeId)) {
                            map.get(equipeId).getPilotos().add(piloto);
                        } else {
                            equipe2.getPilotos().add(piloto);
                            map.put(equipeId, equipe2);
                        }
                    }
                }
                return list;
            } else {
                return new ArrayList<>();
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Piloto> findAll() {
        try {
            List<Piloto> list;
            Map<Integer, Equipe> map = new HashMap<>();

            TypedQuery<Piloto> query = em.createQuery("SELECT p FROM Piloto p", Piloto.class);

            list = query.getResultList();
            for (Piloto piloto : list) {
                Equipe equipe = piloto.getEquipe();
                if (equipe != null) {
                    int equipeId = equipe.getId();
                    if (map.containsKey(equipeId)) {
                        map.get(equipeId).getPilotos().add(piloto);
                    } else {
                        equipe.getPilotos().add(piloto);
                        map.put(equipeId, equipe);
                    }
                }
            }
            return list;
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }
}
