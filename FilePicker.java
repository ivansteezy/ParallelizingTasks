import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FilePicker 
{
    FilePicker(Component frame)
    {
        JFileChooser fileChooser = new JFileChooser("./");
        int option = fileChooser.showOpenDialog(frame);

        if (option == JFileChooser.APPROVE_OPTION) 
        {
            mSelectedFile = fileChooser.getSelectedFile();
        }
    }

    public String ReadFile() throws IOException
    {
        System.out.println("Getting content!");
        String pathString = mSelectedFile.toString();
        Path path = Path.of(pathString);
        return Files.readString(path);
    }

    private File mSelectedFile;
}
