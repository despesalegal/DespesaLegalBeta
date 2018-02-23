package br.gov.pb.tce.despesalegalbeta;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //cria variaveis
    private FirebaseAuth firebaseAuth;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView botaoSair, photo;
    private Button botaoCamera, btPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instancia autenticador
        firebaseAuth = FirebaseAuth.getInstance();
        photo = (ImageView) findViewById(R.id.photo);
        botaoSair= (ImageView) findViewById(R.id.btSairID);
        botaoCamera = (Button) findViewById(R.id.btCameraID);

        botaoSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));       //volta pra tela de login
                firebaseAuth.signOut();                                                             //desvalida a sessao
            }
        });

        botaoCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            SaveImage (imageBitmap);
        }
    }

    private static String SaveImage(Bitmap imageBitmap) {
        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
        File myDir = new File(root + "/Despesa Legal");
        if (!myDir.exists())
            myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt();
        String fname = "Image-" + n + ".jpg";
        File file = new File(myDir, fname);
        if(file.exists()) file.delete();
        try{
            FileOutputStream out = new FileOutputStream(file);
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (Throwable e){
            e.printStackTrace();
        }
        return  file.getAbsolutePath();
    }


}
