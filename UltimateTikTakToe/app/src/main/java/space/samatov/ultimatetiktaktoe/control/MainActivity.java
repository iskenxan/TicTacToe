package space.samatov.ultimatetiktaktoe.control;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import java.util.Objects;

import space.samatov.ultimatetiktaktoe.R;

public class MainActivity extends AppCompatActivity {
    private String mDifficulty="Easy";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playButtonClick(View view){
        Intent intent=new Intent(this,PlayActivity.class);
        intent.putExtra("difficulty",mDifficulty);
        finish();
        startActivity(intent);
    }

    public void radioClick(View view){
        RadioButton button=(RadioButton)view;
        mDifficulty=((RadioButton) view).getText().toString();
    }
}
