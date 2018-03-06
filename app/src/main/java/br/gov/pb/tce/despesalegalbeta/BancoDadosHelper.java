package br.gov.pb.tce.despesalegalbeta;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

import java.io.ByteArrayOutputStream;

/**
 * Created by JoséHenrique on 01/03/2018.
 */

public class BancoDadosHelper extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "DespesaLegalBeta.db";
    private static final int VERSAO_BANCO = 1;
    private static final String DB_PATH = "/data/data/br.gov.pb.tce.despesalegalbeta/databases/";

    public static final String TABELA_FOTO = "tb_dadosFoto";
    public static final String COLUNA_ID = "_id";
    public static final String COLUNA_NOME_FOTO = "nome_foto";
    public static final String COLUNA_LATITUDE = "latitude";
    public static final String COLUNA_LONGITUDE = "longitude";
    public static final String COLUNA_ALTITUDE = "altitude";
    public static final String COLUNA_DATA_HORA = "data_hora";
    public static final String COLUNA_NUM_OBRA = "num_obra";
    public static final String COLUNA_NUM_MEDICAO = "num_medicao";
    public static final String COLUNA_ANGULO = "angulo";
    public static final String COLUNA_IMG_CRIP = "imagem";

    public BancoDadosHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABELA_FOTO+"("+
                COLUNA_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                COLUNA_NOME_FOTO+" TEXT NOT NULL,"+
                COLUNA_LATITUDE+" TEXT, "+
                COLUNA_LONGITUDE+" TEXT, "+
                COLUNA_ALTITUDE+" TEXT, "+
                COLUNA_DATA_HORA+" TEXT, "+
                COLUNA_ANGULO+" INTEGER, "+
                COLUNA_NUM_OBRA+" TEXT, "+
                COLUNA_NUM_MEDICAO+" TEXT, "+
                COLUNA_IMG_CRIP+" BLOB )" );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
