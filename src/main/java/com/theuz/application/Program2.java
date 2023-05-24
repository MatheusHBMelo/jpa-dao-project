package com.theuz.application;

import com.theuz.model.dao.DaoFactory;
import com.theuz.model.dao.EquipeDao;
import com.theuz.model.dao.PilotoDao;
import com.theuz.model.entities.Equipe;
import com.theuz.model.entities.Piloto;

import java.util.Calendar;
import java.util.List;

public class Program2 {
    public static void main(String[] args) {
        // Classe Main para testar os métodos de persistencia da classe Piloto
        PilotoDao pd = DaoFactory.createPiloto();
        EquipeDao ed = DaoFactory.createEquipe();
        Calendar cal = Calendar.getInstance();

        // Insert na tabela piloto
        cal.set(1990, Calendar.JANUARY, 26);
        Equipe equipe = new Equipe(1, null, null);
        Piloto piloto = new Piloto(null, "Sergio Perez", cal.getTime(), equipe);
        pd.insert(piloto);

        // Update na tabela piloto
        cal.set(2003, Calendar.OCTOBER, 11);
        Piloto piloto2 = new Piloto();
        piloto.setId(3);
        piloto.setDataNascimento(new java.sql.Date(cal.getTimeInMillis()));
        pd.update(piloto2);

        // Remove em um registro da tabela piloto
        pd.remove(2);

        // FindById nos registros da tabela piloto
        Piloto piloto3 = pd.findById(1);
        System.out.println(piloto3);

        // FindAll nos registros da tabela piloto
        List<Piloto> lista = pd.findAll();
        for (Piloto piloto4 : lista) {
            System.out.println(piloto4);
        }

        // FindByEquipe nos registros da tabela piloto
        Equipe equipe2 = ed.findById(1);
        List<Piloto> list = pd.findByEquipe(equipe2);
        for (Piloto piloto5 : list) {
            System.out.println(piloto5);
        }
    }
}
