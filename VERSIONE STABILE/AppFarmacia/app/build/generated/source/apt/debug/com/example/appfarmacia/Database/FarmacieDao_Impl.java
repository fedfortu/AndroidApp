package com.example.appfarmacia.Database;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import com.example.appfarmacia.Model.Farmacie;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class FarmacieDao_Impl implements FarmacieDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfFarmacie;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfFarmacie;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public FarmacieDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFarmacie = new EntityInsertionAdapter<Farmacie>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `farmacie`(`id`,`codiceFaarmacia`,`indirizzo`,`descrizioneFarmacia`,`partitaIva`,`cap`,`codiceComuneIstat`,`descrizioneComune`,`frazione`,`codiceProvinciaIstat`,`siglaProvincia`,`descrizioneProvincia`,`codiceRegione`,`descrizioneRegione`,`dataInizioValidita`,`dataFineValidita`,`descrizioneTipologia`,`codiceTipologia`,`latitudine`,`longitudine`,`localize`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Farmacie value) {
        stmt.bindLong(1, value.getId());
        if (value.getCodiceFarmacia() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getCodiceFarmacia());
        }
        if (value.getIndirizzo() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getIndirizzo());
        }
        if (value.getDescrizioneFarmacia() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDescrizioneFarmacia());
        }
        if (value.getPartitaIva() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getPartitaIva());
        }
        if (value.getCap() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getCap());
        }
        if (value.getCodiceComuneIstat() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getCodiceComuneIstat());
        }
        if (value.getDescrizioneComune() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getDescrizioneComune());
        }
        if (value.getFrazione() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getFrazione());
        }
        if (value.getCodiceProvinciaIstat() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getCodiceProvinciaIstat());
        }
        if (value.getSiglaProvincia() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getSiglaProvincia());
        }
        if (value.getDescrizioneProvincia() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getDescrizioneProvincia());
        }
        if (value.getCodiceRegione() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.getCodiceRegione());
        }
        if (value.getDescrizioneRegione() == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindString(14, value.getDescrizioneRegione());
        }
        if (value.getDataInizioValidita() == null) {
          stmt.bindNull(15);
        } else {
          stmt.bindString(15, value.getDataInizioValidita());
        }
        if (value.getDataFineValidita() == null) {
          stmt.bindNull(16);
        } else {
          stmt.bindString(16, value.getDataFineValidita());
        }
        if (value.getDescrizioneTipologia() == null) {
          stmt.bindNull(17);
        } else {
          stmt.bindString(17, value.getDescrizioneTipologia());
        }
        if (value.getCodiceTipologia() == null) {
          stmt.bindNull(18);
        } else {
          stmt.bindString(18, value.getCodiceTipologia());
        }
        if (value.getLatitudine() == null) {
          stmt.bindNull(19);
        } else {
          stmt.bindString(19, value.getLatitudine());
        }
        if (value.getLongitudine() == null) {
          stmt.bindNull(20);
        } else {
          stmt.bindString(20, value.getLongitudine());
        }
        if (value.getLocalize() == null) {
          stmt.bindNull(21);
        } else {
          stmt.bindString(21, value.getLocalize());
        }
      }
    };
    this.__deletionAdapterOfFarmacie = new EntityDeletionOrUpdateAdapter<Farmacie>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `farmacie` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Farmacie value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM farmacie";
        return _query;
      }
    };
  }

  @Override
  public void save(Farmacie farmacie) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfFarmacie.insert(farmacie);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(Farmacie farmacie) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfFarmacie.handle(farmacie);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAll() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public List<Farmacie> getAllFarmacie() {
    final String _sql = "SELECT * FROM farmacie";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfCodiceFarmacia = _cursor.getColumnIndexOrThrow("codiceFaarmacia");
      final int _cursorIndexOfIndirizzo = _cursor.getColumnIndexOrThrow("indirizzo");
      final int _cursorIndexOfDescrizioneFarmacia = _cursor.getColumnIndexOrThrow("descrizioneFarmacia");
      final int _cursorIndexOfPartitaIva = _cursor.getColumnIndexOrThrow("partitaIva");
      final int _cursorIndexOfCap = _cursor.getColumnIndexOrThrow("cap");
      final int _cursorIndexOfCodiceComuneIstat = _cursor.getColumnIndexOrThrow("codiceComuneIstat");
      final int _cursorIndexOfDescrizioneComune = _cursor.getColumnIndexOrThrow("descrizioneComune");
      final int _cursorIndexOfFrazione = _cursor.getColumnIndexOrThrow("frazione");
      final int _cursorIndexOfCodiceProvinciaIstat = _cursor.getColumnIndexOrThrow("codiceProvinciaIstat");
      final int _cursorIndexOfSiglaProvincia = _cursor.getColumnIndexOrThrow("siglaProvincia");
      final int _cursorIndexOfDescrizioneProvincia = _cursor.getColumnIndexOrThrow("descrizioneProvincia");
      final int _cursorIndexOfCodiceRegione = _cursor.getColumnIndexOrThrow("codiceRegione");
      final int _cursorIndexOfDescrizioneRegione = _cursor.getColumnIndexOrThrow("descrizioneRegione");
      final int _cursorIndexOfDataInizioValidita = _cursor.getColumnIndexOrThrow("dataInizioValidita");
      final int _cursorIndexOfDataFineValidita = _cursor.getColumnIndexOrThrow("dataFineValidita");
      final int _cursorIndexOfDescrizioneTipologia = _cursor.getColumnIndexOrThrow("descrizioneTipologia");
      final int _cursorIndexOfCodiceTipologia = _cursor.getColumnIndexOrThrow("codiceTipologia");
      final int _cursorIndexOfLatitudine = _cursor.getColumnIndexOrThrow("latitudine");
      final int _cursorIndexOfLongitudine = _cursor.getColumnIndexOrThrow("longitudine");
      final int _cursorIndexOfLocalize = _cursor.getColumnIndexOrThrow("localize");
      final List<Farmacie> _result = new ArrayList<Farmacie>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Farmacie _item;
        _item = new Farmacie();
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpCodiceFarmacia;
        _tmpCodiceFarmacia = _cursor.getString(_cursorIndexOfCodiceFarmacia);
        _item.setCodiceFarmacia(_tmpCodiceFarmacia);
        final String _tmpIndirizzo;
        _tmpIndirizzo = _cursor.getString(_cursorIndexOfIndirizzo);
        _item.setIndirizzo(_tmpIndirizzo);
        final String _tmpDescrizioneFarmacia;
        _tmpDescrizioneFarmacia = _cursor.getString(_cursorIndexOfDescrizioneFarmacia);
        _item.setDescrizioneFarmacia(_tmpDescrizioneFarmacia);
        final String _tmpPartitaIva;
        _tmpPartitaIva = _cursor.getString(_cursorIndexOfPartitaIva);
        _item.setPartitaIva(_tmpPartitaIva);
        final String _tmpCap;
        _tmpCap = _cursor.getString(_cursorIndexOfCap);
        _item.setCap(_tmpCap);
        final String _tmpCodiceComuneIstat;
        _tmpCodiceComuneIstat = _cursor.getString(_cursorIndexOfCodiceComuneIstat);
        _item.setCodiceComuneIstat(_tmpCodiceComuneIstat);
        final String _tmpDescrizioneComune;
        _tmpDescrizioneComune = _cursor.getString(_cursorIndexOfDescrizioneComune);
        _item.setDescrizioneComune(_tmpDescrizioneComune);
        final String _tmpFrazione;
        _tmpFrazione = _cursor.getString(_cursorIndexOfFrazione);
        _item.setFrazione(_tmpFrazione);
        final String _tmpCodiceProvinciaIstat;
        _tmpCodiceProvinciaIstat = _cursor.getString(_cursorIndexOfCodiceProvinciaIstat);
        _item.setCodiceProvinciaIstat(_tmpCodiceProvinciaIstat);
        final String _tmpSiglaProvincia;
        _tmpSiglaProvincia = _cursor.getString(_cursorIndexOfSiglaProvincia);
        _item.setSiglaProvincia(_tmpSiglaProvincia);
        final String _tmpDescrizioneProvincia;
        _tmpDescrizioneProvincia = _cursor.getString(_cursorIndexOfDescrizioneProvincia);
        _item.setDescrizioneProvincia(_tmpDescrizioneProvincia);
        final String _tmpCodiceRegione;
        _tmpCodiceRegione = _cursor.getString(_cursorIndexOfCodiceRegione);
        _item.setCodiceRegione(_tmpCodiceRegione);
        final String _tmpDescrizioneRegione;
        _tmpDescrizioneRegione = _cursor.getString(_cursorIndexOfDescrizioneRegione);
        _item.setDescrizioneRegione(_tmpDescrizioneRegione);
        final String _tmpDataInizioValidita;
        _tmpDataInizioValidita = _cursor.getString(_cursorIndexOfDataInizioValidita);
        _item.setDataInizioValidita(_tmpDataInizioValidita);
        final String _tmpDataFineValidita;
        _tmpDataFineValidita = _cursor.getString(_cursorIndexOfDataFineValidita);
        _item.setDataFineValidita(_tmpDataFineValidita);
        final String _tmpDescrizioneTipologia;
        _tmpDescrizioneTipologia = _cursor.getString(_cursorIndexOfDescrizioneTipologia);
        _item.setDescrizioneTipologia(_tmpDescrizioneTipologia);
        final String _tmpCodiceTipologia;
        _tmpCodiceTipologia = _cursor.getString(_cursorIndexOfCodiceTipologia);
        _item.setCodiceTipologia(_tmpCodiceTipologia);
        final String _tmpLatitudine;
        _tmpLatitudine = _cursor.getString(_cursorIndexOfLatitudine);
        _item.setLatitudine(_tmpLatitudine);
        final String _tmpLongitudine;
        _tmpLongitudine = _cursor.getString(_cursorIndexOfLongitudine);
        _item.setLongitudine(_tmpLongitudine);
        final String _tmpLocalize;
        _tmpLocalize = _cursor.getString(_cursorIndexOfLocalize);
        _item.setLocalize(_tmpLocalize);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Farmacie> findFarmacieByComune(long id) {
    final String _sql = "SELECT * FROM farmacie WHERE id=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfCodiceFarmacia = _cursor.getColumnIndexOrThrow("codiceFaarmacia");
      final int _cursorIndexOfIndirizzo = _cursor.getColumnIndexOrThrow("indirizzo");
      final int _cursorIndexOfDescrizioneFarmacia = _cursor.getColumnIndexOrThrow("descrizioneFarmacia");
      final int _cursorIndexOfPartitaIva = _cursor.getColumnIndexOrThrow("partitaIva");
      final int _cursorIndexOfCap = _cursor.getColumnIndexOrThrow("cap");
      final int _cursorIndexOfCodiceComuneIstat = _cursor.getColumnIndexOrThrow("codiceComuneIstat");
      final int _cursorIndexOfDescrizioneComune = _cursor.getColumnIndexOrThrow("descrizioneComune");
      final int _cursorIndexOfFrazione = _cursor.getColumnIndexOrThrow("frazione");
      final int _cursorIndexOfCodiceProvinciaIstat = _cursor.getColumnIndexOrThrow("codiceProvinciaIstat");
      final int _cursorIndexOfSiglaProvincia = _cursor.getColumnIndexOrThrow("siglaProvincia");
      final int _cursorIndexOfDescrizioneProvincia = _cursor.getColumnIndexOrThrow("descrizioneProvincia");
      final int _cursorIndexOfCodiceRegione = _cursor.getColumnIndexOrThrow("codiceRegione");
      final int _cursorIndexOfDescrizioneRegione = _cursor.getColumnIndexOrThrow("descrizioneRegione");
      final int _cursorIndexOfDataInizioValidita = _cursor.getColumnIndexOrThrow("dataInizioValidita");
      final int _cursorIndexOfDataFineValidita = _cursor.getColumnIndexOrThrow("dataFineValidita");
      final int _cursorIndexOfDescrizioneTipologia = _cursor.getColumnIndexOrThrow("descrizioneTipologia");
      final int _cursorIndexOfCodiceTipologia = _cursor.getColumnIndexOrThrow("codiceTipologia");
      final int _cursorIndexOfLatitudine = _cursor.getColumnIndexOrThrow("latitudine");
      final int _cursorIndexOfLongitudine = _cursor.getColumnIndexOrThrow("longitudine");
      final int _cursorIndexOfLocalize = _cursor.getColumnIndexOrThrow("localize");
      final List<Farmacie> _result = new ArrayList<Farmacie>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Farmacie _item;
        _item = new Farmacie();
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpCodiceFarmacia;
        _tmpCodiceFarmacia = _cursor.getString(_cursorIndexOfCodiceFarmacia);
        _item.setCodiceFarmacia(_tmpCodiceFarmacia);
        final String _tmpIndirizzo;
        _tmpIndirizzo = _cursor.getString(_cursorIndexOfIndirizzo);
        _item.setIndirizzo(_tmpIndirizzo);
        final String _tmpDescrizioneFarmacia;
        _tmpDescrizioneFarmacia = _cursor.getString(_cursorIndexOfDescrizioneFarmacia);
        _item.setDescrizioneFarmacia(_tmpDescrizioneFarmacia);
        final String _tmpPartitaIva;
        _tmpPartitaIva = _cursor.getString(_cursorIndexOfPartitaIva);
        _item.setPartitaIva(_tmpPartitaIva);
        final String _tmpCap;
        _tmpCap = _cursor.getString(_cursorIndexOfCap);
        _item.setCap(_tmpCap);
        final String _tmpCodiceComuneIstat;
        _tmpCodiceComuneIstat = _cursor.getString(_cursorIndexOfCodiceComuneIstat);
        _item.setCodiceComuneIstat(_tmpCodiceComuneIstat);
        final String _tmpDescrizioneComune;
        _tmpDescrizioneComune = _cursor.getString(_cursorIndexOfDescrizioneComune);
        _item.setDescrizioneComune(_tmpDescrizioneComune);
        final String _tmpFrazione;
        _tmpFrazione = _cursor.getString(_cursorIndexOfFrazione);
        _item.setFrazione(_tmpFrazione);
        final String _tmpCodiceProvinciaIstat;
        _tmpCodiceProvinciaIstat = _cursor.getString(_cursorIndexOfCodiceProvinciaIstat);
        _item.setCodiceProvinciaIstat(_tmpCodiceProvinciaIstat);
        final String _tmpSiglaProvincia;
        _tmpSiglaProvincia = _cursor.getString(_cursorIndexOfSiglaProvincia);
        _item.setSiglaProvincia(_tmpSiglaProvincia);
        final String _tmpDescrizioneProvincia;
        _tmpDescrizioneProvincia = _cursor.getString(_cursorIndexOfDescrizioneProvincia);
        _item.setDescrizioneProvincia(_tmpDescrizioneProvincia);
        final String _tmpCodiceRegione;
        _tmpCodiceRegione = _cursor.getString(_cursorIndexOfCodiceRegione);
        _item.setCodiceRegione(_tmpCodiceRegione);
        final String _tmpDescrizioneRegione;
        _tmpDescrizioneRegione = _cursor.getString(_cursorIndexOfDescrizioneRegione);
        _item.setDescrizioneRegione(_tmpDescrizioneRegione);
        final String _tmpDataInizioValidita;
        _tmpDataInizioValidita = _cursor.getString(_cursorIndexOfDataInizioValidita);
        _item.setDataInizioValidita(_tmpDataInizioValidita);
        final String _tmpDataFineValidita;
        _tmpDataFineValidita = _cursor.getString(_cursorIndexOfDataFineValidita);
        _item.setDataFineValidita(_tmpDataFineValidita);
        final String _tmpDescrizioneTipologia;
        _tmpDescrizioneTipologia = _cursor.getString(_cursorIndexOfDescrizioneTipologia);
        _item.setDescrizioneTipologia(_tmpDescrizioneTipologia);
        final String _tmpCodiceTipologia;
        _tmpCodiceTipologia = _cursor.getString(_cursorIndexOfCodiceTipologia);
        _item.setCodiceTipologia(_tmpCodiceTipologia);
        final String _tmpLatitudine;
        _tmpLatitudine = _cursor.getString(_cursorIndexOfLatitudine);
        _item.setLatitudine(_tmpLatitudine);
        final String _tmpLongitudine;
        _tmpLongitudine = _cursor.getString(_cursorIndexOfLongitudine);
        _item.setLongitudine(_tmpLongitudine);
        final String _tmpLocalize;
        _tmpLocalize = _cursor.getString(_cursorIndexOfLocalize);
        _item.setLocalize(_tmpLocalize);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
