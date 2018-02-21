package br.gov.pb.tce.despesalegalbeta;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    private Button botaoLogin;
    private EditText campoEmail;
    private EditText campoSenha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
        }else{

        }

        campoEmail = (EditText) findViewById(R.id.emailID);
        campoSenha = (EditText) findViewById(R.id.senhaID);
        botaoLogin = (Button) findViewById(R.id.btLoginID);

        final String email = "@gmail.com";

        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if((campoEmail.getText().toString().equals(""))||(campoSenha.getText().toString().equals(""))){
                    Toast.makeText(LoginActivity.this, "VERIFIQUE OS CAMPOS DIGITADOS", Toast.LENGTH_SHORT).show();
                }else{
                    firebaseAuth.signInWithEmailAndPassword(campoEmail.getText().toString()+email,campoSenha.getText().toString()).
                            addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()){

                                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                                        Log.i("Signin","TESTE OK");

                                    }else{
                                        Log.i("Signin","TESTE ERRO");
                                        Toast.makeText(LoginActivity.this,"E-mail ou Senha incorretos",Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
                }


        });


    }
}
