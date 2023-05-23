package com.theuz.application;

import com.theuz.model.dao.DaoFactory;
import com.theuz.model.dao.EquipeDao;
import com.theuz.model.entities.Equipe;

import java.util.Calendar;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        // Classe Main para testar os métodos de persistencia da classe Equipe

        EquipeDao ed = DaoFactory.createEquipe();
        Calendar calendar = Calendar.getInstance();

        // Insert Equipe
        calendar.set(1956, Calendar.JULY, 13);
        Equipe equipeOne = new Equipe(null, "Mclaren", new java.sql.Date(calendar.getTimeInMillis()));
        ed.insert(equipeOne);

        // Update Equipe
        calendar.set(2005, Calendar.APRIL, 31);
        Equipe equipeDois = new Equipe();
        equipeDois.setId(3);
        equipeDois.setDataFundacao(new java.sql.Date(calendar.getTimeInMillis()));
        ed.update(equipeDois);

        // Remove Equipe
        ed.remove(4);

        // FindById equipe
        Equipe find = ed.findById(2);
        System.out.println(find);

        // FindAll equipe
        List<Equipe> lista = ed.findAll();
        for (Equipe e : lista) {
            System.out.println(e);
        }
    }
}
