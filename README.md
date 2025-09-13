# Info
**This library is supported by API 21 (Android 5.0) to API 36 (Android 16) and it has the following features :**
** This is a fork of https://github.com/amir-asdp/WebViewToPdf without the permission management and open pdf management.
- Ability to Create a PDF file from a WebView.
- Ability to Save the created PDF file in an arbitrary directory.
- No permission required. If permissions are needed, the original app must manage them. This is to avoid problematic permissions in Google Store

### 
### 
### 
# Gradle Configuration
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

```
dependencies {
	        implementation 'com.github.Philippe-Mignard:WebViewToPdf:1.0.0'
	}
```

### 
### 
### 
# How To Use

**SAMPLE CODE :**

Use `convertWebViewToPdf(..)` to save a WebView object as a PDF file in your arbitrary directory. Use `openPdfFile(..)` to open a pdf file using chooser.
Also you can use `buildPdfPermissionsRationalDialog(..)` when the permissions are denied.
```
Context mContext = MyExampleActivity.this;
File destinationDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS + "/MyExampleApp/");
String fileName = "Example.pdf";


//Make sure that the url is fully loaded in your WebView object and then pass it to the following method:

WebViewToPdf.convertWebViewToPdf(mContext, webView, PrintAttributes.MediaSize.ISO_A4, PrintAttributes.Margins.NO_MARGINS, destinationDirectory, fileName, new WebViewToPdf.OnConvertResultListener() {

            @Override
            public void onSuccess(String pdfFilePath) {
                // Copy the PDF, transform it, see it...
            }
            @Override
            public void onFailure(String failMessage) {
                Toast.makeText(mContext, failMessage, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled() {
                Toast.makeText(mContext, "Convert Cancelled", Toast.LENGTH_SHORT).show();
            }
            
        });
```
