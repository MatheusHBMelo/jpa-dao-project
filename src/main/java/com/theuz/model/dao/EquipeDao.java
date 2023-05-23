package com.theuz.model.dao;

import com.theuz.model.entities.Equipe;

import java.util.List;

public interface EquipeDao {
    void insert(Equipe equipe);

    void update(Equipe equipe);

    void remove(Integer id);

    Equipe findById(Integer id);

    List<Equipe> findAll();
}
