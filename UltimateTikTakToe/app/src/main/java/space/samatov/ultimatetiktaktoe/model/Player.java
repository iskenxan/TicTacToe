package space.samatov.ultimatetiktaktoe.model;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class Player {

    public boolean isWinner(String sign, String[] cellPics)
    {

        if(cellPics[0]==sign&&cellPics[3]==sign&&cellPics[6]==sign){
            return true;
        }
        else if(cellPics[0]==sign&&cellPics[1]==sign&&cellPics[2]==sign){
            return true;
        }
        else if(cellPics[2]==sign&&cellPics[5]==sign&&cellPics[8]==sign){
            return true;
        }
        else if(cellPics[6]==sign&&cellPics[7]==sign&&cellPics[8]==sign)
        {
            return true;
        }
        else if(cellPics[0]==sign&&cellPics[4]==sign&&cellPics[8]==sign){
            return  true;
        }
        else if(cellPics[2]==sign&&cellPics[4]==sign&&cellPics[6]==sign){
            return true;
        }
        else if(cellPics[1]==sign&&cellPics[4]==sign&&cellPics[7]==sign){
            return true;
        }
        else if(cellPics[3]==sign&&cellPics[4]==sign&&cellPics[5]==sign){
            return true;
        }
        return false;
    }

}
