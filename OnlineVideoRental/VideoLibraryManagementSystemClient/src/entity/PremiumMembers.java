/**
 * PremiumMembers.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package entity;

public class PremiumMembers  implements java.io.Serializable {
    private java.lang.String address;

    private java.lang.String city;

    private float dueAmount;

    private java.lang.String emailId;

    private java.util.Calendar expiryDate;

    private java.lang.String firstName;

    private java.util.Calendar lastLogin;

    private java.lang.String lastName;

    private long membershipId;

    private java.lang.String password;

    private int premiumMemberId;

    private java.util.Calendar registrationDate;

    private java.lang.String state;

    private int totalOutstandingMovies;

    private int userType;

    private long zipCode;

    public PremiumMembers() {
    }

    public PremiumMembers(
           java.lang.String address,
           java.lang.String city,
           float dueAmount,
           java.lang.String emailId,
           java.util.Calendar expiryDate,
           java.lang.String firstName,
           java.util.Calendar lastLogin,
           java.lang.String lastName,
           long membershipId,
           java.lang.String password,
           int premiumMemberId,
           java.util.Calendar registrationDate,
           java.lang.String state,
           int totalOutstandingMovies,
           int userType,
           long zipCode) {
           this.address = address;
           this.city = city;
           this.dueAmount = dueAmount;
           this.emailId = emailId;
           this.expiryDate = expiryDate;
           this.firstName = firstName;
           this.lastLogin = lastLogin;
           this.lastName = lastName;
           this.membershipId = membershipId;
           this.password = password;
           this.premiumMemberId = premiumMemberId;
           this.registrationDate = registrationDate;
           this.state = state;
           this.totalOutstandingMovies = totalOutstandingMovies;
           this.userType = userType;
           this.zipCode = zipCode;
    }


    /**
     * Gets the address value for this PremiumMembers.
     * 
     * @return address
     */
    public java.lang.String getAddress() {
        return address;
    }


    /**
     * Sets the address value for this PremiumMembers.
     * 
     * @param address
     */
    public void setAddress(java.lang.String address) {
        this.address = address;
    }


    /**
     * Gets the city value for this PremiumMembers.
     * 
     * @return city
     */
    public java.lang.String getCity() {
        return city;
    }


    /**
     * Sets the city value for this PremiumMembers.
     * 
     * @param city
     */
    public void setCity(java.lang.String city) {
        this.city = city;
    }


    /**
     * Gets the dueAmount value for this PremiumMembers.
     * 
     * @return dueAmount
     */
    public float getDueAmount() {
        return dueAmount;
    }


    /**
     * Sets the dueAmount value for this PremiumMembers.
     * 
     * @param dueAmount
     */
    public void setDueAmount(float dueAmount) {
        this.dueAmount = dueAmount;
    }


    /**
     * Gets the emailId value for this PremiumMembers.
     * 
     * @return emailId
     */
    public java.lang.String getEmailId() {
        return emailId;
    }


    /**
     * Sets the emailId value for this PremiumMembers.
     * 
     * @param emailId
     */
    public void setEmailId(java.lang.String emailId) {
        this.emailId = emailId;
    }


    /**
     * Gets the expiryDate value for this PremiumMembers.
     * 
     * @return expiryDate
     */
    public java.util.Calendar getExpiryDate() {
        return expiryDate;
    }


    /**
     * Sets the expiryDate value for this PremiumMembers.
     * 
     * @param expiryDate
     */
    public void setExpiryDate(java.util.Calendar expiryDate) {
        this.expiryDate = expiryDate;
    }


    /**
     * Gets the firstName value for this PremiumMembers.
     * 
     * @return firstName
     */
    public java.lang.String getFirstName() {
        return firstName;
    }


    /**
     * Sets the firstName value for this PremiumMembers.
     * 
     * @param firstName
     */
    public void setFirstName(java.lang.String firstName) {
        this.firstName = firstName;
    }


    /**
     * Gets the lastLogin value for this PremiumMembers.
     * 
     * @return lastLogin
     */
    public java.util.Calendar getLastLogin() {
        return lastLogin;
    }


    /**
     * Sets the lastLogin value for this PremiumMembers.
     * 
     * @param lastLogin
     */
    public void setLastLogin(java.util.Calendar lastLogin) {
        this.lastLogin = lastLogin;
    }


    /**
     * Gets the lastName value for this PremiumMembers.
     * 
     * @return lastName
     */
    public java.lang.String getLastName() {
        return lastName;
    }


    /**
     * Sets the lastName value for this PremiumMembers.
     * 
     * @param lastName
     */
    public void setLastName(java.lang.String lastName) {
        this.lastName = lastName;
    }


    /**
     * Gets the membershipId value for this PremiumMembers.
     * 
     * @return membershipId
     */
    public long getMembershipId() {
        return membershipId;
    }


    /**
     * Sets the membershipId value for this PremiumMembers.
     * 
     * @param membershipId
     */
    public void setMembershipId(long membershipId) {
        this.membershipId = membershipId;
    }


    /**
     * Gets the password value for this PremiumMembers.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this PremiumMembers.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the premiumMemberId value for this PremiumMembers.
     * 
     * @return premiumMemberId
     */
    public int getPremiumMemberId() {
        return premiumMemberId;
    }


    /**
     * Sets the premiumMemberId value for this PremiumMembers.
     * 
     * @param premiumMemberId
     */
    public void setPremiumMemberId(int premiumMemberId) {
        this.premiumMemberId = premiumMemberId;
    }


    /**
     * Gets the registrationDate value for this PremiumMembers.
     * 
     * @return registrationDate
     */
    public java.util.Calendar getRegistrationDate() {
        return registrationDate;
    }


    /**
     * Sets the registrationDate value for this PremiumMembers.
     * 
     * @param registrationDate
     */
    public void setRegistrationDate(java.util.Calendar registrationDate) {
        this.registrationDate = registrationDate;
    }


    /**
     * Gets the state value for this PremiumMembers.
     * 
     * @return state
     */
    public java.lang.String getState() {
        return state;
    }


    /**
     * Sets the state value for this PremiumMembers.
     * 
     * @param state
     */
    public void setState(java.lang.String state) {
        this.state = state;
    }


    /**
     * Gets the totalOutstandingMovies value for this PremiumMembers.
     * 
     * @return totalOutstandingMovies
     */
    public int getTotalOutstandingMovies() {
        return totalOutstandingMovies;
    }


    /**
     * Sets the totalOutstandingMovies value for this PremiumMembers.
     * 
     * @param totalOutstandingMovies
     */
    public void setTotalOutstandingMovies(int totalOutstandingMovies) {
        this.totalOutstandingMovies = totalOutstandingMovies;
    }


    /**
     * Gets the userType value for this PremiumMembers.
     * 
     * @return userType
     */
    public int getUserType() {
        return userType;
    }


    /**
     * Sets the userType value for this PremiumMembers.
     * 
     * @param userType
     */
    public void setUserType(int userType) {
        this.userType = userType;
    }


    /**
     * Gets the zipCode value for this PremiumMembers.
     * 
     * @return zipCode
     */
    public long getZipCode() {
        return zipCode;
    }


    /**
     * Sets the zipCode value for this PremiumMembers.
     * 
     * @param zipCode
     */
    public void setZipCode(long zipCode) {
        this.zipCode = zipCode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PremiumMembers)) return false;
        PremiumMembers other = (PremiumMembers) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.address==null && other.getAddress()==null) || 
             (this.address!=null &&
              this.address.equals(other.getAddress()))) &&
            ((this.city==null && other.getCity()==null) || 
             (this.city!=null &&
              this.city.equals(other.getCity()))) &&
            this.dueAmount == other.getDueAmount() &&
            ((this.emailId==null && other.getEmailId()==null) || 
             (this.emailId!=null &&
              this.emailId.equals(other.getEmailId()))) &&
            ((this.expiryDate==null && other.getExpiryDate()==null) || 
             (this.expiryDate!=null &&
              this.expiryDate.equals(other.getExpiryDate()))) &&
            ((this.firstName==null && other.getFirstName()==null) || 
             (this.firstName!=null &&
              this.firstName.equals(other.getFirstName()))) &&
            ((this.lastLogin==null && other.getLastLogin()==null) || 
             (this.lastLogin!=null &&
              this.lastLogin.equals(other.getLastLogin()))) &&
            ((this.lastName==null && other.getLastName()==null) || 
             (this.lastName!=null &&
              this.lastName.equals(other.getLastName()))) &&
            this.membershipId == other.getMembershipId() &&
            ((this.password==null && other.getPassword()==null) || 
             (this.password!=null &&
              this.password.equals(other.getPassword()))) &&
            this.premiumMemberId == other.getPremiumMemberId() &&
            ((this.registrationDate==null && other.getRegistrationDate()==null) || 
             (this.registrationDate!=null &&
              this.registrationDate.equals(other.getRegistrationDate()))) &&
            ((this.state==null && other.getState()==null) || 
             (this.state!=null &&
              this.state.equals(other.getState()))) &&
            this.totalOutstandingMovies == other.getTotalOutstandingMovies() &&
            this.userType == other.getUserType() &&
            this.zipCode == other.getZipCode();
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
        if (getAddress() != null) {
            _hashCode += getAddress().hashCode();
        }
        if (getCity() != null) {
            _hashCode += getCity().hashCode();
        }
        _hashCode += new Float(getDueAmount()).hashCode();
        if (getEmailId() != null) {
            _hashCode += getEmailId().hashCode();
        }
        if (getExpiryDate() != null) {
            _hashCode += getExpiryDate().hashCode();
        }
        if (getFirstName() != null) {
            _hashCode += getFirstName().hashCode();
        }
        if (getLastLogin() != null) {
            _hashCode += getLastLogin().hashCode();
        }
        if (getLastName() != null) {
            _hashCode += getLastName().hashCode();
        }
        _hashCode += new Long(getMembershipId()).hashCode();
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        _hashCode += getPremiumMemberId();
        if (getRegistrationDate() != null) {
            _hashCode += getRegistrationDate().hashCode();
        }
        if (getState() != null) {
            _hashCode += getState().hashCode();
        }
        _hashCode += getTotalOutstandingMovies();
        _hashCode += getUserType();
        _hashCode += new Long(getZipCode()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PremiumMembers.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://entity", "PremiumMembers"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("address");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "address"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("city");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "city"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dueAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "dueAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("emailId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "emailId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("expiryDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "expiryDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("firstName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "firstName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastLogin");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "lastLogin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "lastName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("membershipId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "membershipId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("password");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "password"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("premiumMemberId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "premiumMemberId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("registrationDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "registrationDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("state");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "state"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalOutstandingMovies");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "totalOutstandingMovies"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "userType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("zipCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "zipCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
