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
 * Ez az osztály a megjelenítésért felelõs, ezért örököl a JFrame osztályból.
 * Az egyes komponensek (JPanel, JButton, JTable, JTextField, JComboBox, JScrollPane) ebben az osztályban
 * vannak definiálva és az actionlistenereket is ebben az osztályban kezelem. Ennek megfelelõen
 * van az osztályban egy MyActionListener belsõ osztály.
 */
public class MovieFrame extends JFrame {
	
	//adatbázis a táblázatban való megjelenítéshez
	private MovieDataBase data;
    
	//szövegmezõk az úg film hozzáadásához
    private JTextField title;
	private JTextField length;
	private JTextField premier;
	private JTextField director;
	
	//szövegmezõ a törléshez
	private JTextField torol_szoveg;
	
	//szövegmezõ a kereséshez
	private JTextField search;
	
	//egy JComboBox a keresésnél a szempont kiválasztásához
	@SuppressWarnings("rawtypes")
	private JComboBox combo;
	
	//egy JComboBox a törlésnél a szempont kiválasztásához
	@SuppressWarnings("rawtypes")
	private JComboBox combo2;
	
	//a JComboBoxokat ezzel a tömbbel töltöm fel
	Object[] c = {"cím", "hossz", "premier", "rendezõ"};
    
	/**
	 * Ebben a metódusban építem fel az ablakot.
	 */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private void initComponents() {
    	
        this.setLayout(new BorderLayout());
        JTable a = new JTable(data);
        a.setAutoCreateRowSorter(true); //rendezés ha a fejlécre kattintunk
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
        
        /*a szövegmezõk és JComboBoxok definiálása*/
		title = new JTextField(15);
		length = new JTextField(15);
		premier = new JTextField(15);
		director = new JTextField(15);
		search = new JTextField(15);
		torol_szoveg = new JTextField(15);
		combo = new JComboBox(c);
		combo2 = new JComboBox(c);
		
		/*a hozzáadó gomb létrahozása és egy actionlistener ráállítása*/
		JButton uj = new JButton("Hozzáad");
		uj.setActionCommand("hozzaad");
		ActionListener action = new MyActionListener();
		uj.addActionListener(action);
		
		/*a törlés gombok és az actionlistenerek*/
		JButton kijeloltek_torlese = new JButton("Kijelöltek törlése");
		JButton torol_gomb = new JButton("Törlés");
		kijeloltek_torlese.setActionCommand("delete");
		kijeloltek_torlese.addActionListener(action);
		torol_gomb.setActionCommand("torol");
		torol_gomb.addActionListener(action);
		
		/*a bal oldali panel létrehozása, címkék, szövegmezõk és gombok beállításával*/
		JPanel panel_bal = new JPanel(new GridBagLayout());
		panel_bal.add(new JLabel("Cím"), new GridBagConstraints(0, 0, 0, 1, 4, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(0,10,0,10),20,1));
		panel_bal.add(title, new GridBagConstraints(0, 1, 0, 1, 4, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(0,10,20,10),20,1));
		panel_bal.add(new JLabel("Rendezõ"), new GridBagConstraints(0, 2, 0, 1, 4, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(0,10,0,10),20,1));
		panel_bal.add(director, new GridBagConstraints(0, 3, 0, 1, 4, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(0,10,20,10),20,1));
		panel_bal.add(new JLabel("Premier"), new GridBagConstraints(0, 4, 0, 1, 4, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(0,10,0,10),20,1));
		panel_bal.add(premier, new GridBagConstraints(0, 5, 0, 1, 4, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(0,10,20,10),20,1));
		panel_bal.add(new JLabel("Hossz (percben)"),new GridBagConstraints(0, 6, 0, 1, 4, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(0,10,0,10),20,1));
		panel_bal.add(length, new GridBagConstraints(0, 7, 0, 1, 4, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(0,10,20,10),20,1));
		panel_bal.add(uj);
		
		/*a keresés gomb és az actionlistener*/
		JButton kereses = new JButton("Keresés");
		kereses.setActionCommand("kereses");
		kereses.addActionListener(action);
		
		/*a jobb oldali panel*/
		JPanel panel_jobb = new JPanel(new BorderLayout());
		JPanel panel_jobb_fent = new JPanel(new GridBagLayout());
		JPanel panel_jobb_fent_lent = new JPanel(); //a keresés
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
		
		/*Üres panel az alján*/
		JPanel panel_alul = new JPanel();
		
		/*Üres panel a tetején*/
		JPanel panel_felul = new JPanel();
		
		/*a JScrollPane görgethetõvé teszi a táblázatot*/
        JScrollPane sp = new JScrollPane(a);
        
        /*komponensek hozzáadása az ablakhoz*/
        this.add(panel_bal, BorderLayout.WEST);
        this.add(panel_jobb,BorderLayout.EAST);
        this.add(panel_alul, BorderLayout.SOUTH);
        this.add(panel_felul, BorderLayout.NORTH);
        this.add(sp,BorderLayout.CENTER);
    }
    
    /**
     * Ez az osztály a táblázat színezéséért felelõs, megvalósítja a TabbleCellRenderer
     * interfészt.
     */
    public class MovieTableCellRenderer implements TableCellRenderer{

    	private TableCellRenderer renderer;
    	
    	public MovieTableCellRenderer(TableCellRenderer renderer){
    		this.renderer = renderer;
    	}
    	
    	/**
    	 * ez a metódus állítja be a megfelelõ színeket
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
     * Ez az osztály felel a különbözõ eseménykezelésekért, implementálja az ActionListener
     * osztályt.
     */
	public class MyActionListener implements ActionListener{
		
		/**
		 * az ActionListener egy megvalósítandó függvénye, ez kezeli a különbözõ eseményeket
		 */
		@Override
		public void actionPerformed(ActionEvent e){
			if(e.getActionCommand().equals("hozzaad")){
				System.out.println("hejj");
				data.addMovie(new Movie(title.getText(), director.getText(), premier.getText(), Integer.parseInt(length.getText()))); // felvesz egy újat
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
	 * Az ablak konstruktora. A Program induláskor egy szerializált fájlból olvassa be az
	 * adatokat és bezáráskor pedig ugyanebbe a fájlba menti õket.
	 */
    @SuppressWarnings("unchecked")
    public MovieFrame() {
        super("Film adatbázis");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        /*Induláskor betöltjük az adatokat*/
       try {
            data = new MovieDataBase();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("movies.dat"));
            data.dataBase = (List<Movie>)ois.readObject();
            ois.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        /*Bezáráskor mentjük az adatokat*/
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

        /*Az ablak felépítése*/
        setMinimumSize(new Dimension(1000, 700));
        initComponents();
    }

    /**
     * main metódus, itt már csak létrehozunk egy keretet és láthatóvá tesszük
     * @param args
     */
    public static void main(String[] args) {
        MovieFrame mf = new MovieFrame();
        mf.setVisible(true);
    }
}
