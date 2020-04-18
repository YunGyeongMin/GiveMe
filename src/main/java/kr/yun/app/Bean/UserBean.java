package kr.yun.app.Bean;


public class UserBean {
	
	int no;
	String id;
	String nick_name;
	String profile_img;
	String name;
	String gender;
	String email;
	String address1;
	String address2;
	String address3;
	String birth;
	String tel;
	
	
	public int getNo() {
		return no;
	}


	public String getId() {
		return id;
	}


	public String getNick_name() {
		return nick_name;
	}


	public String getProfile_img() {
		return profile_img;
	}


	public String getName() {
		return name;
	}


	public String getGender() {
		return gender;
	}


	public String getEmail() {
		return email;
	}


	public String getAddress1() {
		return address1;
	}


	public String getAddress2() {
		return address2;
	}


	public String getAddress3() {
		return address3;
	}


	public String getBirth() {
		return birth;
	}


	public String getTel() {
		return tel;
	}

	
	public void setNo(int no) {
		this.no = no;
	}


	public void setId(String id) {
		this.id = id;
	}


	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}


	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setAddress1(String address1) {
		this.address1 = address1;
	}


	public void setAddress2(String address2) {
		this.address2 = address2;
	}


	public void setAddress3(String address3) {
		this.address3 = address3;
	}


	public void setBirth(String birth) {
		this.birth = birth;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	@Override
	public String toString() {
		return "UserBean [no=" + no + ", id=" + id + ", nick_name=" + nick_name + ", profile_img=" + profile_img
				+ ", name=" + name + ", gender=" + gender + ", email=" + email + ", address1=" + address1
				+ ", address2=" + address2 + ", address3=" + address3 + ", birth=" + birth + ", tel=" + tel + "]";
	}
	
	
}
