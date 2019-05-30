package com.example.appfarmacia.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.appfarmacia.Model.Farmacie;

import java.util.List;

@Dao
public interface FarmacieDao {

    @Insert
    public void save(Farmacie farmacie);

    @Delete
    public void delete(Farmacie farmacie);

    @Query("DELETE FROM farmacie")
    public void deleteAll();

    @Query("SELECT * FROM farmacie")
    public List<Farmacie> getAllFarmacie();

    @Query("SELECT * FROM farmacie WHERE descrizioneComune LIKE :nomeComune")
    public abstract List<Farmacie> findFarmacieByComune(String nomeComune);


}
