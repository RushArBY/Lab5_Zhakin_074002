package com.company;

import java.io.*;
import java.util.StringTokenizer;

class Symbol {
    String data;
    String name;
    String choice;
    String choice2;
    RandomAccessFile fio;
    BufferedReader inf;

    public Symbol() throws UnsupportedEncodingException {
        this.inf = new BufferedReader(new InputStreamReader(System.in, "Cp1251"));
    }

    public void runConsole() throws IOException {
        while (true) {
            Menu();
            choice = inf.readLine();
            if (choice.compareTo("1") == 0) {
                textFromFile();
            } else if (choice.compareTo("2") == 0) {
                RedactMenu();
                choice2 = inf.readLine();
                if (choice2.compareTo("1") == 0) {
                    addStart();
                } else if (choice2.compareTo("2") == 0) {
                    addEnd();
                } else if (choice2.compareTo("3") == 0) {
                    addRandom();
                }
            } else if (choice.compareTo("3") == 0) {
                task2();
            } else if (choice.compareTo("4") == 0) {
                textIntoFile();
            }else if (choice.compareTo("5")==0){
                return;
            }
        }
    }

    public void Menu() {
        System.out.println("Enter your choice:");
        System.out.println("1.Show text from existing file;");
        System.out.println("2.Redact this text;");
        System.out.println("3.Given task;");
        System.out.println("4.Add text to file;");
        System.out.println("5.Exit;");
    }

    public void textFromFile() throws IOException {
        System.out.println("Enter name of file:");
        name = inf.readLine();
        fio = new RandomAccessFile(new File(name), "r");
        data = fio.readLine();
        fio.close();
        System.out.println("Information from file: " + data);
    }

    public void RedactMenu() {
        System.out.println("Enter choice:");
        System.out.println("1 Add text to the beginning of file;");
        System.out.println("2 Add text to the end of file;");
        System.out.println("3 Add text to any position;");
    }

    public void addStart() throws IOException {
        System.out.println("Enter name of file:");
        name = inf.readLine();
        fio = new RandomAccessFile(new File(name), "rw");
        data = fio.readLine();
        System.out.println("Enter string to the beginning:");
        String s;
        s = inf.readLine();
        fio.seek(0);
        fio.writeBytes(s);
        fio.seek(s.length());
        fio.writeBytes(data);
        fio.close();
        System.out.println("String is added to the beginning of file.");
    }

    public void addEnd() throws IOException {
        System.out.println("Enter name of file:");
        name = inf.readLine();
        fio = new RandomAccessFile(new File(name), "rw");
        data = fio.readLine();
        System.out.println("Enter string to the end:");
        String s;
        s = inf.readLine();
        fio.seek(fio.length());
        fio.writeBytes(s);
        fio.close();
        System.out.println("String is added to the end of file.");
    }

    public void addRandom() throws IOException {
        System.out.println("Enter name of file:");
        name = inf.readLine();
        fio = new RandomAccessFile(new File(name), "rw");
        System.out.println("Enter string to the random position:");
        String s;
        s = inf.readLine();
        System.out.println("Enter the position:");
        int n;
        n = Integer.parseInt(inf.readLine());
        fio.seek(n);
        data = fio.readLine();
        fio.seek(n);
        fio.writeBytes(s);
        fio.writeBytes(data);
        fio.close();
        System.out.println("String is added to file.");
    }

    public void task2() throws IOException {
        System.out.println("Enter name of file:");
        name = inf.readLine();
        fio = new RandomAccessFile(new File(name), "r");
        data = fio.readLine();
        fio.close();
        char ch;
        char spaces = 0;
        for (int i = 0; i < data.length(); i++) {
            ch = Character.toLowerCase(data.charAt(i));
            if (Character.isWhitespace(ch)) {

                spaces++;
                if (data.charAt(i - 1) == data.charAt(i + 1)) {
                    StringTokenizer tokenizer = new StringTokenizer(data);
                    int n = tokenizer.countTokens();
                    String[] tokens = new String[n];
                    for (int j = 0; j < n; j++) {
                        tokens[j] = tokenizer.nextToken();
                    }
                    System.out.println(tokens[spaces - 1 ]+ " " + tokens[spaces]);
                }
            }
        }
    }


    public void textIntoFile () throws IOException {
        System.out.println("Enter text:");
        data = inf.readLine();//ввод текста
        System.out.println("Enter name of file :");
        name = inf.readLine();
        fio = new RandomAccessFile(new File(name), "rw");
        fio.writeBytes(data);
        fio.close();
        System.out.println("Your text is saved .");
    }

    public static void main (String args[]) throws UnsupportedEncodingException, IOException {
        Symbol n = new Symbol();
        n.runConsole();
    }

}