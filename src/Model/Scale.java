package Model;

/**
 * Created by user on 22.05.2017.
 */
public class Scale {
    private int scaleProcent;
    private int changeInScale;
    private String scale;

    public Scale() {
        scaleProcent = 100;
        changeInScale = 25;
        scale = scaleProcent + "%";
    }

    public void zoomPlus(){
        scaleProcent += changeInScale;
        scale = scaleProcent + "%";
    }

    public void zoomMinus(){
        scaleProcent -= changeInScale;
        scale = scaleProcent + "%";
    }

    public String getCurrentStringScale(){
        return scale;
    }

    public int getScaleProcent(){
        return scaleProcent;
    }
}
