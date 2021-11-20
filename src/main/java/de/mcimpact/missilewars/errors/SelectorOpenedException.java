package de.mcimpact.missilewars.errors;

public class SelectorOpenedException extends Exception{



    @Override
    public String getMessage() {
        return "cant change SelectorEntries after opened";
    }


}
