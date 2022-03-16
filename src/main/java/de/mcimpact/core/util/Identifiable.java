package de.mcimpact.core.util;

public interface Identifiable {

    String UTF = "!\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~                                  ¡¢£¤¥¦§¨©ª«¬­®¯°±²³´µ¶·¸¹º»¼½¾¿ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖ×ØÙÚÛÜÝÞßàáâãäåæçèéêëìíîïðñòóôõö÷øùúûüýþÿ";
    String ALLOWED = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz-_";

    /**
     *
     * @return ID that instances of <code>"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz-_"</code>
     */
    String getID();


    static String generateID(String s) {
        s = s.replace(" ", "_");
        for (char c : s.toCharArray()) {
            if(!ALLOWED.contains(c + ""))
               s =  s.replace(c + "", "");
        }
        return s;
    }

    static CharSequence getUTF() {
        return UTF;
    }
}
