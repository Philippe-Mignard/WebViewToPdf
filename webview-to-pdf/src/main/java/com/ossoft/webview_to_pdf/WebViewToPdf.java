package com.ossoft.webview_to_pdf;

import android.content.Context;
import android.print.PdfFileWriter;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.webkit.WebView;

import androidx.annotation.NonNull;

import java.io.File;

@SuppressWarnings("unused")
public class WebViewToPdf {

    public static void convertWebViewToPdf(@NonNull Context context, @NonNull WebView webView, @NonNull File destinationDirectory,
                                           @NonNull String fileName, @NonNull OnConvertResultListener convertResultListener){

        convertWebViewToPdf(context, webView, PrintAttributes.MediaSize.ISO_A4, PrintAttributes.Margins.NO_MARGINS, destinationDirectory, fileName, convertResultListener);

    }

    public static void convertWebViewToPdf(@NonNull Context context, @NonNull WebView webView, @NonNull PrintAttributes.MediaSize pageLayout,
                                           @NonNull File destinationDirectory, @NonNull String fileName, @NonNull OnConvertResultListener convertResultListener){

        convertWebViewToPdf(context, webView, pageLayout, PrintAttributes.Margins.NO_MARGINS, destinationDirectory, fileName, convertResultListener);

    }

    public static void convertWebViewToPdf(@NonNull Context context, @NonNull WebView webView, @NonNull PrintAttributes.Margins pageMargins,
                                           @NonNull File destinationDirectory, @NonNull String fileName, @NonNull OnConvertResultListener convertResultListener){

        convertWebViewToPdf(context, webView, PrintAttributes.MediaSize.ISO_A4, pageMargins, destinationDirectory, fileName, convertResultListener);

    }

    public static void convertWebViewToPdf(@NonNull Context context, @NonNull WebView webView, @NonNull PrintAttributes.MediaSize pageLayout,
                                           @NonNull PrintAttributes.Margins pageMargins, @NonNull File destinationDirectory,
                                           @NonNull String fileName, @NonNull OnConvertResultListener convertResultListener){

        PrintAttributes printAttributes = new PrintAttributes.Builder()
                .setMediaSize(pageLayout)
                .setMinMargins(pageMargins)
                .setColorMode(PrintAttributes.COLOR_MODE_COLOR)
                .setResolution(new PrintAttributes.Resolution("pdf", "pdf", 600, 600))
                .build();
        PrintDocumentAdapter printAdapter = webView.createPrintDocumentAdapter(fileName);
        PdfFileWriter.writePdf(printAttributes, printAdapter, destinationDirectory, fileName, new PdfFileWriter.OnPdfWriteListener() {
            @Override
            public void onSuccess(String pdfFilePath) {
                convertResultListener.onSuccess(pdfFilePath);
            }

            @Override
            public void onFailure(String failureMessage) {
                convertResultListener.onFailure(failureMessage);
            }

            @Override
            public void onCancelled() {
                convertResultListener.onCancelled();
            }
        });
    }

    public interface OnConvertResultListener{

        void onSuccess(String pdfFilePath);

        void onFailure(String failMessage);

        void onCancelled();
    }

}
