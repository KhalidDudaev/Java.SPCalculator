package lexicon.ud.khalid.simple.tools;

/**
* SPCalculator - String parsing calculator
*/

// import java regex classes for using regex
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class SPCalculator {

    /*
        create patterns for match ...
     */

    private static Pattern patternParen     = Pattern.compile("(?<paren>\\((?<inner>[^\\(\\)]+)\\))");                                          // ... paren
    private static Pattern patternMuxDiv    = Pattern.compile("(?<muxdiv>(?<A>\\d+(?:\\.\\d+)?)\\s*(?<OP>\\*|\\/)\\s*(?<B>\\d+(?:\\.\\d+)?))"); // ... multiplication and division
    private static Pattern patternAddSub    = Pattern.compile("(?<addsub>(?<A>\\d+(?:\\.\\d+)?)\\s*(?<OP>\\+|\\-)\\s*(?<B>\\d+(?:\\.\\d+)?))"); // ... add and sub

    // public method for evalution expression
    public String eval (String expr) {
        return calc(expr);
    }

    // static function for evalution expression using parsers
    private static String calc (String expr) {

        // using operation strict mathemathical priority
        expr = paren(expr);  // parsing paren
        expr = muxdiv(expr); // parsing multiplication and division
        expr = addsub(expr); // parsing plus and minus

        return expr;
    }

    // parrser for paren
    private static String paren (String expr){
        Matcher paren           = patternParen.matcher(expr);

        if(paren.find()){
            expr            = paren.replaceAll(calc(paren.group("inner")));
            expr            = paren(expr);
        }

        return expr;
    }

    // parrser for multiplication and division
    private static String muxdiv (String expr){
        Matcher muxdiv          = patternMuxDiv.matcher(expr);

        if(muxdiv.find()){
            String argA             = muxdiv.group("A");
            String argB             = muxdiv.group("B");
            String op               = muxdiv.group("OP");

            if (op.matches("\\/") && argB.matches("0(?:\\.0+)?")) {
//                System.err.println("ERROR: It is impossible to divide by zero");
                return "!!! ERROR !!! It is impossible to divide by zero --> " + expr;
            }

            switch (op) {
                case "/": expr          = muxdiv.replaceAll( Double.toString( div( Double.parseDouble(argA), Double.parseDouble(argB) ))); break;
                case "*": expr          = muxdiv.replaceAll( Double.toString( mux( Double.parseDouble(argA), Double.parseDouble(argB) ))); break;
                default: break;
            }
            expr = muxdiv(expr);
        }

        return expr;
    }

    // parrser for plus and minus
    private static String addsub (String expr){

        Matcher addsub          = patternAddSub.matcher(expr);

        if(addsub.find()){
            String argA             = addsub.group("A");
            String argB             = addsub.group("B");
            String op               = addsub.group("OP");

            switch (op) {
                case "-": expr          = addsub.replaceAll( Double.toString( sub( Double.parseDouble(argA), Double.parseDouble(argB) ))); break;
                case "+": expr          = addsub.replaceAll( Double.toString( add( Double.parseDouble(argA), Double.parseDouble(argB) ))); break;
                default: break;
            }
            expr = addsub(expr);
        }

        return expr;
    }

    private static Double div (Double a, Double b) { return a / b; } // function for division

    private static Double mux (Double a, Double b) { return a * b; } // function for multiplication

    private static Double sub (Double a, Double b) { return a - b; } // function for minus

    private static Double add (Double a, Double b) { return a + b; } // function for plus

}

