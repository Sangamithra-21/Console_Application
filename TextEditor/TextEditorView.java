package TextEditor;

import java.util.Scanner;

public class TextEditorView {

    private static TextEditor editor;

    public TextEditorView()
    {
        this.editor = new TextEditor(this);
    }

    public void run() {

        Scanner sc = new Scanner(System.in);

        while(true)
        {
            System.out.println("1)Insert 2)Delete Characters 3)Delete Line 4)Search 5)Find and Replace 6)Print Text 7)Number of Words in Text 8)Copy 9)Paste 10)Allign Text 11)Exit");
            int option = sc.nextInt();

            switch(option)
            {
                case 1 :
                    System.out.print("Enter the Line Number : ");
                    int lineNumber = sc.nextInt();
                    System.out.print("Enter the Column Number :");
                    int colNumber = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter the text to Insert : ");
                    String line = sc.nextLine();
                    editor.insertText(line,lineNumber,colNumber);
                    break;
                case 2 :
                    System.out.print("Enter the Line Number :");
                    int lineNo = sc.nextInt();
                    System.out.print("Enter the Start Position : ");
                    int start = sc.nextInt();
                    System.out.println("Enter the End Position : ");
                    int end = sc.nextInt();
                    editor.deleteCharacters(lineNo,start,end);
                    break;
                case 3 :
                    System.out.print("Enter the Line Number : ");
                    int lineNum = sc.nextInt();
                    editor.deleteLine(lineNum);
                    break;
                case 4 :
                    System.out.print("Enter the Word to Search : ");
                    String word = sc.nextLine();
                    editor.searchWord(word);
                    break;
                case 5 :
                    System.out.print("Enter the word to Find : ");
                    String orgWord = sc.nextLine();
                    System.out.print("Enter the word to replace : ");
                    String replaceWord = sc.nextLine();
                    editor.replaceWord(orgWord,replaceWord);
                    break;
                case 6 :
                    editor.printText();
                    break;
                case 7 :
                    editor.numberOfWords();
                    break;
                case 8 :
                    System.out.print("Enter the Line Number :");
                    int lineNos = sc.nextInt();
                    editor.copyLine(lineNos);
                    break;
                case 9 :
                    System.out.print("Enter the Line Number : ");
                    int lineNumbers = sc.nextInt();
                    System.out.print("Enter the Column Number :");
                    int colNumbers = sc.nextInt();
                    sc.nextLine();
                    editor.pasteLine(lineNumbers,colNumbers);
                    break;
                case 10 :
                    System.out.print("Enter the Alignment : [LEFT | RIGHT | CENTER | JUSTIFY]");
                    String align = sc.next();
                    editor.setAllignment(align);
                    break;
                case 11 :
                    System.exit(0);
                default :
                    System.out.println("Invalid Option...Try Again");
                    break;
            }
        }
    }
}
