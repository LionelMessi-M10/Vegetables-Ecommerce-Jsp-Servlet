package entity;

public class Category {

	private Long id;
	private String categoryName;
	private Integer status;

	public Category(Long id, String categoryName, Integer status) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
