package lexicon.ud.khalid.simple.tools;


public class Tools {

    static public void say (Double data) {
        System.out.println(data);
    }
    static public void say (Double data, boolean newline) {
        if(newline) System.out.println(data);
        else  System.out.print(data);
    }

    static public void say (String data) {
        System.out.println(data);
    }
    static public void say (String data, boolean newline) {
        if(newline) System.out.println(data);
        else  System.out.print(data);
    }

    static public void error (String data) { System.err.println(data); }


}
