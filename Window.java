import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Console;
import java.io.IOException;
import java.rmi.RemoteException;

public class Window extends JFrame
{
    private JButton sequencialFilePickerButton;
    private JButton concurrentFilePickerButton;
    private JLabel parallelTitleLabel;
    private JLabel sequencialTitleLabel;
    private JLabel concurrentTitleLabel;
    private JTextField serverIpTextField;
    private JTextField clientIpTextField;
    private JRadioButton isServerRadioButton;
    private JButton searchParallelButton;
    private JLabel numberOfWordsSequencialLabel;
    private JLabel durationSequencialLabel;
    private JTextField wordToFindSequencialTextField;
    private JButton searchSequencialButton;
    private JLabel numberOfWordsConcurrentLabel;
    private JLabel durationConcurrentLabel;
    private JTextField wordToFindConcurrentTextField;
    private JButton searchConcurrentButton;
    private JTextField wordToFindParallelTextField;
    private JButton parallelFilePicker;
    private JLabel numberOfThreadsConcurrentLabel;
    private JTextField numberOfThreadsConcurrentTextField;

    public Window()
    {
        super("Syro");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(562, 320);
        setLayout(null);

        InitializeWindowElements();
    }

    void InitializeWindowElements()
    {
        // sequencial elements
        sequencialTitleLabel = new JLabel ("Secuencial");
        sequencialFilePickerButton = new JButton ("Texto..."); // secuencial filepicker button
        numberOfWordsSequencialLabel = new JLabel ("Ocurrencia:"); // numero de palabras secuencial
        durationSequencialLabel = new JLabel ("Tiempo:"); // duracion secuencial
        wordToFindSequencialTextField = new JTextField ("the", 5); //palabra a buscar secuencial
        searchSequencialButton = new JButton ("Buscar"); // buscar secuencial

        // concurrent elements
        concurrentTitleLabel = new JLabel ("Concurrente");
        concurrentFilePickerButton = new JButton ("Texto..."); // concurrent filepicker button
        numberOfWordsConcurrentLabel = new JLabel ("Ocurrencia:");// numero de palabras concurrente
        durationConcurrentLabel = new JLabel ("Tiempo");//duracionconcurrente
        wordToFindConcurrentTextField = new JTextField ("the", 5); //palabra a buscar concurrente
        searchConcurrentButton = new JButton ("Buscar"); // buscar concurrente
        numberOfThreadsConcurrentLabel = new JLabel ("Hilos");
        numberOfThreadsConcurrentTextField = new JTextField (5);

        // parallel elements
        parallelTitleLabel = new JLabel ("Paralelo");
        parallelFilePicker = new JButton ("Texto...");
        isServerRadioButton = new JRadioButton ("Servidor");
        serverIpTextField = new JTextField ("Server ip", 5);
        clientIpTextField = new JTextField ("Client ip", 5);
        wordToFindParallelTextField = new JTextField ("the", 5); // parallel filepicker button
        searchParallelButton = new JButton ("Start!"); // buscar paralelo


        //add components
        add (sequencialFilePickerButton);
        add (concurrentFilePickerButton);
        add (parallelTitleLabel);
        add (sequencialTitleLabel);
        add (concurrentTitleLabel);
        add (serverIpTextField);
        add (clientIpTextField);
        add (isServerRadioButton);
        add (searchParallelButton);
        add (numberOfWordsSequencialLabel);
        add (durationSequencialLabel);
        add (wordToFindSequencialTextField);
        add (searchSequencialButton);
        add (numberOfWordsConcurrentLabel);
        add (durationConcurrentLabel);
        add (wordToFindConcurrentTextField);
        add (searchConcurrentButton);
        add (wordToFindParallelTextField);
        add(parallelFilePicker);
        add (numberOfThreadsConcurrentTextField);
        add(numberOfThreadsConcurrentLabel);
        
        sequencialFilePickerButton.setBounds (5, 55, 105, 30);
        concurrentFilePickerButton.setBounds (215, 55, 105, 30);
        parallelTitleLabel.setBounds (410, 5, 165, 45);
        sequencialTitleLabel.setBounds (10, 5, 155, 50);
        concurrentTitleLabel.setBounds (215, 5, 150, 50);
        serverIpTextField.setBounds (410, 125, 130, 30);
        clientIpTextField.setBounds (410, 160, 130, 30);
        isServerRadioButton.setBounds (405, 95, 100, 25);
        searchParallelButton.setBounds (410, 240, 130, 30);
        numberOfWordsSequencialLabel.setBounds (20, 135, 150, 30);
        durationSequencialLabel.setBounds (20, 160, 200, 35);
        wordToFindSequencialTextField.setBounds (15, 200, 120, 30);
        searchSequencialButton.setBounds (15, 240, 120, 30);
        numberOfWordsConcurrentLabel.setBounds (215, 140, 200, 25);
        durationConcurrentLabel.setBounds (215, 165, 200, 25);
        wordToFindConcurrentTextField.setBounds (210, 200, 120, 30);
        searchConcurrentButton.setBounds (210, 240, 120, 30);
        wordToFindParallelTextField.setBounds (410, 200, 130, 30);
        parallelFilePicker.setBounds (410, 55, 105, 30);
        numberOfThreadsConcurrentTextField.setBounds (210, 110, 115, 30);
        numberOfThreadsConcurrentLabel.setBounds (210, 85, 100, 25);

        InitializeFilePickerButtonEvents();
        InitializeSearchButtonEvents();
        InitializeRadioEvents();
    }

