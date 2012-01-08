/**
 * ComMsgHeader.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package iros.kma.ummd.iss.req;

public class ComMsgHeader  implements java.io.Serializable {
    private java.lang.String requestMsgID="netinamu42";

    private java.lang.String serviceKey="6i1zuQWddVTMFSqlHyhnbw8VL7uvfuHHt3Pth12H3VuFrUvdzFRcfYBZRfTE0Eidf/hV3otAz36MR1F/VDWUDw==";

    private java.lang.String requestTime;

    private java.lang.String callBackURI;

    public ComMsgHeader() {
    }

    public ComMsgHeader(
           java.lang.String requestMsgID,
           java.lang.String serviceKey,
           java.lang.String requestTime,
           java.lang.String callBackURI) {
//           this.requestMsgID = requestMsgID;
//           this.serviceKey = serviceKey;
           this.requestTime = requestTime;
           this.callBackURI = callBackURI;
    }


    /**
     * Gets the requestMsgID value for this ComMsgHeader.
     * 
     * @return requestMsgID
     */
    public java.lang.String getRequestMsgID() {
        return requestMsgID;
    }


    /**
     * Sets the requestMsgID value for this ComMsgHeader.
     * 
     * @param requestMsgID
     */
    public void setRequestMsgID(java.lang.String requestMsgID) {
  //      this.requestMsgID = requestMsgID;
    }


    /**
     * Gets the serviceKey value for this ComMsgHeader.
     * 
     * @return serviceKey
     */
    public java.lang.String getServiceKey() {
        return serviceKey;
    }


    /**
     * Sets the serviceKey value for this ComMsgHeader.
     * 
     * @param serviceKey
     */
    public void setServiceKey(java.lang.String serviceKey) {
//        this.serviceKey = serviceKey;
    }


    /**
     * Gets the requestTime value for this ComMsgHeader.
     * 
     * @return requestTime
     */
    public java.lang.String getRequestTime() {
        return requestTime;
    }


    /**
     * Sets the requestTime value for this ComMsgHeader.
     * 
     * @param requestTime
     */
    public void setRequestTime(java.lang.String requestTime) {
        this.requestTime = requestTime;
    }


    /**
     * Gets the callBackURI value for this ComMsgHeader.
     * 
     * @return callBackURI
     */
    public java.lang.String getCallBackURI() {
        return callBackURI;
    }


    /**
     * Sets the callBackURI value for this ComMsgHeader.
     * 
     * @param callBackURI
     */
    public void setCallBackURI(java.lang.String callBackURI) {
        this.callBackURI = callBackURI;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ComMsgHeader)) return false;
        ComMsgHeader other = (ComMsgHeader) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.requestMsgID==null && other.getRequestMsgID()==null) || 
             (this.requestMsgID!=null &&
              this.requestMsgID.equals(other.getRequestMsgID()))) &&
            ((this.serviceKey==null && other.getServiceKey()==null) || 
             (this.serviceKey!=null &&
              this.serviceKey.equals(other.getServiceKey()))) &&
            ((this.requestTime==null && other.getRequestTime()==null) || 
             (this.requestTime!=null &&
              this.requestTime.equals(other.getRequestTime()))) &&
            ((this.callBackURI==null && other.getCallBackURI()==null) || 
             (this.callBackURI!=null &&
              this.callBackURI.equals(other.getCallBackURI())));
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
        if (getRequestMsgID() != null) {
            _hashCode += getRequestMsgID().hashCode();
        }
        if (getServiceKey() != null) {
            _hashCode += getServiceKey().hashCode();
        }
        if (getRequestTime() != null) {
            _hashCode += getRequestTime().hashCode();
        }
        if (getCallBackURI() != null) {
            _hashCode += getCallBackURI().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ComMsgHeader.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://iss.ummd.kma.iros/req", "ComMsgHeader"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestMsgID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RequestMsgID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceKey");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceKey"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RequestTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("callBackURI");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CallBackURI"));
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
