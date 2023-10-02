package com;
 
import org.zkoss.bind.annotation.Command;
 
public class FormViewModel extends UserForm {
    private String dateFormat;
    private String foregroundColour = "#000000", backgroundColour = "#FDC966";
 
    public String getForegroundColour() {
        return foregroundColour;
    }
 
    public void setForegroundColour(String foregroundColor) {
        this.foregroundColour = foregroundColor;
    }
 
    public String getBackgroundColour() {
        return backgroundColour;
    }
 
    public void setBackgroundColour(String backgroundColor) {
        this.backgroundColour = backgroundColor;
    }
 
    public String getDateFormat() {
        return dateFormat;
    }
 
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }
 
    @Command
    public void submit() {
    }
 
}