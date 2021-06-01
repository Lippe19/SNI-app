package br.com.sni_app.sniapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
//import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.mail.Message;
import javax.mail.Session;

import fr.ganfra.materialspinner.MaterialSpinner;

public class FormActivity extends AppCompatActivity {

    //referente ao botao salvar
    private static final String NOME_ARQUIVO = "observacao.txt";
    //Atributos
    EditText txtNome, txtEmail;
    //EditText messageEditText;
    EditText edit_nomeAss;
    EditText edit_data;
    EditText edit_hora;
    EditText edit_qtdPar;
    EditText edit_qtd1;
    EditText edit_qtdCol;
    EditText edit_qtdLvr;
    EditText edit_1m;
    EditText edit_3m;
    EditText edit_6m;
    EditText edit_1a;
    EditText edit_proCarro;
    EditText edit_proCasa;
    //EditText edit_total1;
    EditText edit_fami;
    EditText edit_alma;
    EditText edit_ange;
    EditText edit_fonL;
    EditText edit_mulF;
    EditText edit_munI;
    EditText edit_quer;
    EditText edit_ang1;
    EditText edit_fonL1;
    EditText edit_mulF1;
    EditText edit_munI1;
    EditText edit_quer1;
    //ArrayAdapter<Integer> adapter;
    //Atributos Spinners
    MaterialSpinner organizacao;
    MaterialSpinner regional;
    MaterialSpinner dpevt;
    MaterialSpinner evt;
    //atributo botao
    Button btnEmail;
    Button btnSalvar;
    ProgressDialog carregamento;
    //Declaring Variables
    private Context context;
    private Session session;
    //Progressdialog to show while sending email
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
       // MultiDex.install(this);

        txtNome = (EditText)findViewById(R.id.txtNome);
        txtEmail = (EditText)findViewById(R.id.txtEmail);
        //messageEditText = (EditText)findViewById(R.id.messageEditText);
        //1
        edit_nomeAss = (EditText)findViewById(R.id.edit_nomeAss);
        edit_data = (EditText)findViewById(R.id.edit_data);
        edit_hora = (EditText)findViewById(R.id.edit_hora);
        edit_qtdPar = (EditText)findViewById(R.id.edit_qtdPar);
        edit_qtd1 = (EditText)findViewById(R.id.edit_qtd1);
        edit_qtdCol = (EditText)findViewById(R.id.edit_qtdCol);
        edit_qtdLvr = (EditText)findViewById(R.id.edit_qtdLvr);
        //2
        edit_1m = (EditText)findViewById(R.id.edit_1m);
        edit_3m = (EditText)findViewById(R.id.edit_3m);
        edit_6m = (EditText)findViewById(R.id.edit_6m);
        edit_1a = (EditText)findViewById(R.id.edit_1a);
        edit_proCarro = (EditText)findViewById(R.id.edit_proCarro);
        edit_proCasa = (EditText)findViewById(R.id.edit_proCasa);
        //edit_total1 = (EditText)findViewById(R.id.edit_total1);
        edit_fami = (EditText)findViewById(R.id.edit_fami);
        edit_alma = (EditText)findViewById(R.id.edit_alma);
        edit_ange = (EditText)findViewById(R.id.edit_ange);
        //3
        edit_fonL = (EditText)findViewById(R.id.edit_fonL);
        edit_mulF = (EditText)findViewById(R.id.edit_mulF);
        edit_munI = (EditText)findViewById(R.id.edit_munI);
        edit_quer = (EditText)findViewById(R.id.edit_quer);
        //4
        edit_fonL1 = (EditText)findViewById(R.id.edit_fonL1);
        edit_mulF1 = (EditText)findViewById(R.id.edit_mulF1);
        edit_munI1 = (EditText)findViewById(R.id.edit_munI1);
        edit_quer1 = (EditText)findViewById(R.id.edit_quer1);

