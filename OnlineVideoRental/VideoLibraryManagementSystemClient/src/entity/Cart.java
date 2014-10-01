/**
 * Cart.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package entity;

public class Cart  implements java.io.Serializable {
    private int cartId;

    private long membershipId;

    private entity.Movie movieDetails;

    private int movieId;

    public Cart() {
    }

    public Cart(
           int cartId,
           long membershipId,
           entity.Movie movieDetails,
           int movieId) {
           this.cartId = cartId;
           this.membershipId = membershipId;
           this.movieDetails = movieDetails;
           this.movieId = movieId;
    }


    /**
     * Gets the cartId value for this Cart.
     * 
     * @return cartId
     */
    public int getCartId() {
        return cartId;
    }


    /**
     * Sets the cartId value for this Cart.
     * 
     * @param cartId
     */
    public void setCartId(int cartId) {
        this.cartId = cartId;
    }


    /**
     * Gets the membershipId value for this Cart.
     * 
     * @return membershipId
     */
    public long getMembershipId() {
        return membershipId;
    }


    /**
     * Sets the membershipId value for this Cart.
     * 
     * @param membershipId
     */
    public void setMembershipId(long membershipId) {
        this.membershipId = membershipId;
    }


    /**
     * Gets the movieDetails value for this Cart.
     * 
     * @return movieDetails
     */
    public entity.Movie getMovieDetails() {
        return movieDetails;
    }


    /**
     * Sets the movieDetails value for this Cart.
     * 
     * @param movieDetails
     */
    public void setMovieDetails(entity.Movie movieDetails) {
        this.movieDetails = movieDetails;
    }


    /**
     * Gets the movieId value for this Cart.
     * 
     * @return movieId
     */
    public int getMovieId() {
        return movieId;
    }


    /**
     * Sets the movieId value for this Cart.
     * 
     * @param movieId
     */
    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Cart)) return false;
        Cart other = (Cart) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.cartId == other.getCartId() &&
            this.membershipId == other.getMembershipId() &&
            ((this.movieDetails==null && other.getMovieDetails()==null) || 
             (this.movieDetails!=null &&
              this.movieDetails.equals(other.getMovieDetails()))) &&
            this.movieId == other.getMovieId();
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
        _hashCode += getCartId();
        _hashCode += new Long(getMembershipId()).hashCode();
        if (getMovieDetails() != null) {
            _hashCode += getMovieDetails().hashCode();
        }
        _hashCode += getMovieId();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Cart.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://entity", "Cart"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cartId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "cartId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("membershipId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "membershipId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("movieDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "movieDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://entity", "Movie"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("movieId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "movieId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
