/**
 * BaseResponseVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cha.crlts.areacrlts.service;

public class BaseResponseVO  implements java.io.Serializable {
    private java.lang.String errMsg;

    private java.lang.String failrCause;

    private int nowPageNo;

    private int pageMg;

    private java.lang.String resultCd;

    private java.lang.String returnCode;

    private java.lang.Integer totCnt;

    public BaseResponseVO() {
    }

    public BaseResponseVO(
           java.lang.String errMsg,
           java.lang.String failrCause,
           int nowPageNo,
           int pageMg,
           java.lang.String resultCd,
           java.lang.String returnCode,
           java.lang.Integer totCnt) {
           this.errMsg = errMsg;
           this.failrCause = failrCause;
           this.nowPageNo = nowPageNo;
           this.pageMg = pageMg;
           this.resultCd = resultCd;
           this.returnCode = returnCode;
           this.totCnt = totCnt;
    }


    /**
     * Gets the errMsg value for this BaseResponseVO.
     * 
     * @return errMsg
     */
    public java.lang.String getErrMsg() {
        return errMsg;
    }


    /**
     * Sets the errMsg value for this BaseResponseVO.
     * 
     * @param errMsg
     */
    public void setErrMsg(java.lang.String errMsg) {
        this.errMsg = errMsg;
    }


    /**
     * Gets the failrCause value for this BaseResponseVO.
     * 
     * @return failrCause
     */
    public java.lang.String getFailrCause() {
        return failrCause;
    }


    /**
     * Sets the failrCause value for this BaseResponseVO.
     * 
     * @param failrCause
     */
    public void setFailrCause(java.lang.String failrCause) {
        this.failrCause = failrCause;
    }


    /**
     * Gets the nowPageNo value for this BaseResponseVO.
     * 
     * @return nowPageNo
     */
    public int getNowPageNo() {
        return nowPageNo;
    }


    /**
     * Sets the nowPageNo value for this BaseResponseVO.
     * 
     * @param nowPageNo
     */
    public void setNowPageNo(int nowPageNo) {
        this.nowPageNo = nowPageNo;
    }


    /**
     * Gets the pageMg value for this BaseResponseVO.
     * 
     * @return pageMg
     */
    public int getPageMg() {
        return pageMg;
    }


    /**
     * Sets the pageMg value for this BaseResponseVO.
     * 
     * @param pageMg
     */
    public void setPageMg(int pageMg) {
        this.pageMg = pageMg;
    }


    /**
     * Gets the resultCd value for this BaseResponseVO.
     * 
     * @return resultCd
     */
    public java.lang.String getResultCd() {
        return resultCd;
    }


    /**
     * Sets the resultCd value for this BaseResponseVO.
     * 
     * @param resultCd
     */
    public void setResultCd(java.lang.String resultCd) {
        this.resultCd = resultCd;
    }


    /**
     * Gets the returnCode value for this BaseResponseVO.
     * 
     * @return returnCode
     */
    public java.lang.String getReturnCode() {
        return returnCode;
    }


    /**
     * Sets the returnCode value for this BaseResponseVO.
     * 
     * @param returnCode
     */
    public void setReturnCode(java.lang.String returnCode) {
        this.returnCode = returnCode;
    }


    /**
     * Gets the totCnt value for this BaseResponseVO.
     * 
     * @return totCnt
     */
    public java.lang.Integer getTotCnt() {
        return totCnt;
    }


    /**
     * Sets the totCnt value for this BaseResponseVO.
     * 
     * @param totCnt
     */
    public void setTotCnt(java.lang.Integer totCnt) {
        this.totCnt = totCnt;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BaseResponseVO)) return false;
        BaseResponseVO other = (BaseResponseVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.errMsg==null && other.getErrMsg()==null) || 
             (this.errMsg!=null &&
              this.errMsg.equals(other.getErrMsg()))) &&
            ((this.failrCause==null && other.getFailrCause()==null) || 
             (this.failrCause!=null &&
              this.failrCause.equals(other.getFailrCause()))) &&
            this.nowPageNo == other.getNowPageNo() &&
            this.pageMg == other.getPageMg() &&
            ((this.resultCd==null && other.getResultCd()==null) || 
             (this.resultCd!=null &&
              this.resultCd.equals(other.getResultCd()))) &&
            ((this.returnCode==null && other.getReturnCode()==null) || 
             (this.returnCode!=null &&
              this.returnCode.equals(other.getReturnCode()))) &&
            ((this.totCnt==null && other.getTotCnt()==null) || 
             (this.totCnt!=null &&
              this.totCnt.equals(other.getTotCnt())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getErrMsg() != null) {
            _hashCode += getErrMsg().hashCode();
        }
        if (getFailrCause() != null) {
            _hashCode += getFailrCause().hashCode();
        }
        _hashCode += getNowPageNo();
        _hashCode += getPageMg();
        if (getResultCd() != null) {
            _hashCode += getResultCd().hashCode();
        }
        if (getReturnCode() != null) {
            _hashCode += getReturnCode().hashCode();
        }
        if (getTotCnt() != null) {
            _hashCode += getTotCnt().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BaseResponseVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.areacrlts.crlts.cha/", "baseResponseVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errMsg");
        elemField.setXmlName(new javax.xml.namespace.QName("", "errMsg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("failrCause");
        elemField.setXmlName(new javax.xml.namespace.QName("", "failrCause"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("resultCd");
        elemField.setXmlName(new javax.xml.namespace.QName("", "resultCd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("returnCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "returnCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totCnt");
        elemField.setXmlName(new javax.xml.namespace.QName("", "totCnt"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
