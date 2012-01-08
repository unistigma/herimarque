/**
 * AgeCrltsImageResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cha.crlts.agecrlts.service;

public class AgeCrltsImageResponse  extends cha.crlts.agecrlts.service.BaseResponseVO  implements java.io.Serializable {
    private cha.crlts.agecrlts.service.AgeCrltsImageVO[] items;

    public AgeCrltsImageResponse() {
    }

    public AgeCrltsImageResponse(
           java.lang.String errMsg,
           java.lang.String failrCause,
           int nowPageNo,
           int pageMg,
           java.lang.String resultCd,
           java.lang.String returnCode,
           java.lang.Integer totCnt,
           cha.crlts.agecrlts.service.AgeCrltsImageVO[] items) {
        super(
            errMsg,
            failrCause,
            nowPageNo,
            pageMg,
            resultCd,
            returnCode,
            totCnt);
        this.items = items;
    }


    /**
     * Gets the items value for this AgeCrltsImageResponse.
     * 
     * @return items
     */
    public cha.crlts.agecrlts.service.AgeCrltsImageVO[] getItems() {
        return items;
    }


    /**
     * Sets the items value for this AgeCrltsImageResponse.
     * 
     * @param items
     */
    public void setItems(cha.crlts.agecrlts.service.AgeCrltsImageVO[] items) {
        this.items = items;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AgeCrltsImageResponse)) return false;
        AgeCrltsImageResponse other = (AgeCrltsImageResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.items==null && other.getItems()==null) || 
             (this.items!=null &&
              java.util.Arrays.equals(this.items, other.getItems())));
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
        if (getItems() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getItems());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getItems(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AgeCrltsImageResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.agecrlts.crlts.cha/", "ageCrltsImageResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("items");
        elemField.setXmlName(new javax.xml.namespace.QName("", "items"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.agecrlts.crlts.cha/", "ageCrltsImageVO"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
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
