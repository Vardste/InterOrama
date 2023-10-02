package com;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;


public class ButtonComponents extends SelectorComposer<Component>
{	  
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Wire
	private Window win;
	@Wire
	private Textbox userNameTextbox;
	@Wire
	private Datebox birthDateBox;
	@Wire
	private Intbox barcodeIntbox;
	@Wire
	private Doublebox unitPriceDoublebox;
	@Wire
	private Spinner quantitySpinner;
	@Wire
	private Textbox locationTextbox;

	public void showNotif(String msg, Component ref)
	{
		Clients.showNotification(msg, "info", ref, "end_center", 2000);
	}
	
	@Listen("onChange=#titleTextbox")
	public void title()
	{
		String title = userNameTextbox.getValue();
		System.out.println("Title : " + title);
		showNotif("Change To : " + title, userNameTextbox);
	}
	
	@Listen("onChange=#createdDatebox")
	public void dateBox()
	{
		Date dateBox = birthDateBox.getValue();
		System.out.println("Date : " + dateBox);
		DateFormat formater = new SimpleDateFormat(birthDateBox.getFormat());
		showNotif("Change To : " + formater.format(dateBox), birthDateBox);
	}
	
	 @Listen("onChange = #barcodeIntbox")
	    public void changeBarcode() {
	        Integer barcode = barcodeIntbox.getValue();
	        NumberFormat formatter = new DecimalFormat(barcodeIntbox.getFormat());
	        showNotif("Changed to: " + formatter.format(barcode), barcodeIntbox);
	    }
	 
	    @Listen("onChange = #unitPriceDoublebox")
	    public void changeUnitPrice() {
	        Double unitPrice = unitPriceDoublebox.getValue();	      
	        NumberFormat formatter = new DecimalFormat(unitPriceDoublebox.getFormat());
	        showNotif("Changed to: " + formatter.format(unitPrice), unitPriceDoublebox);
	    }
	 
	    @Listen("onChange = #quantitySpinner")
	    public void changeQuantity() 
	    {
	        Integer quantity = quantitySpinner.getValue(); 
	        NumberFormat formatter = new DecimalFormat(quantitySpinner.getFormat());
	        showNotif("Changed to: " + formatter.format(quantity), quantitySpinner);
	    }
	 
	    @Listen("onChange = #locationTextbox")
	    public void changeLocation() 
	    {
	        String location = locationTextbox.getValue();
	        showNotif("Changed to:" + location, locationTextbox);
	    }
	 
	    @Listen("onClick = #submitButton")
	    public void submit() 
	    {
	    	showNotif("Saved", win);
	    }	
}
