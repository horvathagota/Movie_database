package movie;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

/**
 * Ez az oszt�ly a megjelen�t�s�rt felel�s, ez�rt �r�k�l a JFrame oszt�lyb�l.
 * Az egyes komponensek (JPanel, JButton, JTable, JTextField, JComboBox, JScrollPane) ebben az oszt�lyban
 * vannak defini�lva �s az actionlistenereket is ebben az oszt�lyban kezelem. Ennek megfelel�en
 * van az oszt�lyban egy MyActionListener bels� oszt�ly.
 */
public class MovieFrame extends JFrame {
	
	//adatb�zis a t�bl�zatban val� megjelen�t�shez
	private MovieDataBase data;
    
	//sz�vegmez�k az �g film hozz�ad�s�hoz
    private JTextField title;
	private JTextField length;
	private JTextField premier;
	private JTextField director;
	
	//sz�vegmez� a t�rl�shez
	private JTextField torol_szoveg;
	
	//sz�vegmez� a keres�shez
	private JTextField search;
	
	//egy JComboBox a keres�sn�l a szempont kiv�laszt�s�hoz
	@SuppressWarnings("rawtypes")
	private JComboBox combo;
	
	//egy JComboBox a t�rl�sn�l a szempont kiv�laszt�s�hoz
	@SuppressWarnings("rawtypes")
	private JComboBox combo2;
	
	//a JComboBoxokat ezzel a t�mbbel t�lt�m fel
	Object[] c = {"c�m", "hossz", "premier", "rendez�"};
    
	/**
	 * Ebben a met�dusban �p�tem fel az ablakot.
	 */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private void initComponents() {
    	
        this.setLayout(new BorderLayout());
        JTable a = new JTable(data);
        a.setAutoCreateRowSorter(true); //rendez�s ha a fejl�cre kattintunk
        data.fireTableDataChanged();
        a.setFillsViewportHeight(true);
        
        a.setDefaultRenderer(a.getColumnClass(0), new MovieTableCellRenderer(
                a.getDefaultRenderer(a.getColumnClass(0))));
        a.setDefaultRenderer(a.getColumnClass(1), new MovieTableCellRenderer(
                a.getDefaultRenderer(a.getColumnClass(1))));
        a.setDefaultRenderer(a.getColumnClass(2), new MovieTableCellRenderer(
                a.getDefaultRenderer(a.getColumnClass(2))));
        a.setDefaultRenderer(a.getColumnClass(3), new MovieTableCellRenderer(
                a.getDefaultRenderer(a.getColumnClass(3))));
        a.setDefaultRenderer(a.getColumnClass(4), new MovieTableCellRenderer(
                a.getDefaultRenderer(a.getColumnClass(4))));
        
        /*a sz�vegmez�k �s JComboBoxok defini�l�sa*/
		title = new JTextField(15);
		length = new JTextField(15);
		premier = new JTextField(15);
		director = new JTextField(15);
		search = new JTextField(15);
		torol_szoveg = new JTextField(15);
		combo = new JComboBox(c);
		combo2 = new JComboBox(c);
		
		/*a hozz�ad� gomb l�trahoz�sa �s egy actionlistener r��ll�t�sa*/
		JButton uj = new JButton("Hozz�ad");
		uj.setActionCommand("hozzaad");
		ActionListener action = new MyActionListener();
		uj.addActionListener(action);
		
		/*a t�rl�s gombok �s az actionlistenerek*/
		JButton kijeloltek_torlese = new JButton("Kijel�ltek t�rl�se");
		JButton torol_gomb = new JButton("T�rl�s");
		kijeloltek_torlese.setActionCommand("delete");
		kijeloltek_torlese.addActionListener(action);
		torol_gomb.setActionCommand("torol");
		torol_gomb.addActionListener(action);
		
		/*a bal oldali panel l�trehoz�sa, c�mk�k, sz�vegmez�k �s gombok be�ll�t�s�val*/
		JPanel panel_bal = new JPanel(new GridBagLayout());
		panel_bal.add(new JLabel("C�m"), new GridBagConstraints(0, 0, 0, 1, 4, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(0,10,0,10),20,1));
		panel_bal.add(title, new GridBagConstraints(0, 1, 0, 1, 4, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(0,10,20,10),20,1));
		panel_bal.add(new JLabel("Rendez�"), new GridBagConstraints(0, 2, 0, 1, 4, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(0,10,0,10),20,1));
		panel_bal.add(director, new GridBagConstraints(0, 3, 0, 1, 4, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(0,10,20,10),20,1));
		panel_bal.add(new JLabel("Premier"), new GridBagConstraints(0, 4, 0, 1, 4, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(0,10,0,10),20,1));
		panel_bal.add(premier, new GridBagConstraints(0, 5, 0, 1, 4, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(0,10,20,10),20,1));
		panel_bal.add(new JLabel("Hossz (percben)"),new GridBagConstraints(0, 6, 0, 1, 4, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(0,10,0,10),20,1));
		panel_bal.add(length, new GridBagConstraints(0, 7, 0, 1, 4, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(0,10,20,10),20,1));
		panel_bal.add(uj);
		
		/*a keres�s gomb �s az actionlistener*/
		JButton kereses = new JButton("Keres�s");
		kereses.setActionCommand("kereses");
		kereses.addActionListener(action);
		
		/*a jobb oldali panel*/
		JPanel panel_jobb = new JPanel(new BorderLayout());
		JPanel panel_jobb_fent = new JPanel(new GridBagLayout());
		JPanel panel_jobb_fent_lent = new JPanel(); //a keres�s
		panel_jobb_fent_lent.add(search);
		panel_jobb_fent_lent.add(kereses);
		JPanel panel_jobb_lent = new JPanel(new GridBagLayout());
		panel_jobb_fent.add(combo, new GridBagConstraints(0, 0, 0, 1, 10, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(190,10,0,10),1,1));
		panel_jobb_fent.add(panel_jobb_fent_lent, new GridBagConstraints(0, 1, 0, 1, 4, 1, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(10,10,0,10),10,1));
		
		
		JPanel panel_jobb_lent_kozep = new JPanel();
		panel_jobb_lent_kozep.add(torol_szoveg);
		panel_jobb_lent_kozep.add(torol_gomb);
		panel_jobb_lent.add(combo2, new GridBagConstraints(0, 0, 0, 1, 10, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(0,10,0,10),1,1));
		panel_jobb_lent.add(panel_jobb_lent_kozep, new GridBagConstraints(0, 1, 0, 1, 10, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(10,10,0,10),1,1));
		panel_jobb_lent.add(kijeloltek_torlese, new GridBagConstraints(60, 2, 60, 50, 30, 30, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(10,10,190,10),1,1));
		
		panel_jobb.add(panel_jobb_lent, BorderLayout.SOUTH);
		panel_jobb.add(panel_jobb_fent, BorderLayout.NORTH);
		
		/*�res panel az alj�n*/
		JPanel panel_alul = new JPanel();
		
		/*�res panel a tetej�n*/
		JPanel panel_felul = new JPanel();
		
		/*a JScrollPane g�rgethet�v� teszi a t�bl�zatot*/
        JScrollPane sp = new JScrollPane(a);
        
        /*komponensek hozz�ad�sa az ablakhoz*/
        this.add(panel_bal, BorderLayout.WEST);
        this.add(panel_jobb,BorderLayout.EAST);
        this.add(panel_alul, BorderLayout.SOUTH);
        this.add(panel_felul, BorderLayout.NORTH);
        this.add(sp,BorderLayout.CENTER);
    }
    
