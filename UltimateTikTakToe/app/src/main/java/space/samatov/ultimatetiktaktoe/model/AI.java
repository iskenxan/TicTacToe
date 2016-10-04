package space.samatov.ultimatetiktaktoe.model;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.util.LinkedList;
import java.util.Random;
import java.util.Vector;

import space.samatov.ultimatetiktaktoe.control.PlayActivity;

public class AI extends Player {

    private Random mRandom;
    public String mSign="o";
    PlayActivity GM;
    public AI(PlayActivity gm)
    {
        GM=gm;
        mRandom=new Random();
    }

    public int makeMoveRandom(){
            int index=mRandom.nextInt(9);
            while(!isEmpty(index,GM.cellMap()))
            {
                index=mRandom.nextInt(9);
            }
            return index;
    }

    private boolean isEmpty(int index,String[] cells) {
        return (cells[index]=="0");
    }

    public int makeMoveEasy(){
        LinkedList winningCells=CheckBoard(mSign, GM.cellMap());
        if(winningCells.size()>0)
            return (int)winningCells.getFirst();
        return makeMoveRandom();
    }

    public int makeMoveMedium()
    {
        LinkedList winningCells=CheckBoard(mSign, GM.cellMap());
        if(winningCells.size()>0)
            return (int)winningCells.getFirst();
        winningCells=CheckBoard("x",GM.cellMap());
        if(winningCells.size()>0)
            return (int)winningCells.getFirst();

        //Actual logic here
        int bestMove=makeMoveRandom();
        int winningPossibilities;
        int highestNumberOfWinningPossibilieties=0;
        String[] cells;
        String[] checkedCells=GM.cellMap();
        for (int i=0;i<9;i++){
            cells=GM.cellMap();
            if(isEmpty(i,checkedCells))
            {
                cells[i]=mSign;
                checkedCells[i]=mSign;
                if(CheckBoard("x",copy(cells)).size()>0)
                    continue;
                for (int x=0;x<9;x++){
                    if(isEmpty(x,cells)){
                        cells[x]="x";
                        LinkedList winnerCells=CheckBoard(mSign,copy(cells));
                        if(winnerCells.size()>highestNumberOfWinningPossibilieties)
                        {
                            highestNumberOfWinningPossibilieties=winnerCells.size();
                            bestMove=i;
                        }
                        cells[x]="0";
                    }
                }
            }
        }
        return bestMove;
    }


    public  String[] copy(String[] original){
        String[] copy=new String[9];
        for (int i=0;i<9;i++)
            copy[i]=original[i];
        return copy;
    }

    public LinkedList CheckBoard(String sign,String[] cellsCopy)
    {
        LinkedList winningCells=new LinkedList();
        int currentIndex=0;
        for (int i=0;i<9;i++){
            if(isEmpty(i,GM.cellMap())){
                cellsCopy[i]=sign;
                if(isWinner(sign,cellsCopy)){
                    winningCells.addLast(i);
                    currentIndex++;
                }
                cellsCopy[i]="0";
            }
        }
        return winningCells;
    }

}
