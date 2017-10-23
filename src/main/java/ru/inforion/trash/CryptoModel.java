package main.java.ru.inforion.trash;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CryptoModel {
    private String text;
    private List<String> history;
    private List<Pair<Character, Character>> letterMapping;
    private StringBuilder stringBuilder;
    List<Integer> occurrences;

    public CryptoModel(String cipherText) {
        text = cipherText;
        history = new ArrayList<>();
        history.add(cipherText);
        stringBuilder = new StringBuilder();
        occurrences = new ArrayList<>();
        letterMapping = new ArrayList<>();
    }

    public String decrypt(char currentOne, char newOne) {
        stringBuilder = new StringBuilder(history.get(history.size() - 1));
        occurrences = getOccurrences(currentOne);
        for (int index: occurrences) {
            stringBuilder.setCharAt(index, newOne);
        }
        String iteration = stringBuilder.toString();
        history.add(iteration);
        letterMapping.add(new Pair<>(currentOne, newOne));
        return iteration;
    }

    public String revert() {
        if (history.size() > 1) {
            history.remove(history.size() - 1);
            letterMapping.remove(letterMapping.size() - 1);

        }
        return history.get(history.size() - 1);
    }

    public List<Integer> getOccurrences(char charToFind) {
        Matcher m = Pattern.compile(String.format("(?=(%s))", charToFind)).matcher(history.get(0));
        List<Integer> occurrences = new ArrayList<>();
        while (m.find())
        {
            occurrences.add(m.start());
        }
        return occurrences;
    }

    public List<Pair<Character, Character>> stop() {
        return letterMapping;
    }
}
