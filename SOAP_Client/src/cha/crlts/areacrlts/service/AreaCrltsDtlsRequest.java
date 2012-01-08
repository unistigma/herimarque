/**
 * AreaCrltsDtlsRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cha.crlts.areacrlts.service;

public class AreaCrltsDtlsRequest  extends iros.kma.ummd.iss.req.ComMsgHeader  implements java.io.Serializable {
    private java.lang.String itemCd;

    private java.lang.String crltsNo;

    private java.lang.String ctrdCd;

    public AreaCrltsDtlsRequest() {
    }

    public AreaCrltsDtlsRequest(
           java.lang.String requestMsgID,
           java.lang.String serviceKey,
           java.lang.String requestTime,
           java.lang.String callBackURI,
           java.lang.String itemCd,
           java.lang.String crltsNo,
           java.lang.String ctrdCd) {
        super(
            requestMsgID,
            serviceKey,
            requestTime,
            callBackURI);
        this.itemCd = itemCd;
        this.crltsNo = crltsNo;
        this.ctrdCd = ctrdCd;
    }


    /**
     * Gets the itemCd value for this AreaCrltsDtlsRequest.
     * 
     * @return itemCd
     */
    public java.lang.String getItemCd() {
        return itemCd;
    }


    /**
     * Sets the itemCd value for this AreaCrltsDtlsRequest.
     * 
     * @param itemCd
     */
    public void setItemCd(java.lang.String itemCd) {
        this.itemCd = itemCd;
    }


    /**
     * Gets the crltsNo value for this AreaCrltsDtlsRequest.
     * 
     * @return crltsNo
     */
    public java.lang.String getCrltsNo() {
        return crltsNo;
    }


    /**
     * Sets the crltsNo value for this AreaCrltsDtlsRequest.
     * 
     * @param crltsNo
     */
    public void setCrltsNo(java.lang.String crltsNo) {
        this.crltsNo = crltsNo;
    }


    /**
     * Gets the ctrdCd value for this AreaCrltsDtlsRequest.
     * 
     * @return ctrdCd
     */
    public java.lang.String getCtrdCd() {
        return ctrdCd;
    }


    /**
     * Sets the ctrdCd value for this AreaCrltsDtlsRequest.
     * 
     * @param ctrdCd
     */
    public void setCtrdCd(java.lang.String ctrdCd) {
        this.ctrdCd = ctrdCd;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AreaCrltsDtlsRequest)) return false;
        AreaCrltsDtlsRequest other = (AreaCrltsDtlsRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.itemCd==null && other.getItemCd()==null) || 
             (this.itemCd!=null &&
              this.itemCd.equals(other.getItemCd()))) &&
            ((this.crltsNo==null && other.getCrltsNo()==null) || 
             (this.crltsNo!=null &&
              this.crltsNo.equals(other.getCrltsNo()))) &&
            ((this.ctrdCd==null && other.getCtrdCd()==null) || 
             (this.ctrdCd!=null &&
              this.ctrdCd.equals(other.getCtrdCd())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getItemCd() != null) {
            _hashCode += getItemCd().hashCode();
        }
        if (getCrltsNo() != null) {
            _hashCode += getCrltsNo().hashCode();
        }
        if (getCtrdCd() != null) {
            _hashCode += getCtrdCd().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AreaCrltsDtlsRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.areacrlts.crlts.cha/", "areaCrltsDtlsRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("itemCd");
        elemField.setXmlName(new javax.xml.namespace.QName("", "itemCd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("crltsNo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "crltsNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ctrdCd");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ctrdCd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
