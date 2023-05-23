package com.theuz.model.dao.impl;

import com.theuz.model.dao.EquipeDao;
import com.theuz.model.entities.Equipe;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class EquipeDaoImpl implements EquipeDao {
    private EntityManager em;

    public EquipeDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void insert(Equipe equipe) {
        if (equipe == null) {
            throw new IllegalArgumentException("A equipe não pode ser nula!");
        } else {
            try {
                em.getTransaction().begin();
                em.persist(equipe);
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
    public void update(Equipe equipe) {
        if (equipe == null) {
            throw new IllegalArgumentException("A equipe não pode ser nula!");
        } else {
            try {
                Equipe equipePersistida = em.find(Equipe.class, equipe.getId());
                if (equipePersistida == null) {
                    throw new NoResultException("A equipe não existe no banco de dados!");
                } else {
                    if (equipe.getName() == null) {
                        equipe.setName(equipePersistida.getName());
                    }
                    if (equipe.getDataFundacao() == null) {
                        equipe.setDataFundacao(equipePersistida.getDataFundacao());
                    }
                    em.getTransaction().begin();
                    em.merge(equipe);
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
            throw new IllegalArgumentException("O id não pode ser nulo!");
        } else {
            try {
                Equipe equipePersistida = em.find(Equipe.class, id);
                if (equipePersistida != null) {
                    em.getTransaction().begin();
                    em.remove(equipePersistida);
                    em.getTransaction().commit();
                } else {
                    throw new NoResultException("A equipe não existe no banco de dados!");
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
    public Equipe findById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("O id não pode ser nulo!");
        } else {
            try {
                Equipe equipePersistida = em.find(Equipe.class, id);
                if (equipePersistida != null) {
                    return equipePersistida;
                } else {
                    throw new NoResultException("A equipe não existe no banco de dados!");
                }
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<Equipe> findAll() {
        List<Equipe> list;
        try {
            TypedQuery<Equipe> query = em.createQuery("SELECT e FROM Equipe e", Equipe.class);
            list = query.getResultList();
            return list;
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }
}
