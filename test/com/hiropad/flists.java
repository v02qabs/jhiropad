package com.hiropad;
import javax.swing.event.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.lang.*;

class MainPanel
{
	/*public static void main(String[] args)
	{
		new print.show("Hello.");
		new Main().init();
	}*/
	public MainPanel()
	{}
	public JPanel init()
	{
		
		JTextArea text_area = new JTextArea();
		text_area.setBounds(0,0,500,400);
		JButton open_button = new JButton("開く");
		open_button.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				new FileSelect();
			}
		});

		open_button.setBounds(10,410,100,30);
		JPanel main_panel = new JPanel();
		main_panel.setBounds(0,0,500,500);
		main_panel.add(text_area);
		main_panel.add(open_button);
		return main_panel;
	}
}

class MainFrame extends JFrame
{
	public static void main(String[] args)
	{
		new MainFrame().init();
	}
	public MainFrame(){}
	private void init()
	{
		JPanel main_panel = new MainPanel().init();
		main_panel.setLayout(null);
		main_panel.setBounds(0,0,500,500);
		setBounds(0,0,500,500);
		setVisible(true);
		add(main_panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}


class FileSelect extends JFrame
{
	public FileSelect()
	{
		init();
	}
	private void init()
	{
		setLayout(null);
		setBounds(0,0,500,500);
		JTextField file_full_path = new JTextField();
		file_full_path.setBounds(0,0,300,20);
		JButton go_path_file = new JButton("go");
		JList file_lists = new JList();
		JScrollPane pane = new JScrollPane();
		pane.getViewport().setView(file_lists);
		pane.setBounds(0,30,400,400);
		
		go_path_file.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				new print().show("click go button");
				String line = file_full_path.getText().toString();
				go_file(line, file_lists);
			}
		});

		go_path_file.setBounds(310,0,100,20);
		add(file_full_path);
		add(go_path_file);
		add(pane);
		setVisible(true);
	}
	private void go_file(String line, JList file_lists)
	{
		JList files_lists = file_lists;
		new print().show("line: " + line);
		DefaultListModel model = new DefaultListModel();
		File list = new File(line);
		File[] files =	new File(line).listFiles();
		Arrays.sort(files);
		for(int i=0; i<files.length; i++)
		{
			int x = 0;
			if(files[i].isFile())
			{
				new print().show("F: " + files[i].getName());
				model.addElement(files[i].getName());
				files_lists.setModel(model);
			}
			else if(files[i].isDirectory())
			{
				new print().show("D: " + files[i].getName());
				model.addElement(files[i].getName());
				files_lists.setModel(model);
			}
		}
		files_lists.addListSelectionListener(new ListSelectionListener()
		{
			@Override
			public void valueChanged(ListSelectionEvent e)
		 	{
				    if (e.getValueIsAdjusting()) {
        				// The user is still manipulating the selection.
     				   	return;
				    }
				valueselect(line, file_lists);
			}
		});
	}
	private void valueselect(String line, JList file_lists)
	{
		Object obj = file_lists.getSelectedValue();
		String select_value = obj.toString();
		new print().show("select value " + select_value);
		int x=0;
		++x;
		new print().show("count x: " + String.valueOf(x));
		select_value = line + select_value;
		go_file(select_value, file_lists);
	}

	private void dir_select_files(String file_absolutepath)
	{
		new print().show("Select Dir: " + file_absolutepath);
		File[] files = new File(file_absolutepath).listFiles();
		for(File file : files)
		{
			new print().show("SelectFiles : " +  file.getName());
		}
	}

}

class print
{
	public void show(String message)
	{
		System.out.println(message);
	}
}

