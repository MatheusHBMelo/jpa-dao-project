package com.theuz.application;

import com.theuz.model.dao.DaoFactory;
import com.theuz.model.dao.EquipeDao;
import com.theuz.model.entities.Equipe;

import java.util.Calendar;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        // Classe Main para testar os métodos de persistência da classe Equipe
        EquipeDao ed = DaoFactory.createEquipe();
        Calendar calendar = Calendar.getInstance();

        // Insert na tabela Equipe
        calendar.set(2005, Calendar.APRIL, 18);
        Equipe equipeOne = new Equipe(null, "Red Bull Racing", new java.sql.Date(calendar.getTimeInMillis()));
        ed.insert(equipeOne);

        // Update na tabela Equipe
        calendar.set(2005, Calendar.APRIL, 19);
        Equipe equipeDois = new Equipe();
        equipeDois.setId(3);
        equipeDois.setDataFundacao(new java.sql.Date(calendar.getTimeInMillis()));
        ed.update(equipeDois);

        // Remove na tabela Equipe
        ed.remove(4);

        // FindById nos registros da tabela Equipe
        Equipe find = ed.findById(2);
        System.out.println(find);

        // FindAll nos registros da tabela Equipe
        List<Equipe> lista = ed.findAll();
        for (Equipe e : lista) {
            System.out.println(e);
        }
    }
}
