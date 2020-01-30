package hotelSys.bean;

public class User implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
    private int id;
    private String loginName;
    private String password;
    private String email;
    private String phone;
    private java.util.Date createDate;
    private int disabled;//0：新建   1：已激活

    /** setter and getter method */
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setLoginName(String loginName){
        this.loginName = loginName;
    }
    public String getLoginName(){
        return this.loginName;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return this.password;
    }

    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return this.email;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
    public String getPhone(){
        return this.phone;
    }

    public void setCreateDate(java.util.Date createDate){
        this.createDate = createDate;
    }
    public java.util.Date getCreateDate(){
        return this.createDate;
    }
    public void setDisabled(int disabled){
        this.disabled = disabled;
    }
    public int getDisabled(){
        return this.disabled;
    }
    @Override
    public String toString() {
        return "User [id=" + id + ", loginName=" + loginName + ", password=" + password + ", email=" + email
                + ", phone=" + phone + ", createDate=" + createDate + ", disabled=" + disabled + "]";
    }

}
