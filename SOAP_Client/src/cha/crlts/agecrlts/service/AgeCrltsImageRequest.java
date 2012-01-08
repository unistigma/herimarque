/**
 * AgeCrltsImageRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cha.crlts.agecrlts.service;

public class AgeCrltsImageRequest  extends iros.kma.ummd.iss.req.ComMsgHeader  implements java.io.Serializable {
    private int nowPageNo;

    private int pageMg;

    private java.lang.String itemCd;

    private java.lang.String itemNm;

    private java.lang.String crltsNo;

    private java.lang.String crltsNoNm;

    private java.lang.String ctrdCd;

    private java.lang.String crltsNm;

    public AgeCrltsImageRequest() {
    }

    public AgeCrltsImageRequest(
           java.lang.String requestMsgID,
           java.lang.String serviceKey,
           java.lang.String requestTime,
           java.lang.String callBackURI,
           int nowPageNo,
           int pageMg,
           java.lang.String itemCd,
           java.lang.String itemNm,
           java.lang.String crltsNo,
           java.lang.String crltsNoNm,
           java.lang.String ctrdCd,
           java.lang.String crltsNm) {
        super(
            requestMsgID,
            serviceKey,
            requestTime,
            callBackURI);
        this.nowPageNo = nowPageNo;
        this.pageMg = pageMg;
        this.itemCd = itemCd;
        this.itemNm = itemNm;
        this.crltsNo = crltsNo;
        this.crltsNoNm = crltsNoNm;
        this.ctrdCd = ctrdCd;
        this.crltsNm = crltsNm;
    }


    /**
     * Gets the nowPageNo value for this AgeCrltsImageRequest.
     * 
     * @return nowPageNo
     */
    public int getNowPageNo() {
        return nowPageNo;
    }


    /**
     * Sets the nowPageNo value for this AgeCrltsImageRequest.
     * 
     * @param nowPageNo
     */
    public void setNowPageNo(int nowPageNo) {
        this.nowPageNo = nowPageNo;
    }


    /**
     * Gets the pageMg value for this AgeCrltsImageRequest.
     * 
     * @return pageMg
     */
    public int getPageMg() {
        return pageMg;
    }


    /**
     * Sets the pageMg value for this AgeCrltsImageRequest.
     * 
     * @param pageMg
     */
    public void setPageMg(int pageMg) {
        this.pageMg = pageMg;
    }


    /**
     * Gets the itemCd value for this AgeCrltsImageRequest.
     * 
     * @return itemCd
     */
    public java.lang.String getItemCd() {
        return itemCd;
    }


    /**
     * Sets the itemCd value for this AgeCrltsImageRequest.
     * 
     * @param itemCd
     */
    public void setItemCd(java.lang.String itemCd) {
        this.itemCd = itemCd;
    }


    /**
     * Gets the itemNm value for this AgeCrltsImageRequest.
     * 
     * @return itemNm
     */
    public java.lang.String getItemNm() {
        return itemNm;
    }


    /**
     * Sets the itemNm value for this AgeCrltsImageRequest.
     * 
     * @param itemNm
     */
    public void setItemNm(java.lang.String itemNm) {
        this.itemNm = itemNm;
    }


    /**
     * Gets the crltsNo value for this AgeCrltsImageRequest.
     * 
     * @return crltsNo
     */
    public java.lang.String getCrltsNo() {
        return crltsNo;
    }


    /**
     * Sets the crltsNo value for this AgeCrltsImageRequest.
     * 
     * @param crltsNo
     */
    public void setCrltsNo(java.lang.String crltsNo) {
        this.crltsNo = crltsNo;
    }


    /**
     * Gets the crltsNoNm value for this AgeCrltsImageRequest.
     * 
     * @return crltsNoNm
     */
    public java.lang.String getCrltsNoNm() {
        return crltsNoNm;
    }


    /**
     * Sets the crltsNoNm value for this AgeCrltsImageRequest.
     * 
     * @param crltsNoNm
     */
    public void setCrltsNoNm(java.lang.String crltsNoNm) {
        this.crltsNoNm = crltsNoNm;
    }


    /**
     * Gets the ctrdCd value for this AgeCrltsImageRequest.
     * 
     * @return ctrdCd
     */
    public java.lang.String getCtrdCd() {
        return ctrdCd;
    }


    /**
     * Sets the ctrdCd value for this AgeCrltsImageRequest.
     * 
     * @param ctrdCd
     */
    public void setCtrdCd(java.lang.String ctrdCd) {
        this.ctrdCd = ctrdCd;
    }


    /**
     * Gets the crltsNm value for this AgeCrltsImageRequest.
     * 
     * @return crltsNm
     */
    public java.lang.String getCrltsNm() {
        return crltsNm;
    }


    /**
     * Sets the crltsNm value for this AgeCrltsImageRequest.
     * 
     * @param crltsNm
     */
    public void setCrltsNm(java.lang.String crltsNm) {
        this.crltsNm = crltsNm;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AgeCrltsImageRequest)) return false;
        AgeCrltsImageRequest other = (AgeCrltsImageRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            this.nowPageNo == other.getNowPageNo() &&
            this.pageMg == other.getPageMg() &&
            ((this.itemCd==null && other.getItemCd()==null) || 
             (this.itemCd!=null &&
              this.itemCd.equals(other.getItemCd()))) &&
            ((this.itemNm==null && other.getItemNm()==null) || 
             (this.itemNm!=null &&
              this.itemNm.equals(other.getItemNm()))) &&
            ((this.crltsNo==null && other.getCrltsNo()==null) || 
             (this.crltsNo!=null &&
              this.crltsNo.equals(other.getCrltsNo()))) &&
            ((this.crltsNoNm==null && other.getCrltsNoNm()==null) || 
             (this.crltsNoNm!=null &&
              this.crltsNoNm.equals(other.getCrltsNoNm()))) &&
            ((this.ctrdCd==null && other.getCtrdCd()==null) || 
             (this.ctrdCd!=null &&
              this.ctrdCd.equals(other.getCtrdCd()))) &&
            ((this.crltsNm==null && other.getCrltsNm()==null) || 
             (this.crltsNm!=null &&
              this.crltsNm.equals(other.getCrltsNm())));
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
        _hashCode += getNowPageNo();
        _hashCode += getPageMg();
        if (getItemCd() != null) {
            _hashCode += getItemCd().hashCode();
        }
        if (getItemNm() != null) {
            _hashCode += getItemNm().hashCode();
        }
        if (getCrltsNo() != null) {
            _hashCode += getCrltsNo().hashCode();
        }
        if (getCrltsNoNm() != null) {
            _hashCode += getCrltsNoNm().hashCode();
        }
        if (getCtrdCd() != null) {
            _hashCode += getCtrdCd().hashCode();
        }
        if (getCrltsNm() != null) {
            _hashCode += getCrltsNm().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AgeCrltsImageRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.agecrlts.crlts.cha/", "ageCrltsImageRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nowPageNo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nowPageNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pageMg");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pageMg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("itemCd");
        elemField.setXmlName(new javax.xml.namespace.QName("", "itemCd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("itemNm");
        elemField.setXmlName(new javax.xml.namespace.QName("", "itemNm"));
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
        elemField.setFieldName("crltsNoNm");
        elemField.setXmlName(new javax.xml.namespace.QName("", "crltsNoNm"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("crltsNm");
        elemField.setXmlName(new javax.xml.namespace.QName("", "crltsNm"));
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
