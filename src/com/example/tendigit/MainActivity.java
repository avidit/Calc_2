package com.example.tendigit;

//import com.xperttech.helloworld.MainActivity;

import java.util.ArrayList;
import java.math.*;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {

	Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0;
	Button btnPlus,btnMinus,btnDivide,btnTimes,btnClear,btnEqual;
	Button btnMemadd, btnMemminus, btnMemread, btnMemclear;
	Button btnNegate, btnSquare, btnSqrt, btnPower, btnInv;

	double numberOne=0;
	double numberTwo=0;
	double numberResult=0;
	double memory=0;

	TextView screen;

	boolean operationdone = false;
	boolean userIsInMiddleOfTypingANumber = false;

	String operationEntered = null;
	//private OnClickListener spListener;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		MyNumberListener myNumberListner = new MyNumberListener();
		MyOperationListener myOperationListner = new MyOperationListener();
		MyCleanListener myCleanListener = new MyCleanListener();
		MySpListener mySpListener = new MySpListener(); 


		setContentView(R.layout.activity_main);
		screen= (TextView)findViewById(R.id.txtResult);
		btn1=(Button)findViewById(R.id.btnOne);
		btn2=(Button)findViewById(R.id.btnTwo);
		btn3=(Button)findViewById(R.id.btnThree);
		btn4=(Button)findViewById(R.id.btnFour);
		btn5=(Button)findViewById(R.id.btnFive);
		btn6=(Button)findViewById(R.id.btnSix);
		btn7=(Button)findViewById(R.id.btnSeven);
		btn8=(Button)findViewById(R.id.btnEight);
		btn9=(Button)findViewById(R.id.btnNine);
		btn0=(Button)findViewById(R.id.btnZero);
		btnPlus=(Button)findViewById(R.id.btnPlus);
		btnMinus=(Button)findViewById(R.id.btnMinus);
		btnDivide=(Button)findViewById(R.id.btnDivide);
		btnTimes=(Button)findViewById(R.id.btnMultiply);
		btnClear=(Button)findViewById(R.id.btnClear);
		btnEqual=(Button)findViewById(R.id.btnEqual);
		btnSquare=(Button)findViewById(R.id.btnSquare);
		btnSqrt=(Button)findViewById(R.id.btnSqrt);
		btnPower=(Button)findViewById(R.id.btnPower);
		btnNegate=(Button)findViewById(R.id.btnNegate);
		btnInv=(Button)findViewById(R.id.btnInv);
		btnMemadd=(Button)findViewById(R.id.memAdd);
		btnMemminus=(Button)findViewById(R.id.memMinus);
		btnMemread=(Button)findViewById(R.id.memRead);
		btnMemclear=(Button)findViewById(R.id.memClear);


		btn1.setOnClickListener(myNumberListner);
		btn2.setOnClickListener(myNumberListner);
		btn3.setOnClickListener(myNumberListner);
		btn4.setOnClickListener(myNumberListner);
		btn5.setOnClickListener(myNumberListner);
		btn6.setOnClickListener(myNumberListner);
		btn7.setOnClickListener(myNumberListner);
		btn8.setOnClickListener(myNumberListner);
		btn9.setOnClickListener(myNumberListner);
		btn0.setOnClickListener(myNumberListner);
		btnPlus.setOnClickListener(myOperationListner);
		btnMinus.setOnClickListener(myOperationListner);
		btnTimes.setOnClickListener(myOperationListner);
		btnDivide.setOnClickListener(myOperationListner);
		btnClear.setOnClickListener(myCleanListener);
		btnEqual.setOnClickListener(myOperationListner);
		btnSquare.setOnClickListener(mySpListener);
		btnSqrt.setOnClickListener(mySpListener);
		btnPower.setOnClickListener(mySpListener);
		btnNegate.setOnClickListener(mySpListener);
		btnInv.setOnClickListener(mySpListener);
		btnMemadd.setOnClickListener(mySpListener);
		btnMemminus.setOnClickListener(mySpListener);
		btnMemread.setOnClickListener(mySpListener);
		btnMemclear.setOnClickListener(mySpListener);

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	class MyCleanListener implements OnClickListener

	{
		@Override
		public void onClick(View v) {
			screen.setText("");

		}
	}
	class MyNumberListener implements OnClickListener
	{
		@Override
		public void onClick(View arg0) {	
			Button whichButton = (Button) arg0;
			String strNumberEntered = whichButton.getText().toString();
			int numberAsInteger = Integer.parseInt(strNumberEntered);
			if(operationdone)
			{
				screen.setText("");
				operationdone = false;
			}

			if(!userIsInMiddleOfTypingANumber)
			{
				numberOne = numberAsInteger;
				userIsInMiddleOfTypingANumber = true;	
				screen.setText(strNumberEntered);
			}
			else
			{
				String oldNumber;
				String newNumber;
				oldNumber = screen.getText().toString() ;
				newNumber = oldNumber + strNumberEntered;
				screen.setText(newNumber);

			}
		}

	}
	class MyOperationListener implements OnClickListener
	{
		@Override
		public void onClick(View arg0) {
			
			Button whichButton = (Button) arg0;
			operationEntered = whichButton.getText().toString();
			operationdone = false;

			if(userIsInMiddleOfTypingANumber)
			{
				if(operationEntered.equals("="))
				{
					performMath();
					return;
				}
				String existingText = screen.getText().toString() ;
				String newText = existingText + operationEntered;
				screen.setText(newText);
			}
		}
	}
	class MySpListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			double number=0;
		
			number = getnumber();
			switch(v.getId())
			{
			case R.id.btnSquare:
				number = number*number;
				break;
			case R.id.btnSqrt:
				number = Math.sqrt(number);
				break;
			case R.id.btnPower:
				double base = number;
				//valueNumberIsDone=true;
				screen.setText("");
				double exponent = getnumber();
				number= Math.pow(base, exponent);				
				break;
			case R.id.btnNegate:
				number = -1*number;
				break;
			case R.id.btnInv:
				number = 1/number;
				break;
			}
			screen.setText(String.valueOf(number));
		}
	}

	void performMath()
	{
		String textEnteredSoFar = screen.getText().toString();
		int length = textEnteredSoFar.length();
		ArrayList<String> valuesArray = new ArrayList<String>();
		char operand = ' ';
		boolean value1IsDone = false;


		for(int i=0; i<length; i++)
		{
			char currentCharacter = textEnteredSoFar.charAt(i);
			if(currentCharacter == '+')
			{
				operand = currentCharacter;
				value1IsDone = true;
			}
			else if(currentCharacter == '-')
			{
				value1IsDone = true;
				operand = currentCharacter;

			}
			else if(currentCharacter == '*')
			{
				value1IsDone = true;
				operand = currentCharacter;

			}
			else if(currentCharacter == '/')
			{
				value1IsDone = true;
				operand = currentCharacter;
			}
			else
			{

				if(value1IsDone)
				{
					valuesArray.add(String.valueOf(currentCharacter));
				}
				else
				{
					valuesArray.add(String.valueOf(currentCharacter));
				}
				operationdone = true;
			}
		}

		if(operand == '+')
		{
			doAddition(valuesArray);
		}
		if(operand == '-')
		{
			//doSubstraction(valuesArray);
		}
		if(operand == '*')
		{
			//doMultiplication(valuesArray);
		}
		if(operand == '/')
		{
			//doDivision(valuesArray);
		}
	}

	void doAddition(ArrayList<String> valuesArray)
	{
		int runningTotal = 0;

		for(int i=0; i<valuesArray.size(); i+=1)
		{// todo
			int number2 =  0;
			int number1 = Integer.parseInt(valuesArray.get(i));
			
				number2 = Integer.parseInt(valuesArray.get(i));
			
			int tot = number1 + number2;
			runningTotal += tot;
		}
		screen.setText(String.valueOf(runningTotal));
	}


	void doSubstraction(String value1, String value2)
	{
		int number1 = Integer.parseInt(value1);
		int number2 = Integer.parseInt(value2);

		int tot = number1 - number2;
		screen.setText(String.valueOf(tot));
	}
	void doMultiplication(String value1, String value2)
	{
		int number1 = Integer.parseInt(value1);
		int number2 = Integer.parseInt(value2);

		int tot = number1 * number2;
		screen.setText(String.valueOf(tot));
	}
	void doDivision(String value1, String value2)
	{
		int number1 = Integer.parseInt(value1);
		int number2 = Integer.parseInt(value2);

		int tot = number1 / number2;
		screen.setText(String.valueOf(tot));
	}
	int getnumber()
	{
		String textEnteredSoFar = screen.getText().toString();
		int num=0;
		int length = textEnteredSoFar.length();
		for(int i=0; i<length; i++)
		{
			char currentCharacter = textEnteredSoFar.charAt(i);
			if(currentCharacter != '+' && currentCharacter != '-' && currentCharacter != '/' && currentCharacter != '*')
			{
				String numberInTheBox;
				numberInTheBox = screen.getText().toString();
				num = Integer.parseInt(numberInTheBox);
			}
		}

		return num;
	}

};



