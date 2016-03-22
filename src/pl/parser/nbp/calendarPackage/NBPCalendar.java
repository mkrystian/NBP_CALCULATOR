package pl.parser.nbp.calendarPackage;

import java.util.Calendar;
import java.util.LinkedList;

/**
 * Created by PREZES on 2016-03-21.
 */
public class NBPCalendar {

    private String day;
    private String month;
    private String year;


    public NBPCalendar(String date){
        String[] dateVector = date.split("-");
        year = dateVector[0];
        month = dateVector[1];
        day = dateVector[2];
    }


    public NBPCalendar(NBPCalendar element){
        this.day = element.getDay();
        this.month = element.getMonth();
        this.year = element.getYear();
    }

    public String getDay() {
        return day;
    }

    public int getIntDay() {
        return Integer.valueOf(day);
    }
    public int getIntMonth() {
        return Integer.valueOf(month);
    }
    public int getIntYear() {
        return Integer.valueOf(year);
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setIntYear(int year) {
        this.year = Integer.toString(year);
        if( this.year.length() < 2) this.year = "0" + this.year;
    }
    public void setIntMonth(int month) {
        this.month = Integer.toString(month);
        if( this.month.length() < 2) this.month = "0" + this.month;
    }
    public void setIntDay(int day) {
        this.day = Integer.toString(day);
        if( this.day.length() < 2) this.day = "0" + this.day;
    }

    @Override
    public String toString(){
        return year + "-" + month + "-" + day;
    }


    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getDayNumber(){
        return (getIntMonth()-1)*32 + getIntDay();
    }

    public void setDayNumber( int dayNumber){
        setIntDay( dayNumber % 32);
        //dayNumber = dayNumber - ( dayNumber % 32);
        setIntMonth( ( dayNumber / 32) +1 );
    }

    public boolean isYearCurrent(){

        return (Integer.valueOf(year) == Calendar.getInstance().get(Calendar.YEAR));


    }

    public LinkedList<NBPCalendar> getDatesBeetwen( NBPCalendar date){
        NBPCalendar dateFirst;
        NBPCalendar dateSecond;

        LinkedList<NBPCalendar> dates = new LinkedList<NBPCalendar>();

        if( this.getIntYear() < date.getIntYear() ||
            this.getIntMonth() < date.getIntMonth() ||
            this.getIntDay() < date.getIntDay()){
            dateFirst = new NBPCalendar(this);
            dateSecond = new NBPCalendar(date);
        }
        else{
            dateFirst = date;
            dateSecond = this;
        }


        while( dateFirst.getIntYear() <= dateSecond.getIntYear()){

            while( dateFirst.getDayNumber() <= dateSecond.getDayNumber() || (dateFirst.getIntYear() < dateSecond.getIntYear() && dateFirst.getDayNumber() <= 383 )){
                dates.add( new NBPCalendar(dateFirst));
                dateFirst.setDayNumber( dateFirst.getDayNumber() + 1);

            }
            dateFirst.setDayNumber( 1 );
            dateFirst.setIntYear(dateFirst.getIntYear()+1);
        }




        return dates;


    }
}
