package br.gov.pb.tce.despesalegalbeta;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

/**
 * Created by JoséHenrique on 01/03/2018.
 */

public class Foto implements Serializable {

    public long id;
    public String nomeFoto;
    public String latitudeFoto;
    public String longitudeFoto;
    public String altitudeFoto;
    public String dataFoto;
    public int numObra;
    public int numMedicao;
    public int angulo;
    public ByteArrayOutputStream imagem;

    public Foto() {
    }



    public Foto(String nomeFoto){
        this.nomeFoto = nomeFoto;
    }
    public Foto(long id,String nomeFoto){
        this.id = id;
        this.nomeFoto = nomeFoto;
    }


    public Foto(long id, String nomeFoto,String latitudeFoto,
                String longitudeFoto, String altitudeFoto,
                String dataFoto, int numObra, int numMedicao, int angulo){
        this.id = id;
        this.nomeFoto = nomeFoto;
        this.latitudeFoto = latitudeFoto;
        this.longitudeFoto = longitudeFoto;
        this.altitudeFoto = altitudeFoto;
        this.dataFoto = dataFoto;
        this.numObra = numObra;
        this.numMedicao = numMedicao;
        this.angulo = angulo;
    }



    @Override
    public String toString(){
        return nomeFoto;
    }



}
