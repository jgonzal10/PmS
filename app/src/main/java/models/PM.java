package models;

/**
 * Created by jgo on 02/06/2016.
 */
public class PM {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private int id;
    private String date;


    public PM(){}

    public PM(String date){
        this.id=id;
        this.date=date;
    }



}
