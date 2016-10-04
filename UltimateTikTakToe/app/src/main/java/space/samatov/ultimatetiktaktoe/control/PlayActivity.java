package space.samatov.ultimatetiktaktoe.control;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import space.samatov.ultimatetiktaktoe.R;
import space.samatov.ultimatetiktaktoe.model.AI;

public class PlayActivity extends AppCompatActivity {
    String mDifficulty;
    ImageView[] mCells = new ImageView[9];
    boolean mGameFinished = false;
    boolean playersTurn = true;
    String mGameResult = "";
    AI mAI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        Intent intent = getIntent();
        mDifficulty = intent.getStringExtra("difficulty");
        mCells[0] = (ImageView) findViewById(R.id.cell0);
        mCells[1] = (ImageView) findViewById(R.id.cell1);
        mCells[2] = (ImageView) findViewById(R.id.cell2);
        mCells[3] = (ImageView) findViewById(R.id.cell3);
        mCells[4] = (ImageView) findViewById(R.id.cell4);
        mCells[5] = (ImageView) findViewById(R.id.cell5);
        mCells[6] = (ImageView) findViewById(R.id.cell6);
        mCells[7] = (ImageView) findViewById(R.id.cell7);
        mCells[8] = (ImageView) findViewById(R.id.cell8);
        mAI = new AI(this);
    }

    public void OnCellClick(View view) {
        ImageView imageView=(ImageView)view;
        if(imageView.getContentDescription()==null) {
            Task task = new Task((ImageView) view);
            task.execute();
        }
    }

    public void ShowResults() {
        SystemClock.sleep(500);
        Intent intent = new Intent(PlayActivity.this, ResultsActivity.class);
        intent.putExtra("result", mGameResult);
        finish();
        startActivity(intent);
    }


    public void setImage(ImageView imageView, String sign) {
        if (imageView.getContentDescription() == null) {
            Drawable drawable;
            if (sign == "x") {
                drawable = getResources().getDrawable(R.drawable.x);
            } else {
                drawable = getResources().getDrawable(R.drawable.o);
            }

            imageView.setImageDrawable(drawable);
            imageView.setContentDescription(sign);
        }
    }

    public boolean cellsAvailable() {
        boolean available = false;
        for (int i = 0; i < 9; i++) {
            if (mCells[i].getDrawable() == null)
                available = true;
        }
        return available;
    }

    public String[] cellMap() {
        String[] cellPics = new String[9];
        for (int i = 0; i < 9; i++) {
            if (mCells[i].getContentDescription() == null)
                cellPics[i] = "0";
            else
                cellPics[i] = mCells[i].getContentDescription().toString();
        }
        return cellPics;
    }

    public int countEmptyCells() {
        int emptyCells = 0;
        for (int i = 0; i < 9; i++) {
            if (mCells[i].getContentDescription() == null)
                emptyCells++;
        }
        return emptyCells;
    }

    public class Task extends AsyncTask<Void, Void, Void> {
        ImageView mView;

        public Task(ImageView view) {
            mView = view;
        }

        @Override
        protected Void doInBackground(Void... params) {

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (playersTurn) {
                setImage(mView, "x");
                playersTurn = false;
            }

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (!playersTurn) {
                if (mAI.isWinner("x", cellMap())) {
                    mGameResult = "Congrats!You won!";
                    mGameFinished = true;
                    ShowResults();
                    return;
                }
                if (cellsAvailable()) {
                    SystemClock.sleep(1000);
                    int aiMove;
                    if(mDifficulty.equals("Easy"))
                     aiMove = mAI.makeMoveEasy();
                    else
                    aiMove=mAI.makeMoveMedium();
                    setImage(mCells[aiMove], "o");
                    playersTurn = true;
                    if (mAI.isWinner("o", cellMap())) {
                        mGameResult = "Sorry =/ Better luck next time!";
                        mGameFinished = true;
                        ShowResults();
                        return;
                    }
                }

                else {
                    mGameResult = "It's a draw!";
                    mGameFinished = true;
                    ShowResults();
                    return;
                }
            }
        }
    }
}


