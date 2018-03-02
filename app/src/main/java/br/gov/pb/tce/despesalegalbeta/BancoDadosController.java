package br.gov.pb.tce.despesalegalbeta;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JoséHenrique on 01/03/2018.
 */

public class BancoDadosController {

    private BancoDadosHelper helper;

    public BancoDadosController(Context ctx){
        helper = new BancoDadosHelper(ctx);
    }

    private long inserir(Foto foto){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(BancoDadosHelper.COLUNA_NOME_FOTO,foto.nomeFoto);

        long id = db.insert(BancoDadosHelper.TABELA_FOTO,null,cv);
        if(id != -1){
            foto.id = id;
        }

        db.close();
        return id;
    }

    private int atualizar(Foto foto){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(BancoDadosHelper.COLUNA_NOME_FOTO,foto.nomeFoto);

        int linhasAfetadas = db.update(BancoDadosHelper.TABELA_FOTO,cv,
                BancoDadosHelper.COLUNA_ID +" = ?",new String[]{String.valueOf(foto.id)});

        db.close();
        return linhasAfetadas;
    }

    public void salvar(Foto foto){
        if(foto.id == 0){
            inserir(foto);
        }else{
            atualizar(foto);
        }
    }

    public int excluir(Foto foto){
        SQLiteDatabase db = helper.getWritableDatabase();

        int linhasAfetadas = db.delete(BancoDadosHelper.TABELA_FOTO,
                BancoDadosHelper.COLUNA_ID +" = ?",new String[]{String.valueOf(foto.id)});
        db.close();
        return linhasAfetadas;
    }
    public List<Foto> buscarFoto(String filtro){
        SQLiteDatabase db = helper.getReadableDatabase();

        String sql = "SELECT * FROM "+ BancoDadosHelper.TABELA_FOTO;
        String[] argumentos = null;
        if(filtro != null){
            sql += " WHERE "+ BancoDadosHelper.COLUNA_NOME_FOTO + "LIKE ?";
            argumentos = new String[]{filtro};
        }
        sql += " ORDER BY "+ BancoDadosHelper.COLUNA_NOME_FOTO;

        Cursor cursor = db.rawQuery(sql,argumentos);

        List<Foto> fotos = new ArrayList<Foto>();
        while (cursor.moveToNext()){
            long id = cursor.getLong(cursor.getColumnIndex(BancoDadosHelper.COLUNA_ID));
            String nome_foto = cursor.getString(cursor.getColumnIndex(BancoDadosHelper.COLUNA_NOME_FOTO));

            Foto foto = new Foto(nome_foto);
            fotos.add(foto);
        }
        cursor.close();
        db.close();
        return fotos;
    }


}
