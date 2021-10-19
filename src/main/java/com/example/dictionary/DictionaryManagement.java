package com.example.dictionary;

import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;


public class DictionaryManagement {
    // Insert from command line
    public static void insertFromCommandline() {
        Scanner sc = new Scanner(System.in);
        int numberWord = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < numberWord; i++) {
            //Enter properties with command
            String word_target = sc.nextLine();
            String word_type = sc.nextLine();
            String word_pronun = sc.nextLine();
            String word_explain = sc.nextLine();

            //Add word to arraylist
            Word newWord = new Word(word_target, word_type, word_pronun, word_explain);
            Dictionary.DictionaryEV.add(newWord);
        }
    }

    //Insert from filename.txt
    public static void insertFromFile() throws IOException {
        //Import file
        File fileTXT = new File("filename.txt");
        Scanner scFile = new Scanner(fileTXT);
        if (scFile.hasNextLine()) {
            while (scFile.hasNextLine()) {
                //Properties
                String eWord = scFile.next();
                String typeWord = scFile.next();
                String proWord = scFile.next();
                String vnMeans = scFile.nextLine();

                //Add word to arraylist
                Word newWord = new Word(eWord, typeWord, proWord, vnMeans);
                Dictionary.DictionaryEV.add(newWord);
            }
            //Close when add all word to arraylist
            scFile.close();
        }

        //In case file empty
        else {
            System.out.println("File not found.");
        }
    }

    //Insert word from app to filename.txt
    public static void insertWord(String target_word, String type_word, String pronun_word, String explain_word) throws IOException{
        Word word = new Word(target_word, type_word, pronun_word, explain_word);
        FileWriter fw = new FileWriter("filename.txt",true);

        //Insert at end
        fw.write(  target_word + "\t" + type_word + "\t" + pronun_word + "\t" + explain_word + "\n");
        fw.close();
    }

    //Repair word in filename.txt if file has this word
    public static void repairWord(String wordToRepair, String newTarget, String newType, String newPronun, String newExplain ) throws IOException{
        int count = dictionaryLookup(wordToRepair);
        FileWriter fwrw = new FileWriter("filename.txt");
        for (int i = 0 ; i < Dictionary.DictionaryEV.size(); i++){
            String str = Dictionary.DictionaryEV.get(i).getWord_target() + "\t" + Dictionary.DictionaryEV.get(i).getWord_type() + "\t"
                    + Dictionary.DictionaryEV.get(i).getWord_pronun() + Dictionary.DictionaryEV.get(i).getWord_explain() + "\n";
            if (i != count - 1) fwrw.write(str);
            else fwrw.write(newTarget  +"\t" + newType + "\t" + newPronun + "\t" + newExplain + "\n");
        }
        fwrw.close();
    }

    //Delete a word in filename.txt if file has this word
    public static void deleteWord(String wordtoDelete) throws IOException{
        int count = dictionaryLookup(wordtoDelete);
        FileWriter fwdw = new FileWriter("filename.txt");
        for (int i = 0 ; i < Dictionary.DictionaryEV.size() ; i++){
            String str = Dictionary.DictionaryEV.get(i).getWord_target() + "\t" + Dictionary.DictionaryEV.get(i).getWord_type() + "\t"
                    + Dictionary.DictionaryEV.get(i).getWord_pronun()  + Dictionary.DictionaryEV.get(i).getWord_explain() + "\n";
            if(i != count - 1) {
                fwdw.write(str);
            }
        }
        fwdw.close();
    }

    //bool Look up
    public static boolean lookUp(String lookUpWord) {
        int count = 0;
        for (int i = 0; i < Dictionary.DictionaryEV.size(); i++) {
            if (Dictionary.DictionaryEV.get(i).getWord_target().equals(lookUpWord)) {
                count ++;
                break;
            }
        }
        if (count == 0) return false;
        else return true;
    }

    //Look up by command line
    public static void dictionaryLookUp() {
        System.out.println("You want to look  up the word: ");
        Scanner sc = new Scanner(System.in);
        String lookUpWord = sc.nextLine();
        if (DictionaryManagement.lookUp(lookUpWord)){
            System.out.println("I found it.");
        } else {
            System.out.println("This word is not found.");
        }
    }

    //Return pos of the word we find
    public static int dictionaryLookup(String word_lookup) {
        int pos = 0;
        for (int i = 0; i < Dictionary.DictionaryEV.size(); i++){
            if (Dictionary.DictionaryEV.get(i).getWord_target().equals(word_lookup)) {
                pos = i + 1;
                break;
            }
        }
        return pos;
    }

    //Export to a file
    public static void dictionaryExportToFile() throws IOException{
        FileWriter fileWriter = new FileWriter("C:\\Users\\Admin\\Desktop\\dictionaries.txt");
        for (int i = 0 ; i < Dictionary.DictionaryEV.size() ; i++){
            String str = Dictionary.DictionaryEV.get(i).getWord_target() + "\t" + Dictionary.DictionaryEV.get(i).getWord_type() + "\t"
                    + Dictionary.DictionaryEV.get(i).getWord_pronun()  + Dictionary.DictionaryEV.get(i).getWord_explain() + "\n";
            fileWriter.write(str);
        }
        fileWriter.close();
    }

}