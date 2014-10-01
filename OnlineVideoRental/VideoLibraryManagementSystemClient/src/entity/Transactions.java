/**
 * Transactions.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package entity;

public class Transactions  implements java.io.Serializable {
    private java.util.Calendar actualReturnDate;

    private java.util.Calendar dueDate;

    private float fineAmount;

    private java.util.Calendar issueDate;

    private long membershipId;

    private entity.Movie movie;

    private int movieId;

    private java.lang.String movieName;

    private long transactionId;

    public Transactions() {
    }

    public Transactions(
           java.util.Calendar actualReturnDate,
           java.util.Calendar dueDate,
           float fineAmount,
           java.util.Calendar issueDate,
           long membershipId,
           entity.Movie movie,
           int movieId,
           java.lang.String movieName,
           long transactionId) {
           this.actualReturnDate = actualReturnDate;
           this.dueDate = dueDate;
           this.fineAmount = fineAmount;
           this.issueDate = issueDate;
           this.membershipId = membershipId;
           this.movie = movie;
           this.movieId = movieId;
           this.movieName = movieName;
           this.transactionId = transactionId;
    }


    /**
     * Gets the actualReturnDate value for this Transactions.
     * 
     * @return actualReturnDate
     */
    public java.util.Calendar getActualReturnDate() {
        return actualReturnDate;
    }


    /**
     * Sets the actualReturnDate value for this Transactions.
     * 
     * @param actualReturnDate
     */
    public void setActualReturnDate(java.util.Calendar actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
    }


    /**
     * Gets the dueDate value for this Transactions.
     * 
     * @return dueDate
     */
    public java.util.Calendar getDueDate() {
        return dueDate;
    }


    /**
     * Sets the dueDate value for this Transactions.
     * 
     * @param dueDate
     */
    public void setDueDate(java.util.Calendar dueDate) {
        this.dueDate = dueDate;
    }


    /**
     * Gets the fineAmount value for this Transactions.
     * 
     * @return fineAmount
     */
    public float getFineAmount() {
        return fineAmount;
    }


    /**
     * Sets the fineAmount value for this Transactions.
     * 
     * @param fineAmount
     */
    public void setFineAmount(float fineAmount) {
        this.fineAmount = fineAmount;
    }


    /**
     * Gets the issueDate value for this Transactions.
     * 
     * @return issueDate
     */
    public java.util.Calendar getIssueDate() {
        return issueDate;
    }


    /**
     * Sets the issueDate value for this Transactions.
     * 
     * @param issueDate
     */
    public void setIssueDate(java.util.Calendar issueDate) {
        this.issueDate = issueDate;
    }


    /**
     * Gets the membershipId value for this Transactions.
     * 
     * @return membershipId
     */
    public long getMembershipId() {
        return membershipId;
    }


    /**
     * Sets the membershipId value for this Transactions.
     * 
     * @param membershipId
     */
    public void setMembershipId(long membershipId) {
        this.membershipId = membershipId;
    }


    /**
     * Gets the movie value for this Transactions.
     * 
     * @return movie
     */
    public entity.Movie getMovie() {
        return movie;
    }


    /**
     * Sets the movie value for this Transactions.
     * 
     * @param movie
     */
    public void setMovie(entity.Movie movie) {
        this.movie = movie;
    }


    /**
     * Gets the movieId value for this Transactions.
     * 
     * @return movieId
     */
    public int getMovieId() {
        return movieId;
    }


    /**
     * Sets the movieId value for this Transactions.
     * 
     * @param movieId
     */
    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }


    /**
     * Gets the movieName value for this Transactions.
     * 
     * @return movieName
     */
    public java.lang.String getMovieName() {
        return movieName;
    }


    /**
     * Sets the movieName value for this Transactions.
     * 
     * @param movieName
     */
    public void setMovieName(java.lang.String movieName) {
        this.movieName = movieName;
    }


    /**
     * Gets the transactionId value for this Transactions.
     * 
     * @return transactionId
     */
    public long getTransactionId() {
        return transactionId;
    }


    /**
     * Sets the transactionId value for this Transactions.
     * 
     * @param transactionId
     */
    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Transactions)) return false;
        Transactions other = (Transactions) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.actualReturnDate==null && other.getActualReturnDate()==null) || 
             (this.actualReturnDate!=null &&
              this.actualReturnDate.equals(other.getActualReturnDate()))) &&
            ((this.dueDate==null && other.getDueDate()==null) || 
             (this.dueDate!=null &&
              this.dueDate.equals(other.getDueDate()))) &&
            this.fineAmount == other.getFineAmount() &&
            ((this.issueDate==null && other.getIssueDate()==null) || 
             (this.issueDate!=null &&
              this.issueDate.equals(other.getIssueDate()))) &&
            this.membershipId == other.getMembershipId() &&
            ((this.movie==null && other.getMovie()==null) || 
             (this.movie!=null &&
              this.movie.equals(other.getMovie()))) &&
            this.movieId == other.getMovieId() &&
            ((this.movieName==null && other.getMovieName()==null) || 
             (this.movieName!=null &&
              this.movieName.equals(other.getMovieName()))) &&
            this.transactionId == other.getTransactionId();
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
        if (getActualReturnDate() != null) {
            _hashCode += getActualReturnDate().hashCode();
        }
        if (getDueDate() != null) {
            _hashCode += getDueDate().hashCode();
        }
        _hashCode += new Float(getFineAmount()).hashCode();
        if (getIssueDate() != null) {
            _hashCode += getIssueDate().hashCode();
        }
        _hashCode += new Long(getMembershipId()).hashCode();
        if (getMovie() != null) {
            _hashCode += getMovie().hashCode();
        }
        _hashCode += getMovieId();
        if (getMovieName() != null) {
            _hashCode += getMovieName().hashCode();
        }
        _hashCode += new Long(getTransactionId()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Transactions.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://entity", "Transactions"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("actualReturnDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "actualReturnDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dueDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "dueDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fineAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "fineAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("issueDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "issueDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("membershipId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "membershipId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("movie");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "movie"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://entity", "Movie"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("movieId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "movieId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("movieName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "movieName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "transactionId"));
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
