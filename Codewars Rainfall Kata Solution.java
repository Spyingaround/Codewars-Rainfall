public class Rainfall {

    public static double[] getRainDataValues(String town, String strng) {

      //Initialisere double[] og String[] til null.
      //Hvis town ikke findes, så retuneres rainValues denne som den er.
      double[] rainValues = new double[12];
      String[] monthSubstring = null;

      //Splitter data (strng) efter \n.
      String[] townLines = strng.split("\n");

      //Linje som kommer til at holde alt data for town
      String townLine = "";

      //Loop igennem townLines for at tjekke om town findes i datasættet.
      for (int i = 0; i < townLines.length; i++) {

        //Finder "currTown" ved at tage fx. {London:Jan 48.0, Feb 63.2, Mar 70.3 ... Dec 97.9}
        //og derefter bruge .split(":")[0]
        //Jeg får dermed 0-element som er byens navn i datasættet.
        String currTown = townLines[i].split(":")[0];

        //Tjekker om currTown eksistere i linjen fra datasættet
        if (currTown.equals(town)) {
          //Assign til townLine
          townLine = townLines[i];
        }
      }

      //Tjekker om townLine indeholder et "," - hvis ikke, er der intet data for byen.
      if (townLine.contains(",")) {
        monthSubstring = townLine.split(",");

        for(int i = 0; i < monthSubstring.length; i++){

          String tmp = monthSubstring[i].split(" ")[1];

          rainValues[i] = Double.parseDouble(tmp);

        }
      }

      return rainValues;

    }

    public static double mean(String town, String strng) {

      double sum = 0.0;
      double[] rainValuesOfTown;

      //Får et double[] tilbage med data a la {48.0, 63.2, 70.3...97.9}
      rainValuesOfTown = getRainDataValues(town, strng);

      //Tjekker at rainValuesOfTown != null
      //Hvis double[] == 0 så skal den retunere -1, da byen og dens data ikke findes i strng
      if (rainValuesOfTown[0] != 0.0) {

        //Loop igennem for at få alle double værdier, for at kunne retunere mean efterfølgende
        for (double value : rainValuesOfTown) {
          sum += value;
        }

        //Dividerer med 12, da der er 12 måneders data.
        return sum/12;

      } else {
        return -1.0;
      }

    }

    public static double variance(String town, String strng) {

      //Bruger mean() metode til at få mean istedet for at regne den igen.
      double mean = mean(town,strng);

      //Variable til at summere difference mellem regnværdi og mean.
      double diffSquared = 0.0;

      double[] rainValuesOfTown;

      //Får et double[] tilbage med data á la {London:Jan 48.0, Feb 63.2, Mar 70.3 ... Dec 97.9}
      rainValuesOfTown = getRainDataValues(town, strng);

      //Hvis data ved 1. index == 0.0 så skal den retunere -1, da byen og dens data ikke findes i strng
      if (rainValuesOfTown[0] != 0.0) {

        //Loop hver element i rainValuesOfTown
        for (double value : rainValuesOfTown) {

          //Variansberegning hvor jeg summere kvadreret værdier
          double diff = value - mean;
          diffSquared += (diff*diff);
        }

        //Dividerer med 12, da der er 12 måneders data.
        return diffSquared/12;

      } else {
        return -1.0;
      }

    }
}