    /**
     * Ez az oszt�ly a t�bl�zat sz�nez�s��rt felel�s, megval�s�tja a TabbleCellRenderer
     * interf�szt.
     */
    public class MovieTableCellRenderer implements TableCellRenderer{

    	private TableCellRenderer renderer;
    	
    	public MovieTableCellRenderer(TableCellRenderer renderer){
    		this.renderer = renderer;
    	}
    	
    	/**
    	 * ez a met�dus �ll�tja be a megfelel� sz�neket
    	 */
		@Override
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			Component component = renderer.getTableCellRendererComponent(
					 table, value, isSelected, hasFocus, row, column);

			boolean found = (Boolean)table.getValueAt(row, 4);
		    
			if(found)
		    	component.setBackground(new Color(255,105,180));
		    else if(row % 2 == 0)
		    	component.setBackground(new Color(255,225,255));
		    else
		    	component.setBackground(new Color(255,192,203));
			
			return component;
		}
    	
    }
    
    /**
     * Ez az oszt�ly felel a k�l�nb�z� esem�nykezel�sek�rt, implement�lja az ActionListener
     * oszt�lyt.
     */
	public class MyActionListener implements ActionListener{
		
		/**
		 * az ActionListener egy megval�s�tand� f�ggv�nye, ez kezeli a k�l�nb�z� esem�nyeket
		 */
		@Override
		public void actionPerformed(ActionEvent e){
			if(e.getActionCommand().equals("hozzaad")){
				System.out.println("hejj");
				data.addMovie(new Movie(title.getText(), director.getText(), premier.getText(), Integer.parseInt(length.getText()))); // felvesz egy �jat
			}
			else if(e.getActionCommand().equals("delete")){
				data.delete();
			}
			else if(e.getActionCommand().equals("torol")){
				data.delete((String)combo2.getSelectedItem(), torol_szoveg.getText());
			}
			else if(e.getActionCommand().equals("kereses")){
				data.searchIf((String)combo.getSelectedItem(), search.getText());
			}
		}

	}
	
	/**
	 * Az ablak konstruktora. A Program indul�skor egy szerializ�lt f�jlb�l olvassa be az
	 * adatokat �s bez�r�skor pedig ugyanebbe a f�jlba menti �ket.
	 */
    @SuppressWarnings("unchecked")
    public MovieFrame() {
        super("Film adatb�zis");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        /*Indul�skor bet�ltj�k az adatokat*/
       try {
            data = new MovieDataBase();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("movies.dat"));
            data.dataBase = (List<Movie>)ois.readObject();
            ois.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        /*Bez�r�skor mentj�k az adatokat*/
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("movies.dat"));
                    oos.writeObject(data.dataBase);
                    oos.close();
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        /*Az ablak fel�p�t�se*/
        setMinimumSize(new Dimension(1000, 700));
        initComponents();
    }

    /**
     * main met�dus, itt m�r csak l�trehozunk egy keretet �s l�that�v� tessz�k
     * @param args
     */
    public static void main(String[] args) {
        MovieFrame mf = new MovieFrame();
        mf.setVisible(true);
    }
}
