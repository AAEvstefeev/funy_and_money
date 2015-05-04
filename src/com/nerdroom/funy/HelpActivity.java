package com.nerdroom.funy;

import com.nerdroom.fcash.help.MyActivity;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class HelpActivity extends MyActivity {
	protected void onCreate(Bundle savedInstanceState) {
		//    setTheme(R.style.Theme_Sherlock);
		    super.onCreate(savedInstanceState);
		    setContentView(R.layout.help);
	
		    TextView tv = (TextView) findViewById(R.id.tv_help);
String text_help="<P CLASS=\"western\" STYLE=\"margin-bottom: 0cm\">Данная версия приложения является <U><B>ТЕСТОВОЙ</B></U>.</P><P CLASS=\"western\" STYLE=\"margin-bottom: 0cm\">Приглашая друзей в свой круг сейчас – вы <U>гарантированно</U> получите неожиданный <B>Сюрприз</B> в <U>полной версии</U> (Чем больше друзей, тем <B>Сюрприз</B> интереснее<FONT FACE=\"Wingdings\"><SPAN LANG=\"en-US\"></SPAN></FONT>).</P><P CLASS=\"western\" STYLE=\"margin-bottom: 0cm\"><BR></P><P CLASS=\"western\" STYLE=\"margin-bottom: 0cm\">ПРАВИЛА УЧАСТИЯ В РОЗЫГРЫШЕ:</P><P CLASS=\"western\" STYLE=\"margin-bottom: 0cm\">1. Зарегистрируйтесь в приложении.</P>"+
"<P CLASS=\"western\" STYLE=\"margin-bottom: 0cm\">2. Дату и время начала и окончания розыгрыша вы можете увидеть на главном экране приложения.</P> <P CLASS=\"western\" STYLE=\"margin-bottom: 0cm\">3. Для участия просматривай разделы приложения и зарабатывай <U>монеты </U><SPAN LANG=\"en-US\"><U>FC</U></SPAN>.</P> <P CLASS=\"western\" STYLE=\"margin-bottom: 0cm\">4. Заходи в раздел <B>Лидеры</B> и следи за своей позицией в общем рейтинге.</P> <P CLASS=\"western\" STYLE=\"margin-bottom: 0cm\">5. Двадцатка участников (<B>ТОП 20</B>) набравших наибольшее количество <U>монет </U><SPAN LANG=\"en-US\"><U>FC</U></SPAN><U>,</U> получат призы в размере от <B>500</B> до <B>50</B> ₱, в зависимости от занятого места (В"+
" целях конфиденциальности, сумму выигрыша, которая напрямую связанна с местом занятым в (<B>ТОП 20</B>), вы сможете увидеть в личном кабинете по окончанию розыгрыша).</P> <P CLASS=\"western\" STYLE=\"margin-bottom: 0cm\"><BR> </P> <P CLASS=\"western\" STYLE=\"margin-bottom: 0cm\"><U>Розыгрыши проводятся не менее трех раз в неделю.</U></P>";
		    tv.setText(Html.fromHtml(text_help));
		    
		    
	
	}
}
