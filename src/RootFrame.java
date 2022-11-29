import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class RootFrame extends JFrame {
    String fileName = "./images/2D-warehouse-model-final-path-executed.png";

    public RootFrame() throws IOException {
        System.out.println(new File(fileName).exists());

        ImageJPanel panel = new ImageJPanel(fileName);
        setSize(panel.getImageWidth(),panel.getImageHeight());

        System.out.println(getSize());
        this.setContentPane(panel);


        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //pack();
        setLayout(null);
        setVisible(true);
    }
}
