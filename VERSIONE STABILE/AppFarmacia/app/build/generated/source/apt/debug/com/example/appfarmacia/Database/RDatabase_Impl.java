package com.example.appfarmacia.Database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import android.arch.persistence.room.util.TableInfo.ForeignKey;
import android.arch.persistence.room.util.TableInfo.Index;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public class RDatabase_Impl extends RDatabase {
  private volatile FarmacieDao _farmacieDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `farmacie` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `codiceFaarmacia` TEXT, `indirizzo` TEXT, `descrizioneFarmacia` TEXT, `partitaIva` TEXT, `cap` TEXT, `codiceComuneIstat` TEXT, `descrizioneComune` TEXT, `frazione` TEXT, `codiceProvinciaIstat` TEXT, `siglaProvincia` TEXT, `descrizioneProvincia` TEXT, `codiceRegione` TEXT, `descrizioneRegione` TEXT, `dataInizioValidita` TEXT, `dataFineValidita` TEXT, `descrizioneTipologia` TEXT, `codiceTipologia` TEXT, `latitudine` TEXT, `longitudine` TEXT, `localize` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"6b9b1e5281795ce39de0aa455e56475c\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `farmacie`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsFarmacie = new HashMap<String, TableInfo.Column>(21);
        _columnsFarmacie.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsFarmacie.put("codiceFaarmacia", new TableInfo.Column("codiceFaarmacia", "TEXT", false, 0));
        _columnsFarmacie.put("indirizzo", new TableInfo.Column("indirizzo", "TEXT", false, 0));
        _columnsFarmacie.put("descrizioneFarmacia", new TableInfo.Column("descrizioneFarmacia", "TEXT", false, 0));
        _columnsFarmacie.put("partitaIva", new TableInfo.Column("partitaIva", "TEXT", false, 0));
        _columnsFarmacie.put("cap", new TableInfo.Column("cap", "TEXT", false, 0));
        _columnsFarmacie.put("codiceComuneIstat", new TableInfo.Column("codiceComuneIstat", "TEXT", false, 0));
        _columnsFarmacie.put("descrizioneComune", new TableInfo.Column("descrizioneComune", "TEXT", false, 0));
        _columnsFarmacie.put("frazione", new TableInfo.Column("frazione", "TEXT", false, 0));
        _columnsFarmacie.put("codiceProvinciaIstat", new TableInfo.Column("codiceProvinciaIstat", "TEXT", false, 0));
        _columnsFarmacie.put("siglaProvincia", new TableInfo.Column("siglaProvincia", "TEXT", false, 0));
        _columnsFarmacie.put("descrizioneProvincia", new TableInfo.Column("descrizioneProvincia", "TEXT", false, 0));
        _columnsFarmacie.put("codiceRegione", new TableInfo.Column("codiceRegione", "TEXT", false, 0));
        _columnsFarmacie.put("descrizioneRegione", new TableInfo.Column("descrizioneRegione", "TEXT", false, 0));
        _columnsFarmacie.put("dataInizioValidita", new TableInfo.Column("dataInizioValidita", "TEXT", false, 0));
        _columnsFarmacie.put("dataFineValidita", new TableInfo.Column("dataFineValidita", "TEXT", false, 0));
        _columnsFarmacie.put("descrizioneTipologia", new TableInfo.Column("descrizioneTipologia", "TEXT", false, 0));
        _columnsFarmacie.put("codiceTipologia", new TableInfo.Column("codiceTipologia", "TEXT", false, 0));
        _columnsFarmacie.put("latitudine", new TableInfo.Column("latitudine", "TEXT", false, 0));
        _columnsFarmacie.put("longitudine", new TableInfo.Column("longitudine", "TEXT", false, 0));
        _columnsFarmacie.put("localize", new TableInfo.Column("localize", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFarmacie = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesFarmacie = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoFarmacie = new TableInfo("farmacie", _columnsFarmacie, _foreignKeysFarmacie, _indicesFarmacie);
        final TableInfo _existingFarmacie = TableInfo.read(_db, "farmacie");
        if (! _infoFarmacie.equals(_existingFarmacie)) {
          throw new IllegalStateException("Migration didn't properly handle farmacie(com.example.appfarmacia.Model.Farmacie).\n"
                  + " Expected:\n" + _infoFarmacie + "\n"
                  + " Found:\n" + _existingFarmacie);
        }
      }
    }, "6b9b1e5281795ce39de0aa455e56475c", "ed0e90d131cd935ec4dddb816b4c59ce");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "farmacie");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `farmacie`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public FarmacieDao getFarmacieDao() {
    if (_farmacieDao != null) {
      return _farmacieDao;
    } else {
      synchronized(this) {
        if(_farmacieDao == null) {
          _farmacieDao = new FarmacieDao_Impl(this);
        }
        return _farmacieDao;
      }
    }
  }
}
