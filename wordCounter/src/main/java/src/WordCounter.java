package src;

import java.util.Random;

public class WordCounter {

    public static String return_random_paragraph(int numWords){
        StringBuilder paragraph = new StringBuilder();
        Random random = new Random();

        String[] arts ={"The", "A", "An"};
        String[] nouns = {"dog", "cat", "tree", "house", "car" };
        String[] verbs = {"detect", "attack", "Phish", "excecute", "deffend"};
        String[] adjectives = {"great", "good", "dangerous", "suspicious"};
        String[] adverbs = {"quickly", "slowly", "loudly", "softly", "always"};


        for (int i =0; i<numWords; i++){
            String art = arts[random.nextInt(arts.length)];
            String noun = nouns[random.nextInt(nouns.length)];
            String verb = verbs[random.nextInt(verbs.length)];
            String adjective = adjectives[random.nextInt(adjectives.length)];
            String adverb = adverbs[random.nextInt(adjectives.length)];

            paragraph.append(art)
                    .append(" ")
                    .append(noun)
                    .append(" ")
                    .append(verb)
                    .append(" ")
                    .append(adverb)
                    .append(" ")
                    .append(adjective);
        }

        return paragraph.toString();
    }

}
