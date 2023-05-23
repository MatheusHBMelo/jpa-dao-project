package com.theuz.model.dao;

import com.theuz.model.dao.impl.EquipeDaoImpl;
import com.theuz.model.dao.impl.PilotoDaoImpl;
import com.theuz.model.db.DB;

public class DaoFactory {
    public static EquipeDao createEquipe(){
        return new EquipeDaoImpl(DB.getConnection());
    }
    public static PilotoDao createPiloto(){
     return new PilotoDaoImpl(DB.getConnection());
    }
}
