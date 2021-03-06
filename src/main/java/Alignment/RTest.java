package Alignment;

/**
 * Created by gabe on 6/09/2016.
 */

import org.rosuda.JRI.*;

import java.util.ArrayList;

public class RTest {
    public static void main(String a[]) {

//        // Create an R vector in the form of a string.
//        String javaVector = "c(1,2,3,4,5)";
//
//        // Start Rengine.
//        Rengine engine = new Rengine(new String[] { "--no-save" }, false, null);
//
//        // The vector that was created in JAVA context is stored in 'rVector' which is a variable in R context.
//        engine.eval("rVector=" + javaVector);
//
//        //Calculate MEAN of vector using R syntax.
//        engine.eval("meanVal=mean(rVector)");
//
//        //Retrieve MEAN value
//        double mean = engine.eval("meanVal").asDouble();
//
//        //Print output values
//        System.out.println("Mean of given vector is=" + mean);

        ArrayList<String> seqs = new ArrayList<String>();
        ArrayList<Double> scores = new ArrayList<Double>();

        seqs.add("BB40019");
        seqs.add("BB40020");
        seqs.add("BB40021");

        scores.add(.57);
        scores.add(.21);
        scores.add(.03);

        String seqString = "c(";

        for (String seq: seqs){
            seqString += "'" + seq + "',";
        }

        seqString = seqString.substring(0, seqString.lastIndexOf(","));

        seqString += ")";

        String scoreString = "c(";

        for (Double score: scores){
            scoreString += score + ",";
        }

        scoreString = scoreString.substring(0, scoreString.lastIndexOf(","));

        scoreString += ")";

        System.out.println(seqString);
        System.out.println(scoreString);






        Rengine engine = new Rengine(new String[] { "--no-save"}, false, null);

        engine.eval("require(ggplot2)");
        engine.eval("Seqs <-" + seqString);
        engine.eval("Scores <-" + scoreString);
        engine.eval("joined <- data.frame(Seqs, Scores)");
        engine.eval("plot <- ggplot(data=joined, aes(x=Seqs, y=Scores, group=1)) + geom_line()");
        engine.eval("ggsave('/Users/gabe/Dropbox/scores.png', plot)");
        System.out.println("Done");



    }
}
