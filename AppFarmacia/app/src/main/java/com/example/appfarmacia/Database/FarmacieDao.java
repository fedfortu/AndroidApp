package com.example.appfarmacia.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.appfarmacia.Model.Farmacie;

import java.util.List;

@Dao
public interface FarmacieDao {

    @Insert
    public void save(Farmacie farmacie);


}
