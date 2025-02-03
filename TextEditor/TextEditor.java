package TextEditor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextEditor {

    private static TextEditorView view;

    private static final int LINE_WIDTH = 40;
    private String clipboard = "";
    private String alignment = "LEFT";

    private List<String> text;

    public TextEditor(TextEditorView editorView) {
        this.view = editorView;
        this.text = new ArrayList<>();
        insertDefaultText();
    }

    private void insertDefaultText()
    {
       String content = "Independence Day, observed annually on 15 August, is a national holiday in India commemorating the nation's independence from British rule on 15 August 1947.";
       wrapAndStore(content);
    }

    private void wrapAndStore(String content) {
        text.clear();
        String[] words = content.split(" ");
        StringBuilder line = new StringBuilder();

        for(String word : words)
        {
            if(line.length()+word.length()+1 > LINE_WIDTH)
            {
                text.add(formatLine(line.toString().trim()));
                line = new StringBuilder();
            }
            line.append(word).append(" ");
        }

        if(!line.isEmpty())
        {
            text.add(formatLine(line.toString().trim()));
        }
    }

    private String formatLine(String line) {

        switch (alignment)
        {
            case "RIGHT" :
                return String.format("%"+LINE_WIDTH+"s",line);
            case "CENTER" :
                int padding = (LINE_WIDTH - line.length())/2;
                return " ".repeat(padding)+line;
            case "JUSTIFY" :
                return justifyText(line);
            default :
                return line;
        }
    }

    private String justifyText(String line) {

        String[] words = line.split(" ");

        int totalSpaces = LINE_WIDTH - line.replaceAll(" ","").length();
        int gaps = words.length-1;
        int gapSpace = totalSpaces / gaps;
        int extraSpace = totalSpaces % gaps;

        StringBuilder lines = new StringBuilder();
        for(int i=0;i<words.length-1;i++)
        {
            lines.append(words[i]).append(" ".repeat(gapSpace));
            if(extraSpace-- > 0)
            {
                lines.append(" ");
            }
        }
        lines.append(words[words.length-1]);
        return lines.toString();
    }

    public void insertText(String line, int lineNumber, int colNumber) {

        if(lineNumber<1 || lineNumber>text.size())
        {
            System.out.println("Invalid Line Number..!");
            return;
        }

        String orgLine = text.get(lineNumber-1);
        if(colNumber<1 || colNumber > orgLine.length()+1)
        {
            System.out.println("Invalid Column Number..!");
            return;
        }

        String newLine = orgLine.substring(0,colNumber-1)+line+orgLine.substring(colNumber-1);
        text.set(lineNumber-1,newLine);
        formatText();

    }

    private void formatText() {

        StringBuilder str = new StringBuilder();
        for(String lines : text)
        {
            str.append(lines).append(" ");
        }
        wrapAndStore(str.toString());
    }



    public void deleteCharacters(int lineNo, int start, int end) {

        if(lineNo<1 || lineNo>text.size())
        {
            System.out.println("Invalid Line Number..!");
            return;
        }

        String orgLine = text.get(lineNo-1);
        if(start < 1 || end > orgLine.length() || start > end)
        {
            System.out.println("Invalid Start and End Position");
            return;
        }

        String newLine = orgLine.substring(0,start-1) + orgLine.substring(end);
        text.set(lineNo-1,newLine);
        formatText();
    }


    public void deleteLine(int lineNum) {

        if(lineNum<1 || lineNum > text.size())
        {
            System.out.println("Invalid Line Number..!");
            return;
        }

        text.remove(lineNum-1);
        formatText();
    }


    public void searchWord(String word) {

        boolean found = false;

        for(int i=0;i<text.size();i++)
        {
            String str = text.get(i);
            int ind = str.indexOf(word);
            while(ind!=-1)
            {
                System.out.println("Line Number - "+(i+1)+" Column Number - "+(ind+1));
                ind = str.indexOf(word,ind+1);
                found = true;
            }
        }

        if(!found)
        {
            System.out.println("No Such Word Found..!");
        }
    }


    public void replaceWord(String orgWord, String replaceWord) {

        for(String str : text)
        {
            str.replaceAll(orgWord,replaceWord);
        }
        formatText();
    }

    public void printText() {

        for(String lines : text)
        {
            System.out.println(lines);
        }
    }

    public void numberOfWords()
    {
        int count = 0;
        for(String lines : text)
        {
            String[] words = lines.split(" ");
            count += words.length;
        }
        System.out.println("Total Number of Words : "+count);
    }

    public void copyLine(int lineNos) {

        if(lineNos<1 || lineNos>text.size())
        {
            System.out.println("Invalid Line Number...!");
            return;
        }

        clipboard = text.get(lineNos-1);
        System.out.println("Text Copied in Clipboard..!");
    }

    public void pasteLine(int lineNumbers, int colNumbers) {

        if(clipboard.isEmpty()) {
            System.out.println("Clipboard is Empty..!");
            return;
        }

        insertText(clipboard,lineNumbers,colNumbers);
    }


    public void setAllignment(String align) {

        List<String> alignments = Arrays.asList("LEFT","RIGHT","CENTER","JUSTIFY");
        if(!alignments.contains(align))
        {
            System.out.println("Invalid Alignment..!");
            return;
        }

        alignment = align;
        formatText();
        System.out.println("Text Alignment is Set as : "+alignment);
    }
}
