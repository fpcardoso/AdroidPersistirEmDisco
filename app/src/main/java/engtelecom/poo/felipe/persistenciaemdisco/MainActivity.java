package engtelecom.poo.felipe.persistenciaemdisco;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private SharedPreferences mPreferences;
    private static final String mSharedPrefFile = "poo.engetelecom.contador";
    private final String COUNTER_KEY = "contador";
    private final String COLOR_KEY = "cor";
    private TextView textView;
    public Button btBlue;
    public Button btGreen;
    public Button btRed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPreferences = getSharedPreferences(mSharedPrefFile,MODE_PRIVATE);

        int contador = mPreferences.getInt(COUNTER_KEY,0);

        textView = (TextView) findViewById(R.id.textView);
        textView.setText(String.format("%s",contador));

        btBlue = (Button) findViewById(R.id.btnBlue);
        btBlue.setOnClickListener(this);
        btGreen = (Button) findViewById(R.id.btnGreen);
        btGreen.setOnClickListener(this);
        btRed = (Button) findViewById(R.id.btnRed);
        btRed.setOnClickListener(this);
    }

    public void incrementar(View view){
        TextView textView = (TextView) findViewById(R.id.textView);
        int resultado = Integer.parseInt(textView.getText().toString());
        resultado++;
        textView.setText(String.format("%s",resultado));
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(COUNTER_KEY,Integer.parseInt(textView.getText().toString()));
        editor.putInt(COLOR_KEY,textView.getCurrentTextColor());
        editor.apply();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
          case R.id.btnBlue : textView.setTextColor(((ColorDrawable) btBlue.getBackground()).getColor());
          break;
          case R.id.btnGreen : textView.setTextColor(((ColorDrawable) btGreen.getBackground()).getColor());
          break;
          case R.id.btnRed : textView.setTextColor(((ColorDrawable) btRed.getBackground()).getColor());
          break;
        }
    }
}
