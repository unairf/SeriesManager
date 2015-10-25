package com.unairf.seriesmanager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private String myDirectory;
    private String miFichero = "Shippuden.txt";
    private String miFicheroAnother = "Another.txt";
    private String miFicheroLost = "Lost.txt";
    private TextView contenidoFichero;
    private String allLines;
    private String[] finalString;
    private int iteraciones;
    private Intent intent;
    private ImageButton miImageButton;
    private ImageButton miImageButton2;
    private ImageButton miImageButton3;
    private Intent webIntent;

    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        intent = new Intent(this, EmptyActivity.class);

        String[] items = {getResources().getString(R.string.menu_settings,getResources().getString(R.string.menu_help))};
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item);
     //   ListView miLista = new ListView();
        miImageButton = (ImageButton)findViewById(R.id.imageButton);
        Drawable narutoImage = Drawable.createFromPath("@drawable/naruto");
        miImageButton.setImageResource(R.drawable.naruto);
        miImageButton2 = (ImageButton)findViewById(R.id.imageButton2);
        Drawable anotherImage = Drawable.createFromPath("@drawable/another");
        miImageButton2.setImageResource(R.drawable.another);
        miImageButton3 = (ImageButton)findViewById(R.id.imageButton3);
        Drawable lostImage = Drawable.createFromPath("@drawable/naruto");
        miImageButton3.setImageResource(R.drawable.lost);
        TextView myText1;
        myText1 = (TextView)findViewById(R.id.textview_naruto);
        TextView myText2;
        myText2 = (TextView)findViewById(R.id.textview_another);
        TextView myText3;
        myText3 = (TextView)findViewById(R.id.textview_lost);

        if(checkStorage()) {
            Context context;
            context = getBaseContext();
            Toast toast = Toast.makeText(context, "Acceso concedido", Toast.LENGTH_LONG);
            toast.show();

          //  readFile(miFichero);
        }
       // createButton(3);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

        } else if(id == R.id.action_help) {
            Intent intent = new Intent(this,AboutActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean checkStorage () {

        // Del entorno tomamos info de almacenamiento
        String estadoAlmacenamiento = Environment.getExternalStorageState();
        // Tenemos permiso para todo
        if (Environment.MEDIA_MOUNTED.equals(estadoAlmacenamiento)) {
            Log.d("DEBUG", "Puedo leer y escribir");


            return true;
            // o quiza tenemos permiso de solo lectura?
        }else if
                (Environment.MEDIA_MOUNTED_READ_ONLY.equals(estadoAlmacenamiento)) {
            Log.d("DEBUG","Puedo leer pero no escribir");
            return false;
        }else{
            // cualquier otro caso, no podemos hacer nada
            Log.d("DEBUG","No podemos ni leer ni escribir");
            return false;
        }
    }

    public void readFile (String file) {
        String content = "";

        try{
            File ruta_sd = Environment.getExternalStorageDirectory();
            File f = new File(ruta_sd.getAbsolutePath(), file);
            BufferedReader lectorFichero = new BufferedReader(new
                    InputStreamReader(new FileInputStream(f)));

            // Vamos a leer una linea a ver:
            String line = lectorFichero.readLine();
            lectorFichero.read();
            StringBuilder stringBuilder = new StringBuilder();

            while (( line =lectorFichero.readLine()) != null)
            {
                stringBuilder.append(line);
                stringBuilder.append('\n');
            }
            finalString = stringBuilder.toString().split("\n");
            for (int i=0;i<finalString.length;i++){
             //     createButton(1);
                Log.d("DEBUG", "Boron creado");
             //   Button myButton = (Button) findViewById(R.id.button_layout);
                //myButton.callOnClick();
                Log.d("DEBUG", "Llamando al onclick...");
            }
            createButton(finalString.length - 1);
            lectorFichero.close();



            // this.contenidoFichero.setText(content);
            // lectorFichero.close();
            TextView allLinesTxt = (TextView)findViewById(R.id.all_lines);

            allLinesTxt.setText(line);

            Log.d("DEBUG","Ok, fichero leido, ruta: " +
                    ruta_sd.getAbsolutePath()+ "/" + this.miFichero);
            //  iteraciones = finalString.length;
            //    for (int i=0;i<iteraciones;i++){
            //       createButton(1);
            //   }
        }catch(Exception e){
            Log.e(this.getClass().getName(), "Error al leer Fichero: " +
                    e.getMessage());
        }
    }

    public void createButton(int numero) {
        LinearLayout myLayout = (LinearLayout)findViewById(R.id.relative_layout_buttons);
        myLayout.removeAllViews();
      //  for(int i=0;i<myLayout.getChildCount();i++){
       //     View child=myLayout.getChildAt(i);
            //your processing....
      //      child.setEnabled(false);
     //   }
        for (int i = 0;i<numero;i++) {
            LinearLayout row2 = (LinearLayout) findViewById(R.id.relative_layout_buttons);
            Button dinamicButton = new Button(this);

            String[] lineDivided = finalString[i].split("\\*");
            dinamicButton.setText(lineDivided[0]);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(findViewById(R.id.relative_layout_buttons).getWidth() - 30, findViewById(R.id.button_layout).getHeight());
            layoutParams.setMargins(0, 0, 0, 0); // left, top, right, bottom
            dinamicButton.setLayoutParams(layoutParams);
            row2.addView(dinamicButton);
            dinamicButton.setWidth(layoutParams.width);
            dinamicButton.setTag(lineDivided[3]);
           dinamicButton.setOnClickListener(this.click);
            dinamicButton.setBackgroundResource(R.color.blanched_almond);

            GradientDrawable drawable = new GradientDrawable();
            drawable.setShape(GradientDrawable.RECTANGLE);

            //    int alpha = Color.alpha(R.color.dark_goldenrod);
            //drawable.setStroke(3, Color.DKGRAY);

            //Commited


          //  dinamicButton.setBackground(drawable);

        }
    }

    public void readNaruto(View view) {
        readFile(miFichero);
    }

    public void readAnother(View view) {
        readFile(miFicheroAnother);
    }

    public void readLost(View view) {
        readFile(miFicheroLost);
    }

    private View.OnClickListener click = new View.OnClickListener() {

        public void onClick(View v){


            intent.putExtra("url",v.getTag().toString());
            startActivity(intent);
        }
    };
}
