package com.android.ilmanaque;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.ContentValues;

public class DBHelper extends SQLiteOpenHelper {
	
	private static final int DB_VERSION = 1;
	private static final String DB_NAME = "Agri.db";
	
	public static final String TAB_PLANT = "PlantationTable";
	public static final String P_ID = "_id";
	public static final String P_PLANT = "Plant";
	public static final String P_LOC = "Location";
	public static final String P_DATE = "Date";
	public static final String P_QUANT = "Quantity";
	public static final String P_OBS = "Observations";
	public static final String P_PHOTO = "Photo";
	
	public static final String TAB_TASK = "TaskTable";
	public static final String T_ID = "_id";
	public static final String T_TITLE = "Title";
	public static final String T_DESC = "Description";
	public static final String T_DATE = "Date";

	public static final String TAB_INF = "InfoTable";
	public static final String I_ID = "_id";
	public static final String I_SEMENTE = "Semente";
	public static final String I_OBS = "Observation";
	public static final String I_MES_P = "Mes";
	public static final String I_DIAS_C = "Dias";
	public static final String I_PH = "PH";
	
   public DBHelper(Context context){
      super(context,DB_NAME,null,DB_VERSION);
   }
   
   @Override
   public void onCreate(SQLiteDatabase db) {
	
	String plant_tab = "CREATE TABLE "+TAB_PLANT+"("
	+ P_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
	+ P_PLANT + " TEXT NOT NULL,"
	+ P_LOC + " TEXT NOT NULL,"
	+ P_DATE + " DATE NOT NULL,"
	+ P_QUANT + " TEXT NOT NULL,"
	+ P_OBS + " TEXT NOT NULL,"
	+ P_PHOTO  +" BLOB"
	+")";
	
	String task_tab = "CREATE TABLE "+TAB_TASK+"("
	+ T_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
	+ T_TITLE + " TEXT NOT NULL,"
	+ T_DESC + " TEXT NOT NULL,"
	+ T_DATE + " DATE NOT NULL"
	+")";

		
	String info_tab = "CREATE TABLE "+TAB_INF+"("
	+ I_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
	+ I_SEMENTE + " TEXT NOT NULL,"
	+ I_OBS + " TEXT NOT NULL,"
	+ I_MES_P + " TEXT NOT NULL,"
	+ I_DIAS_C + " INTEGER NOT NULL,"
	+ I_PH + " TEXT NOT NULL"
	+")";

	
	
	db.execSQL(plant_tab); 			
	db.execSQL(task_tab); 
	db.execSQL(info_tab);

	 String sql = "INSERT INTO "+TAB_INF+" ("+I_SEMENTE+","+I_OBS+","+I_MES_P+","+I_DIAS_C+","+I_PH+") VALUES('Alface','A Alface � uma hortense anual ou bienal, utilizada na alimenta��o humana desde cerca de 500 a.C.','Mar�o, Abril, Maio, Junho, Julho, Agosto, Setembro, Outubro e Novembro','90','7')" ;       
	 db.execSQL(sql);
	 sql = "INSERT INTO "+TAB_INF+" ("+I_SEMENTE+","+I_OBS+","+I_MES_P+","+I_DIAS_C+","+I_PH+") VALUES('Ab�bora','A Ab�bora , fruto da aboboreira, � uma designa��o popular atribu�da a diversas esp�cies de plantas da fam�lia Cucurbitaceae','Mar�o, Abril e Maio','150','6.5')" ;       
	 db.execSQL(sql);
	 sql = "INSERT INTO "+TAB_INF+" ("+I_SEMENTE+","+I_OBS+","+I_MES_P+","+I_DIAS_C+","+I_PH+") VALUES('Beterraba','A beterraba � um tub�rculo levemente adocicado, e possui v�rias propriedades nutritivas e medicinais.','Mar�o, Abril, Maio, Stembro e Novembro','60','6.5')" ;       
	 db.execSQL(sql);
	 sql = "INSERT INTO "+TAB_INF+" ("+I_SEMENTE+","+I_OBS+","+I_MES_P+","+I_DIAS_C+","+I_PH+") VALUES('Batata','A Batata � um tub�rculo pertencente � fam�lia das solan�ceas','Fevereiro, Mar�o, Abril, Maio, Agosto, Stembro e Novembro','70','7')" ;       
	 db.execSQL(sql);
	 sql = "INSERT INTO "+TAB_INF+" ("+I_SEMENTE+","+I_OBS+","+I_MES_P+","+I_DIAS_C+","+I_PH+") VALUES('Cenoura','A cenoura � uma planta da fam�lia das api�ceas conhecida e apreciada desde a �poca dos antigos gregos e romanos.','Fevereiro, Mar�o, Abril, Maio, Agosto, Stembro e Novembro','50','7')" ;       
	 db.execSQL(sql);
	 sql = "INSERT INTO "+TAB_INF+" ("+I_SEMENTE+","+I_OBS+","+I_MES_P+","+I_DIAS_C+","+I_PH+") VALUES('Cebola','Cebola � o nome popular da planta cujo nome cient�fico � Allium cepa e � muito utilizada na alimenta��o.','Mar�o, Abril, Maio, Agosto, Stembro e Novembro','45','7')" ;       
	 db.execSQL(sql);
	 sql = "INSERT INTO "+TAB_INF+" ("+I_SEMENTE+","+I_OBS+","+I_MES_P+","+I_DIAS_C+","+I_PH+") VALUES('Couve','Couve � o nome vulgar, gen�rico, das diversas variedades cultivares da esp�cie Brassica oleracea.','Janeiro, Fevereiro, Mar�o, Abril, Maio, Agosto, Stembro e Novembro','50','7')" ;       
	 db.execSQL(sql);
	 sql = "INSERT INTO "+TAB_INF+" ("+I_SEMENTE+","+I_OBS+","+I_MES_P+","+I_DIAS_C+","+I_PH+") VALUES('Ervilha','Pisum sativum popularmente chamada de ervilha � uma planta da qual existem mais de duzentas variedades.',' Mar�o, Abril, Maio, Outubro, Stembro e Novembro','120','7')" ;       
	 db.execSQL(sql);
	 sql = "INSERT INTO "+TAB_INF+" ("+I_SEMENTE+","+I_OBS+","+I_MES_P+","+I_DIAS_C+","+I_PH+") VALUES('Feij�o','Feij�o � um nome comum para uma grande variedade de sementes de plantas de alguns g�neros da fam�lia Fabaceae. Proporciona nutrientes essenciais como prote�nas, ferro, c�lcio, vitaminas, carboidratos e fibras.',' Mar�o, Abril, Maio, Junho, Julho, Agosto','150','7')" ;       
	 db.execSQL(sql);
	 sql = "INSERT INTO "+TAB_INF+" ("+I_SEMENTE+","+I_OBS+","+I_MES_P+","+I_DIAS_C+","+I_PH+") VALUES('Tomate','O tomate � o fruto do tomateiro. Da sua fam�lia, fazem tamb�m parte as berinjelas, as pimentas e os piment�es, al�m de algumas esp�cies n�o comest�veis.','Janeiro, Fevereiro, Mar�o','120','7')" ;       
	 db.execSQL(sql);
	 sql = "INSERT INTO "+TAB_INF+" ("+I_SEMENTE+","+I_OBS+","+I_MES_P+","+I_DIAS_C+","+I_PH+") VALUES('Melancia','Melancia � o nome de uma planta da fam�lia Cucurbitaceae e do seu fruto. Trata-se de uma erva trepadeira rastejante origin�ria da �frica.','Janeiro, Fevereiro, Mar�o','180','7')" ;       
	 db.execSQL(sql);
	 sql = "INSERT INTO "+TAB_INF+" ("+I_SEMENTE+","+I_OBS+","+I_MES_P+","+I_DIAS_C+","+I_PH+") VALUES('Mel�o','Mel�o � uma fruta provavelmente nativa do Oriente M�dio. Existem in�meras variedades cultivadas em regi�es semi-�ridas de todo o mundo','Janeiro, Fevereiro, Mar�o','180','7')" ;       
	 db.execSQL(sql);
	 sql = "INSERT INTO "+TAB_INF+" ("+I_SEMENTE+","+I_OBS+","+I_MES_P+","+I_DIAS_C+","+I_PH+") VALUES('Pimento','Piment�o ou pimento refere-se a um grupo de cultivares da esp�cie Capsicum annuum, muito utilizado na culin�ria de todo o mundo. ','Janeiro, Fevereiro, Mar�o, Abril, Maio, Junho','90','7')" ;       
	 db.execSQL(sql);
	 sql = "INSERT INTO "+TAB_INF+" ("+I_SEMENTE+","+I_OBS+","+I_MES_P+","+I_DIAS_C+","+I_PH+") VALUES('Rabanete','','Janeiro, Fevereiro, Mar�o, Abril, Maio, Junho, Julho, Agosto','90','7')" ;       
	 db.execSQL(sql);




    }
   
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TAB_PLANT);
		db.execSQL("DROP TABLE IF EXISTS " + TAB_TASK);
		db.execSQL("DROP TABLE IF EXISTS " + TAB_INF);
        onCreate(db);
    }
}