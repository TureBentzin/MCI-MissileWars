package de.mcimpact.missilewars.errors;

public class SelectorOpenedException extends Exception{



    @Override
    public String getMessage() {
        return "Can´t change selectorentries after opened!";
    }


}
