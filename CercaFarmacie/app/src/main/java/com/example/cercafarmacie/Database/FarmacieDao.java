package com.example.cercafarmacie.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.cercafarmacie.Model.Farmacia;

import java.util.List;

@Dao
public interface FarmacieDao {

    @Insert
    public void save(Farmacia farmacia);

    @Delete
    public void delete(Farmacia farmacia);

    @Query("DELETE FROM farmacie")
    public void deleteAll();

    @Query("SELECT * FROM farmacie")
    public List<Farmacia> getAllFarmacie();

    @Query("SELECT * FROM farmacie WHERE descrizioneComune LIKE :nomeComune")
    public abstract List<Farmacia> findFarmacieByComune(String nomeComune);


}