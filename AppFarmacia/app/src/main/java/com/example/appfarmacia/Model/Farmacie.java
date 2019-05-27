package com.example.appfarmacia.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity (tableName="farmacie")
public class Farmacie {


   @PrimaryKey (autoGenerate=true)
   private long id;

    @ColumnInfo(name="codiceFarmacia")
    private String codiceFarmacia;

    @ColumnInfo(name="indirizzo")
    private String indirizzo;

    @ColumnInfo (name="descrizioneFarmacia")
    private String descrizioneFarmacia;

    @ColumnInfo(name="partitaIva")
    private String partitaIva;

    @ColumnInfo(name="cap")
    private String cap;

    @ColumnInfo(name="codiceComuneIstat")
    private String codiceComuneIstat;

    @ColumnInfo(name="descrizioneComune")
    private String descrizioneComune;

    @ColumnInfo(name="frazione")
    private String frazione;

    @ColumnInfo(name="codiceProvinciaIstat")
    private String codiceProvinciaIstat;

    @ColumnInfo(name="siglaProvincia")
    private String siglaProvincia;

    @ColumnInfo(name="descrizioneProvincia")
    private String descrizioneProvincia;

    @ColumnInfo(name="codiceRegione")
    private String codiceRegione;

    @ColumnInfo(name="descrizioneRegione")
    private String descrizioneRegione;

    @ColumnInfo(name="dataInizioValidita")
    private String dataInizioValidita;

    @ColumnInfo(name="dataFineValidita")
    private String dataFineValidita;

    @ColumnInfo(name="descrizioneTipologia")
    private String descrizioneTipologia;

    @ColumnInfo(name="codiceTipologia")
    private String codiceTipologia;

    @ColumnInfo(name="latitudine")
    private String latitudine;

    @ColumnInfo(name="longitudine")
    private String longitudine;

    @ColumnInfo(name="localize")
    private String localize;

    public Farmacie() {}



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCodiceFarmacia() {
        return codiceFarmacia;
    }

    public void setCodiceFarmacia(String codiceFarmacia) {
        this.codiceFarmacia = codiceFarmacia;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getDescrizioneFarmacia() {
        return descrizioneFarmacia;
    }

    public void setDescrizioneFarmacia(String descrizioneFarmacia) {
        this.descrizioneFarmacia = descrizioneFarmacia;
    }

    public String getPartitaIva() {
        return partitaIva;
    }

    public void setPartitaIva(String partitaIva) {
        this.partitaIva = partitaIva;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getCodiceComuneIstat() {
        return codiceComuneIstat;
    }

    public void setCodiceComuneIstat(String codiceComuneIstat) {
        this.codiceComuneIstat = codiceComuneIstat;
    }

    public String getDescrizioneComune() {
        return descrizioneComune;
    }

    public void setDescrizioneComune(String descrizioneComune) {
        this.descrizioneComune = descrizioneComune;
    }

    public String getFrazione() {
        return frazione;
    }

    public void setFrazione(String frazione) {
        this.frazione = frazione;
    }

    public String getCodiceProvinciaIstat() {
        return codiceProvinciaIstat;
    }

    public void setCodiceProvinciaIstat(String codiceProvinciaIstat) {
        this.codiceProvinciaIstat = codiceProvinciaIstat;
    }

    public String getSiglaProvincia() {
        return siglaProvincia;
    }

    public void setSiglaProvincia(String siglaProvincia) {
        this.siglaProvincia = siglaProvincia;
    }

    public String getDescrizioneProvincia() {
        return descrizioneProvincia;
    }

    public void setDescrizioneProvincia(String descrizioneProvincia) {
        this.descrizioneProvincia = descrizioneProvincia;
    }

    public String getCodiceRegione() {
        return codiceRegione;
    }

    public void setCodiceRegione(String codiceRegione) {
        this.codiceRegione = codiceRegione;
    }

    public String getDescrizioneRegione() {
        return descrizioneRegione;
    }

    public void setDescrizioneRegione(String descrizioneRegione) {
        this.descrizioneRegione = descrizioneRegione;
    }

    public String getDataInizioValidita() {
        return dataInizioValidita;
    }

    public void setDataInizioValidita(String dataInizioValidita) {
        this.dataInizioValidita = dataInizioValidita;
    }

    public String getDataFineValidita() {
        return dataFineValidita;
    }

    public void setDataFineValidita(String dataFineValidita) {
        this.dataFineValidita = dataFineValidita;
    }

    public String getDescrizioneTipologia() {
        return descrizioneTipologia;
    }

    public void setDescrizioneTipologia(String descrizioneTipologia) {
        this.descrizioneTipologia = descrizioneTipologia;
    }

    public String getCodiceTipologia() {
        return codiceTipologia;
    }

    public void setCodiceTipologia(String codiceTipologia) {
        this.codiceTipologia = codiceTipologia;
    }

    public String getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(String latitudine) {
        this.latitudine = latitudine;
    }

    public String getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(String longitudine) {
        this.longitudine = longitudine;
    }

    public String getLocalize() {
        return localize;
    }

    public void setLocalize(String localize) {
        this.localize = localize;
    }
}


