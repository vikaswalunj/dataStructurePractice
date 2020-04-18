package test;


import java.io.*;
import java.util.*;

/*
You are in charge of a display advertising program. Your ads are displayed on websites all over the internet. You have some CSV input data that counts how many times you showed an ad on each individual domain. Every line consists of a count and a domain name. It looks like this:

counts = [ "900,google.com",
     "60,mail.yahoo.com",
     "10,mobile.sports.yahoo.com",
     "40,sports.yahoo.com",
     "300,yahoo.com",
     "10,stackoverflow.com",
     "2,en.wikipedia.org",
     "1,es.wikipedia.org",
     "1,mobile.sports" ]

Write a function that takes this input as a parameter and returns a data structure containing the number of hits that were recorded on each domain AND each domain under it. For example, an impression on "mail.yahoo.com" counts for "mail.yahoo.com", "yahoo.com", and "com". (Subdomains are added to the left of their parent domain. So "mail" and "mail.yahoo" are not valid domains. Note that "mobile.sports" appears as a separate domain as the last item of the input.)

Sample output (in any order/format):

getTotalsByDomain(counts)
1320    com
 900    google.com
 410    yahoo.com
  60    mail.yahoo.com
  10    mobile.sports.yahoo.com
  50    sports.yahoo.com
  10    stackoverflow.com
   3    org
   3    wikipedia.org
   2    en.wikipedia.org
   1    es.wikipedia.org
   1    mobile.sports
   1    sports
 */

class NewSolution {
    public static void main(String[] args) {
        String[] counts = {
                "900,google.com",
                "60,mail.yahoo.com",
                "10,mobile.sports.yahoo.com",
                "40,sports.yahoo.com",
                "300,yahoo.com",
                "10,stackoverflow.com",
                "2,en.wikipedia.org",
                "1,es.wikipedia.org",
                "1,mobile.sports"
        };

        HashMap<String, Integer> result = numberOfVisits(counts);

        System.out.println(result);



    }

    // TODO: Write a function that takes a parameter and returns a data structure

    public static HashMap<String, Integer> numberOfVisits(String[] counts) {


        HashMap<String, Integer> result = new HashMap<>();

        for (String domain : counts) {

            String[] visitDomain = domain.split(",");
            System.out.println("visitDomain[1] -" +visitDomain[1] );
            System.out.println("visitDomain[1] from map - " +result.get(visitDomain[1]));
            if (result.containsKey(visitDomain[1])) {
                result.put(visitDomain[1], result.get(visitDomain[1]) + Integer.valueOf(visitDomain[0]));
            } else {
                result.put(visitDomain[1], Integer.valueOf(visitDomain[0]));
            }

            String fullDomain = visitDomain[1];
            boolean lastNode = false;

            while (!lastNode) {
                String[] str = fullDomain.split("\\.");

                StringBuilder sb = new StringBuilder();
                for (int i=1; i<= str.length-1 ; i++) {
                	sb = sb.append(str[i]).append(".");
                }
                fullDomain = sb.toString();

                System.out.println("fullDomain -" +fullDomain );
                System.out.println("fullDomain from map - " +result.get(fullDomain));
                if (result.containsKey(fullDomain)) {

                    result.put(fullDomain, result.get(fullDomain) + Integer.valueOf(visitDomain[0]));
                } else {
                    result.put(fullDomain, Integer.valueOf(visitDomain[0]));
                }

                if(str.length == 1){
                    lastNode = true;
                }


            }
        }
        return result;


    }

}



