
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class App {
    public static void main(String[] args) throws Exception {
        JFrame marco = new MarcoRebote();
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.setVisible(true);
    }
    


}// end of class App

class Pelota{

    private static final int TAMX = 15;
    private static final int TAMY = 15;

    private double x = 0;
    private double y = 0;
    private double dx = 1;
    private double dy = 1;

        //mueve pelota inviertindo la posicion si choca con el limite
        public void mueve_pelota(Rectangle2D limites){
            x += dx;
            y += dy;

            if(x < limites.getMinX()){
                x = limites.getMinX();
                dx = -dx;
            }

            if(x + TAMX > limites.getMaxX()){
                x = limites.getMaxX() - TAMX;
                dx = -dx;
            }

            if(y < limites.getMinY()){
                y = limites.getMinY();
                dy = -dy;
            }

            if(y + TAMY > limites.getMaxY()){
                y = limites.getMaxY() - TAMY;
                dy = -dy;
        }
    }

     //formato de la pelota en su posicion inicial
     public Ellipse2D getShape(){
        return new Ellipse2D.Double(x, y, TAMX, TAMY);
    }
       
}//cierre clase Pelota

class LaminaPelota extends JPanel{

    // private Pelota pelota;
    // private Rectangle2D limites;
    private ArrayList<Pelota> pelotas = new ArrayList<Pelota>();

    public void add(Pelota b){
        pelotas.add(b);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for(Pelota b : pelotas){
            g2.fill(b.getShape());
        }
    }  

}//cierre clase LaminaPelota

class MarcoRebote extends JFrame{

    public MarcoRebote(){
        setBounds(600, 600, 400, 350);
        setTitle("Rebotes");
        lamina = new LaminaPelota();
        add(lamina, BorderLayout.CENTER);
        JPanel laminaBotones  = new JPanel();
        ponerBoton(laminaBotones, "Iniciar", new ActionListener(){
            public void actionPerformed(ActionEvent evento){
                comienza_el_juego();
            }
        });

        ponerBoton(laminaBotones, "Salir", new ActionListener(){
            public void actionPerformed(ActionEvent evento){
                System.exit(0);
            }
        });

        add(laminaBotones, BorderLayout.SOUTH);
    }

    //Poner botones
    private void ponerBoton(Container c, String titulo, ActionListener oyente){
        JButton boton = new JButton(titulo);
        c.add(boton);
        boton.addActionListener(oyente);
    }

    //inicia el juego
    public void comienza_el_juego(){
        Pelota pelota = new Pelota();
        lamina.add(pelota);
        for(int i=1; i < 3000; i++){
            pelota.mueve_pelota(lamina.getBounds());
            lamina.paint(getGraphics());
        }
    }

    private LaminaPelota lamina;


}//fin class MarcoRebote
