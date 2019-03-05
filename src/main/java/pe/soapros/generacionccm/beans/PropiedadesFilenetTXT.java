package pe.soapros.generacionccm.beans;

import javax.validation.constraints.NotNull;

class PropiedadesFilenetTXT {

    @NotNull(message = "cmisObjectTypeId de PropiedadesFilenetTXT es obligatorio")
    String cmisObjectTypeId;

    @NotNull(message = "cmisName de PropiedadesFilenetTXT es obligatorio")
    String cmisName;

    @NotNull(message = "ecmid de PropiedadesFilenetTXT es obligatorio")
    String ecmid;

    @NotNull(message = "userName de PropiedadesFilenetTXT es obligatorio")
    String userName;

    @NotNull(message = "batchId de PropiedadesFilenetTXT es obligatorio")
    String batchId;

    @NotNull(message = "documentType de PropiedadesFilenetTXT es obligatorio")
    String documentType;

    @NotNull(message = "existingFolder de PropiedadesFilenetTXT es obligatorio")
    String existingFolder;

    @NotNull(message = "grDocumentType de PropiedadesFilenetTXT es obligatorio")
    String grDocumentType;

    public String getCmisObjectTypeId() {
        return cmisObjectTypeId;
    }

    public void setCmisObjectTypeId(String cmisObjectTypeId) {
        this.cmisObjectTypeId = cmisObjectTypeId;
    }

    public String getCmisName() {
        return cmisName;
    }

    public void setCmisName(String cmisName) {
        this.cmisName = cmisName;
    }

    public String getEcmid() {
        return ecmid;
    }

    public void setEcmid(String ecmid) {
        this.ecmid = ecmid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getExistingFolder() {
        return existingFolder;
    }

    public void setExistingFolder(String existingFolder) {
        this.existingFolder = existingFolder;
    }

    public String getGrDocumentType() {
        return grDocumentType;
    }

    public void setGrDocumentType(String grDocumentType) {
        this.grDocumentType = grDocumentType;
    }

}
