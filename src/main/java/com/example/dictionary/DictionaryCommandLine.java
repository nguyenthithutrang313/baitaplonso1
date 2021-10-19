package com.example.dictionary;

import java.io.IOException;
import java.util.ArrayList;

public class DictionaryCommandLine {
    //Show all word(word, type, pronunciation, meaning)
    public void showAllWords() {
        System.out.println("No " + "\t\t" + "| Endlish " + "\t\t" + "| Type " + "\t\t" + "| Pronunciation " + "| Vietnamese ");
        for (int i = 0; i < Dictionary.DictionaryEV.size(); i++) {
            System.out.println((i + 1) + "\t\t|" + Dictionary.DictionaryEV.get(i).getWord_target() + "\t\t|" + Dictionary.DictionaryEV.get(i).getWord_type()
                   + "\t\t|" + Dictionary.DictionaryEV.get(i).getWord_pronun() + "\t\t|" + Dictionary.DictionaryEV.get(i).getWord_explain());
        }
    }

    //Show all word from command line
    public void dictionaryBasic() {
        DictionaryManagement.insertFromCommandline();
        showAllWords();
    }


    //Show all word from file
    public void dictionaryAdvanced() throws IOException {
        DictionaryManagement.insertFromFile();
        showAllWords();
    }

    //Search word by suggest
    public ArrayList<String> dictionarySearch(String searchWord){
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < Dictionary.DictionaryEV.size(); i++){
            int count = 0 ;
            String word = Dictionary.DictionaryEV.get(i).getWord_target();
            if(searchWord.length() <= word.length()){
                for (int j = 0; j < searchWord.length(); j++) {
                    if (searchWord.charAt(j) == word.charAt(j)) {
                        count++;
                    } else break;
                    if (count == searchWord.length()) {
                        list.add(word);
                    }
                }
            }
        }
        return list;
    }
}