        // - - - - - -SPINNERS
        organizacao = (MaterialSpinner) findViewById(R.id.spinOrganizacao);/**/
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.organizacao, android.R.layout.simple_spinner_item);
        organizacao.setAdapter(adapter);
        //capturando objeto do spinner
        organizacao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                organizacao.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        regional = (MaterialSpinner) findViewById(R.id.spinRegional);
        adapter = ArrayAdapter.createFromResource(this, R.array.regional, android.R.layout.simple_spinner_item);
        regional.setAdapter(adapter);
        regional.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?>  parent, View view, int position, long id) {
                regional.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        dpevt = (MaterialSpinner) findViewById(R.id.spinDpEvt);
        adapter = ArrayAdapter.createFromResource(this, R.array.dpevt, android.R.layout.simple_spinner_item);
        dpevt.setAdapter(adapter);
        dpevt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?>  parent, View view, int position, long id) {
                dpevt.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        evt = (MaterialSpinner) findViewById(R.id.spinEvt);
        adapter = ArrayAdapter.createFromResource(this, R.array.evt, android.R.layout.simple_spinner_item);
        evt.setAdapter(adapter);
        evt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?>  parent, View view, int position, long id) {
                evt.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        /**//**/
        btnEmail = (Button)findViewById(R.id.btnEmail);
        btnEmail.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Informa se foram preenchidos os campos com '*' -------------------------------------------------------------------------------------------
                try {
                    String nomeAss = edit_nomeAss.getText().toString().trim();
                    String data = edit_data.getText().toString();
                    String qtdPar = edit_qtdPar.getText().toString();

                    //INFORMA SE UM DOS CAMPOS NAO ESTIVER PREENCHIDO !
                    if(nomeAss.isEmpty()){
                        Toast.makeText(FormActivity.this, "Voce não informou o Nome da Associação Local...", Toast.LENGTH_LONG).show();
                    }else if(data.isEmpty()){
                        Toast.makeText(FormActivity.this, "Voce não informou a Data do Evento...", Toast.LENGTH_LONG).show();
                    }else if(qtdPar.isEmpty()){
                        Toast.makeText(FormActivity.this, "Voce não informou a Quantidade de participantes...", Toast.LENGTH_LONG).show();
                    }else {
                        //everything is filled out
                        //send email
                        enviarEmail();//new Mail().send(nomeAss, data, qtdPar);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //-------------------------------------------------------------------------------------------------------------------------------------------

            }
        });

























        ///Botao SALVAR
        btnSalvar=(Button) findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*final String org = (String) organizacao.getSelectedItem();
                long id = organizacao.getSelectedItemId();
                int posicao = organizacao.getSelectedItemPosition();
                final String reg = (String) regional.getSelectedItem();
                final String dpe = (String) dpevt.getSelectedItem();
                final String ev = (String) evt.getSelectedItem();*/

                final String nomeAss = edit_nomeAss.getText().toString();
                final String data = edit_data.getText().toString();
                /*final String hora = edit_hora.getText().toString();
                final String qtdPar = edit_qtdPar.getText().toString();
                final String qtd1 = edit_qtd1.getText().toString();
                final String qtdCol = edit_qtdCol.getText().toString();
                final String qtdLvr = edit_qtdLvr.getText().toString();
                final String Um = edit_1m.getText().toString();
                final String Tm = edit_3m.getText().toString();
                final String Sm = edit_6m.getText().toString();
                final String Ua = edit_1a.getText().toString();
                final String pCarro = edit_proCarro.getText().toString();
                final String pCasa = edit_proCasa.getText().toString();
                //final String tot1 = edit_total1.getText().toString();
                final String fami = edit_fami.getText().toString();
                final String alma = edit_alma.getText().toString();
                final String ange = edit_ange.getText().toString();
                final String fonL = edit_fonL.getText().toString();
                final String mulF = edit_mulF.getText().toString();
                final String munI = edit_munI.getText().toString();
                final String quer = edit_quer.getText().toString();
                final String fonL1 = edit_fonL1.getText().toString();
                final String mulF1 = edit_mulF1.getText().toString();
                final String munI1 = edit_munI1.getText().toString();
                final String quer1 = edit_quer1.getText().toString();*/
