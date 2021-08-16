import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

public class Juego extends JPanel implements KeyListener, ActionListener {

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals("INS")) {
            System.out.println(" Debes de mover el carrito al lado derecho de la pantalla \n Pero si te equivocas te manda hacia atras. Aguas \n Usa las flechas de tu teclado");
            System.out.println(" ENGLISH: Move the car to the right side of the screen \n It sends you back if you make a mistake.  \n Use your keyboard arrows");
        } else if (cmd.equals("RESET")) {
            this.reset();
            this.repaint();
        }
        return;
    }

    // atributos de la clase
    private double x; // posicion horizontal (0 = izq, 1 = der)
    private double y; // posicion vertical (0 = arriba, 1 = abajo)
    private double ancho; // tamano rel. del objeto a dibujar
    private double altura; // tamano rel. del objeto a dibujar
    private double prevx = 0; // previo x
    private BufferedImage carrito = null; // Imagen del carrito

    // constante
    private static final double PASO = 0.02; // desplazamiento de 3%
    private static final double MIN = 0.001;
    private static final double MAX = 0.95;



    // metodo interno propio nuestro
    private void dibujaObjeto(Graphics2D g, int ancho, int altura) {
        g.setColor(Color.BLUE);
        //g.fill(new Rectangle2D.Double(this.x*ancho, this.y*altura, this.ancho*ancho, this.altura*altura)); //Rectangulo para debug
        //Dibujando el carrito
        g.drawImage(carrito,(int) (this.x*ancho),(int) (this.y*altura),this);
        g.setColor(Color.BLACK);

        //Lineas Verticales

        g.drawLine(30, 0, 30, 50);
        g.drawLine(30, 100, 30, 320);
        g.drawLine(90, 0, 90, 50);
        g.drawLine(90, 100, 90, 320);
        g.drawLine(150, 0, 150, 200);
        g.drawLine(150, 250, 150, 320);
        g.drawLine(210, 0, 210, 200);
        g.drawLine(210, 250, 210, 320);
        g.drawLine(270, 50, 270, 320);
        g.drawLine(330, 50, 330, 320);
        g.drawLine(390, 0, 390, 150);
        g.drawLine(390, 200, 390, 320);
        g.drawLine(450, 0, 450, 150);
        g.drawLine(450, 200, 450, 320);
        g.drawLine(510, 0, 510, 300);
        g.drawLine(570, 0, 570, 300);


        //Lineas Horizontales

        g.drawLine(30, 50, 90, 50);
        g.drawLine(150, 50, 210, 50);
        g.drawLine(270, 50, 330, 50);
        g.drawLine(390, 50, 450, 50);
        g.drawLine(510, 50, 590, 50);
        g.drawLine(30, 100, 90, 100);
        g.drawLine(150, 100, 210, 100);
        g.drawLine(270, 100, 330, 100);
        g.drawLine(390, 100, 450, 100);
        g.drawLine(510, 100, 590, 100);
        g.drawLine(30, 150, 90, 150);
        g.drawLine(150, 150, 210, 150);
        g.drawLine(270, 150, 330, 150);
        g.drawLine(390, 150, 450, 150);
        g.drawLine(510, 150, 590, 150);
        g.drawLine(30, 200, 90, 200);
        g.drawLine(150, 200, 210, 200);
        g.drawLine(270, 200, 330, 200);
        g.drawLine(390, 200, 450, 200);
        g.drawLine(510, 200, 590, 200);
        g.drawLine(30, 250, 90, 250);
        g.drawLine(150, 250, 210, 250);
        g.drawLine(270, 250, 330, 250);
        g.drawLine(390, 250, 450, 250);
        g.drawLine(510, 250, 590, 250);
        g.drawLine(30, 300, 90, 300);
        g.drawLine(150, 300, 210, 300);
        g.drawLine(270, 300, 330, 300);
        g.drawLine(390, 300, 450, 300);
        g.drawLine(510, 300, 590, 300);

        return;
    }

    // override (reescritura) del metodo paint definido en la clase JPanel
    public void paint(Graphics g) {
        super.paint(g); // llamar a clase padre para que ejecute lo suyo
        this.dibujaObjeto((Graphics2D)g, // typecasting
                super.getWidth(), // pedir el ancho en pixeles
                super.getHeight()); // altura actual en pixeles
        return;
    }

    private void reset() {
        this.x = 0;
        this.y = 0;

        return;
    }

    // constructor (no trae return ni tiene definido el tipo de salida)
    public Juego() { // porque extiende a JPanel
        super(); // llamamos al constructor de JPanel
        // valores iniciales a los atributos (entre cero y uno)
        this.reset();

        //Obteniendo la imagen
        try {
            URL resource = getClass().getResource("/");
            String path = resource.getPath();
            System.out.println(path);
            this.carrito = ImageIO.read(new FileInputStream(path + "resources/SmallCar.png"));
        } catch (IOException e) {
            System.out.println("Error obteniendo la imagen");
            System.out.println("Error loading car image" + e);
        }
    }

    //Evento cuando aplastas una tecla
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();

        switch (c) {
            case KeyEvent.VK_DOWN:
                this.y += Juego.PASO;
                break;
            case KeyEvent.VK_UP:
                this.y -= Juego.PASO;
                break;
            case KeyEvent.VK_LEFT:
                this.x -= Juego.PASO;
                break;
            case KeyEvent.VK_RIGHT:
                this.x += Juego.PASO;
                break;


            default: // si no fue ninguna de las flechas
                System.err.println("Tecla no conocida.");
                System.err.println("Unkown key");
                break;
        }

        //Limites maximos y minimos
        if(this.x < 0){
            this.x = 0.0;
        }else if(this.x > .975) {
            this.x = .975;
        }

        // Deja pasar 1
        if((x>=.0329 && x<=.04) &&(y>=0 && y<=.1484)) { this.x=prevx; }
        if((x>=.091 && x<=.143) &&(y>=0 && y<=.1484)) { this.x=.143; }

        if((x>=.0329 && x<=.09) &&(y>=.2969 && y<=.95)) { this.x=0.0329; }
        if((x>=.091 && x<=.143) &&(y>=.2969 && y<=.95)) { this.x=0.143; }

        // Deja pasar 2
        if ((x>=.2314 && x<=.3305) &&(y>=0 && y<=.5938)) { this.x=0.2314; }
        if ((x>=0.2314 && x<=.3305) &&(y>=.7422 && y<=.95)) { this.x=0.2314; }

        // Deja pasar 3
        if ((x>=.4247 && x<=.5288) &&(y>=.1484)) { this.x=.4247; }

        // Deja pasar 4
        if ((x>=.6445 && x<=.7271) &&(y>=0 && y<=.4453)) { this.x=.6445; }
        if ((x>=.6445 && x<=.7271) &&(y>=.5938 && y<=.95)) { this.x=.6445; }

        // Deja pasar 5 Entre las lineas horizontales 9 y 10 (510,570)
        if ((x>=.8263 && x<=.9254) &&(y>=0 && y<=.8906)) { this.x=.8263; }

        //Ganaste
        if (this.x > 0.9) {
            System.out.println("GANASTEEE!!! \n YOU WON ");
        }

        //Limites maximos y minimos

        if(this.y < 0){
            this.y = 0.0;
        }else if(this.y > .95){
            this.y = .95;
        }


        if(this.ancho < Juego.MIN){
            this.ancho = Juego.MIN;
        }else if(this.ancho > Juego.MAX){
            this.ancho = Juego.MAX;
        }

        if(this.altura < Juego.MIN){
            this.altura = Juego.MIN;
        }else if(this.altura > Juego.MAX){
            this.altura = Juego.MAX;
        }

        //Si el movimiento es invalido para regresar a la x anterior
        prevx = x;


        super.repaint();

        return;
        }


        public void keyReleased(KeyEvent e) {
            return; // no se usa ahora
        }

        public void keyTyped(KeyEvent e) {
            return; // no se usa ahora
        }

        //Main donde se corre el programa
        public static void main(String[] args) {
            //Creando el frame
            JFrame ventana = new JFrame();
            ventana.setTitle("Maneja el carro / Drive the car");
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventana.setLocation(100, 150);
            ventana.setSize(600, 400);

            Juego t = new Juego();

            //Creando el panel y agregando escuchadores, botones y layouts
            JPanel p = new JPanel();
            p.setFocusable(true);
            p.setLayout(new BorderLayout());
            JButton b = new JButton("RESET");
            b.setActionCommand("RESET");
            b.addKeyListener(t); // poner escuchador
            b.addActionListener(t);
            p.add(b, BorderLayout.NORTH);
            b = new JButton("INSTRUCCIONES / INSTRUCTIONS");
            b.setActionCommand("INS");
            p.add(b, BorderLayout.SOUTH);
            b.addActionListener(t);
            t.setBackground(Color.WHITE);
            p.addKeyListener(t); // poner escuchador
            p.add(t, BorderLayout.CENTER);
            //Agregando el panel al frame
            ventana.setContentPane(p);
            ventana.setVisible(true);

            return;
        }

}
