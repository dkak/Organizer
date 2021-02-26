import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Component;
import javax.swing.UIManager;
import java.awt.Cursor;
import java.awt.SystemColor;

public class FilterJList extends JFrame {
	
	private JFrame frame;
	private JPanel mainPanel,panelBtns,panelBottom,panelSearch;
	private JScrollPane scrollPane;
	private JLabel searchLabel;
	private JTextField searchTxt;
	private JButton btnAdd,btnUpdate,btnDelete;
	private JList myJList;		
	private ArrayList<Product> productsList;
	DefaultListModel defaultListModel=new DefaultListModel();

/*Constructor*/
		    public FilterJList(ArrayList<Product> productsList) {
		    	this.productsList=productsList;
		        initComponents();
		        this.bindData();
		    }
		    
/*Bind data to JList*/
		    private void bindData(){
		        productsList.stream().forEach((p) -> {
		            defaultListModel.addElement(p.getTitle());
		        });
		    }
		     
/*Initializing components*/		    
		    private void initComponents() {
		    	frame= new JFrame();
		    	mainPanel = new JPanel();
		    	panelBtns=new JPanel();
		    	panelSearch=new JPanel();
		    	panelSearch.setMinimumSize(new Dimension(10, 50));
		    	panelSearch.setPreferredSize(new Dimension(10, 50));
		    	panelSearch.setMaximumSize(new Dimension(32767, 50));
		    	panelBottom=new JPanel();
		    	panelBottom.setPreferredSize(new Dimension(300, 500));
		        searchTxt = new JTextField(15);
		        searchTxt.setCaretColor(Color.GRAY);
		        searchTxt.setBorder(UIManager.getBorder("RadioButtonMenuItem.border"));
		        searchTxt.setSelectedTextColor(Color.BLACK);
		        searchTxt.setSelectionColor(Color.WHITE);
		        searchLabel = new JLabel();

/*JList/ScrollPane*/
		        //Initialization
		        myJList=new JList();
		        myJList.setForeground(new Color(0, 0, 0));
		        myJList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		        myJList.setBackground(SystemColor.textHighlightText);
		        myJList.setSelectionForeground(Color.BLACK);
		        myJList.setSelectionBackground(Color.YELLOW);
		    	scrollPane=new JScrollPane(myJList);
		    	scrollPane.setViewportBorder(UIManager.getBorder("CheckBoxMenuItem.border"));
		    	scrollPane.setMaximumSize(new Dimension(600, 400));
		    	scrollPane.setPreferredSize(new Dimension(300, 200));
		    	
		    	
		        
/*Search Text/Label customization*/
		        searchTxt.setFont(new Font("Calibri", Font.PLAIN, 15));
		        searchTxt.addKeyListener(new java.awt.event.KeyAdapter() {
		            public void keyReleased(java.awt.event.KeyEvent evt) {
		                searchTxtKeyReleased(evt);
		            }
		        });
		        searchLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
		        searchLabel.setForeground(Color.YELLOW);
		        searchLabel.setText("Αναζήτηση : ");
		        
		   
		        
/*Listeners*/	        
		        //Button Listener
		        ButtonListener buttonListener=new ButtonListener();
		        
		        //JList Listener
		        myJList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
                myJList.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        myJListMouseClicked(evt);
                    }
                });

		        
/*Buttons*/	
		        //Add Button
		        btnAdd = new JButton("Add");
		        btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
		        btnAdd.setBackground(Color.GREEN);
		        btnAdd.addActionListener(buttonListener);
		        btnAdd.setForeground(Color.BLACK);
		        //Update Button
		        btnUpdate = new JButton("Update");
		        btnUpdate.setBackground(Color.YELLOW);
		        btnUpdate.addActionListener(buttonListener);
		        //Delete Button
		        btnDelete = new JButton("Delete");
		        btnDelete.setBackground(Color.RED);
		        btnDelete.setAlignmentX(Component.CENTER_ALIGNMENT);
		        btnDelete.addActionListener(buttonListener);
		        

/*Layouts*/	
		        //mainPanel Layout
		        mainPanel.setBorder(new LineBorder(Color.BLUE));
		        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		       
		        //panelBottom Layout
		        panelBottom.setLayout(new BoxLayout(panelBottom, BoxLayout.Y_AXIS));

		        
/*Panels*/     
		        //Coloring background Panels
		        panelBtns.setBackground(Color.GRAY);
		        panelSearch.setBackground(Color.GRAY);
		        panelBottom.setBackground(Color.GRAY);

		        //Adding elements to sub-panels 
		        panelBtns.add(btnDelete);
		        panelBtns.add(btnUpdate);
		        panelBtns.add(btnAdd);
		        panelSearch.add(searchLabel);
		        panelSearch.add(searchTxt);
		        panelBottom.add(panelSearch);
		        panelBottom.add(scrollPane);

		        //Adding elements to Main Panel
		        mainPanel.add(panelBtns);
		        mainPanel.add(panelBottom);
		        mainPanel.setBackground(Color.GRAY);
		        

/*Frame*/
		        frame.getContentPane().add(mainPanel);
		        frame.setTitle("Driver Page");
				frame.setSize(800, 600);
				frame.setResizable(false);
				frame.setVisible(true);
				frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		        pack();
		    }
		    
		 
/*Search Filter data*/
		    private void searchFilter(String searchTerm)
		    {
		        DefaultListModel filteredItems=new DefaultListModel();
		        productsList.stream().forEach((p) -> {
		            String title=p.getTitle().toLowerCase();
		            if (title.contains(searchTerm.toLowerCase())) {
		                filteredItems.addElement(p.getTitle());
		            }
		        });
		        defaultListModel=filteredItems;
		        myJList.setModel(defaultListModel);
		    }
		    @SuppressWarnings("unchecked")
		    
		    
/*Key Released Event*/
		    //KeyReleased Event
		    private void searchTxtKeyReleased(java.awt.event.KeyEvent evt) {                                      
		        searchFilter(searchTxt.getText());
		    }                                     

		    
/*Double Click on Item*/
		    //MouseClickedEvent
		    private void myJListMouseClicked(MouseEvent evt) {  
		    	
		    	if (evt.getClickCount() == 2 && !evt.isConsumed()) {
		    	     evt.consume();
		    	     
		    	    Product product=null;
		    	    String productTitle = (String)myJList.getSelectedValue();
		    		for(Product p: productsList) {
		    			if(p.getTitle().equals(productTitle)) {
		    				product=p;
		    			}
		    		}
		   
					JOptionPane.showMessageDialog( null,"Προϊόν : "+ product.getTitle()+"\n\n"+"Τιμή : "+product.getPrice()+" Ε\n\nΠοσότητα :"+product.getQuantity()+"\n\nΣύνδεσμος : ");
		    	}
		    }                                    
   
		        
/*Button Listener */
		    //ButtonListener
		    class ButtonListener implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					
					}
				}
		    }                       