    void InitializeFilePickerButtonEvents()
    {
        sequencialFilePickerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FilePicker fp = new FilePicker(Window.this);
                String fileContent = new String();

                try 
                {
                    fileContent = fp.ReadFile();
                    SequencialCounter sc = new SequencialCounter(fileContent, GetSequencialWordToSearch());
                    sc.Search();

                    durationSequencialLabel.setText(" ");
                    durationSequencialLabel.setText("Tiempo: " + sc.GetDurationAsString() + "ns");

                    numberOfWordsSequencialLabel.setText(" ");
                    numberOfWordsSequencialLabel.setText("Cantidad: " + sc.GetNumberOfFindsAsString());
                }
                catch (IOException e1) 
                {
                    e1.printStackTrace();
                }
            }
        });

        concurrentFilePickerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FilePicker fp = new FilePicker(Window.this);
                String fileContent = new String();

                try 
                {
                    fileContent = fp.ReadFile();
                    int numberOfThreads = Integer.parseInt(numberOfThreadsConcurrentTextField.getText());
                    ConcurrentCounter cc = new ConcurrentCounter(numberOfThreads, fileContent, "the");
                    cc.Search();

                    durationConcurrentLabel.setText(" ");
                    durationConcurrentLabel.setText("Tiempo: " + cc.GetDurationAsString() + "ns");

                    numberOfWordsConcurrentLabel.setText(" ");
                    numberOfWordsConcurrentLabel.setText("Cantidad: " + cc.GetNumberOfFindsAsString());
                }
                catch (IOException e1) 
                {
                    e1.printStackTrace();
                } 
                catch (InterruptedException e1) 
                {
                    e1.printStackTrace();
                }
            }
        });

        parallelFilePicker.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // do something
            }
        });
    }

    void InitializeSearchButtonEvents()
    {
        searchSequencialButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // do something
            }
        });

        searchConcurrentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // do something
            }
        });

        searchParallelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // rise server
                if(isServerRadioButton.isSelected())
                {
                    try 
                    {
                        Server s = new Server("1234");
                        s.StartServer();
                    } catch (RemoteException e1) 
                    {
                        e1.printStackTrace();
                    }
                }
                else
                {
                    // start one thread for local calculations and one thread for remote calculations
                    try 
                    {
                        FilePicker fp = new FilePicker(Window.this);
                        String fileContent = new String();
                        fileContent = fp.ReadFile();
                        ParallelCounter pc = new ParallelCounter("", "", 2, fileContent, "the");
                        pc.Search();

                        System.out.println("------------------FINAL PARALLEL RESULTS---------------");
                    } 
                    catch (InterruptedException | IOException e1) 
                    {
                        e1.printStackTrace();
                    }
                }
            }
        });
    }

    void InitializeRadioEvents()
    {
        isServerRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(isServerRadioButton.isSelected())
                {
                    clientIpTextField.setEditable(false);
                    searchParallelButton.setEnabled(true);
                }
                else
                {
                    clientIpTextField.setEditable(true);
                    searchParallelButton.setEnabled(false);
                }
            }
        });
    }

    String GetSequencialWordToSearch()
    {
        return wordToFindSequencialTextField.getText();
    }

    String GetConcurrentWordToSearch()
    {
        return wordToFindConcurrentTextField.getText();
    }

    String GetParallelWordToSearch()
    {
        return wordToFindParallelTextField.getText();
    }

    int GetNumberOfThreadsToUse()
    {
        return Integer.parseInt(numberOfThreadsConcurrentTextField.getText());
    }

    String GetServerIp()
    {
        return serverIpTextField.getText();
    }

    String GetClientIp()
    {
        return clientIpTextField.getText();
    }

    boolean GetIsServer()
    {
        return isServerRadioButton.isSelected();
    }

}
