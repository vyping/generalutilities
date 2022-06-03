package com.vyping.masterlibrary.dialogs;

import static com.vyping.masterlibrary.Common.Definitions.TEXT_INPUT_NORMAL;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.airbnb.paris.Paris;
import com.github.barteksc.pdfviewer.PDFView;
import com.google.firebase.database.DatabaseError;
import com.vyping.masterlibrary.Common.Definitions;
import com.vyping.masterlibrary.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

// TODO --- Dialogo diseñado para el ingreso de texto por parte del usuario (En una sola linea).


public abstract class DialogPdfView extends CreateDialog {

    private PDFView Pv_PdfViewer;

    private String pdfUrl;


    /*----- SetUp -----*/

    public DialogPdfView(@NonNull Context context, int arrayParameters, String pdfUrl) {

        super(context, arrayParameters);

        setParameters(pdfUrl);
        setDialogViews();
        setModeDialogButtons();
    }

    public DialogPdfView(@NonNull Context context, @DialogMode int dialogMode, int arrayParameters, String pdfUrl) {

        super(context, dialogMode, arrayParameters);

        setParameters(pdfUrl);
        setDialogViews();
        setModeDialogButtons();
    }

    public DialogPdfView(@NonNull Context context, Drawable icon, String title, String description, String help, String error, String success, String pdfUrl) {

        super(context, icon, title, description, help, error, success);

        setParameters(pdfUrl);
        setDialogViews();
        setModeDialogButtons();
    }

    public DialogPdfView(@NonNull Context context, @DialogMode int dialogMode, Drawable icon, String title, String description, String help, String error, String success, String pdfUrl) {

        super(context, dialogMode, icon, title, description, help, error, success);

        setParameters(pdfUrl);
        setDialogViews();
        setModeDialogButtons();
    }

    public DialogPdfView(@NonNull Context context, int icon, String title, String description, String help, String error, String success, String pdfUrl) {

        super(context, icon, title, description, help, error, success);

        setParameters(pdfUrl);
        setDialogViews();
        setModeDialogButtons();
    }

    public DialogPdfView(@NonNull Context context, @DialogMode int dialogMode, int icon, String title, String description, String help, String error, String success, String pdfUrl) {

        super(context, dialogMode, icon, title, description, help, error, success);

        setParameters(pdfUrl);
        setDialogViews();
        setModeDialogButtons();
    }

    private void setParameters(String pdfUrl) {

        this.pdfUrl = pdfUrl;
    }

    private void setDialogViews() {

        int style = R.style.DialogInputText;
        int attr = R.attr.dialogInputText;

        LayoutInflater inflater = (LayoutInflater) binding.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.pdf_view, null, false);

        AddCustomView(view);

        Pv_PdfViewer = view.findViewById(R.id.pdfView);

        new RetrivedPdf().execute(pdfUrl);
    }


    /*----- Process -----*/

    class RetrivedPdf extends AsyncTask<String, Void, InputStream> {
        // we are calling async task and performing
        // this task to load pdf in background.
        @Override
        protected InputStream doInBackground(String... strings) {
            // below line is for declaring
            // our input stream.
            InputStream pdfStream = null;
            try {
                // creating a new URL and passing
                // our string in it.
                URL url = new URL(strings[0]);

                // creating a new http url connection and calling open
                // connection method to open http url connection.
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                if (httpURLConnection.getResponseCode() == 200) {
                    // if the connection is successful then
                    // we are getting response code as 200.
                    // after the connection is successful
                    // we are passing our pdf file from url
                    // in our pdfstream.
                    pdfStream = new BufferedInputStream(httpURLConnection.getInputStream());
                }

            } catch (IOException e) {
                // this method is
                // called to handle errors.
                return null;
            }
            // returning our stream
            // of PDF file.
            return pdfStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            // after loading stream we are setting
            // the pdf in your pdf view.
            Pv_PdfViewer.fromStream(inputStream).load();
        }
    }

    public void setError() {

        this.setError(PARAMS[5]);
    }

    public void setError(String error) {

        SetErrorMessage(error);
        setModeDialogButtons();
    }


    /*----- Utilities -----*/

    private void setModeDialogButtons() {

        if (MODE == DIALOG_NORMAL) {

            SetButtonConfirm(BUTTON_RIGHT, new Interfase() {

                @Override
                public boolean ClickButton() {

                    return PositiveButton();
                }

                private void DummyVoid() {
                }
            });

        } else {

            SetButtonNext(BUTTON_RIGHT, new Interfase() {

                @Override
                public boolean ClickButton() {

                    return PositiveButton();
                }

                private void DummyVoid() {
                }
            });
        }
    }


    /*----- Return -----*/

    protected abstract boolean PositiveButton();
}