///*,hora,qtdPar,qtd1,qtdCol,qtdLvr,Um,Tm,Sm,Ua,pCarro,pCasa,fami,alma,ange,fonL,munI,mulF,quer,fonL1,mulF1,munI1,quer1*/
                gravar(nomeAss,( data));
                Toast.makeText(FormActivity.this, "Formulário salvo com sucesso", Toast.LENGTH_SHORT).show();
            }
        });

        //ONDE PAROU LINHA 50
        if ( lerArquivo() != null ){
            edit_nomeAss.setText( lerArquivo() );
            edit_data.setText( lerArquivo() );
            /*edit_hora.setText( lerArquivo() );
            edit_qtdPar.setText( lerArquivo() );
            edit_qtd1.setText( lerArquivo() );
            edit_qtdCol.setText( lerArquivo() );
            edit_qtdLvr.setText( lerArquivo() );
            edit_1m.setText( lerArquivo() );
            edit_3m.setText( lerArquivo() );
            edit_6m.setText( lerArquivo() );
            edit_1a.setText( lerArquivo() );
            edit_proCarro.setText( lerArquivo() );
            edit_proCasa.setText( lerArquivo() );
            edit_fami.setText( lerArquivo() );
            edit_alma.setText( lerArquivo() );
            edit_ange.setText( lerArquivo() );
            edit_fonL.setText( lerArquivo() );
            edit_munI.setText( lerArquivo() );
            edit_mulF.setText( lerArquivo() );
            edit_quer.setText( lerArquivo() );
            edit_fonL1.setText( lerArquivo() );
            edit_munI1.setText( lerArquivo() );
            edit_mulF1.setText( lerArquivo() );
            edit_quer1.setText( lerArquivo() );*/

        }
    }

    /*private void gravar(String nomeAss, String data) {
    }*/

    //salvando
    private void gravar(String nomeAss, String data/*, String hora, String qtdPar, String qtd1, String qtdCol, String qtdLvr, String Um, String Tm, String Sm, String Ua, String pCarro, String pCasa, String fami, String alma, String ange, String fonL, String munI, String mulF, String quer, String fonL1, String mulF1, String munI1, String quer1*/) {

        try {

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("NOME_ARQUIVO", Context.MODE_PRIVATE));/**/
            outputStreamWriter.write(nomeAss);
            outputStreamWriter.write( data );
            outputStreamWriter.close();

            //outputStreamWriter.write( data );
            //outputStreamWriter.write( hora );
            /*outputStreamWriter.write( qtdPar );
            outputStreamWriter.write( qtd1 );
            outputStreamWriter.write( qtdCol );
            outputStreamWriter.write( qtdLvr );
            outputStreamWriter.write( Um );
            outputStreamWriter.write( Tm );
            outputStreamWriter.write( Sm );
            outputStreamWriter.write( Ua );
            outputStreamWriter.write( pCarro );
            outputStreamWriter.write( pCasa );
            outputStreamWriter.write( fami );
            outputStreamWriter.write( alma );
            outputStreamWriter.write( ange );
            outputStreamWriter.write( fonL );
            outputStreamWriter.write( munI );
            outputStreamWriter.write( mulF );
            outputStreamWriter.write( quer );
            outputStreamWriter.write( fonL1 );
            outputStreamWriter.write( mulF1 );
            outputStreamWriter.write( munI1 );
            outputStreamWriter.write( quer1 );*/

        }catch (IOException e){
            Log.v("FromActivity", e.toString() );
        }
    }
    private String lerArquivo(){

        String resultado="";

        try {
            //abrir arquivo
            InputStream arquivo = openFileInput("NOME_ARQUIVO");
            if ( arquivo != null ){
                //ler o arquivo
                InputStreamReader inputStreamReader = new InputStreamReader( arquivo );
                //gerar buffer do arquivo
                BufferedReader bufferedReader = new BufferedReader( inputStreamReader );

                String linhaArquivo = "";
                while ( (linhaArquivo = bufferedReader.readLine() ) != null ){
                    resultado += linhaArquivo;
                }

                arquivo.close();
            }

        }catch (IOException e){
            Log.v("FormActivity", e.toString() );
        }

        return resultado;
    }




    //SALVAR
    //--------------------------------------------------------------------------------------------


































    private static Message message;
    /*Toast.makeText(this, ""+nome1, Toast.LENGTH_SHORT).show();*/

    private void enviarEmail(){
        //informando NOME , ID , POSIÇÃO
        final String org = (String) organizacao.getSelectedItem();
        long id = organizacao.getSelectedItemId();
        int posicao = organizacao.getSelectedItemPosition();
        final String reg = (String) regional.getSelectedItem();
        final String dpe = (String) dpevt.getSelectedItem();
        final String ev = (String) evt.getSelectedItem();

        final String nome = txtNome.getText().toString();
        final String email = txtEmail.getText().toString();
        //final String message = messageEditText.getText().toString();

        final String nomeAss = edit_nomeAss.getText().toString();
        final String data = edit_data.getText().toString();
        final String hora = edit_hora.getText().toString();
        final String qtdPar = edit_qtdPar.getText().toString();
        final String qtd1 = edit_qtd1.getText().toString();
        final String qtdCol = edit_qtdCol.getText().toString();
        final String qtdLvr = edit_qtdLvr.getText().toString();
        final String Um = edit_1m.getText().toString();
        final String Tm = edit_3m.getText().toString();
        final String Sm = edit_6m.getText().toString();
        final String Ua = edit_1a.getText().toString();
        final String pCarro = edit_proCarro.getText().toString();
        final String pCasa = edit_proCasa.getText().toString();
        //final String tot1 = edit_total1.getText().toString();
        final String fami = edit_fami.getText().toString();
        final String alma = edit_alma.getText().toString();
        final String ange = edit_ange.getText().toString();
        final String fonL = edit_fonL.getText().toString();
        final String mulF = edit_mulF.getText().toString();
        final String munI = edit_munI.getText().toString();
        final String quer = edit_quer.getText().toString();
        final String fonL1 = edit_fonL1.getText().toString();
        final String mulF1 = edit_mulF1.getText().toString();
        final String munI1 = edit_munI1.getText().toString();
        final String quer1 = edit_quer1.getText().toString();

        //final String total= edit_fami.getText() + edit_alma.getText() + edit_ange.getText();

        if(isOnline()) {
            new Thread(new Runnable(){
                @Override
                public void run() {
                    Mail m = new Mail("app.sni2018@gmail.com", "fraternidade2018");

                    String[] toArr = {email};
                    m.setTo(toArr);

                    m.setFrom("app.sni2018@gmail.com");
                    m.setSubject("Formulário: "+reg);

                    m.setBody("<html><b>Organização: </b>"+org+"<br><b>Nome da Regional: </b>"+reg+
                            "<br><b>Nome da Associação Local: </b>"+nomeAss+"<br><b>Departamento que promoveu o Evento: </b>"+dpe+
                            "<br><b>Data do Evento: </b>"+data+"<br><b>Horário do Evento: </b>"+hora+
                            "<br><b>Tipo de Evento: </b>"+ev+"<br><b>Quantidade de Participantes: </b>"+qtdPar+
                            "<br><b>Quantidade de Participantes pela primeira vez: </b>"+qtd1+
                            "<br><b>Quantidade de Colaboradores|Líderes: </b>"+qtdCol+"<br><b>Quantidade de Livros vendidos: </b>"+qtdLvr+
                            "<p>" +"<hr>"+
                            "<table border=0 bgcolor='#c0f6ff'>" +
                            "<caption>"+"<b>Quantidade de Oração(s) de Cura Divina(s) divulgada(s)</b>"+"</caption>"+
                            "<tr>"+
                            "<td bgcolor='#d6f9ff'>"+"<b>1 mes</b>"+"</td>"+
                            "<td bgcolor='#d6f9ff'>"+"<b>3 mes</b>"+"</td>"+
                            "<td bgcolor='#d6f9ff'>"+"<b>6 mes</b>"+"</td>"+
                            "<td bgcolor='#d6f9ff'>"+"<b>1 ano</b>"+"</td>"+
                            "<td bgcolor='#d6f9ff'>"+"<b>Proteção para Carro</b>"+"</td>"+
                            "<td bgcolor='#d6f9ff'>"+"<b>Proteção para Casa/Estab.</b>"+"</td>"+
                            "<td bgcolor='#d6f9ff'>"+"<b>Total</b>"+"</td>"+
                            "</tr>"+
                            "<tr align=center>"+
                            "<td bgcolor='#eefdff'>"+Um+"</td>"+
                            "<td bgcolor='#eefdff'>"+Tm+"</td>"+
                            "<td bgcolor='#eefdff'>"+Sm+"</td>"+
                            "<td bgcolor='#eefdff'>"+Ua+"</td>"+
                            "<td bgcolor='#eefdff'>"+pCarro+"</td>"+
                            "<td bgcolor='#eefdff'>"+pCasa+"</td>"+
                            "<td bgcolor='#eefdff'>"+nome+"</td>"+
                            "</tr>"+
                            "</table>"+

                            "<br>" +
                            "<table border=0 bgcolor='#c0f6ff'>" +
                            "<caption>"+"<b>Quantidade de Registros Espirituais divulgados</b>"+"</caption>"+
                            "<tr>"+
                            "<td bgcolor='#d6f9ff'>"+"Família"+"</td>"+
                            "<td bgcolor='#d6f9ff'>"+"Alma"+"</td>"+
                            "<td bgcolor='#d6f9ff'>"+"Angelical"+"</td>"+
                            "<td bgcolor='#d6f9ff'>"+"Total"+"</td>"+
                            "</tr>"+
                            "<tr>"+
                            "<td bgcolor='#eefdff'>"+fami+"</td>"+
                            "<td bgcolor='#eefdff'>"+alma+"</td>"+
                            "<td bgcolor='#eefdff'>"+ange+"</td>"+
                            "<td bgcolor='#eefdff'>"+nome+"</td>"+
                            "</tr>"+
                            "</table>"+

                            "<br>" +
                            "<table border=0 bgcolor='#c0f6ff'>" +
                            "<caption>"+"<b>Quantidade de Revistas da SNI e jornal Querubim divulgados</b>"+"</caption>"+
                            "<tr>"+
                            "<td bgcolor='#d6f9ff'>"+"Fonte de Luz"+"</td>"+
                            "<td bgcolor='#d6f9ff'>"+"Mulher Feliz"+"</td>"+
                            "<td bgcolor='#d6f9ff'>"+"Mundo Ideal"+"</td>"+
                            "<td bgcolor='#d6f9ff'>"+"Querubim"+"</td>"+
                            "<td bgcolor='#d6f9ff'>"+"Total"+"</td>"+
                            "</tr>"+
                            "<tr>"+
                            "<td bgcolor='#eefdff'>"+fonL+"</td>"+
                            "<td bgcolor='#eefdff'>"+mulF+"</td>"+
                            "<td bgcolor='#eefdff'>"+munI+"</td>"+
                            "<td bgcolor='#eefdff'>"+quer+"</td>"+
                            "<td bgcolor='#eefdff'>"+nome+"</td>"+
                            "</tr>"+
                            "</table>"+

                            "<br>" +
                            "<table border=0 bgcolor='#c0f6ff'>" +
                            "<caption>"+"<b>Quantidade de Assinatura(s) das Revistas da SNI e jornal Querubim divulgados</b>"+"</caption>"+
                            "<tr>"+
                            "<td bgcolor='#d6f9ff'>"+"Fonte de Luz"+"</td>"+
                            "<td bgcolor='#d6f9ff'>"+"Mulher Feliz"+"</td>"+
                            "<td bgcolor='#d6f9ff'>"+"Mundo Ideal"+"</td>"+
                            "<td bgcolor='#d6f9ff'>"+"Querubim"+"</td>"+
                            "<td bgcolor='#d6f9ff'>"+"Total"+"</td>"+
                            "</tr>"+
                            "<tr>"+
                            "<td bgcolor='#eefdff'>"+fonL1+"</td>"+
                            "<td bgcolor='#eefdff'>"+mulF1+"</td>"+
                            "<td bgcolor='#eefdff'>"+munI1+"</td>"+
                            "<td bgcolor='#eefdff'>"+quer1+"</td>"+
                            "<td bgcolor='#eefdff'>"+nome+"</td>"+
                            "</tr>"+
                            "</table>"+
                            "<br><b>Quantidade de Orientação(s) Pessoal(s): </b>"+nome+
                            "<br><b>Observações | Anotações referentes ao Evento: </b>"+nome+"</html>");
                    // m.setBody("Nome: "+message);

                    try {
                        //m.addAttachment("pathDoAnexo");//anexo opcional
                        m.send(nomeAss, data, qtdPar);
                    }
                    catch(RuntimeException rex){ }//erro ignorado
                    catch(Exception e) {
                        //tratar algum outro erro aqui
                    }

                    //System.exit(0);
                }
            })/**/.start();
        }
        else {
            Toast.makeText(getApplicationContext(), "Não estava online para enviar e-mail!", Toast.LENGTH_SHORT).show();
            //System.exit(0);
        }
    }
    //--------------------------------------------

    //--------------------------------------------
    public boolean isOnline() {
        try {
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            return netInfo != null && netInfo.isConnectedOrConnecting();
        }
        catch(Exception ex){
            Toast.makeText(getApplicationContext(), "Erro ao verificar se estava online! ", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

}
