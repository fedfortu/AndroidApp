package com.example.appfarmacia.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RoomWarnings;

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

    @Query("SELECT * FROM farmacie ")
    public List<Farmacie> getAllFarmacie();

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM farmacie WHERE id=:id")
    public List<Farmacie> findFarmacieByComune(long id);

}
