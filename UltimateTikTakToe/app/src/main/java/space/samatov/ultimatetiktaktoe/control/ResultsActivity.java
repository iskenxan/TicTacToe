package space.samatov.ultimatetiktaktoe.control;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import space.samatov.ultimatetiktaktoe.R;

public class ResultsActivity extends AppCompatActivity {
    TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        mTextView=(TextView) findViewById(R.id.resultsTextView);
        Intent intent=getIntent();
        String result= intent.getStringExtra("result");
        mTextView.setText(result);
    }

    public void playAgain(View view){
        Intent intent=new Intent(ResultsActivity.this,MainActivity.class);
        finish();
        startActivity(intent);
    }

    public void exit(View view)
    {
        finish();
    }


}
