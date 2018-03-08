package lexicon.ud.khalid.simple;

import lexicon.ud.khalid.simple.tools.SPCalculator;

import java.util.Scanner;

import static lexicon.ud.khalid.simple.tools.Tools.say;

public class Main {

    private static Scanner scan             = new Scanner(System.in);
    private static SPCalculator calc        = new SPCalculator();
    private static String expr              = "0.0";
    private static String res               = "0.0";


    public static void main(String[] args) {
        say("################################################################################\n" +
                "#                             SPCalculator v0.01                               #\n" +
                "#  This is are simple commandline calculator.                                  #\n" +
                "#    1. Typing expression and press ENTER key.                                 #\n" +
                "#    2. For quit typing 'X' or 'Q' or 'EXIT' or 'QUIT'                         #\n" +
                "################################################################################");

        say("CALCULATE: ", false);
        expr            = scan.nextLine();
        while(!expr.matches("x|X|q|Q|exit|EXIT|quit|QUIT")) {
            res             = calc.eval(expr);
            say(makeSpace(res.length()) + "           RESULT: " + res);
            say("--------------------------------------------------------------------------------");
            say("CALCULATE: ", false);
            expr            = scan.nextLine();
        }

        say("################################################################################\n" +
                "#                             Thanks for using!                                #\n" +
                "# SPCalculator v0.01                                    Earth Copyright © 2018 #\n" +
                "################################################################################");
    }

    private static String makeSpace (int len) {
        int spaceLen            = 60 - len;
        String space            = "";

        for (int i = 0; i <= spaceLen; i++){
            space += " ";
        }
        return space;
    }

}

