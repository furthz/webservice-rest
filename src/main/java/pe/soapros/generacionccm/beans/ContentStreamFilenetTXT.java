package pe.soapros.generacionccm.beans;

import javax.validation.constraints.NotNull;

class ContentStreamFilenetTXT {

    @NotNull(message = "length de ContentStreamFilenetTXT es obligatorio")
    String length;

    @NotNull(message = "mimeType de ContentStreamFilenetTXT es obligatorio")
    String mimeType;

    @NotNull(message = "fileName de ContentStreamFilenetTXT es obligatorio")
    String fileName;

    @NotNull(message = "stream de ContentStreamFilenetTXT es obligatorio")
    String stream;

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

}
