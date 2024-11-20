package br.edu.fateczl.aulajogosoi20242n;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText etNumero;
    private Button btnJogar;
    private Button btnReinicio;
    private TextView tvResultado;
    private int valorGerado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etNumero = findViewById(R.id.etNumero);
        etNumero.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        btnJogar = findViewById(R.id.btnJogar);
        btnReinicio = findViewById(R.id.btnReinicio);
        btnReinicio.setVisibility(View.INVISIBLE);
        tvResultado = findViewById(R.id.tvResultado);
        tvResultado.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        valorGerado = (int)((Math.random() * 100) + 1);
        btnJogar.setOnClickListener(click -> jogar());
        Log.i("Valor", String.valueOf(valorGerado));

        btnReinicio.setOnClickListener(click -> reinicio());
    }

    private void jogar() {
        try {
            int entrada = Integer.parseInt(etNumero.getText().toString());
            if (entrada < valorGerado) {
                tvResultado.setText(R.string.maior);
            } else if (entrada > valorGerado) {
                tvResultado.setText(R.string.menor);
            } else {
                String res = getString(R.string.acertou) + " " + valorGerado;
                tvResultado.setText(res);
                btnReinicio.setVisibility(View.VISIBLE);
                etNumero.setVisibility(View.INVISIBLE);
                btnJogar.setVisibility(View.INVISIBLE);
                Toast.makeText(this, R.string.parabens, Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, R.string.erro_input, Toast.LENGTH_LONG).show();
        } finally {
            etNumero.setText("");
        }
    }

    private void reinicio() {
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
        this.finish();
    }
}