package gash.jpa.entities;

import java.io.Serializable;
import javax.persistence.*;
import static javax.persistence.GenerationType.AUTO;

/**
 * Entity implementation class for Entity: UserImage
 *
 */
@Entity
@Table(name = "ImageData")
@NamedQueries({
	@NamedQuery(name = "getImage", query = "SELECT i FROM ImageData i WHERE i.iid = :iid AND i.uid = :uid"),
	@NamedQuery(name = "getImages", query = "SELECT i FROM ImageData i WHERE i.uid = :uid"),
	@NamedQuery(name = "removeImage", query = "DELETE FROM ImageData i WHERE i.iid = :id AND i.uid = :uid")
})
public class ImageData implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="imagedata_id_seq")
    @SequenceGenerator(name="imagedata_id_seq", sequenceName="imagedata_id_seq", allocationSize=1)
	private long iid;
	private String uid;
	private byte[] data;
	private static final long serialVersionUID = 1L;

	public ImageData() {
		super();
	}   
	
	public long getIid() {
		return this.iid;
	}

	public void setIid(long iid) {
		this.iid = iid;
	}   
	public String getUid() {
		return this.uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}   
	public byte[] getData() {
		return this.data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
   
}
