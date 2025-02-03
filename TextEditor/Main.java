package TextEditor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        TextEditorView view = new TextEditorView();
        TextEditor editor = new TextEditor(view);

        view.run();
    }
}
