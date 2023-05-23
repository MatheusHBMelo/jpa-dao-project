package com.theuz.model.dao;

import com.theuz.model.entities.Equipe;
import com.theuz.model.entities.Piloto;

import java.util.List;

public interface PilotoDao {
    void insert(Piloto piloto);
    void update(Piloto piloto);
    void remove(Integer id);
    Piloto findById(Integer id);
    List<Piloto> findByEquipe(Equipe equipe);
    List<Piloto> findAll();
}
