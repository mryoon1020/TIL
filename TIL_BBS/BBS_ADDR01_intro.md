# 게시판에 주소록 항목 추가하기

## 순서

1. AddrDTO.java, AddrDAO.java 생성 
2. create: -> createForm.jsp -> dao -> createproc
3. list : dao 만들기 -> list dao(paging, 검색: name, handphone, address, list 자체에 삭제기능 추가)
4. read: dao -> read.jsp(조회수X)
5. update: updateform.jsp -> dao -> updateproc.jsp(비밀번호 체크기능 X)
5. delete: dao -> deleteProc.jsp(비밀번호 체크X)



```
-------------------------------------------------------------------------------
AddrDTO.java
-------------------------------------------------------------------------------
public class AddrDTO {

	private int     addressnum;
	private String  name;
	private String  handphone;
	private String  zipcode;
	private String  address;
	private String  address2;
	
	public AddrDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AddrDTO(int addressnum, String name, String handphone, String zipcode, String address, String address2) {
		super();
		this.addressnum = addressnum;
		this.name = name;
		this.handphone = handphone;
		this.zipcode = zipcode;
		this.address = address;
		this.address2 = address2;
	}

	@Override
	public String toString() {
		return "AddrDTO [addressnum=" + addressnum + ", name=" + name + ", handphone=" + handphone + ", zipcode="
				+ zipcode + ", address=" + address + ", address2=" + address2 + "]";
	}

	public int getAddressnum() {
		return addressnum;
	}

	public void setAddressnum(int addressnum) {
		this.addressnum = addressnum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHandphone() {
		return handphone;
	}

	public void setHandphone(String handphone) {
		this.handphone = handphone;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}
		
}
```

```
public class AddrDAO {
		
}
```

