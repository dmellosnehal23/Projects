/**
 * Movie.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package entity;

public class Movie  implements java.io.Serializable {
    private int availableCopies;

    private java.lang.String movieBanner;

    private java.lang.String movieCategory;

    private int movieId;

    private java.lang.String movieName;

    private java.util.Calendar movieReleaseDate;

    private float movieRent;

    private entity.Person[] users;

    public Movie() {
    }

    public Movie(
           int availableCopies,
           java.lang.String movieBanner,
           java.lang.String movieCategory,
           int movieId,
           java.lang.String movieName,
           java.util.Calendar movieReleaseDate,
           float movieRent,
           entity.Person[] users) {
           this.availableCopies = availableCopies;
           this.movieBanner = movieBanner;
           this.movieCategory = movieCategory;
           this.movieId = movieId;
           this.movieName = movieName;
           this.movieReleaseDate = movieReleaseDate;
           this.movieRent = movieRent;
           this.users = users;
    }


    /**
     * Gets the availableCopies value for this Movie.
     * 
     * @return availableCopies
     */
    public int getAvailableCopies() {
        return availableCopies;
    }


    /**
     * Sets the availableCopies value for this Movie.
     * 
     * @param availableCopies
     */
    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }


    /**
     * Gets the movieBanner value for this Movie.
     * 
     * @return movieBanner
     */
    public java.lang.String getMovieBanner() {
        return movieBanner;
    }


    /**
     * Sets the movieBanner value for this Movie.
     * 
     * @param movieBanner
     */
    public void setMovieBanner(java.lang.String movieBanner) {
        this.movieBanner = movieBanner;
    }


    /**
     * Gets the movieCategory value for this Movie.
     * 
     * @return movieCategory
     */
    public java.lang.String getMovieCategory() {
        return movieCategory;
    }


    /**
     * Sets the movieCategory value for this Movie.
     * 
     * @param movieCategory
     */
    public void setMovieCategory(java.lang.String movieCategory) {
        this.movieCategory = movieCategory;
    }


    /**
     * Gets the movieId value for this Movie.
     * 
     * @return movieId
     */
    public int getMovieId() {
        return movieId;
    }


    /**
     * Sets the movieId value for this Movie.
     * 
     * @param movieId
     */
    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }


    /**
     * Gets the movieName value for this Movie.
     * 
     * @return movieName
     */
    public java.lang.String getMovieName() {
        return movieName;
    }


    /**
     * Sets the movieName value for this Movie.
     * 
     * @param movieName
     */
    public void setMovieName(java.lang.String movieName) {
        this.movieName = movieName;
    }


    /**
     * Gets the movieReleaseDate value for this Movie.
     * 
     * @return movieReleaseDate
     */
    public java.util.Calendar getMovieReleaseDate() {
        return movieReleaseDate;
    }


    /**
     * Sets the movieReleaseDate value for this Movie.
     * 
     * @param movieReleaseDate
     */
    public void setMovieReleaseDate(java.util.Calendar movieReleaseDate) {
        this.movieReleaseDate = movieReleaseDate;
    }


    /**
     * Gets the movieRent value for this Movie.
     * 
     * @return movieRent
     */
    public float getMovieRent() {
        return movieRent;
    }


    /**
     * Sets the movieRent value for this Movie.
     * 
     * @param movieRent
     */
    public void setMovieRent(float movieRent) {
        this.movieRent = movieRent;
    }


    /**
     * Gets the users value for this Movie.
     * 
     * @return users
     */
    public entity.Person[] getUsers() {
        return users;
    }


    /**
     * Sets the users value for this Movie.
     * 
     * @param users
     */
    public void setUsers(entity.Person[] users) {
        this.users = users;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Movie)) return false;
        Movie other = (Movie) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.availableCopies == other.getAvailableCopies() &&
            ((this.movieBanner==null && other.getMovieBanner()==null) || 
             (this.movieBanner!=null &&
              this.movieBanner.equals(other.getMovieBanner()))) &&
            ((this.movieCategory==null && other.getMovieCategory()==null) || 
             (this.movieCategory!=null &&
              this.movieCategory.equals(other.getMovieCategory()))) &&
            this.movieId == other.getMovieId() &&
            ((this.movieName==null && other.getMovieName()==null) || 
             (this.movieName!=null &&
              this.movieName.equals(other.getMovieName()))) &&
            ((this.movieReleaseDate==null && other.getMovieReleaseDate()==null) || 
             (this.movieReleaseDate!=null &&
              this.movieReleaseDate.equals(other.getMovieReleaseDate()))) &&
            this.movieRent == other.getMovieRent() &&
            ((this.users==null && other.getUsers()==null) || 
             (this.users!=null &&
              java.util.Arrays.equals(this.users, other.getUsers())));
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
        _hashCode += getAvailableCopies();
        if (getMovieBanner() != null) {
            _hashCode += getMovieBanner().hashCode();
        }
        if (getMovieCategory() != null) {
            _hashCode += getMovieCategory().hashCode();
        }
        _hashCode += getMovieId();
        if (getMovieName() != null) {
            _hashCode += getMovieName().hashCode();
        }
        if (getMovieReleaseDate() != null) {
            _hashCode += getMovieReleaseDate().hashCode();
        }
        _hashCode += new Float(getMovieRent()).hashCode();
        if (getUsers() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUsers());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUsers(), i);
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
        new org.apache.axis.description.TypeDesc(Movie.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://entity", "Movie"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("availableCopies");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "availableCopies"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("movieBanner");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "movieBanner"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("movieCategory");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "movieCategory"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("movieReleaseDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "movieReleaseDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("movieRent");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "movieRent"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("users");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "users"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://entity", "Person"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://VideoLibrary", "item"));
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
