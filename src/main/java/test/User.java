package test;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = -5527566248002296042L;

    @Id
    @Column(name = "Id")
    @GeneratedValue
    private Integer id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Age")
    private String Age;

    @Column(name = "isAdmin")
    private boolean isAdmin;

    @Column(name = "createdDate")
    private Timestamp createDate;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return Age;
    }

    public boolean getisAdmin() {
        return isAdmin;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String Age) {
        this.Age = Age;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
}
