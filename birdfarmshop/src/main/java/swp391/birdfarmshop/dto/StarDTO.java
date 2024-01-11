/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.birdfarmshop.dto;

/**
 *
 * @author Admin
 */
public class StarDTO {
    private int oneStar;
    private int twoStar;
    private int threeStar;
    private int fourStar;
    private int fiveStar;

    public StarDTO() {
    }

    
    public StarDTO(int oneStar, int twoStar, int threeStar, int fourStar, int fiveStar) {
        this.oneStar = oneStar;
        this.twoStar = twoStar;
        this.threeStar = threeStar;
        this.fourStar = fourStar;
        this.fiveStar = fiveStar;
    }

    public int getOneStar() {
        return oneStar;
    }

    public void setOneStar(int oneStar) {
        this.oneStar = oneStar;
    }

    public int getTwoStar() {
        return twoStar;
    }

    public void setTwoStar(int twoStar) {
        this.twoStar = twoStar;
    }

    public int getThreeStar() {
        return threeStar;
    }

    public void setThreeStar(int threeStar) {
        this.threeStar = threeStar;
    }

    public int getFourStar() {
        return fourStar;
    }

    public void setFourStar(int fourStar) {
        this.fourStar = fourStar;
    }

    public int getFiveStar() {
        return fiveStar;
    }

    public void setFiveStar(int fiveStar) {
        this.fiveStar = fiveStar;
    }

    public int totalStar(){
       return oneStar + twoStar + threeStar+ fourStar + fiveStar;
    }
    
    public int totalAllPerStar() {
        return oneStar * 1 + twoStar * 2 + 3 * threeStar + 4 * fourStar + 5 * fiveStar;
    }
    
    public double percentOneStar() {
        double number = (double) Math.round((double) oneStar / totalStar() * 100 * 100) / 100;
        return number;
    }

    public double percentTwoStar() {
        double number = (double) Math.round((double) twoStar / totalStar() * 100 * 100) / 100;
        return number;
    }

    public double percentThreeStar() {
        double number = (double) Math.round((double) threeStar / totalStar() * 100 * 100) / 100;
        return number;
    }

    public double percentFourStar() {
        double number = (double) Math.round((double) fourStar / totalStar() * 100 * 100) / 100;
        return number;
    }

    public double percentFiveStar() {
        double number = (double) Math.round((double) fiveStar / totalStar() * 100 * 100) / 100;
        return number;
    }

    public double totalRating() {
        if(totalStar() == 0) return 0;
        double number = ((double) totalAllPerStar() / totalStar());
        return Math.round(number * 10) / 10.0;
    }    
     public double percentTotalRating() {
        double number = (double) Math.round((double) totalRating() / 5 * 100 * 100) / 100;
        return number;
    }  
}
