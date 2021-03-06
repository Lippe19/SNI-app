package br.com.sni_app.sniapp;

import android.app.ProgressDialog;
import android.content.Context;

import java.util.Date;
import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.MailcapCommandMap;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Created by Luiz Philippe
 * Site: lptag.com.br
 * E-mail: contato@lptag.com.br
 */

public class Mail extends javax.mail.Authenticator {
    private String _user;
    private String _pass;

    private String[] _to;
    private String[] _cco;
    private String _from;

    private String _port;
    private String _sport;

    private String _host;

    private String _subject;
    private String _body;

    //Progressdialog to show while sending email
    private ProgressDialog carregamento;
    private Session session;
    private Context context;
    //------------------------------------------

    private boolean _auth;
    private boolean _useSsl;
    private boolean _isHtmlBody;
    private boolean _debuggable;

    private Multipart _multipart;


    public Mail() {
        _host = "smtp.gmail.com";
        _port = "465"; // porta padrão, se não funcionar use 587 ou em último caso a 25
        _sport = "465"; // porta socket padrão, mesmas instruções anteriores

        _user = ""; // email de quem envia
        _pass = ""; // senha
        _from = ""; // email do remetente
        _subject = ""; // assunto
        _body = ""; // mensagem html

        _debuggable = false;
        _auth = true;
        _useSsl = true;//gmail usa true
        _isHtmlBody = true;

        _multipart = new MimeMultipart();


        MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
        mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
        mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
        mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
        mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
        mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
        CommandMap.setDefaultCommandMap(mc);
    }

    public Mail(String user, String pass) {
        this();
        _user = user;
        _pass = pass;
        //context = context;


    }

    //--------------------------
    /*public class Mail1 extends AsyncTask<Void,Void,Void>{
        @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //Mostrando a caixa de diálogo de progresso ao enviar e-mail
        carregamento = ProgressDialog.show(context,"Enviando mensagem","Por favor, espere...",false,false);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //Dispensando o diálogo de progresso
        carregamento.dismiss();
        //Mostrando uma mensagem de sucesso
        Toast.makeText(context,"Mensagem enviada",Toast.LENGTH_LONG).show();
    }

        @Override
        protected Void doInBackground(Void... params) {
            return null;
        }
    }*///---------------------------------
    public boolean send(String qtdPar, String nomeAss, String data) throws Exception {
        Properties props = _setProperties();

        if(!_user.equals("") && !_pass.equals("") && _to.length > 0 && !_from.equals("") && !_subject.equals("") && !_body.equals("")) {
            Session session = Session.getInstance(props, this);

            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(_from));

            InternetAddress[] addressTo = new InternetAddress[_to.length];
            for (int i = 0; i < _to.length; i++) {
                addressTo[i] = new InternetAddress(_to[i]);
            }
            msg.setRecipients(MimeMessage.RecipientType.TO, addressTo);

            //se tem de enviar cópia oculta para alguém
            if(_cco != null && _cco.length > 0) {
                InternetAddress[] addressCco = new InternetAddress[_cco.length];
                for(int i=0; i < _cco.length; i++){
                    addressCco[i] = new InternetAddress(_cco[i]);
                }
                msg.addRecipients(Message.RecipientType.BCC, addressCco);
            }

            msg.setSubject(_subject);
            msg.setSentDate(new Date());

            // corpo da mensagem
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(_body);
            if(_isHtmlBody) {
                messageBodyPart.setHeader("charset", "utf-8");
                messageBodyPart.setHeader("content-type", "text/html");
            }
            _multipart.addBodyPart(messageBodyPart);
            msg.setContent(_multipart);

            // envia o email
            Transport.send(msg);

            return true;
        } else {
            return false;
        }

    }

    public void addAttachment(String filename) throws Exception {
        filename = filename.replace("file:","").replace("//","/");
        BodyPart messageBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(filename);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(filename);

        _multipart.addBodyPart(messageBodyPart);
    }

    @Override
    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(_user, _pass);
    }

    private Properties _setProperties() {
        Properties props = new Properties();
        props.put("mail.smtp.host", _host);
        props.put("mail.smtp.port", _port);
        if(_debuggable) props.put("mail.debug", "true");
        if(_auth) props.put("mail.smtp.auth", "true");

        if(_useSsl) {
            props.put("mail.smtp.socketFactory.port", _sport);
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.fallback", "false");
        }

        return props;
    }

    // the getters and setters
    public String getBody() {
        return _body;
    }

    public void setBody(String _body) {
        this._body = _body;
    }

    public void setTo(String[] toArr) {
        this._to=toArr;
    }

    public void setCco(String[] ccoArr){
        this._cco = ccoArr;
    }

    public void setFrom(String string) {
        this._from=string;
    }

    public void setSubject(String string) {
        this._subject=string;
    }
}