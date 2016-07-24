package task;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class Constants {
	public static String fontFamily ="Segoe UI";
	
	// Colors
	public static Color transparent = new Color(0,0,0,0);
	public static Color backgroundColor = new Color(245,245,245);
	public static Color backgroundColor_02 = new Color(44,44,44);
	public static Color fontColor= new Color(51,51,51);
	public static Color fontColor_02= new Color(170,170,170);
	public static Color focusColor = new Color(201,208,217);
	public static Color accentColor = new Color(0,122,204);
	
	public static JButton CreateMyButton(String content)
	{
		JButton b = new JButton(content);
		b.setForeground(Color.WHITE);
		b.setBackground(backgroundColor_02);
		b.setFont(new Font(fontFamily, Font.BOLD, 16));
		//b.setBorderPainted(false);
		b.setBorder(new EmptyBorder(10,10,10,10));
		return b;
	}
	
	public static JRadioButton CreateMyRadioButton(String text)
	{
		JRadioButton radio = new JRadioButton(text);
		Font font = new Font(fontFamily, Font.PLAIN, 15);
		radio.setFont(font);
		radio.setForeground(Color.WHITE);
		radio.setBackground(backgroundColor_02);
		radio.setBorder(new EmptyBorder(5, 5, 5, 5));
		return radio;
	}
	
	public static JLabel CreateMyLabel(String text, int fontSize, int style, float alignment)
	{
		Font font = new Font(fontFamily, style, fontSize);
		JLabel l = new JLabel(text);
		l.setAlignmentX(alignment);
		l.setFont(font);
		l.setForeground(fontColor);
		return l;
	}
	
	
	public static JTextArea CreateMyTextarea(String text, int fontSize, int style, float alignment)
	{
		Font font = new Font(fontFamily, style, fontSize);
		JTextArea  textArea = new JTextArea(text);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		textArea.setOpaque(false);
		textArea.setAlignmentX(alignment);
		textArea.setFont(font);
		textArea.setForeground(fontColor);
		return textArea;
	}
}
