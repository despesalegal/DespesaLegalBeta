package br.gov.pb.tce.despesalegalbeta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    //cria variaveis
    private FirebaseAuth firebaseAuth;

    private ImageView botaoSair;
    private Button botaoCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instancia autenticador
        firebaseAuth = FirebaseAuth.getInstance();

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

            }
        });




    }
}
